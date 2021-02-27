package org.mediabump.db.mysql.repositories;

import org.mediabump.auth.domain.models.User;
import org.mediabump.db.mysql.connector.DbConnection;
import org.mediabump.usecases.repository.NotFoundException;
import org.mediabump.usecases.repository.RepositoryException;
import org.mediabump.usecases.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl() {
        this.connection = DbConnection.getConnection();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public User delete(User user) {
        return null;
    }

    @Override
    public User delete(String id) {
        return null;
    }

    @Override
    public User get(String id) {
        String sql = "SELECT * FROM USERS JOIN PHONE P on USERS.PHONE_ID = P.ID WHERE USERS.id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            return new User(rs);
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }

    }

    @Override
    public User get(String email, boolean withSession) {
        String sql = "SELECT * FROM USERS " +
                "JOIN PHONE P on USERS.PHONE_ID = P.ID " +
                "WHERE USERS.EMAIL = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new User(rs);
            } else {
                throw new NotFoundException("User not found");
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
    }

    @Override
    public List<User> all() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM USERS JOIN PHONE P on USERS.PHONE_ID = P.ID";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                users.add(new User(rs));
            }
        } catch (SQLException e) {
            throw new RepositoryException(e.getMessage());
        }
        return users;
    }
}

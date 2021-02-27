package org.mediabump.db.mysql.repositories;

import com.sun.jdi.request.ClassPrepareRequest;
import org.mediabump.auth.domain.models.Session;
import org.mediabump.auth.domain.models.User;
import org.mediabump.db.mysql.connector.DbConnection;
import org.mediabump.usecases.repository.NotFoundException;
import org.mediabump.usecases.repository.RepositoryException;
import org.mediabump.usecases.repository.SessionRepository;

import java.sql.*;
import java.util.function.Predicate;

public class SessionRepositoryImpl implements SessionRepository {

    private final Connection connection;

    public SessionRepositoryImpl() {
        this.connection = DbConnection.getConnection();
    }

    @Override
    public void clear(User user) {

    }

    @Override
    public Session save(Session session) {
        try {
            String query = "INSERT INTO SESSION(`KEY`, `USER_ID`, `EXPIRE_DATE`) VALUES(?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, session.getKey());
            stmt.setString(2, session.getUser().getId());
            stmt.setTimestamp(3, Timestamp.valueOf(session.getExpireDate()));
            stmt.executeUpdate();
            return session;
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public Session get(String key) {
        try {
            String query = "SELECT * FROM SESSION S " +
                    "LEFT JOIN USERS U ON S.USER_ID = U.ID " +
                    "LEFT JOIN PHONE P on U.PHONE_ID = P.ID " +
                    "WHERE S.KEY = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, key);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Session(rs);
            } else {
                throw new NotFoundException();
            }
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }

    @Override
    public void delete(String key) {
        try {
            String query = "DELETE FROM SESSION WHERE `KEY` = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, key);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RepositoryException();
        }
    }
}

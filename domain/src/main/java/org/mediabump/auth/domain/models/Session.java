package org.mediabump.auth.domain.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Session {
    private String key;
    private User user;
    private LocalDateTime expireDate;

    public Session(String key, User user, LocalDateTime expireDate) {
        this.key = key;
        this.user = user;
        this.expireDate = expireDate;
    }

    public Session(ResultSet rs) throws SQLException {
        this.key = rs.getString("S.KEY");
        this.user = User.from(rs);
        this.expireDate = rs.getTimestamp("S.EXPIRE_DATE").toLocalDateTime();
    }

    public String getKey() {
        return key;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(getExpireDate());
    }

    public static Session from(ResultSet resultSet) {
        try {
            return new Session(resultSet);
        } catch (SQLException e) {
            return null;
        }
    }
}

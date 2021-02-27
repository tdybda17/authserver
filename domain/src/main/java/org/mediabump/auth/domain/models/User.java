package org.mediabump.auth.domain.models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class User implements Serializable {
    private final String id;
    private final String username;
    private final String email;
    private final Phone phone;
    private final String firstName;
    private final String lastName;
    private final Password password;
    private final boolean admin;
    private final Set<Client> clients;
    private final LocalDateTime joinedDate;
    private final LocalDateTime lastModified;

    public User(
            String id,
            String username,
            String email,
            Phone phone,
            String firstName,
            String lastName,
            Password password,
            boolean admin,
            Set<Client> clients,
            LocalDateTime joinedDate,
            LocalDateTime lastModified) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.admin = admin;
        this.clients = clients;
        this.joinedDate = joinedDate;
        this.lastModified = lastModified;
    }

    public User(ResultSet resultSet) throws SQLException {
        this.id = resultSet.getString("ID");
        this.username = resultSet.getString("USERNAME");
        this.email = resultSet.getString("EMAIL");
        this.phone = new Phone(resultSet);
        this.firstName = resultSet.getString("FIRST_NAME");
        this.lastName = resultSet.getString("LAST_NAME");
        this.password = new Password(resultSet.getString("PASSWORD"));
        this.admin = resultSet.getBoolean("ADMIN");
        this.joinedDate = resultSet.getTimestamp("JOINED_DATE").toLocalDateTime();
        this.lastModified = resultSet.getTimestamp("LAST_MODIFIED").toLocalDateTime();
        this.clients = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public Phone getPhone() {
        return phone;
    }

    public Password getPassword() {
        return password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public LocalDateTime getJoinedDate() {
        return joinedDate;
    }

    public LocalDateTime getLastModified() {
        return lastModified;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public static class UserBuilder {
        private String id;
        private String username;
        private String email;
        private Phone phone;
        private String firstName;
        private String lastName;
        private Password password;
        private boolean admin;
        private Set<Client> clients;
        private Session session;
        private LocalDateTime joinedDate;
        private LocalDateTime lastModified;

        public UserBuilder() {
        }

        public UserBuilder(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.firstName = user.getFirstName();
            this.lastName = user.getLastName();
            this.password = user.getPassword();
            this.admin = user.isAdmin();
            this.clients = user.getClients();
            this.joinedDate = user.getJoinedDate();
            this.lastModified = user.getLastModified();
        }

        public UserBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public UserBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public UserBuilder setEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder setPhone(Phone phone) {
            this.phone = phone;
            return this;
        }

        public UserBuilder setPassword(Password password) {
            this.password = password;
            return this;
        }

        public UserBuilder setAdmin(boolean admin) {
            this.admin = admin;
            return this;
        }

        public UserBuilder setClients(Set<Client> clients) {
            this.clients = clients;
            return this;
        }

        public UserBuilder setSession(Session session) {
            this.session = session;
            return this;
        }

        public UserBuilder setJoinedDate(LocalDateTime joinedDate) {
            this.joinedDate = joinedDate;
            return this;
        }

        public UserBuilder setLastModified(LocalDateTime lastModified) {
            this.lastModified = lastModified;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public User build() {
            return new User(
                    id,
                    username,
                    email,
                    phone,
                    firstName,
                    lastName,
                    password,
                    admin,
                    clients,
                    joinedDate,
                    lastModified
            );
        }
    }

    public static User from(ResultSet rs) {
        try {
            return new User(rs);
        } catch (SQLException e) {
            return null;
        }
    }

}

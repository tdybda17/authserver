package org.mediabump.auth.domain.models;

import java.util.Set;

public class User {
    private final String id;
    private final String username;
    private final String email;
    private final Phone phone;
    private final Password password;
    private final boolean admin;
    private final Set<Client> clients;
    private Session session;

    public User(
            String id,
            String username,
            String email,
            Phone phone,
            Password password,
            boolean admin,
            Set<Client> clients,
            Session session) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.admin = admin;
        this.clients = clients;
        this.session = session;
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

    public Session getSession() {
        return session;
    }

    public static class UserBuilder {
        private String id;
        private String username;
        private String email;
        private Phone phone;
        private Password password;
        private boolean admin;
        private Set<Client> clients;
        private Session session;

        public UserBuilder(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.phone = user.getPhone();
            this.password = user.getPassword();
            this.admin = user.isAdmin();
            this.clients = user.getClients();
            this.session = user.getSession();
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

        public User build() {
            return new User(
                    id,
                    username,
                    email,
                    phone,
                    password,
                    admin,
                    clients,
                    session
            );
        }
    }
}

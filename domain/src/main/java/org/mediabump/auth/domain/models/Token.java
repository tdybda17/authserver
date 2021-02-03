package org.mediabump.auth.domain.models;

import java.time.LocalDateTime;

public class Token {
    private String id;
    private String refreshToken;
    private String accessToken;
    private LocalDateTime expireDate;
    private User user;

    public Token(
            String id,
            String refreshToken,
            String accessToken,
            LocalDateTime expireDate,
            User user) {
        this.id = id;
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.expireDate = expireDate;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public LocalDateTime getExpireDate() {
        return expireDate;
    }

    public User getUser() {
        return user;
    }

    public static class TokenBuilder {
        private String id;
        private String refreshToken;
        private String accessToken;
        private LocalDateTime expireDate;
        private User user;

        public TokenBuilder(Token token) {
            this.id = token.getId();
            this.refreshToken = token.getRefreshToken();
            this.accessToken = token.getAccessToken();
            this.expireDate = token.getExpireDate();
            this.user = token.getUser();
        }

        public TokenBuilder setId(String id) {
            this.id = id;
            return this;
        }

        public TokenBuilder setRefreshToken(String refreshToken) {
            this.refreshToken = refreshToken;
            return this;
        }

        public TokenBuilder setAccessToken(String accessToken) {
            this.accessToken = accessToken;
            return this;
        }

        public TokenBuilder setExpireDate(LocalDateTime expireDate) {
            this.expireDate = expireDate;
            return this;
        }

        public TokenBuilder setUser(User user) {
            this.user = user;
            return this;
        }

        public Token build() {
            return new Token(
                    this.id,
                    this.refreshToken,
                    this.accessToken,
                    this.expireDate,
                    this.user
            );
        }
    }
}

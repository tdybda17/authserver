package org.mediabump.auth.domain.models;

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
}

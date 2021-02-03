package org.mediabump.auth.domain.models;

import java.util.Set;

public class User {
    private String id;
    private String username;
    private String email;
    private Phone phone;
    private String password;
    private boolean admin;
    private Set<Client> clients;
}

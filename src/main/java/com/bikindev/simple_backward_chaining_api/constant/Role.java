package com.bikindev.simple_backward_chaining_api.constant;

public enum Role {
    ROLE_SUPER_ADMIN("Super Admin"),
    ROLE_ADMIN("Admin"),
    ROLE_USER("User");

    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

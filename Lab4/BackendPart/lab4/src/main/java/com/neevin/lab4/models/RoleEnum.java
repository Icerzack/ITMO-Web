package com.neevin.lab4.models;

public enum RoleEnum {
    USER("user_role"),
    ADMINISTRATOR("administrator_role");

    private final String name;

    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}

package ru.kpfu.itis.kashapova.models;

public enum Role {
    USER("USER"), ADMIN("ADMIN");
    private final String description;

    Role(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

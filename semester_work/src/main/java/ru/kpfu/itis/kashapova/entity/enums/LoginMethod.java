package ru.kpfu.itis.kashapova.entity.enums;

public enum LoginMethod {
    OAUTH("OAUTH"), REGISTRATION("REGISTRATION");
    private final String description;

    LoginMethod(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

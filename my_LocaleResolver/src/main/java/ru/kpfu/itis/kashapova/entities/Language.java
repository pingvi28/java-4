package ru.kpfu.itis.kashapova.entities;

public enum Language {
    RU("RU"), EN("En");
    private final String description;

    Language(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}

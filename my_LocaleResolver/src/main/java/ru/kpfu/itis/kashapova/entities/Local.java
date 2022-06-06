package ru.kpfu.itis.kashapova.entities;

import javax.persistence.*;
import java.util.Objects;

public class Local {
    private Integer id;
    @Enumerated(value = EnumType.STRING)
    private Language lang;
    private String password;
    private String submit;
    private String title;
    private String userName;
    private String input;

    public Local( Language lang, String password, String submit, String title, String userName, String input) {
        this.lang = lang;
        this.password = password;
        this.submit = submit;
        this.title = title;
        this.userName = userName;
        this.input = input;
    }

    @Override
    public String toString() {
        return "Local{" +
                "id=" + id +
                ", lang=" + lang +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Local local = (Local) o;
        return Objects.equals(id, local.id) && lang == local.lang;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lang);
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Language getLang() {
        return lang;
    }
    public void setLang(Language lang) {
        this.lang = lang;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getSubmit() {
        return submit;
    }
    public void setSubmit(String submit) {
        this.submit = submit;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getInput() {
        return input;
    }
    public void setInput(String input) {
        this.input = input;
    }
}

package ru.kpfu.itis.kashapova.models;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

/**
 * @Entity - данный клас сохраняется в бд,  те взаимосвязь
 */

@DynamicUpdate
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Column(unique = true)
    private String slug;

    @Column
    @NotBlank
    private String title;

    @NotEmpty
    private String name;

    @Column(length = 10000000)
    private String content;

    @Column
    private String creatingTime;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Override
    public String toString() {
        return "Article{" +
                "slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id) && Objects.equals(name, article.name) && Objects.equals(content, article.content) && role == article.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, content, role);
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }
    public void setContent(String text) {
        this.content = text;
    }

    public String getCreatingTime() {
        return creatingTime;
    }
    public void setCreatingTime(String date) {
        this.creatingTime = date;
    }

    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }

    public String getSlug() {
        return slug;
    }
    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
}

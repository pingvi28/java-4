package ru.kpfu.itis.kashapova.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.kpfu.itis.kashapova.models.Article;
import ru.kpfu.itis.kashapova.models.Role;

import java.util.List;
import java.util.Optional;
/**
 * Расширяем CrudRepository<Article, Integer>:
 * Объекты сохраняемые в бд и идентификактаор поля
 */

@Repository
public interface ArticleRepository extends CrudRepository<Article, Integer> {
    List<Article> findAllByRole(Role role);

    Optional<Article> findBySlug(String slug);

    Long countAllBySlug(String slug);

    Optional<Article> findTopByOrderByIdDesc();
}

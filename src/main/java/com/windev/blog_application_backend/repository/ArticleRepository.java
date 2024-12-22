package com.windev.blog_application_backend.repository;

import com.windev.blog_application_backend.model.Article;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ArticleRepository extends MongoRepository<Article, String> {
    List<Article> findByTitleContainingIgnoreCase(String title);
}

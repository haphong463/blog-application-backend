package com.windev.blog_application_backend.repository;

import com.windev.blog_application_backend.model.Article;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface ArticleRepository extends MongoRepository<Article, String> {
    @Query("{ $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'tags': { $regex: ?0, $options: 'i' } } ] }")
    Page<Article> findByKeyword(String keyword, Pageable pageable);}

package com.windev.blog_application_backend.service;

import com.windev.blog_application_backend.model.Article;
import com.windev.blog_application_backend.payload.request.ArticleRequest;
import org.springframework.data.domain.Page;

public interface ArticleService {
    Page<Article> articlePage(int pageSize, int pageNumber);
    Article createArticle(ArticleRequest request);
    void deleteArticle(String id);
    Article updateArticle(String id, ArticleRequest request);
}

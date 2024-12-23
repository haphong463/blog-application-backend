package com.windev.blog_application_backend.service;

import com.windev.blog_application_backend.model.Article;
import com.windev.blog_application_backend.payload.request.ArticleRequest;
import org.springframework.data.domain.Page;

public interface IArticleService {
    Page<Article> articlePage(int pageSize, int pageNumber, String title);
    Article createArticle(ArticleRequest request);
    void deleteArticle(String id);
    Article updateArticle(String id, ArticleRequest request);
}

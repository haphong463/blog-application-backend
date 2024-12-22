/**
 * @project blog-application-backend
 * @author Phong Ha on 17/12/2024
 */

package com.windev.blog_application_backend.service.impl;

import com.windev.blog_application_backend.exception.ArticleNotFoundException;
import com.windev.blog_application_backend.mapper.ArticleMapper;
import com.windev.blog_application_backend.model.Article;
import com.windev.blog_application_backend.payload.request.ArticleRequest;
import com.windev.blog_application_backend.repository.ArticleRepository;
import com.windev.blog_application_backend.service.ArticleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public Page<Article> articlePage(int pageSize, int pageNumber) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return articleRepository.findAll(pageable);
    }

    @Override
    public Article createArticle(ArticleRequest request) {
        Article article = articleMapper.toArticle(request);
//        Article article = new Article();
        log.info("createArticle() --> ARTICLE CREATED");
        return articleRepository.save(article);
    }

    @Override
    public void deleteArticle(String id) {
        Article article = articleRepository.findById(id).orElseThrow(
                () -> new ArticleNotFoundException("Article not found")
        );

        articleRepository.delete(article);
        log.info("deleteArticle() --> ARTICLE DELETED");
    }

    @Override
    public Article updateArticle(String id, ArticleRequest request) {
        Article existingArticle = articleRepository.findById(id).orElseThrow(
                () -> new ArticleNotFoundException("Article not found")
        );

        articleMapper.updateArticleFromRequest(request, existingArticle);
        log.info("updateArticle() --> ARTICLE UPDATED");
        return articleRepository.save(existingArticle);
    }
}

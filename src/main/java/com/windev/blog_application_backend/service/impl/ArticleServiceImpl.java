/**
 * @project blog-application-backend
 * @author Phong Ha on 17/12/2024
 */

package com.windev.blog_application_backend.service.impl;

import com.windev.blog_application_backend.dto.ArticleWithUser;
import com.windev.blog_application_backend.exception.ArticleNotFoundException;
import com.windev.blog_application_backend.mapper.ArticleMapper;
import com.windev.blog_application_backend.model.Article;
import com.windev.blog_application_backend.payload.request.ArticleRequest;
import com.windev.blog_application_backend.repository.IArticleRepository;
import com.windev.blog_application_backend.security.user_details.CustomUserDetails;
import com.windev.blog_application_backend.service.IArticleService;

import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ArticleServiceImpl implements IArticleService {

    private final IArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleMapper articleMapper, IArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public Slice<ArticleWithUser> articlePage(int pageSize, int pageNumber, String title) {
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        String regex =".*" + title.replace(" ", ".*") + ".*";
        return articleRepository.findByKeyword(regex, pageable);
    }

    @Override
    public Article createArticle(ArticleRequest request) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found.");
        }

        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        Article article = articleMapper.toArticle(request);

        ObjectId objectId = new ObjectId(userDetails.getId());

        article.setUserId(objectId);
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

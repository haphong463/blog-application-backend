/**
 * @project blog-application-backend
 * @author Phong Ha on 17/12/2024
 */

package com.windev.blog_application_backend.controller;

import com.windev.blog_application_backend.dto.ArticleWithUser;
import com.windev.blog_application_backend.model.Article;
import com.windev.blog_application_backend.payload.request.ArticleRequest;
import com.windev.blog_application_backend.service.IArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    private final IArticleService articleService;

    public ArticleController(IArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<Slice<ArticleWithUser>> articlePage(@RequestParam(defaultValue = "0") int pageNumber,
                                                              @RequestParam(defaultValue = "10") int pageSize,
                                                              @RequestParam String title) {
        return new ResponseEntity<>(articleService.articlePage(pageSize, pageNumber, title), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody ArticleRequest request) {
        return new ResponseEntity<>(articleService.createArticle(request), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable String id, @RequestBody ArticleRequest request) {
        return new ResponseEntity<>(articleService.updateArticle(id, request), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        articleService.deleteArticle(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

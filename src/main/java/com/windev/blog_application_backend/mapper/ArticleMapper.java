package com.windev.blog_application_backend.mapper;

import com.windev.blog_application_backend.model.Article;
import com.windev.blog_application_backend.payload.request.ArticleRequest;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article toArticle(ArticleRequest request);
    void updateArticleFromRequest(ArticleRequest request, @MappingTarget Article existingArticle);
}

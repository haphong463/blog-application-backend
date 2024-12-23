package com.windev.blog_application_backend.repository;

import com.windev.blog_application_backend.dto.ArticleWithUser;
import com.windev.blog_application_backend.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IArticleRepository extends MongoRepository<Article, String> {
    @Aggregation(pipeline = {
            "{ $match: { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'tags': { $regex: ?0, $options: 'i' } } ] } }",
            "{ $lookup: { from: 'users', localField: 'userId', foreignField: '_id', as: 'user' } }",
            "{ $unwind: { path: '$user', preserveNullAndEmptyArrays: true } }",
            "{ $skip: ?#{#pageable.offset} }",
            "{ $limit: ?#{#pageable.pageSize} }"
    })
    Slice<ArticleWithUser> findByKeyword(String keyword, Pageable pageable);

}

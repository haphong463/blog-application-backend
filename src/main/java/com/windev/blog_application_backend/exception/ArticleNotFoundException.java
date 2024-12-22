/**
 * @project blog-application-backend
 * @author Phong Ha on 17/12/2024
 */

package com.windev.blog_application_backend.exception;

public class ArticleNotFoundException extends RuntimeException {
    public ArticleNotFoundException(String message) {
        super(message);
    }
}

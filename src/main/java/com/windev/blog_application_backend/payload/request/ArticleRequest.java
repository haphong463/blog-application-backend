/**
 * @project blog-application-backend
 * @author Phong Ha on 17/12/2024
 */

package com.windev.blog_application_backend.payload.request;

import java.util.List;
import lombok.Data;

@Data
public class ArticleRequest {
    private String title;
    private String citation;
    private String content;
    private List<String> tags;
}

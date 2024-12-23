/**
 * @project blog-application-backend
 * @author Phong Ha on 23/12/2024
 */

package com.windev.blog_application_backend.model;

import com.windev.blog_application_backend.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    private String id;
    private ERole name;
}

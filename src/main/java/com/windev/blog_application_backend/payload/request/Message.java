/**
 * @project blog-application-backend
 * @author Phong Ha on 23/12/2024
 */

package com.windev.blog_application_backend.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    private String role;
    private String content;

    // constructor, getters and setters
}
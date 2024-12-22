/**
 * @project identity-service
 * @author Phong Ha on 29/11/2024
 */

package com.windev.blog_application_backend.payload.request;

import lombok.Data;

@Data
public class SignInRequest {
    private String username;
    private String password;
}

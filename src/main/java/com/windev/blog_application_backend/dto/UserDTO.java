/**
 * @project blog-application-backend
 * @author Phong Ha on 24/12/2024
 */

package com.windev.blog_application_backend.dto;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class UserDTO {
    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private Date dob;

    private List<String> roles;
}

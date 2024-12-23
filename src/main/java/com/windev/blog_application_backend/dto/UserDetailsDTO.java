/**
 * @project blog-application-backend
 * @author Phong Ha on 24/12/2024
 */

package com.windev.blog_application_backend.dto;

import com.windev.blog_application_backend.security.user_details.CustomUserDetails;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsDTO {
    private String id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsDTO fromCustomUserDetails(CustomUserDetails userDetails) {
        return new UserDetailsDTO(
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                userDetails.getFirstName(),
                userDetails.getLastName(),
                userDetails.getAuthorities()
        );
    }
}

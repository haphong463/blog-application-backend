/**
 * @project blog-application-backend
 * @author Phong Ha on 21/12/2024
 */

package com.windev.blog_application_backend.repository;

import com.windev.blog_application_backend.model.User;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}

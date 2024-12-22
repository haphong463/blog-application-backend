/**
 * @project blog-application-backend
 * @author Phong Ha on 21/12/2024
 */

package com.windev.blog_application_backend.controller;

import com.windev.blog_application_backend.model.User;
import com.windev.blog_application_backend.payload.request.SignInRequest;
import com.windev.blog_application_backend.payload.request.SignUpRequest;
import com.windev.blog_application_backend.payload.response.ApiResponse;
import com.windev.blog_application_backend.security.user_details.CustomUserDetails;
import com.windev.blog_application_backend.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse<User>> signUp(@RequestBody SignUpRequest request) {
        return new ResponseEntity<>(new ApiResponse<>(authService.register(request), HttpStatus.CREATED.value()),
                HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody SignInRequest request) {
        return new ResponseEntity<>(new ApiResponse<>(authService.login(request), HttpStatus.OK.value()),
                HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<CustomUserDetails>> currentUser() {
        return new ResponseEntity<>(new ApiResponse<>(authService.currentUser(), HttpStatus.OK.value()), HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout() {
        String message = "Logged out.";

        authService.logout();

        return new ResponseEntity<>(new ApiResponse<>(message, HttpStatus.NO_CONTENT.value()),
                HttpStatus.NO_CONTENT);
    }
}
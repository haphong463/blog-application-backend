package com.windev.blog_application_backend.service;

import com.windev.blog_application_backend.dto.UserDetailsDTO;
import com.windev.blog_application_backend.model.User;
import com.windev.blog_application_backend.payload.request.SignInRequest;
import com.windev.blog_application_backend.payload.request.SignUpRequest;
import com.windev.blog_application_backend.security.user_details.CustomUserDetails;

public interface IAuthService {
    String login(SignInRequest request);

    User register(SignUpRequest request);

    UserDetailsDTO currentUser();

    void logout();
}

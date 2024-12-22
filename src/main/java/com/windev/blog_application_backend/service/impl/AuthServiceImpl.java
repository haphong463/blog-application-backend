/**
 * @project blog-application-backend
 * @author Phong Ha on 21/12/2024
 */

package com.windev.blog_application_backend.service.impl;

import com.windev.blog_application_backend.constant.AuthConstant;
import com.windev.blog_application_backend.model.User;
import com.windev.blog_application_backend.payload.request.SignInRequest;
import com.windev.blog_application_backend.payload.request.SignUpRequest;
import com.windev.blog_application_backend.repository.UserRepository;
import com.windev.blog_application_backend.security.jwt.JwtTokenProvider;
import com.windev.blog_application_backend.security.user_details.CustomUserDetails;
import com.windev.blog_application_backend.service.AuthService;
import com.windev.blog_application_backend.service.cache.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final RedisService redisService;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, AuthenticationManager authenticationManager, RedisService redisService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
        this.redisService = redisService;
    }


    @Override
    public String login(SignInRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );


        String token = jwtTokenProvider.generateToken(authentication);
        String redisKey = AuthConstant.TOKEN_PREFIX + request.getUsername();
        redisService.save(redisKey, token, AuthConstant.TOKEN_EXPIRATION_TIME, AuthConstant.TIME_UNIT_TOKEN);

        log.info("User {} logged in successfully. Token stored in Redis.", request.getUsername());
        return token;
    }

    @Override
    public User register(SignUpRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());

        User savedUser = userRepository.save(user);
        log.info("User {} registered successfully.", savedUser.getUsername());

        return savedUser;
    }

    @Override
    public CustomUserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found.");
        }

        return (CustomUserDetails) authentication.getPrincipal();
    }

    @Override
    public void logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("No authenticated user found.");
        }

        String username = authentication.getName();

        String redisKey = AuthConstant.TOKEN_PREFIX + username;
        redisService.delete(redisKey);
        log.info("User {} logged out and token removed from Redis.", username);
    }
}
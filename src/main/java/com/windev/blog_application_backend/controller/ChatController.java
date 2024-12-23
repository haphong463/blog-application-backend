/**
 * @project blog-application-backend
 * @author Phong Ha on 23/12/2024
 */

package com.windev.blog_application_backend.controller;

import com.windev.blog_application_backend.payload.request.ChatRequest;
import com.windev.blog_application_backend.payload.response.AIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/ai")
public class ChatController {

    @Qualifier("openaiRestTemplate")
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @PostMapping("/chat")
    public String chat(@RequestBody String prompt) {
        // create a request
        ChatRequest request = new ChatRequest(model, prompt);

        // call the API
        AIResponse response = restTemplate.postForObject(apiUrl, request, AIResponse.class);

        if (response.getChoices() == null || response.getChoices().isEmpty()) {
            return "No response";
        }

        // return the first response
        return response.getChoices().get(0).getMessage().getContent();
    }
}

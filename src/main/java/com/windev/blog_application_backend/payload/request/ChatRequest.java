/**
 * @project blog-application-backend
 * @author Phong Ha on 23/12/2024
 */

package com.windev.blog_application_backend.payload.request;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRequest {
    private String model;
    private List<Message> messages;
    private int n;
    private double temperature;

    public ChatRequest(String model, String prompt) {
        this.model = model;
        this.messages = new ArrayList<>();
        this.messages.add(new Message("user", prompt));
        this.n = 1;
        this.temperature = 0.7;
    }
}


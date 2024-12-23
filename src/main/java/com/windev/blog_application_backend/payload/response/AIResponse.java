/**
 * @project blog-application-backend
 * @author Phong Ha on 23/12/2024
 */

package com.windev.blog_application_backend.payload.response;

import com.windev.blog_application_backend.payload.request.Message;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AIResponse {
    private List<Choice> choices;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Choice {
        private int index;
        private Message message;
    }
}

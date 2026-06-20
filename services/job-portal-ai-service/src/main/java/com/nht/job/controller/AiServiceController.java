package com.nht.job;

import com.nht.job.client.GeminiClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ai")
public class AiServiceController {

    private final GeminiClient geminiClient;

    @GetMapping("/{prompt}")
    public ResponseEntity<String> testAi(@PathVariable String prompt) throws Exception {

        String response = geminiClient.generateText(prompt);
        return ResponseEntity.ok(response);

    }

}

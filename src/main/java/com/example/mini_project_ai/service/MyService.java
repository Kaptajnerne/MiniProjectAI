package com.example.mini_project_ai.service;

import com.example.mini_project_ai.dto.PCRequest;
import com.example.mini_project_ai.dto.PCResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MyService {
    private final WebClient webClient;

    @Value("${OPENAI_API_KEY}")
    private String apiKey;

    public MyService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://api.openai.com/v1/engines/davinci-codex/completions")
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<String> getPCRecommendations(String budget, String usage) {
        String prompt = "Recommend PC components for a budget of $" + budget + " for " + usage + " usage.";

        PCRequest request = new PCRequest(prompt);

        return webClient.post()
                .body(Mono.just(request), PCRequest.class)
                .retrieve()
                .bodyToMono(PCResponse.class)
                .map(PCResponse::getText);
    }
}

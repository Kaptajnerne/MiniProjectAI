package com.example.mini_project_ai.service;


import com.example.mini_project_ai.entities.ChatResponse;
import com.example.mini_project_ai.entities.ComputerRequirements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class ChatService {


    private final WebClient webClient;

    @Autowired
    public ChatService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    public Mono<List<ChatResponse>> fetchChatGPT(ComputerRequirements computerRequirements) {
        System.out.println(computerRequirements.toString());
        return webClient
                .post()
                .uri("/chat")
                .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
                .bodyValue(computerRequirements) // Send chatRequest as the request body.
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<ChatResponse>>() {});
    }
}
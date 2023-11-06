package com.example.mini_project_ai.service;

import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

public class MyService {

    private String api_key = System.getenv("API_KEY");

    Mono<String> callGpt3(String input) {
        WebClient client = WebClient.create();

        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("model", "gpt-3.5-turbo");
        formData.add("messages[0].role", "system");
        formData.add("messages[0].content", "You are a helpful assistant.");
        formData.add("messages[1].role", "user");
        formData.add("messages[1].content", input);

        Mono<String> response = client.post()
                .uri("https://api.openai.com/v1/engines/davinci/completions")
                .header("Authorization", "Bearer" + api_key)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class);
        return response;
    }
}

package com.example.mini_project_ai.service;

import com.example.mini_project_ai.dto.PCRequest;
import com.example.mini_project_ai.dto.PCResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class MyService {
    private final WebClient webClient;

    @Value("${chat.model}")
    private String chatModel;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    public MyService() {
        System.out.println("API Key: " + apiKey);
        System.out.println("API URL: " + apiUrl);

        if (apiUrl == null) {
            throw new IllegalArgumentException("apiUrl is null");
        }

        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader("Authorization", "Bearer " + apiKey)
                .build();
    }

    public Mono<PCResponse> getPCRecommendations(String budget, String usage) {
        String prompt = "Recommend PC components for a budget of $" + budget + " for " + usage + " usage. " +
                "I need a CPU, GPU, RAM, Storage, Motherboard, Case, Power Supply Unit, Cooling, and Operating System. " +
                "Give the exact name of the component back as mentioned above. " +
                "Formatting \"Component: model of component - price in dollars\". " +
                "Always end with a dot.";
        PCRequest request = new PCRequest(prompt);
        return webClient.post()
                .body(Mono.just(request), PCRequest.class)
                .retrieve()
                .bodyToMono(PCResponse.class)
                .map(this::mapResponseToPCResponse);
    }

    private PCResponse mapResponseToPCResponse(PCResponse response) {
        PCResponse pcResponse = new PCResponse();
        pcResponse.setText(response.getText());
        pcResponse.setCpu(extractRecommendation("CPU ", response.getText()));
        pcResponse.setGpu(extractRecommendation("GPU", response.getText()));
        pcResponse.setRam(extractRecommendation("RAM", response.getText()));
        pcResponse.setStorage(extractRecommendation("Storage", response.getText()));
        pcResponse.setMotherboard(extractRecommendation("Motherboard", response.getText()));
        pcResponse.setComputerCase(extractRecommendation("Case", response.getText()));
        pcResponse.setPowerSupplyUnit(extractRecommendation("Power Supply Unit", response.getText()));
        pcResponse.setCooling(extractRecommendation("Cooling", response.getText()));
        pcResponse.setOperatingSystem(extractRecommendation("Operating System", response.getText()));
        return pcResponse;
    }

    //Extracts the response text from each component. Seems straight forward but is kinda complicated
    private String extractRecommendation(String component, String responseText) {
        //We define a regular expression pattern based on the component parameter we receive.
        //Component is the name of the component (example: CPU, GPU etc.)
        //After the name of the component a colon is made before the recommendation (": ")
        //([^.]+) does it thing, I don't totally get it. But it gets the text that follows the component name to the nearest dot.
        String pattern = component + ": ([^.]+)";
        //We compile the previous Regular expression pattern into an actual pattern object
        Pattern regex = Pattern.compile(pattern);
        //We create a match object and apply the regular expression pattern to the responseText. It's used to find matches based on the pattern in the responseText.
        Matcher matcher = regex.matcher(responseText);

        // If a match is found, get the recommendation
        if (matcher.find()) {
            return matcher.group(1);
        } else {
            return "The component and description was not found";
        }
    }
}

package com.example.mini_project_ai.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ChatResponse {
    @JsonProperty("index")
    private int index;

    @JsonProperty("message")
    private ChatMessage chatMessage;

    @JsonProperty("finish_reason")
    private String finishReason;
}

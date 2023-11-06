package com.example.mini_project_ai.dto;

import lombok.Data;

@Data
public class PCRequest {
    private String prompt;

    public PCRequest(String prompt) {
        this.prompt = prompt;
    }
}

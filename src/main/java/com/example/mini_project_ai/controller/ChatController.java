package com.example.mini_project_ai.controller;


import com.example.mini_project_ai.entities.ChatResponse;
import com.example.mini_project_ai.entities.ComputerRequirements;
import com.example.mini_project_ai.service.ChatService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@CrossOrigin
@RequestMapping("/chat")
public class ChatController {

    final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping
    public Mono<List<ChatResponse>> getChatResponse(@RequestBody ComputerRequirements computerRequirements) {
        return chatService.fetchChatGPT(computerRequirements);
    }

}


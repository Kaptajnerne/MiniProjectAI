package com.example.mini_project_ai.controller;

import com.example.mini_project_ai.dto.PCResponse;
import com.example.mini_project_ai.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MyController {

    @Autowired
    MyService myService;

    @GetMapping
    public Mono<PCResponse> getRecommendations(@RequestParam("budget") String budget, @RequestParam("usage") String usage) {
        return myService.getPCRecommendations(budget, usage);
    }
}
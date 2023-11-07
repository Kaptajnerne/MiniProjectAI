package com.example.mini_project_ai.controller;

import com.example.mini_project_ai.dto.PCResponse;
import com.example.mini_project_ai.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@CrossOrigin
@RestController
public class MyController {

    final MyService myService;

    public MyController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping(path="getRecommendation")
    public Mono<PCResponse> getRecommendations(@RequestParam("budget") String budget, @RequestParam("usage") String usage) {
        return myService.getPCRecommendations(budget, usage);
    }
}
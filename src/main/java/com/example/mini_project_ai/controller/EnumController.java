package com.example.mini_project_ai.controller;

import com.example.mini_project_ai.service.EnumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class EnumController {

    @Autowired
    private EnumService enumService;

    @GetMapping("/formfactors")
    public ResponseEntity<List<String>> getAllFormFactors(){
        List<String> formFactors = enumService.getAllFormFactors();
        return new ResponseEntity<>(formFactors, HttpStatus.OK);
    }

}
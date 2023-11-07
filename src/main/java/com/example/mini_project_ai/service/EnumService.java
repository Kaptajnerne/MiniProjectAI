package com.example.mini_project_ai.service;

import com.example.mini_project_ai.enums.FormFactor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnumService {

    public List<String> getAllFormFactors() {
        return Arrays.stream(FormFactor.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

}
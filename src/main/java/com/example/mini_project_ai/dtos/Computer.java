package com.example.mini_project_ai.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Computer {
    private String cpu;
    private String gpu;
    private String ram;
    private String storage;
    private String motherboard;
    private String computerCase;
    private String powerSupplyUnit;
}

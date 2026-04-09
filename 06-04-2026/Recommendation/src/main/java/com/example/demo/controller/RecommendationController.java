package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Catalog;
import com.example.demo.service.RecommendationService;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    @Autowired
    private RecommendationService service;

    @PostMapping
    public List<Catalog> getRecommendations(@RequestBody List<Integer> pids) {
        return service.getRecommendations(pids);
    }
}
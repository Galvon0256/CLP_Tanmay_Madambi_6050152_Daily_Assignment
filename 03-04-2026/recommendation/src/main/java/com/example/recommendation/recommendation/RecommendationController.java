package com.example.recommendation.recommendation;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {

    private final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService) {
        this.recommendationService = recommendationService;
    }

    @Operation(summary = "Get product recommendations for cart product ids")
    @PostMapping
    public List<ProductCatalog> getRecommendations(@Valid @RequestBody RecommendationRequest request) {
        return recommendationService.recommend(request.getPids());
    }
}



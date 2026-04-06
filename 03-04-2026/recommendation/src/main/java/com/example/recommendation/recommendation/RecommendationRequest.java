package com.example.recommendation.recommendation;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public class RecommendationRequest {

    @NotEmpty
    @Schema(description = "Product ids already present in cart", example = "[1, 2, 5]")
    private List<Long> pids;

    public RecommendationRequest() {
    }

    public RecommendationRequest(List<Long> pids) {
        this.pids = pids;
    }

    public List<Long> getPids() {
        return pids;
    }

    public void setPids(List<Long> pids) {
        this.pids = pids;
    }
}

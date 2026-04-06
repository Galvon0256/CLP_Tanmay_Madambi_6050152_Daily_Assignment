package com.example.cart.cart;

import java.util.List;

public class RecommendationRequest {

    private List<Long> pids;

    public RecommendationRequest() {
    }

    public RecommendationRequest(List<Long> pids) {
        this.pids = pids;
    }

    public List<Long> getPids() {
        return pids;
    }
}

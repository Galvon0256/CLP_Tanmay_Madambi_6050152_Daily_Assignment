package com.example.recommendation.recommendation;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class RecommendationService {

    private final Map<Long, ProductCatalog> productById;

    public RecommendationService() {
        List<ProductCatalog> seedCatalogs = List.of(
                new ProductCatalog(1L, "iPhone 15", "electronics", 85000, 15),
                new ProductCatalog(2L, "Galaxy S24", "electronics", 74000, 9),
                new ProductCatalog(3L, "Running Shoes", "sports", 4500, 40),
                new ProductCatalog(4L, "Football", "sports", 1100, 55),
                new ProductCatalog(5L, "Coffee Mug", "home", 400, 120)
        );
        this.productById = seedCatalogs.stream()
                .collect(Collectors.toUnmodifiableMap(ProductCatalog::getPid, Function.identity()));
    }

    public List<ProductCatalog> recommend(List<Long> pids) {
        if (pids == null || pids.isEmpty()) {
            return List.of();
        }

        Set<Long> input = Set.copyOf(pids);
        Map<String, List<ProductCatalog>> byCategory = productById.values().stream()
                .collect(Collectors.groupingBy(ProductCatalog::getPcategory, LinkedHashMap::new, Collectors.toList()));

        List<ProductCatalog> recommendations = new ArrayList<>();
        for (Long pid : pids) {
            ProductCatalog source = productById.get(pid);
            if (source == null) {
                continue;
            }

            List<ProductCatalog> candidates = byCategory.getOrDefault(source.getPcategory(), List.of());
            candidates.stream()
                    .filter(candidate -> !candidate.getPid().equals(source.getPid()))
                    .filter(candidate -> !input.contains(candidate.getPid()))
                    .findFirst()
                    .ifPresent(recommendations::add);
        }
        return recommendations.stream().distinct().toList();
    }
}




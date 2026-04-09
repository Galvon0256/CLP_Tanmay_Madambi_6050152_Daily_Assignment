package com.example.demo.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Catalog;

@Service
public class RecommendationService implements IRecommendationService{
	
	@Autowired
    RestTemplate rt;

    public List<Catalog> getRecommendations(List<Integer> pids) {

        Set<String> categories = new HashSet<>();
        Set<Integer> seen = new HashSet<>();
        List<Catalog> recommendations = new ArrayList<>();


        for (Integer pid : pids) {
            Catalog product = rt.getForObject("http://PRODUCTCATALOG/catalogs/products/" + pid, Catalog.class);
            categories.add(product.getCategory());
        }

        for (String category : categories) {
            Catalog[] categoryProducts = rt.getForObject("http://PRODUCTCATALOG/catalogs/products/category/" + category, Catalog[].class);

            for (Catalog c : categoryProducts) {
                if (pids.contains(c.getId())) continue;
                if (seen.add(c.getId())) {
                    recommendations.add(c);
                }
            }
        }

        return recommendations;
    }
}
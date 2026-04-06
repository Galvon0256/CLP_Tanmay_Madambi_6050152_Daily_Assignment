package com.example.cart.cart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Component;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_GATEWAY;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class ServiceRegistryClient {

    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;
    private final String catalogServiceName;
    private final String recommendationServiceName;
    private final String fallbackCatalogUrl;
    private final String fallbackRecommendationUrl;

    public ServiceRegistryClient(DiscoveryClient discoveryClient,
                                 RestClient.Builder restClientBuilder,
                                 @Value("${catalog.service.name:product}") String catalogServiceName,
                                 @Value("${recommendation.service.name:recommendation}") String recommendationServiceName,
                                 @Value("${catalog.service.base-url:http://localhost:8081}") String fallbackCatalogUrl,
                                 @Value("${recommendation.service.base-url:http://localhost:8083}") String fallbackRecommendationUrl) {
        this.discoveryClient = discoveryClient;
        this.restClient = restClientBuilder.build();
        this.catalogServiceName = catalogServiceName;
        this.recommendationServiceName = recommendationServiceName;
        this.fallbackCatalogUrl = fallbackCatalogUrl;
        this.fallbackRecommendationUrl = fallbackRecommendationUrl;
    }

    public ProductCatalog fetchProductCatalog(Long pid) {
        String baseUrl = resolveBaseUrl(catalogServiceName, fallbackCatalogUrl);
        try {
            ProductCatalog catalog = restClient.get()
                    .uri(baseUrl + "/catalogs/products/" + pid)
                    .retrieve()
                    .body(ProductCatalog.class);
            if (catalog == null) {
                throw new ResponseStatusException(BAD_GATEWAY, "Empty response from product catalog for pid: " + pid);
            }
            return catalog;
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (RestClientResponseException ex) {
            if (ex.getStatusCode().value() == NOT_FOUND.value()) {
                throw new ResponseStatusException(NOT_FOUND, "Product not found for pid: " + pid, ex);
            }
            throw new ResponseStatusException(BAD_GATEWAY,
                    "Product service returned status " + ex.getStatusCode().value() + " for pid: " + pid, ex);
        } catch (Exception ex) {
            throw new ResponseStatusException(BAD_GATEWAY, "Unable to fetch product catalog for pid: " + pid, ex);
        }
    }

    public List<ProductCatalog> fetchRecommendations(List<Long> pids) {
        String baseUrl = resolveBaseUrl(recommendationServiceName, fallbackRecommendationUrl);
        try {
            List<ProductCatalog> body = restClient.post()
                    .uri(baseUrl + "/recommendations")
                    .body(new RecommendationRequest(pids))
                    .retrieve()
                    .body(new ParameterizedTypeReference<List<ProductCatalog>>() {
                    });
            if (body == null) {
                return List.of();
            }
            return body;
        } catch (Exception ex) {
            throw new ResponseStatusException(BAD_GATEWAY, "Unable to fetch recommendations", ex);
        }
    }

    private String resolveBaseUrl(String serviceName, String fallbackUrl) {
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceName);
        if (!instances.isEmpty()) {
            return instances.getFirst().getUri().toString();
        }
        return fallbackUrl;
    }
}




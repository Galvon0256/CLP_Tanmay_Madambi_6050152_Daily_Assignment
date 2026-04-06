package com.example.product.catalog;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogs/products")
public class CatalogController {

    private final CatalogService catalogService;

    public CatalogController(CatalogService catalogService) {
        this.catalogService = catalogService;
    }

    @GetMapping
    public List<ProductCatalog> getAllProducts() {
        return catalogService.getAllCatalogs();
    }

    @GetMapping("/{id:\\d+}")
    public ProductCatalog getById(@PathVariable Long id) {
        return catalogService.getCatalogById(id);
    }

    @GetMapping("/{category:[a-zA-Z]+}")
    public List<ProductCatalog> getByCategory(@PathVariable String category) {
        return catalogService.getCatalogByCategory(category);
    }
}


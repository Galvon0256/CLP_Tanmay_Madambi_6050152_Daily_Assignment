package com.example.product.catalog;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class CatalogService {

    private final CatalogDataStore dataStore;

    public CatalogService(CatalogDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public List<ProductCatalog> getAllCatalogs() {
        return dataStore.findAllProducts().stream()
                .map(this::toCatalog)
                .toList();
    }

    public ProductCatalog getCatalogById(Long pid) {
        ProductDetails details = dataStore.findProductById(pid);
        if (details == null) {
            throw new ResponseStatusException(NOT_FOUND, "Product not found for id: " + pid);
        }
        return toCatalog(details);
    }

    public List<ProductCatalog> getCatalogByCategory(String category) {
        return dataStore.findAllProducts().stream()
                .filter(product -> product.getPcategory().equalsIgnoreCase(category))
                .map(this::toCatalog)
                .toList();
    }

    private ProductCatalog toCatalog(ProductDetails product) {
        PricingDetails pricing = dataStore.findPricingById(product.getPid());
        InventoryDetails inventory = dataStore.findInventoryById(product.getPid());
        if (pricing == null || inventory == null) {
            throw new ResponseStatusException(NOT_FOUND, "Pricing/Inventory unavailable for id: " + product.getPid());
        }

        return new ProductCatalog(
                product.getPid(),
                product.getPname(),
                product.getPcategory(),
                pricing.discountedPrice(),
                inventory.getNoOfItemsLeft()
        );
    }
}



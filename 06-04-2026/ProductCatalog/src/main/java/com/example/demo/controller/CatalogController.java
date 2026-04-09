package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Catalog;
import com.example.demo.service.CatalogService;

@RestController
@RequestMapping("/catalogs/products")
public class CatalogController {

    @Autowired
    private CatalogService service;

    @GetMapping
    public List<Catalog> getAllProducts() {
        return service.getWholeCatalog();
    }

    @GetMapping("/{id}")
    public Catalog getById(@PathVariable int id) {
        return service.getCatalogById(id);
    }

    @GetMapping("/category/{category}")
    public List<Catalog> getByCategory(@PathVariable String category) {
        return service.getCatalogByCategory(category);
    }
}
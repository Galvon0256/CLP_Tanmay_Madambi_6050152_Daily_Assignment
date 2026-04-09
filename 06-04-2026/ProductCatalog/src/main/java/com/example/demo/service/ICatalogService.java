package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Catalog;

public interface ICatalogService {
	public List<Catalog> getWholeCatalog();
	public Catalog getCatalogById(int id);
	public List<Catalog> getCatalogByCategory(String category);
}

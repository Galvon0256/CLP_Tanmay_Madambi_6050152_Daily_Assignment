package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Catalog;

public interface IRecommendationService {
	public List<Catalog> getRecommendations(List<Integer> pids);

}

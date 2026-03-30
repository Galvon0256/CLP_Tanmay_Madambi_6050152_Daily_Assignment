package com.cg.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.entities.Movie;

import java.util.List;

public interface IMovie extends JpaRepository<Movie, Long> {
    List<Movie> findByGenre(String genre);
}
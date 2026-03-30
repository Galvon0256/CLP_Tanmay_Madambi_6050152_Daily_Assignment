package com.cg.service;

import com.cg.entities.Movie;
import java.util.List;
import java.util.Optional;

public interface IMovieService {
    Movie saveMovie(Movie movie);
    List<Movie> getAllMovies();
    List<Movie> getMoviesByGenre(String genre);
    Optional<Movie> getMovieById(Long id);
    void deleteMovieById(Long id);
    Movie updateMovie(Long id, Movie updatedMovie);
}
package com.cg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.entities.Movie;
import com.cg.repo.IMovie;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements IMovieService {

    @Autowired
    private IMovie movieRepository;

    @Override
    public Movie saveMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public List<Movie> getMoviesByGenre(String genre) {
        return movieRepository.findByGenre(genre);
    }

    @Override
    public Optional<Movie> getMovieById(Long id) {
        return movieRepository.findById(id);
    }

    @Override
    public void deleteMovieById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Movie updateMovie(Long id, Movie updatedMovie) {
        Optional<Movie> existing = movieRepository.findById(id);
        if (existing.isPresent()) {
            Movie movie = existing.get();
            movie.setMovieName(updatedMovie.getMovieName());
            movie.setRating(updatedMovie.getRating());
            movie.setGenre(updatedMovie.getGenre());
            return movieRepository.save(movie);
        }
        return null;
    }
}
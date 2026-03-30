package com.example.movieapp.Service;

import com.example.movieapp.DTO.MovieSaveDTO;
import com.example.movieapp.Entities.Movie;
import com.example.movieapp.Repository.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepo repo;

    public Movie saveMovie(MovieSaveDTO dto) {
        Movie movie = new Movie();
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setRating(dto.getRating());

        return repo.save(movie);
    }

    public List<Movie> findMovieByGenre(String genre) {
        return repo.findByGenre(genre);
    }

    public List<Movie> getAllMovies() {
        return repo.findAll();
    }

    public Optional<Movie> getMovieById(int id) {
        return repo.findById(id);
    }

    public boolean updateMovie(int id, MovieSaveDTO dto) {
        Optional<Movie> movieOptional = repo.findById(id);
        if (movieOptional.isEmpty()) {
            return false;
        }

        Movie movie = movieOptional.get();
        movie.setTitle(dto.getTitle());
        movie.setGenre(dto.getGenre());
        movie.setRating(dto.getRating());
        repo.save(movie);
        return true;
    }

    public boolean deleteMovieById(int id) {
        if (!repo.existsById(id)) {
            return false;
        }

        repo.deleteById(id);
        return true;
    }
}

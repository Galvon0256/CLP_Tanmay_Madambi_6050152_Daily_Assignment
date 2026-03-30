package com.cg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cg.entities.Movie;
import com.cg.service.IMovieService;

import java.util.List;
import java.util.Optional;

@Controller
public class MyController {

    @Autowired
    private IMovieService movieService;  // ← Now using service, not repo directly

    // ===================== HOME =====================
    @GetMapping("/")
    public ModelAndView home() {
        return new ModelAndView("home");
    }

    // ===================== ADD MOVIE =====================
    @GetMapping("/addMovie")
    public ModelAndView showAddMoviePage() {
        ModelAndView mav = new ModelAndView("addMovie");
        mav.addObject("movie", new Movie());
        return mav;
    }

    @PostMapping("/addMovie")
    public ModelAndView addMovie(@ModelAttribute("movie") Movie movie) {
        ModelAndView mav = new ModelAndView("addMovie");
        mav.addObject("movie", movie);

        String movieName = movie.getMovieName();
        String genre = movie.getGenre();
        double rating = movie.getRating();

        if (movieName == null || movieName.trim().isEmpty()) {
            mav.addObject("movieNameError", "Movie Name is required !!");
            mav.addObject("ratingError", "");
            mav.addObject("genreError", (genre == null || genre.equals("--Select--")) ? "Select Genre!!!" : "");
            return mav;
        }
        if (!movieName.matches("[a-zA-Z0-9 ]+")) {
            mav.addObject("movieNameError", "Movie Name should be only alphanumeric");
            mav.addObject("ratingError", "");
            mav.addObject("genreError", "");
            return mav;
        }
        if (rating < 1 || rating > 10) {
            mav.addObject("movieNameError", "");
            mav.addObject("ratingError", "Rating needs to be a number: eg: 7.5. Rating is on Scale 1-10");
            mav.addObject("genreError", "");
            return mav;
        }
        if (genre == null || genre.equals("--Select--")) {
            mav.addObject("movieNameError", "");
            mav.addObject("ratingError", "");
            mav.addObject("genreError", "Select Genre!!!");
            return mav;
        }

        movieService.saveMovie(movie);
        mav.addObject("successMessage", "Movie '" + movieName + "' added successfully!");
        mav.addObject("movieNameError", "");
        mav.addObject("ratingError", "");
        mav.addObject("genreError", "");
        mav.addObject("movie", new Movie());
        return mav;
    }

    // ===================== SEARCH ALL MOVIES =====================
    @GetMapping("/allMovies")
    public ModelAndView getAllMovies() {
        ModelAndView mav = new ModelAndView("allMovies");
        mav.addObject("movies", movieService.getAllMovies());
        return mav;
    }

    // ===================== SEARCH MOVIE BY CATEGORY =====================
    @GetMapping("/searchMovie")
    public ModelAndView showSearchPage() {
        ModelAndView mav = new ModelAndView("searchMovie");
        mav.addObject("searchMovie", new Movie());
        mav.addObject("movies", null);
        return mav;
    }

    @PostMapping("/searchMovie")
    public ModelAndView searchByCategory(@ModelAttribute("searchMovie") Movie movie) {
        ModelAndView mav = new ModelAndView("searchMovie");
        List<Movie> movies = movieService.getMoviesByGenre(movie.getGenre());
        mav.addObject("movies", movies);
        mav.addObject("searchMovie", new Movie());
        return mav;
    }

    // ===================== DELETE MOVIE =====================
    @GetMapping("/deleteMovie/{id}")
    public ModelAndView deleteMovie(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("allMovies");
        Optional<Movie> movie = movieService.getMovieById(id);

        if (movie.isPresent()) {
            movieService.deleteMovieById(id);
            mav.addObject("successMessage", "Movie deleted successfully!");
        } else {
            mav.addObject("errorMessage", "Movie not found with ID: " + id);
        }

        mav.addObject("movies", movieService.getAllMovies());
        return mav;
    }

    // ===================== UPDATE MOVIE =====================
    @GetMapping("/editMovie/{id}")
    public ModelAndView showEditPage(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView("editMovie");
        Optional<Movie> movie = movieService.getMovieById(id);

        if (movie.isPresent()) {
            mav.addObject("movie", movie.get());
        } else {
            mav.addObject("errorMessage", "Movie not found with ID: " + id);
            mav.addObject("movie", new Movie());
        }
        return mav;
    }

    @PostMapping("/editMovie/{id}")
    public ModelAndView updateMovie(@PathVariable Long id, @ModelAttribute("movie") Movie updatedMovie) {
        ModelAndView mav = new ModelAndView("editMovie");
        mav.addObject("movie", updatedMovie);

        String movieName = updatedMovie.getMovieName();
        String genre = updatedMovie.getGenre();
        double rating = updatedMovie.getRating();

        if (movieName == null || movieName.trim().isEmpty()) {
            mav.addObject("movieNameError", "Movie Name is required !!");
            mav.addObject("ratingError", "");
            mav.addObject("genreError", (genre == null || genre.equals("--Select--")) ? "Select Genre!!!" : "");
            return mav;
        }
        if (!movieName.matches("[a-zA-Z0-9 ]+")) {
            mav.addObject("movieNameError", "Movie Name should be only alphanumeric");
            mav.addObject("ratingError", "");
            mav.addObject("genreError", "");
            return mav;
        }
        if (rating < 1 || rating > 10) {
            mav.addObject("movieNameError", "");
            mav.addObject("ratingError", "Rating needs to be a number: eg: 7.5. Rating is on Scale 1-10");
            mav.addObject("genreError", "");
            return mav;
        }
        if (genre == null || genre.equals("--Select--")) {
            mav.addObject("movieNameError", "");
            mav.addObject("ratingError", "");
            mav.addObject("genreError", "Select Genre!!!");
            return mav;
        }

        Movie result = movieService.updateMovie(id, updatedMovie);
        if (result != null) {
            mav.addObject("successMessage", "Movie updated successfully!");
            mav.addObject("movieNameError", "");
            mav.addObject("ratingError", "");
            mav.addObject("genreError", "");
        } else {
            mav.addObject("errorMessage", "Movie not found with ID: " + id);
        }
        return mav;
    }
}
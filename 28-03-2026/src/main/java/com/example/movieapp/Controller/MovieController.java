package com.example.movieapp.Controller;

import com.example.movieapp.DTO.MovieSaveDTO;
import com.example.movieapp.Entities.Movie;
import com.example.movieapp.Service.MovieService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/movies/new")
    public String showMovieForm(Model model) {
        model.addAttribute("movie", new MovieSaveDTO());
        return "movie-form";
    }

    @PostMapping("/movies/save")
    public String saveMovie(@Valid @ModelAttribute MovieSaveDTO dto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "movie-form";
        }
        movieService.saveMovie(dto);
        redirectAttributes.addFlashAttribute("message", "Movie saved successfully!");
        return "redirect:/";
    }

    @GetMapping("/movies")
    public String listMovies(Model model) {
        model.addAttribute("movies", movieService.getAllMovies());
        return "movies-list";
    }

    @GetMapping("/movies/search")
    public String searchMovieByGenre(@RequestParam(required = false) String genre, Model model) {
        if (genre != null && !genre.isEmpty()) {
            model.addAttribute("movies", movieService.findMovieByGenre(genre));
        }
        return "search-movies";
    }

    @GetMapping("/movies/{id}/edit")
    public String showEditMovieForm(@PathVariable int id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Movie> movieOptional = movieService.getMovieById(id);
        if (movieOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Movie not found.");
            return "redirect:/movies";
        }

        Movie movie = movieOptional.get();
        MovieSaveDTO dto = new MovieSaveDTO();
        dto.setTitle(movie.getTitle());
        dto.setGenre(movie.getGenre());
        dto.setRating(movie.getRating());

        model.addAttribute("movie", dto);
        model.addAttribute("movieId", id);
        return "movie-edit";
    }

    @PostMapping("/movies/{id}/update")
    public String updateMovie(@PathVariable int id,
                              @Valid @ModelAttribute("movie") MovieSaveDTO dto,
                              BindingResult result,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("movieId", id);
            return "movie-edit";
        }

        boolean updated = movieService.updateMovie(id, dto);
        if (!updated) {
            redirectAttributes.addFlashAttribute("message", "Movie not found.");
            return "redirect:/movies";
        }

        redirectAttributes.addFlashAttribute("message", "Movie updated successfully!");
        return "redirect:/movies";
    }

    @PostMapping("/movies/{id}/delete")
    public String deleteMovie(@PathVariable int id, RedirectAttributes redirectAttributes) {
        boolean deleted = movieService.deleteMovieById(id);
        if (!deleted) {
            redirectAttributes.addFlashAttribute("message", "Movie not found.");
            return "redirect:/movies";
        }

        redirectAttributes.addFlashAttribute("message", "Movie deleted successfully!");
        return "redirect:/movies";
    }
}

package com.example.movieapp.DTO;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class MovieSaveDTO {
    @NotBlank(message = "Title cannot be empty")
    private String title;
    @NotBlank(message = "Genre cannot be empty")
    private String genre;
    @NotNull(message = "Rating is required")
    @Min(value = 0, message = "Rating min is 0")
    @Max(value = 10, message = "Rating max is 10")
    private Integer rating;
}

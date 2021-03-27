package com.exam.api.controller.dto;

import com.exam.api.domain.Movie;
import com.exam.api.util.ModelMapperUtil;
import com.fasterxml.jackson.annotation.JsonProperty;


import lombok.Data;


//{"_class": "com.exam.movie.model.Movie", "director_name": "Bart", "director_last_name": "Everson", "director_id": 23500.0, "director_genre": "Short", "movie_name": "'Java Madness' formerly titled 'Coffee Madness'", "movie_rank": 6.6, "movie_year": 1995.0, "movie_id": 136.0, "actor_role": "Lecturer/Java Girl", "movie_genre": "Short", "actor_name": "Christy", "actor_last_name": "Paxson", "actor_gender": "F", "actor_id": 749780.0}

@Data
public class MovieDto {
    
    String id;

    @JsonProperty("director_name")
    private String directorName;

    @JsonProperty("director_last_name")
    private String directorLastName;

    @JsonProperty("director_id")
    private String directorId;

    @JsonProperty("director_genre")
    private String directorGenre;

    @JsonProperty("movie_name")
    private String movieName;

    @JsonProperty("movie_rank")
    private int movieRank;

    @JsonProperty("movie_year")
    private int movieYear; 

    @JsonProperty("movie_id")
    private String movieId;

    @JsonProperty("actor_role")
    private String actorRole;

    @JsonProperty("actor_last_name")
    private String actorLastName;

    @JsonProperty("actor_gender")
    private String actorGender;

    @JsonProperty("actor_id")
    private Long actorId;
 
    public static MovieDto of(Movie movie) {
        return ModelMapperUtil.get().map(movie, MovieDto.class);
    }
    
}

package com.exam.api.entity;

import com.exam.api.controller.dto.MovieDto;
import com.exam.api.controller.dto.MoviePutDto;
import com.exam.api.util.ModelMapperUtil;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection = "movie_collection")
public class Movie {

    private String id;
    private String directorName;
    private String directorLastName;
    private String directorId;
    private String directorGenre;
    private String movieName;
    private int movieRank;
    private int movieYear; 
    private String movieId;
    private String actorRole;
    private String actorLastName;
    private String actorGender;
    private Long actorId;
 
    public void setId(String id) {
        this.id = id;
    }

    public static Movie of(MovieDto movieDto) {
        
        return ModelMapperUtil.get().map(movieDto, Movie.class);
        
    }

    public void updateMovie(MoviePutDto moviePutDto) {
        this.movieName = moviePutDto.getMovieName();
        this.directorName = moviePutDto.getDirectorName();
    }
}

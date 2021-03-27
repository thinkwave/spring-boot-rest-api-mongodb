package com.exam.api.controller.dto;

import com.exam.api.domain.Movie;
import com.exam.api.util.ModelMapperUtil;

import lombok.Data;


@Data
public class MoviePutDto {

    private String directorName;
    private String movieName;

    public static MoviePostDto of(Movie movie) {
        return ModelMapperUtil.get().map(movie, MoviePostDto.class);
    }
    
}

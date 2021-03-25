package com.exam.api.controller.dto;

import com.exam.api.entity.Movie;
import com.exam.api.util.ModelMapperUtil;

import javax.validation.constraints.NotBlank; 
import javax.validation.constraints.NotNull; 
import javax.validation.constraints.Size;


import lombok.Data;


@Data
public class MoviePostDto {

    @NotBlank(message="감독 이름은 필수") 
    @Size(max=500, message="최대 500자 입니다.")
    private String directorName;

    private String directorLastName;
    private String directorId;
    private String directorGenre;

    @NotBlank(message="제목은 필수") 
    @Size(max=500, message="최대 500자 입니다.")
    private String movieName;

    private int movieRank;
    private int movieYear; 
    private String movieId;
    private String actorRole;
    private String actorLastName;
    private String actorGender;
    private Long actorId;
 
    public static MoviePostDto of(Movie movie) {
        return ModelMapperUtil.get().map(movie, MoviePostDto.class);
    }
    
}

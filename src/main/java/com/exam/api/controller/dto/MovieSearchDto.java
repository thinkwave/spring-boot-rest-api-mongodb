package com.exam.api.controller.dto;

import lombok.Data;

@Data
public class MovieSearchDto {
    
    private String directorName;

    private String directorLastName;

    private String directorGenre;

    private String movieName;

    private int movieRank;

    private int movieYear; 

    private String actorRole;

    private String actorLastName;

    private String actorGender;
    
}

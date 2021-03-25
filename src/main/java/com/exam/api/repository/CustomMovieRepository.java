package com.exam.api.repository;

import java.util.List;

import com.exam.api.controller.dto.MovieSearchDto;
import com.exam.api.entity.Movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomMovieRepository {
    
    Page<Movie> query(MovieSearchDto searchDto, Pageable pageable);

}

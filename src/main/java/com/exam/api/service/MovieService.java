package com.exam.api.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import com.exam.api.common.error.exception.EntityNotFoundException;
import com.exam.api.controller.dto.MovieDto;
import com.exam.api.controller.dto.MoviePostDto;
import com.exam.api.controller.dto.MoviePutDto;
import com.exam.api.controller.dto.MovieSearchDto;
import com.exam.api.entity.Movie;
import com.exam.api.repository.MovieRepository;
import com.exam.api.repository.mongoclient.ExamMovieRepositoryImpl;
import com.exam.api.util.ModelMapperUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class MovieService {
    
    private final ExamMovieRepositoryImpl examMovieRepositoryImpl;
    private final MovieRepository movieRepository;


    public List<MovieDto> findAll() {
        return movieRepository.findAll()
                    .stream()
                    .map(MovieDto::of).collect(Collectors.toList());

    }

    public void save(Movie movie) {
        // movieCollectionRepository.save(movie);
    }

    public void saveAll(List<Movie> movies) {
        // movieCollectionRepository.saveAll(movies);
    }

    public Page<MovieDto> findMovieByDirectorName(String directorName, Pageable paging) {

        return movieRepository.findByDirectorName(directorName, paging).map(MovieDto::of);

    }

    public Page<MovieDto> searchMovieByCond(MovieSearchDto searchDto, Pageable pageable) {
        return movieRepository.query(searchDto, pageable).map(MovieDto::of);

    }

    public MovieDto saveMovie(MoviePostDto moviePostDto) {

        Movie movie = ModelMapperUtil.get().map(moviePostDto, Movie.class);
        movie.setId(UUID.randomUUID().toString());

        Movie saved = movieRepository.save(movie);

        return MovieDto.of(saved);
    }

    public MovieDto getMovieById(String movieId) {
        return movieRepository.findById(movieId).map(MovieDto::of).orElseThrow(() -> new EntityNotFoundException(movieId));
    }

    public MovieDto updateMovieById(String movieId, MoviePutDto moviePutDto) {

        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException(movieId));

        movie.updateMovie(moviePutDto);

        return MovieDto.of(movieRepository.save(movie));
    }

    public void deleteMovieById(String movieId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException(movieId));

        movieRepository.delete(movie);
    }


}

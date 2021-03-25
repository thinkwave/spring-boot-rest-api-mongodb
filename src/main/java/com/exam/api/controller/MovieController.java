package com.exam.api.controller;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import com.exam.api.common.response.CommonResponse;
import com.exam.api.controller.dto.MovieDto;
import com.exam.api.controller.dto.MoviePostDto;
import com.exam.api.controller.dto.MoviePutDto;
import com.exam.api.controller.dto.MovieSearchDto;
import com.exam.api.entity.Movie;
import com.exam.api.repository.MovieRepository;
import com.exam.api.service.MovieService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/v1/movie")
public class MovieController {

    private final ObjectMapper objectMapper;
    private final MovieService movieService;
    private final MovieRepository movieRepository;


    @ApiOperation(value = "모든 영화 데이타 조회")
    @GetMapping()
    public CommonResponse<Page<MovieDto>> getAllMovies(        
        MovieSearchDto movieSearchDto, 
        @PageableDefault(sort={ "id" }, direction = Direction.DESC, size=20, page=0) Pageable pageable
        ) {
            log.debug("<<< 모든 영화 데이타 조회 >>>");
            return new CommonResponse<Page<MovieDto>>(movieService.searchMovieByCond(new MovieSearchDto(), pageable));
    }


    @ApiOperation(value = "ID로 영화 조회")
    @GetMapping("/{movieId}")
    public CommonResponse<MovieDto> getMovieById(@PathVariable("movieId") String movieId) {
        log.debug("<<< ID로 영화 조회 >>>");

        MovieDto movieDto = movieService.getMovieById(movieId);

        log.debug(movieDto.toString());

        return new CommonResponse<MovieDto>(movieDto);
    }

    @ApiOperation(value = "ID로 영화 업데이트")
    @PutMapping("/{movieId}")
    public CommonResponse<MovieDto> updateMovieById(@PathVariable("movieId") String movieId, @RequestBody MoviePutDto moviePutDto) {

        return new CommonResponse<MovieDto>(movieService.updateMovieById(movieId, moviePutDto));

    }

    @ApiOperation(value = "ID로 영화 삭제")
    @DeleteMapping("/{movieId}")
    public CommonResponse<Boolean> removeMovieById(@PathVariable("movieId") String movieId) {

        movieService.deleteMovieById(movieId);

        return new CommonResponse<Boolean>(true);
    }


    @ApiOperation(value = "페이징 검색")
    @GetMapping("/search")
    public CommonResponse<Page<MovieDto>> searchMovieByCond(
        MovieSearchDto movieSearchDto, 
        @PageableDefault(sort={ "id" }, direction = Direction.DESC, size=20, page=0) Pageable pageable
        ) {
        return new CommonResponse<Page<MovieDto>>(movieService.searchMovieByCond(movieSearchDto, pageable));
    }

    @ApiOperation(value = "영화 저장")
    @PostMapping
    public CommonResponse<MovieDto> saveMovie(@RequestBody @Valid MoviePostDto moviePostDto) {

        log.info("### MOVIE ###\n" + moviePostDto.toString());

        return new CommonResponse<MovieDto>(movieService.saveMovie(moviePostDto), HttpStatus.CREATED);
    }


    @ApiOperation(value = "영화 데이타 로드하여 MongoDB에 저장")
    @GetMapping("/load")
    public void loadData() {
        
        log.debug("### 로드 ### ");

        try {
            List<MovieDto> movieDtos = objectMapper.readValue(new ClassPathResource("IMDB.json").getInputStream(), new TypeReference<List<MovieDto>>() {});

            log.info(">>> 영화 DTO 사이즈 : " + movieDtos.size());

            List<Movie> movies = movieDtos
                .stream()
                .map(dto -> {
                    Movie m = Movie.of(dto);
                    m.setId(UUID.randomUUID().toString());
                    return m;
                })
                .collect(Collectors.toList())
                ;

            log.info(">>> 영화 Entity 사이즈 : " + movies.size());

            List<Movie> saved = movieRepository.saveAll(movies);

            log.info(">>> 영화 저장 결과 사이즈 : " + saved.size());

        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

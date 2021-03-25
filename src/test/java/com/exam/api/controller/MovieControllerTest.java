package com.exam.api.controller;

import com.exam.api.common.error.exception.EntityNotFoundException;
import com.exam.api.controller.dto.MovieDto;
import com.exam.api.controller.dto.MoviePostDto;
import com.exam.api.controller.dto.MoviePutDto;
import com.exam.api.controller.dto.MoviePutDto;
import com.exam.api.controller.dto.MovieSearchDto;
import com.exam.api.repository.MovieRepository;
import com.exam.api.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.hamcrest.core.IsNull;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import static org.mockito.BDDMockito.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;


@ActiveProfiles("local")
@WebMvcTest(controllers = MovieController.class)
public class MovieControllerTest {
 
    @Autowired 
    MockMvc mvc;

    @Autowired 
    private ObjectMapper objectMapper;

    @MockBean
    private MovieService movieService;

    @MockBean
    private MovieRepository movieRepository;

    private Pageable pageable = PageRequest.of(0, 20);


    @Test
    public void 모든_영화_조회() throws Exception {

        // given
        Page<MovieDto> result = null;
        given(movieService.searchMovieByCond(new MovieSearchDto(), pageable)).willReturn(result);

        // when
        final ResultActions actions = mvc
            .perform(get("/v1/movie"))
            .andDo(print())
            ;

        // then
        actions 
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.message", is("OK")))
            .andExpect(jsonPath("$.status", is(200)))
            .andExpect(jsonPath("$.code", is("200")))
            .andExpect(jsonPath("$.body").value(IsNull.nullValue()) )
            .andDo(print()); 
    }




    @DisplayName("GET /v1/movie/{movidId}")
    @Test
    public void ID로_영화_조회() throws Exception {

        // given
        String movieId = "1qazxsdfgbReSw#4";
        String directorGenre = "Space, Zombie, Action, SF";

        MovieDto result = new MovieDto();
        result.setId(movieId);
        result.setMovieName("영화 제목");
        result.setDirectorGenre(directorGenre);

        given(movieService.getMovieById(anyString())).willReturn(result);

        // when
        final ResultActions actions = mvc
            .perform(get("/v1/movie/" + movieId)
                        .contentType(MediaType.APPLICATION_JSON))
            .andDo(print())
            ;

        // then
        actions 
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message", is("OK")))
            .andExpect(jsonPath("$.status", is(200)))
            .andExpect(jsonPath("$.code", is("200")))
            .andExpect(jsonPath("$.body.id", is(movieId)))
            .andExpect(jsonPath("$.body.director_genre", is(directorGenre)))
            .andDo(print()); 
    }

    @DisplayName("PUT /v1/movie/{movidId}")
    @Test
    public void ID로_영화_업데이트() throws Exception {

        // given
        String movieId = "1qazxsdfgbReSw#4";
        String directorGenre = "Space, Zombie, Action, SF";
        String movieName = "Who is the cutest zombie in the world?";

        MoviePutDto param = new MoviePutDto();
        param.setDirectorName("APPLE");
        param.setMovieName(movieName);

        MovieDto result = new MovieDto();
        result.setId(movieId);
        result.setMovieName(movieName);
        result.setDirectorGenre(directorGenre);

        given(movieService.updateMovieById(anyString(), any())).willReturn(result);

        // when
        final ResultActions actions = mvc
            .perform(put("/v1/movie/" + movieId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(param))
                    )
            .andDo(print())
            ;

        // then
        actions 
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message", is("OK")))
            .andExpect(jsonPath("$.status", is(200)))
            .andExpect(jsonPath("$.code", is("200")))
            .andExpect(jsonPath("$.body.id", is(movieId)))
            .andExpect(jsonPath("$.body.movie_name", is(movieName)))
            .andExpect(jsonPath("$.body.director_genre", is(directorGenre)))
            .andDo(print()); 
    }
    

    @DisplayName("DELETE /v1/movie/{movidId}")
    @Test
    public void ID로_영화_삭제() throws Exception {

        // given
        String movieId = "2qsSDfr345rWESDFVDSf";

        // when
        final ResultActions actions = mvc
            .perform(delete("/v1/movie/" + movieId)
                        .contentType(MediaType.APPLICATION_JSON)
                    )
            .andDo(print())
            ;

        // then
        actions 
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.message", is("OK")))
            .andExpect(jsonPath("$.status", is(200)))
            .andExpect(jsonPath("$.code", is("200")))
            .andExpect(jsonPath("$.body", is(true)))
            .andDo(print()); 
    }

    @DisplayName("POST /v1/movie")
    @Test
    public void 영화_저장() throws Exception {

        // given
        String movieId = "1qazxsdfgbReSw#4";
        String directorGenre = "Space, Zombie, Action, SF";
        String movieName = "Who is the cutest zombie in the world?";

        MoviePostDto param = new MoviePostDto();
        param.setDirectorName("APPLE");
        param.setMovieName(movieName);

        MovieDto result = new MovieDto();
        result.setId(movieId);
        result.setMovieName(movieName);
        result.setDirectorGenre(directorGenre);

        given(movieService.saveMovie(any(MoviePostDto.class))).willReturn(result);

        // when
        final ResultActions actions = mvc
            .perform(post("/v1/movie")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(param))
                    )
            .andDo(print())
            ;

        // then
        actions 
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            // .andExpect(status().isCreated())
            .andExpect(jsonPath("$.message", is("CREATED")))
            .andExpect(jsonPath("$.status", is(201)))
            .andExpect(jsonPath("$.code", is("201")))
            .andExpect(jsonPath("$.body.id", is(movieId)))
            .andExpect(jsonPath("$.body.movie_name", is(movieName)))
            .andExpect(jsonPath("$.body.director_genre", is(directorGenre)))
            .andDo(print()); 
    }


}

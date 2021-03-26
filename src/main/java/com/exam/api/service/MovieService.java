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

import com.exam.api.util.ModelMapperUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class MovieService {
    

}

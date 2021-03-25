package com.exam.api.repository;

import java.util.ArrayList;
import java.util.List;

import com.exam.api.controller.dto.MovieSearchDto;
import com.exam.api.entity.Movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.MongoRegexCreator;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;



import org.springframework.data.repository.query.parser.Part;
import org.springframework.data.support.PageableExecutionUtils;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Repository
public class CustomMovieRepositoryImpl implements CustomMovieRepository {

    private final MongoTemplate mongoTemplate;


    @Override
    public Page<Movie> query(MovieSearchDto searchDto, Pageable pageable) {

        Query query = new Query().with(pageable);
        Criteria criteria = new Criteria();

        if(searchDto.getDirectorName()!=null) {
            criteria.and("directorName").is(searchDto.getDirectorName());
        }

        if(searchDto.getMovieName()!=null) {
            criteria.and("movieName").is(searchDto.getMovieName());
        }

        if(searchDto.getActorGender()!=null) {
            criteria.and("actorGender").is(searchDto.getActorGender());
        }

        // criteria.and("episode").exists(true);


        query.addCriteria(criteria);

		List<Movie> movies = mongoTemplate.find(query, Movie.class);

        return PageableExecutionUtils.getPage(
                    movies, 
                    pageable, 
                    () -> mongoTemplate.count(Query.of(query).limit(-1).skip(-1), Movie.class));

    }

    
}
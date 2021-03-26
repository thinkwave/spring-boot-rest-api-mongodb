package com.exam.api.repository;

import java.util.List;

import com.exam.api.entity.Movie;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

import static com.exam.api.entity.QMovie.movie;


@RequiredArgsConstructor
@Repository
public class MovieQueryRepository {

    private final JPAQueryFactory queryFactory;

    public List<Movie> findByName(String movieName) {
        return queryFactory.selectFrom(movie)
                .where(movie.movieName.eq(movieName))
                .fetch();
    }

    
}

package com.exam.api.repository;

import java.util.List;

import com.exam.api.domain.Movie;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

import static com.exam.api.domain.QMovie.movie;


@RequiredArgsConstructor
@Repository
public class MovieQueryRepository {

    private final JPAQueryFactory queryFactory;


    @Transactional(readOnly = true, propagation = Propagation.REQUIRES_NEW)
    public Movie selectMovieById(String movieId) {
        return queryFactory
                .select(movie)
                .from(movie)
                .where(movie.movieId.eq(movieId))
                .fetchOne();
    }

    public List<Movie> findByName(String movieName) {
        return queryFactory
                .selectFrom(movie)
                .where(movie.movieName.eq(movieName))
                .fetch();
    }

    
}

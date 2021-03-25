package com.exam.api.repository;



import com.exam.api.entity.Movie;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface MovieRepository extends MongoRepository<Movie, String>, CustomMovieRepository {

    Page<Movie> findByDirectorName(String directorName, Pageable pageable);

}
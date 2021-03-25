package com.exam.api.repository.mongoclient;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface ExamMongoRepository<T> {
    T save(T person);

    List<T> saveAll(List<T> entity);

    List<T> findAll();

    List<T> findAll(List<String> ids);

    T findOne(String id);

    long count();

    long delete(String id);

    long delete(List<String> ids);

    long deleteAll();

    T update(T entity);

    long update(List<T> entity);

    double getAverageAge();

}

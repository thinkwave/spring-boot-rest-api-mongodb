package com.exam.api.common.config;


import com.querydsl.jpa.impl.JPAQueryFactory;
// import com.querydsl.sql.H2Templates;
// import com.querydsl.sql.MySQLTemplates;
// import com.querydsl.sql.SQLQueryFactory;
// import com.querydsl.sql.SQLTemplates;
// import com.querydsl.sql.spring.SpringExceptionTranslator;
// import com.querydsl.sql.types.DateTimeType;
// import com.querydsl.sql.types.LocalDateType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
// import pl.exsio.querydsl.entityql.config.EntityQlQueryFactory;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;


@Slf4j
@RequiredArgsConstructor
@Configuration
public class QuerydslConfig {
    private final DataSource dataSource;

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory queryFactory() {
        return new JPAQueryFactory(entityManager);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(dataSource);
    }

    // @Bean
    // @Profile("local")
    // public SQLTemplates h2SqlTemplates() {
    //     return new H2Templates();
    // }

    // @Bean
    // @Profile("!local")
    // public SQLTemplates mySqlTemplates() {
    //     return new MySQLTemplates();
    // }

    // @Bean
    // public SQLQueryFactory sqlQueryFactory(DataSource dataSource, SQLTemplates sqlTemplates) {
    //     com.querydsl.sql.Configuration configuration = new com.querydsl.sql.Configuration(sqlTemplates);
    //     configuration.setExceptionTranslator(new SpringExceptionTranslator());
    //     configuration.register(new DateTimeType());
    //     configuration.register(new LocalDateType());

    //     return new EntityQlQueryFactory(configuration, dataSource)
    //             .registerEnumsByName("com.jojoldu.blogcode.querydsl.domain.pointevent")
    //             .registerEnumsByName("com.jojoldu.blogcode.querydsl.domain.book");
    // }

}

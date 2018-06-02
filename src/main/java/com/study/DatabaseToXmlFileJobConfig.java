package com.study;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class DatabaseToXmlFileJobConfig {
 
    private static final String QUERY_FIND_STUDENTS =
            "SELECT " +
                "email_address, " +
                "name, " +
                "purchased_package " +
            "FROM STUDENTS " +
            "ORDER BY email_address ASC";
 
    @Bean
    ItemReader<StudentDTO> databaseXmlItemReader(DataSource dataSource) {
        JdbcCursorItemReader<StudentDTO> databaseReader = new JdbcCursorItemReader<>();
 
        databaseReader.setDataSource(dataSource);
        databaseReader.setSql(QUERY_FIND_STUDENTS);
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(StudentDTO.class));
 
        return databaseReader;
    }
}
package com.study;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.support.H2PagingQueryProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

@Configuration
public class DatabaseToCsvFileJobConfig {
 
    @Bean
    ItemReader<StudentDTO> databaseCsvItemReader(DataSource dataSource) {
        JdbcPagingItemReader<StudentDTO> databaseReader = new JdbcPagingItemReader<>();
 
        databaseReader.setDataSource(dataSource);
        databaseReader.setPageSize(1);
 
        PagingQueryProvider queryProvider = createQueryProvider();
        databaseReader.setQueryProvider(queryProvider);
 
        databaseReader.setRowMapper(new BeanPropertyRowMapper<>(StudentDTO.class));
 
        return databaseReader;
    }
 
    private PagingQueryProvider createQueryProvider() {
        H2PagingQueryProvider queryProvider = new H2PagingQueryProvider();
 
        queryProvider.setSelectClause("SELECT email_address, name, purchased_package");
        queryProvider.setFromClause("FROM students");
        queryProvider.setSortKeys(sortByEmailAddressAsc());
 
        return queryProvider;
    }
 
    private Map<String, Order> sortByEmailAddressAsc() {
        Map<String, Order> sortConfiguration = new HashMap<>();
        sortConfiguration.put("email_address", Order.ASCENDING);
        return sortConfiguration;
    }
}
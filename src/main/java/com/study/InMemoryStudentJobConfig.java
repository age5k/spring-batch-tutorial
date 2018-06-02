package com.study;

import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryStudentJobConfig {
 
    @Bean
    ItemReader<StudentDTO> inMemoryStudentReader() {
        return new InMemoryStudentReader();
    }
}
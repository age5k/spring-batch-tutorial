package com.study;

import org.springframework.batch.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RESTStudentJobConfig {
 
    @Bean
    ItemReader<StudentDTO> restStudentReader(Environment environment, 
                                             RestTemplate restTemplate) {
        return new RESTStudentReader(
            environment.getRequiredProperty("rest.api.to.database.job.api.url"), 
            restTemplate
        );
    }
}
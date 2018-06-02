package com.study;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class XmlFileToDatabaseJobConfig {
 
    @Bean
    ItemReader<StudentDTO> xmlFileItemReader() {
        StaxEventItemReader<StudentDTO> xmlFileReader = new StaxEventItemReader<>();
        xmlFileReader.setResource(new ClassPathResource("data/students.xml"));
        xmlFileReader.setFragmentRootElementName("student");
 
        Jaxb2Marshaller studentMarshaller = new Jaxb2Marshaller();
        studentMarshaller.setClassesToBeBound(StudentDTO.class);
        xmlFileReader.setUnmarshaller(studentMarshaller);
 
        return xmlFileReader;
    }
}
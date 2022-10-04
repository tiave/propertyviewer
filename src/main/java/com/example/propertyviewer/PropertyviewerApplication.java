package com.example.propertyviewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.propertyviewer.domain.Property;
import com.example.propertyviewer.domain.PropertyRepository;


@SpringBootApplication
public class PropertyviewerApplication {

	
	public static final Logger log = LoggerFactory.getLogger(PropertyviewerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PropertyviewerApplication.class, args);
	}


	@Bean
	public CommandLineRunner demoProperty(PropertyRepository repo) {
		return (args) -> {
			log.info("generate some properties");

			repo.save(new Property("School", "Liisankatu", 13, "00170", "Helsinki", "Finland", "beautiful building"));

			log.info("show the properties");
			for (Property property : repo.findAll()) {
				log.info(property.toString());
			}
		};
	}
}




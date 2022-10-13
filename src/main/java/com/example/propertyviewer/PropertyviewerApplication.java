package com.example.propertyviewer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.example.propertyviewer.domain.Property;
import com.example.propertyviewer.domain.PropertyRepository;
import com.example.propertyviewer.domain.User;
import com.example.propertyviewer.domain.UserRepository;


@SpringBootApplication
public class PropertyviewerApplication {

	
	public static final Logger log = LoggerFactory.getLogger(PropertyviewerApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(PropertyviewerApplication.class, args);
	}

	
	@Bean
	public RestTemplate restTemplate() {
    	return new RestTemplate();
	}


	@Bean
	public CommandLineRunner demoProperty(PropertyRepository pRepo, UserRepository uRepo) {
		return (args) -> {
			log.info("generate some properties");

			pRepo.save(new Property("Sibelius-lukio", "Liisankatu", 13, "00170", "Helsinki", "Finland", "beautiful building"));
			pRepo.save(new Property("Oodi", "Töölönlahdenkatu ", 4, "00100", "Helsinki", "Finland", "very nice library"));

			User userDemo = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
			User adminDemo = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
			uRepo.save(userDemo);
			uRepo.save(adminDemo);

			log.info("show the properties");
			for (Property property : pRepo.findAll()) {
				log.info(property.toString());
			}
		};
	}
}




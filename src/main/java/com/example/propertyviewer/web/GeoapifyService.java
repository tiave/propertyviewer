package com.example.propertyviewer.web;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.propertyviewer.domain.Property;
import com.example.propertyviewer.domain.PropertyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/* @Service
public class GeoapifyService {

    private final PropertyRepository pRepo;

	@Autowired
	public GeoapifyService(PropertyRepository pRepo) {
		this.pRepo = pRepo;
	}

    @Autowired
    private RestTemplate restTemplate;

    
    //TODO: API call

    
    @Value("${geoapify.key}")
    public String geoapifyKey;

    
        //how to pass the property name or sth here for the link
        //form -> controller -> here ?
        public String getCoordinates(@PathVariable("name") String name, Model model) throws JsonMappingException, JsonProcessingException {
            //Property property = pRepo.findByName(name);   
            RestTemplate template = new RestTemplate();
            
            String apiUrl = "https://api.geoapify.com/v1/geocode/search?name=" + name + "&apiKey=" + geoapifyKey;
            
            String response = template.getForObject(apiUrl, String.class);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode node = mapper.readValue(response, JsonNode.class);
            JsonNode lon = node.get("lon");
            JsonNode lat = node.get("lat");
            System.out.println(lon.asText());
            System.out.println(lat.asText());

            String coordinates = lat + ", " + lon;
        
            return coordinates;
        }

} */

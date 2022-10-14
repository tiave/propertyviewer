package com.example.propertyviewer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.example.propertyviewer.domain.PropertyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GeoapifyService {

    private final PropertyRepository pRepo;

	@Autowired
	public GeoapifyService(PropertyRepository pRepo) {
		this.pRepo = pRepo;
	}

    @Autowired
    private RestTemplate restTemplate;

    
    @Value("${geoapify.key}")
    public String geoapifyKey;


    public String getCoordinates(@PathVariable("name") String name) throws JsonMappingException, JsonProcessingException {  
        RestTemplate template = new RestTemplate();
        
        String apiUrl = "https://api.geoapify.com/v1/geocode/search?name=" + name + "&apiKey=" + geoapifyKey;
        String response = template.getForObject(apiUrl, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode node = mapper.readTree(response);
        JsonNode lon = node.findPath("lon");
        JsonNode lat = node.findPath("lat");
        String coordinates= "Latitude " + lat + " & longitude " + lon;
    
        System.out.println(coordinates);
        return coordinates;
    }

}

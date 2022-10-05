package com.example.propertyviewer.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import io.github.cdimascio.dotenv.Dotenv;
import com.example.propertyviewer.domain.Property;
import com.example.propertyviewer.domain.PropertyRepository;

public class RestController {


    @Autowired
    private PropertyRepository repo;
    
    //one of many ways to handle api calls, not sure if a good one
    @Autowired
    private RestTemplate restTemplate;
    
        //TODO: API call
        //ideally the user would press "get coordinates" or so and trigger this
        //property name would be sent here for the link formatting
        //definitely missing some steps and don't know how to continue
        //and what to do with syntax and logic

        @GetMapping (value="/coordinates/{name}")

        public String getCoordinates(@PathVariable("name") String name, Model model) {

            //get api key from separate .env file not visible on version control
            Dotenv dotenv = null;
            dotenv = Dotenv.configure().load();
            dotenv.get("API_KEY");

            model.addAttribute("name", repo.findByName(name));

            String url = "https://api.geoapify.com/v1/geocode/search?name="+ name +"&apiKey=" + dotenv;
            RestTemplate restTemplate= new RestTemplate();
            String coordinates = restTemplate.getForObject(url, String.class);
            System.out.println(coordinates);
            return "redirect:addproperty";
        }

      

}

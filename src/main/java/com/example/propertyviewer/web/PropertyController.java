package com.example.propertyviewer.web;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.propertyviewer.domain.Property;
import com.example.propertyviewer.domain.PropertyRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.cdimascio.dotenv.Dotenv;

@Controller
public class PropertyController {
    @Autowired
    private PropertyRepository pRepo;

        //login page
        @RequestMapping(value= {"/", "/login"})
        public String login() {	
            return "login";
        }	

        //all properties listed on the page
        @RequestMapping(value="/properties")
        public String taskList(Model model) {
            model.addAttribute("properties", pRepo.findAll());
            return "properties";
        }

        //user will navigate to addproperty.html page by pressing "add property"
        @RequestMapping(value="/add")
        public String addProperty(Model model){
            model.addAttribute("property", new Property());
            return "addproperty";
        }

        //saving new property info, redirecting to properties.html
        @RequestMapping(value="/save", method=RequestMethod.POST)
        public String save(Property property){
            pRepo.save(property);
            
            //TODO: one idea is to have the API call method here
            //which means it would be triggered by save-button
            return "redirect:properties";
        }

        //navigate to editproperty page based on id to edit the existing info
        @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
        public String editProperty(@PathVariable("id") Long id, Model model) {
            model.addAttribute("property", pRepo.findById(id));
            return "editproperty";
        }

        //delete method, only users with role Admin see this
        @PreAuthorize("hasAuthority('ADMIN')")
        @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
        public String deleteProperty(@PathVariable("id") Long id, Model model) {
            pRepo.deleteById(id);
            return "redirect:../properties";
        }    


        //TODO: API call
        //ideally the user would press "get coordinates" or so and trigger this
        //property name would be sent here for the link formatting
        //definitely missing some steps and don't know how to continue
        //and what to do with syntax and logic

        /* @GetMapping (value="/coordinates")

        public String getCoordinates(@PathVariable("name") String name, Model model) {

            //get api key from separate .env file not visible on version control
            Dotenv dotenv = null;
            dotenv = Dotenv.configure().load();
            dotenv.get("API_KEY");

            model.addAttribute("name", pRepo.findByName(name));
 
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.geoapify.com/v1/geocode/search?name="+ name +"&apiKey=" + dotenv))
                .header("Content-Type", "application/json")
                .build();

            HttpResponse<String> response =
            client.send(request, BodyHandlers.ofString());
            List<String> coordinates  = Arrays.asList(response.results.lon, response.results.lat); //syntax?

            System.out.println(coordinates);

            return "/addproperty";
        } */


        //REST page for listing all properties
        @RequestMapping(value="/api/allproperties")
        public @ResponseBody List<Property> propertiesRest() {
            return (List<Property>) pRepo.findAll();
        }



}

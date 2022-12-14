package com.example.propertyviewer.web;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.propertyviewer.domain.Property;
import com.example.propertyviewer.domain.PropertyRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;


@Controller
public class PropertyController {
    @Autowired
    private PropertyRepository pRepo;

    @Autowired
    private GeoapifyService gService;

    
    private PropertyService pService;

    
    private PropertyDTO propertyDTO;


        //login page
        @RequestMapping(value= {"/", "/login"})
        public String login() {	
            return "login";
        }	

        //all properties listed on the page
        @RequestMapping(value="/properties")
        public String propertyList(Model model) {
            model.addAttribute("properties", pRepo.findAll());

            //TODO: check if property info is empty,
            //then delete that before listing properties on page

            //iterate through property list
            //see if name=""
            //delete

            return "properties";
        }


        //get coordinates based on property name
        @RequestMapping(value="/coordinates/{name}")
        public String getCoordinates(@PathVariable("name") String name, Model model) {
            String coordinates = "";

            try {
                coordinates = gService.getCoordinates(name);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            
            model.addAttribute("coordinates", coordinates);
            model.addAttribute("properties", pRepo.findAll());
   
            return "properties";
        }


        //user will navigate to addproperty.html page by pressing "add property"
        @RequestMapping(value="/add")
        public String addProperty(Model model){
            PropertyDTO propertiesForm = new PropertyDTO();

            //this could be any amount, rn just fixed to be 3 rows
            for (int i = 1; i <= 3; i++) {
                propertiesForm.addProperty(new Property());
            }
        
            model.addAttribute("form", propertiesForm);

            return "addproperty";
        }


        //saving new property info, redirecting to properties.html
        @RequestMapping(value="/saveMultiple", method=RequestMethod.POST)
        public String saveProperties(@ModelAttribute PropertyDTO form, Model model){

            pRepo.saveAll(form.getProperties());

            model.addAttribute("properties", pRepo.findAll());
        
            return "redirect:properties";
        }

        //another save method used by the editing view
        @RequestMapping(value="/save", method=RequestMethod.POST)
        public String saveProperty(Property property){
        
            pRepo.save(property);
        
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


        //REST page for listing all properties
        @RequestMapping(value="/api/allproperties")
        public @ResponseBody List<Property> propertiesRest() {

            return (List<Property>) pRepo.findAll();
        }



}

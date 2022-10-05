package com.example.propertyviewer.web;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.propertyviewer.domain.Property;
import com.example.propertyviewer.domain.PropertyRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;

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

        //REST page for listing all properties
        @RequestMapping(value="/api/allproperties")
        public @ResponseBody List<Property> propertiesRest() {
            return (List<Property>) pRepo.findAll();
        }



}

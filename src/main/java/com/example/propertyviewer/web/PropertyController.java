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
import org.springframework.stereotype.Controller;

@Controller
public class PropertyController {
    @Autowired
    private PropertyRepository repo;

        //all properties listed on the page
        @RequestMapping(value="/properties")
        public String taskList(Model model) {
            model.addAttribute("properties", repo.findAll());
            return "properties";
        }

        @RequestMapping(value="/allproperties")
        public @ResponseBody List<Property> propertiesRest() {
            return (List<Property>) repo.findAll();
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
            repo.save(property);
            return "redirect:properties";
        }

        //navigate to editproperty page based on id to edit the existing info
        @RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
        public String editProperty(@PathVariable("id") Long id, Model model) {
            model.addAttribute("property", repo.findById(id));
            return "editproperty";
        }



}

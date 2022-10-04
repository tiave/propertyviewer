package com.example.propertyviewer.web;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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


}

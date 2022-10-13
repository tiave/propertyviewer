package com.example.propertyviewer.web;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.propertyviewer.domain.Property;


@Service
public interface PropertyService {

    
    List<Property> findAll();

    void saveAll(List<Property> properties);
        


    
}

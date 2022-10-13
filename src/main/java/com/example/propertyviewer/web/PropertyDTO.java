package com.example.propertyviewer.web;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

import com.example.propertyviewer.domain.Property;

//data transfer object, works somewhat like a repository
//useful for handling properties as a list
public class PropertyDTO {

    @Column
    @ElementCollection(targetClass = Integer.class)
    private List<Property> properties;

    public PropertyDTO() {
        this.properties = new ArrayList<>();
    }

    public PropertyDTO(List<Property> properties) {
        this.properties = properties;
    }

    public List<Property> getProperties() {
        return properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public void addProperty(Property property) {
        this.properties.add(property);
    }


}

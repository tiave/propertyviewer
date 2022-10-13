package com.example.propertyviewer.domain;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface PropertyRepository extends CrudRepository<Property, Long> {
    List<Property> findByName(String name);

    List<Property> findAll();

    

    //is sth like this needed?
    //List<Property> findCoordinates(String coordinates);
    
}

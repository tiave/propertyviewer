package com.example.propertyviewer;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.propertyviewer.domain.Property;
import com.example.propertyviewer.domain.PropertyRepository;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PropertyviewerApplicationTests {
	@Autowired
	private PropertyRepository pRepo;

	@Autowired
	private TestEntityManager entityManager;

	@Test
	public void should_add_property() {
		
		Property property = new Property("Helsingin tuomiokirkko", "Unioninkatu", 29, "00170", "Helsinki", "Finland", "architect: Carl Ludvig Engel");
		pRepo.save(property);
		assertThat(property.getId()).isNotNull();
	}

	@Test
  	public void should_find_all_properties() {

		//delete existing demo properties before creating new ones here
		pRepo.deleteAll();

		Property property1 = new Property("Sibelius-lukio", "Liisankatu", 13, "00170", "Helsinki", "Finland", "beautiful building");
		entityManager.persist(property1);	
		
		Property property2 = new Property("Oodi", "Töölönlahdenkatu ", 4, "00100", "Helsinki", "Finland", "very nice library");
		entityManager.persist(property2);

		Iterable properties = pRepo.findAll();

		assertThat(properties).hasSize(2).contains(property1);
  	}

	@Test
	public void should_delete_property_by_id() {

		//delete existing demo properties before creating new ones here
		pRepo.deleteAll();

		Property property1 = new Property("Sibelius-lukio", "Liisankatu", 13, "00170", "Helsinki", "Finland", "beautiful building");
		entityManager.persist(property1);	
		
		Property property2 = new Property("Oodi", "Töölönlahdenkatu ", 4, "00100", "Helsinki", "Finland", "very nice library");
		entityManager.persist(property2);

		pRepo.deleteById(property2.getId());
		Iterable properties = pRepo.findAll();

		assertThat(properties).hasSize(1).contains(property1);
	}

}

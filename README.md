# Property Viewer

## About

Simple Java Spring application for viewing, adding, editing and deleting property information.
Login feature with demo user (username user, password user) or demo admin (username admin, password admin) available.


## Table of contents


> * [Property Viewer](#property-viewer)
>   * [About](#about)
>   * [Table of contents](#table-of-contents)
>   * [Installation](#installation)
>   * [Usage](#usage)
>     * [Features](#features)
>     * [REST](#REST)
>     * [Tests](#tests)
>   * [Code](#code)
>     * [Requirements](#requirements)
>     * [Build](#build)
>   * [Resources](#resources-i-found-useful)
>   * [Problems during the task](#problems-during-the-task)
>   * [Ideas for further development](#ideas-for-further-development)


## Usage

### Features

* login
* view
* add
* edit
* delete (admin)
* get coordinates based on property name

### REST

* _/api/allproperties_
* returns a list of all property objects
* doesn't require logging in

### Tests

There are three simple unit tests:

* should_add_property
* should_find_all_properties
* should_delete_property_by_id

This was my first attempt on writing tests for a Java project, so it was great to learn. Running the tests was simple with VSCode.


## Code

### Requirements

* Java JDK 17
* Maven 4.0.0
* Git
* Spring Framework
* SpringBoot

### Build

Clone the repository

    mvn clean install
    
Run PropertyviewerApplication.java 
Open localhost:8080


## Resources I found useful

* https://getbootstrap.com/
* https://github.com/bezkoder/spring-boot-jpa-postgresql
* https://www.geeksforgeeks.org/how-to-call-or-consume-external-api-in-spring-boot/
* https://www.baeldung.com/spring-boot-custom-error-page
* https://www.codejava.net/frameworks/spring-boot/fix-websecurityconfigureradapter-deprecated


## Problems during the task

* Time management: it took some time to commemorate Java syntax, Spring JPA project structure and so on
* Working with the geoapify API was more difficult than I first thought
* project structure (Thymeleaf...) got me a bit confused; wasn't clear where to send what and when
* PostgreSQL implementation _almost_ worked (and has worked before); this time there were errors such as
_On release of batch it still contained JDBC statements_ which I couldn't find a proper solution for
* Heroku build failing, but fixing that does not feel urgent at the moment - the main reason for deployment was to get access to Heroku Postgres


## Ideas for further development

* Project structure could've followed WebServlet-based approach instead of Thymeleaf, but that felt less familiar at the moment of doing the task
* With postgreSQL not working yet, another possibility for database structure could've been i.e. having a property.sqlite file at the root of the project, with JDBC Dao files containing methods for SQL selects, inserts etc. (this would've required more time for refactoring the project structure, Property.java model and so on)
* Better test coverage
* Building a proper REST API for a frontend app and having a user friendly listing, sorting and filtering there instead of at the back




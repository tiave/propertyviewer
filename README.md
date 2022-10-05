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
>     * [Screenshots](#screenshots)
>     * [Features](#features)
>   * [Code](#code)
>     * [Content](#content)
>     * [Requirements](#requirements)
>   * [Resources](#resources-i-found-useful)
>   * [Problems during the task](#problems-during-the-task)


## Usage


### Features

* login
* view
* add
* edit
* delete (admin)
* (get coordinates based on property name once postgreSQL connection is fixed)

### REST

* _/api/allproperties_
* returns a list of all property objects
* doesn't require logging in


## Code

### Requirements

Java JDK 17
Maven 4.0.0
Git
Spring Framework
SpringBoot

### Tests

There are three simple unit tests:

* should_add_property
* should_find_all_properties
* should_delete_property_by_id

This was my first attempt on writing tests for a Java project, so it was great to learn. Running the tests was simple with VSCode.


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

* Time management: took some time to commemorate Java syntax, Spring JPA project structure and so on
* Working with the geoapify API was more difficult than I first thought; especially writing the call (turns out I'm a JS fetch() kind of girl after all)
* project structure (Thymeleaf...) got me a bit confused; wasn't clear where to send what and when
* PostgreSQL implementation almost worked (and has  worked before); this time got errors such as
_On release of batch it still contained JDBC statements_ which I couldn't find a proper solution for
* typically I would go for a friend/colleague for help, but didn't find that appropriate on this case -> endless googling with little progress per hour





# Geolocalization app
Rest Api for tracking system. 
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)

## General info
Project are providing funcionality of collecting records and users on database. 
There is provided basic log in interface to see saved data on end-point /localization/wholeList. Author implemented validation of parameters with errors handling.
To test whole application, there was created unit tests with coverage around 80%.

Application are connecting with postgre database and unit tests with H2 database. To prevent leaked author added DTOs handling. 
	
## Technologies
Project is created with:
* Java SE 8
* Spring boot 2
* PostgreDB
	
## Setup
To run this project it's necessary to create postgre database and fill properties:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/**** - fill **** with name of database, port commonly is 5432
spring.datasource.username=**** - fill **** with user name
spring.datasource.password=**** - fill **** password if necessary

```
Next step is just run project.

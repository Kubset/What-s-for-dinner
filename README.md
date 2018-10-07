# What's-for-dinner
Web-application for planning dinners and shopping lists

## Overview
Whats's for dinner is application for preparing dinners.
With application we can prepare dinners schedule for next week or few specified days.
We can also add or delete every meal in database
There is possibility to edit every meal in database 
(e.g change list of products, change name of meal)
We can generate PDF shopping list of products and PDF list of recipes

** version beta is only show version. It has only one base of meals and products,
in nearest future will be added account feature

## Deployment
https://whats-for-dinner-app.herokuapp.com/

## Architecture
#### Database
![Alt text](diagrams/ERD.png?raw=true "ERD")
- all tables has one sequence
- favourite field is for future use

## Used technologies/skills
- Java 8
- Servlets
- Persistence api
- custom implementation DAO (without Spring/Hibernate)
- custom implementation ORM (manual mapping objects)
- Design patterns(Singleton, Repository)
- Postgres
- Github
- Javascript (ES6 with classes)
- HTML5, Css, Bootstrap4
- JUnit 5
- Maven
- Heroku
- SOLID

## Tests
To run tests You have to create postgres database on own localhost,
scripts for create database are available in src/TEST/resources/database dictionary
You should fill data in database.properties in src/TEST/resources/database.properties
** there are two database.properties file one for deploy and one for test !!

### version 1.0 (beta)
##### added features
- add soup/main dish to database
- during add/edit meal possibility to choose one of present product in database, or just create new product
- edit soup/main dish in database
- delete soup/main dish from database
- generate dinner list for few specified days (e.g monday,friday,sunday)
- edit generated list for next week in real-time
- generate PDF shopping list
- generate PDF recipes list
- form validations
- responsible tables and forms

### version 1.01 (beta)
- add heroku deployment

### version 1.2
- Fix problem with id duplicates in database, now all entities has one sequence
- Fix problem with code duplicate in Criteria package
- Add polish charset handling in responses
- Fix bug connected with delete specified meal from database, now its possible to delete every meal in database
- Change architecture of application, now there is fully separated back-end with front-end
- Make more responsible tables, now long recipe do not cover Components column
- Make more responsible navbar, now its possible to use application on mobile devices
- Make Tests for back-end (services, DAO), now tests are performed on own local database
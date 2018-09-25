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

##Deployment
https://whats-for-dinner-app.herokuapp.com/

## Used technologies
- Java
- Servlets
- Persistence api
- custom implementation DAO (without Spring/Hibernate)
- custom implementation ORM (manual mapping objects)
- Postgres
- Javascript (ES6 with classes)
- HTML5, Css, Bootstrap4
- JUnit 5
- Maven
- Github



### version 1.0 (beta)
##### added features
- add soup/main dish to database
- during add/edit meal possibility to choose one of present product in database, or just create new product
- edit soup/main dish to database
- delete soup/main dish to database
- generate dinner list for few specified days (e.g monday,friday,sunday)
- edit generated list for next week in real-time
- generate PDF shopping list
- generate PDF recipes list
- form validations
- responsible tables and forms

###version 1.01 (beta)
- add heroku deployment


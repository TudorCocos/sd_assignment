# Banking-Service

##Development Requirements
Install:
- Java 8
- mysql 

## Getting started
To start the service you need to:
- import in mysql the startup database defined in the https://github.com/UTCN-SD-PS/banking-service/blob/master/sqlscript/script.sql
- configure the url to the database in https://github.com/UTCN-SD-PS/banking-service/blob/master/src/main/resources/application.properties to go to banking-service-test

## Starting the service
Go to bankingservice folder and execute the following command: 
gradlew.bat bootRun

## Accessing the service swagger:
- http://localhost:8080/swagger-ui.html

employee apis: 
- http://localhost:8080/swagger-ui.html#/Account
- http://localhost:8080/swagger-ui.html#/Client

admin apis:
- http://localhost:8080/swagger-ui.html#/Employee

## Calling the service apis:
- each api call is protected with credentials that are saved in the employee_table of the database

## What you can do:
- perform CRUD operations on Accounts and Clients by being logged in as an EMPLOYEE
- perform CRUD operations Employees if you are logged in as an ADMIN
- as an EMPLOYEE, you can also add or delete accounts that are associated with a client
- execute transfers between accounts and pay bills from one account by also specifying some billing details
- if you are an ADMIN, you can generate a report, in PDF or CSV format, that contains all the activities performed by a given employee in a defined timespace
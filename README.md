# Banking-Service

##Development Requirements
Install:
- Java 8
- mysql 

## Getting started
To start the service you need to:
- import in mysql the startup database defined in the https://github.com/UTCN-SD-PS/banking-service/blob/master/sqlscript/bankingservice.sql
- configure the username and password of your mysql user in https://github.com/UTCN-SD-PS/banking-service/blob/master/src/main/resources/application.properties

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
- each api call is protected with credentials that are configured in https://github.com/UTCN-SD-PS/banking-service/blob/master/src/main/resources/application.properties


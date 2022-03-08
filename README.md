# Drones Task By Abdelrahman Alkholy

# Project Description
Simple Api Project Contains 3 entities ( drone, medication and drone trip ) that allow to register drone , and register medications with crud operation for booth 
and can load drones by medications taking into consideration restrictions in weight and date


### Scheduler
Project Checking periodically for drones has batteries bellow 25% and send err message in console to notify with count

### Programming Language
Java With Spring boot

### Database
Simple in file h2 database



### Guides
to run application you must have installed java with maven

# How To Run
write this commands in terminal
```
mvn clean && mvn package
java -jar target/drone-task-0.0.1.jar
```

to access h2 database console from browser
#### Visit http://localhost:8080/h2-console
username : `sa`
password : `password`



to access Swagger API Documentation From Browser
#### Visit http://localhost:8080/swagger-ui/index.html


**to import api docs into postman**
#### Import This Link Into Postman http://localhost:8080/v3/api-docs


## Important
use date time format in json as **`yyyy-MM-dd HH:mm`**


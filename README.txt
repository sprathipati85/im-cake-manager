
# Cake Manager Micro Service 
===========================
Build the Maven project
mvn clean install

# How it works - Run the im-cake-manager application
java -jar im-cake-manager.jar
(or) Import the application into Intellij/Eclipse and Run the Application.java

The project can be access on http://localhost:8080/

# Run Test & Code Coverage
The repository contains test cases to cover both api test and repository test.
Junit & Spock basked groovy Tests used for Integration

# Rest End Points - currently they are non secure. //TODO- Add OAuth2 secure layer for the endpoints

1. / displays all the list of cakes by its Title

2. /cakes GET Method
    returns the list of all cakes

3. /cakes POST Method
    adds a new cake

# Database

It uses a H2 in memory database (for now), can be updated easily in the `application.properties` for any other database.

# Try it out with Docker

You need Docker installed.
	
	docker-compose up -d


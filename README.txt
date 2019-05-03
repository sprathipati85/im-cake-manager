Cake Manager Micro Service 
===========================
Build the Maven project
mvn clean install

Run the im-cake-manager application
java -jar im-cake-manager.jar
(or) Import the application into Intellij/Eclipse and Run the Application.java

The project can be access on http://localhost:8080/

Code Coverage
==============
Junit & Spock Test for Integration

Rest End Points
================
1. / displays all the list of cakes by its Title

2. /cakes GET Method
    returns the list of all cakes

3. /cakes POST Method
    adds a new cake
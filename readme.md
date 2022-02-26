This file contains the instructions to run the solution. 
Prerequisites:
* Java 
* Maven 

- First Solution:
Json Parsing solution for removing "password" keys from json. Can be tested by accessing http://localhost:4567/jsonparse

- Second Solution:
Scrabble Boggle can be tested by accessing http://localhost:4567/scrabbleboggle. 

- Execution Instructions:
* clone the git repo from the instructions mentioned in the email.
* cd into the repo and run the following command **mvn clean install**. This command will build the solution. 
* Now to to run the solution do **mvn exec:java**. The solution will run on the default port 4567. 
* To test **Json Parse**, from postman select POST operation and add To test with different combinations update *testJsonParse.json* and run below command.
'''
    curl -H "Content-Type: application/json" --data @testJsonParse.json http://localhost:4567/jsonparse
'''
* To test **Scrabble Boggle** from cmd/terminal execute the below command. For the below example the output is 1. To test with different combinations update *testScrabbleBoggle.json* and run below command. 
'''
    curl -H "Content-Type: application/json" --data @testScrabbleBoggle.json http://localhost:4567/scrabbleboggle
'''
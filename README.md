# SpringBootNeo4jIntegration

Follow the following steps :-
1. Setup the neo4j database on your local
Follow the following link for setup :-
https://neo4j.com/docs/operations-manual/current/installation/windows/
2.Import the Maven Project on eclipse
3.If error in building then use settings.xml in the code and place it in the .m2 folder.
4.Change the application.properties file with your neo4j credentials.
5.After the project is setup, start the main class SpringBootNeo4jApplicationStarter.java.
6.Import the springneo4j.postman_collection.json file in your postman.
7.Run the APIS Imported after the application is started.

There are 5 Apis
Create Person, Update Person, Get Persons, Delete Person, Connect 2 Persons
After creating the person,You will see uuid in the response.
This uuid is used for performing operations on other apis.




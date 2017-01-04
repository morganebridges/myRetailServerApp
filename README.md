# myRetailServerApp

In order to use this project, you will need the following applications available through your path:
Gradle 2.3+
MongoDB


To build and launch this project, use this command from the project root directory:
gradle clean build;java -jar build/libs/myRetailApp-0.1.0.jar

<strong>REST API:</strong>
The rest API of this application is made available both through the origin of the server app as well as through a RESTful
communication cross-origin, because CORS is enabled through the Spring-Boot application.
The endpoint specifications follow:
{host}/products/{sequence} :
This endpoint is to load the JSON document that represents a product within the database. the "sequence" field is set
by the Spring application and is a unique integer. It is visibile from client and server applications throughout the system.

{host}/products/getlist

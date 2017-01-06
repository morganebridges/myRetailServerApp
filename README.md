# myRetailApp
This application was developed as a technical sample. The server application itself is created from Spring Boot, the data source is the Spring Boot MongoDB integration. I built the frontend app (which is stored in the /resources/static folder) with AngularJS version 1 and Bootstrap CSS for styling.

In order to use this project, you will need the following applications available through your path:
Gradle 2.9+
MongoDB available through the CLI.


To build and launch this project, use this command from the project root directory:<br>
<strong>mvn clean install;java -jar build/libs/myRetailApp-0.1.0.jar</strong>

<h2><strong>REST API:</strong></h2>
<p>The rest API of this application is made available both through the origin of the server app as well as through a RESTful
communication cross-origin, because CORS is enabled through the Spring-Boot application.
</p>
<h3> JSON Specification </h3>
<p> The services for this application communicate through the default Spring Boot Jackson specification.
Here is an example product:</p>
<div>
  {
    "id" : 1,
    "name" : "Product_Name",
    "price":
      {
        "value" : 14.99,
        "currency_code" : "USD"
      }
  }    
</div>
<p>The endpoint specifications follow:</p>
<strong><h3>{host}/products/{sequence} :</h3></strong>
Http method: GET <br>
<p>This endpoint is to load the JSON document that represents a product within the database. the "sequence" field is set
by the Spring application and is a unique integer. It is visibile from client and server applications throughout the system.
</p>
<strong><h3>{host}/products/getlist :</h3></strong>
Http method: GET<br>
<p>This endpoint exists for the purpose of requesting a full list of products within the system. With the current version of
 the version, no filtering is available, but this feature would certainly be considered for a future improvement. 
</p>
<strong><h3>{host}/products/add </h3></strong>
Http method: POST<br>
<p>This method is for inserting new products into the database from the client application.
</p>
<strong><h3>{host}/products/update </h3></strong>
Http method: PUT<br>
Given a valid existing product Model, this endpoint will update name and price fields for that
instance.


# myRETAILRestCase

myRETAIL Rest Case Study
-------------------------

Dependencies
-------------------------

-maven
-java 8
-mongoDB server

Getting started
-------------------------

1. Build the project with maven.
  a. Go to the project directory where pom.xml is location
  b. Run "mvn install"
2. Start your MongoDB Server
  a. Run mongod.exe under the MongoDB installation bin directory
3. Load your server with data
  a. Connect to your mongo instance through mongo shell
  b. Run "use test" to use the database of test
  c. Run "load("{projectDirectory}/loadMongoDb.js")" to load the database with data
4. Start the server.
  a. Inside the project directory run: java -jar target/RESTCase-1.0.0.jar

Some things to note
------------------------

-The database will only be loaded with pricing data for the following product ids: 15117729, 16483589, 16696652, 16752456, 15643793(this one returns not found in the target API).

Example REST Calls
------------------------

Uses with examples:

GET: http://[hostname]:8080/api/rest/product/{productId}

Retrieve product information and pricing for the specified product id.

Return example:

{
  "id": 16483589,
  "name": "AT&T iPhone 6 plus Gold 128GB",
  "currentPrice": {
    "amount": 99.99,
    "currencyCode": "USD"
  }
}

PUT: http://[hostname]:8080/api/rest/product/{productId}

Update pricing for the product by the product id.

With JSON Body:

{
  "id": 16483589,
  "name": "AT&T iPhone 6 plus Gold 128GB",
  "currentPrice": {
    "amount": 201.99,
    "currencyCode": "USD"
  }
}

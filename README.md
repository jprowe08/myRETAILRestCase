

#myRETAIL Rest Case Study#

The application is currently hosted at http://54.213.53.102:8080

Dependencies
-------------------------

* Maven
* Java 8
* MongoDB server

Getting started
-------------------------

1. Build the project with maven.
  1. Go to the project directory where pom.xml is location
  2. Run "mvn install"
2. Start your MongoDB Server
  1. Run mongod.exe under the MongoDB installation bin directory
3. Load your server with data
  1. Connect to your mongo instance through mongo shell
  2. Run "use test" to use the database of test
  3. Run "load("{projectDirectory}/loadMongoDb.js")" to load the database with data
4. Start the server.
  1. Inside the project directory run: java -jar target/RESTCase-1.0.0.jar

Some things to note
------------------------

* The database will only be loaded with pricing data for the following product ids: 15117729, 16483589, 16696652, 16752456, 15643793(this one returns not found in the target API).

Example REST Calls
------------------------

Uses with examples:

####GET: http://[hostname]:8080/api/rest/product/{productId}####

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

####PUT: http://[hostname]:8080/api/rest/product/{productId}####

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

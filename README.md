# Quarkus Reactive Product Management API

This is a RESTful web service built using Quarkus and Reactive Programming principles. 

---

## Features

- Create, Read, Update, Delete (CRUD) for products
- Check stock availability for a product
- Retrieve products sorted by price
- Unit testing using Quarkus test framework

## Tech Stack

- **Quarkus 3.x**
- **Java**
- **Hibernate Reactive**
- **Mutiny Uni**
- **PostgreSQL (via Reactive Pg Client)**
- **Maven**

---

### Prerequisites

- Java 17+ 
- Maven 3+
- PostgreSQL running 
- Git 

### Project Structure
src/
├── main/
│ ├── java/com/bhavesh/product/
│ │ ├── entity/Product.java
│ │ ├── service/ProductService.java
│ │ └── resource/ProductResource.java
│ └── resources/application.properties
├── test/
│ └── java/com/bhavesh/product/resource/ProductResourceTest.java


### Run the App

1. Clone the repository:

git clone https://github.com/Bhaveshkhot/quarkus-product-api-full
cd quarkus-product-api-full

2. Start the application:

mvn quarkus:dev
Application will run at: http://localhost:8080

###  ALL API endpoints 

- Create Product
POST /products

Request Body:
{
  "name": "Keyboard",
  "description": "Mechanical keyboard",
  "price": 1500,
  "quantity": 10
}


- Get All Products
GET /products


- Get Product by ID
GET /products/{id}


-Update Product
PUT /products/{id}

Request Body:
{
  "name": "Mouse",
  "description": "Wireless Mouse",
  "price": 799,
  "quantity": 5
}


-Delete Product
DELETE /products/{id}

-  Check Stock Availability
GET /products/{id}/stock?count=5

Response :
{
  "available": true
}


-Get Products Sorted by Price
GET /products/sorted


### Running Tests
Run the tests using:
mvn test

/This runs unit tests from ProductResourceTest.java

### Test Coverage
Covered scenarios:

Create product
Get all products
Get product by ID
Update product
Delete product
Stock availability check
Sorted listing

## 📸 Screenshots

Application Startup
(screenshots/startup.png)

Create Product (POST)
screenshots/post-create.png)

Get All Products (GET)
(screenshots/get-all.png)

Get Product by ID (GET)
(screenshots/get-by-id.png)

Update Product (PUT)
(screenshots/put-update.png)

Stock Check
(screenshots/stock-check.png)

Sorted Products
(screenshots/sorted.png)

Unit Test Results
(screenshots/tests.png)
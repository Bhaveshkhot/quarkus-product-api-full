package com.bhavesh.product;

import com.bhavesh.product.entity.Product;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testCreateProduct() {
        Product product = new Product();
        product.name = "Laptop";
        product.description = "Gaming laptop";
        product.price = 75000.0;
        product.quantity = 10;

        given()
            .contentType(ContentType.JSON)
            .body(product)
        .when()
            .post("/products")
        .then()
            .statusCode(200)
            .body("name", equalTo("Laptop"));
    }

    @Test
    public void testGetAllProducts() {
        when()
            .get("/products")
        .then()
            .statusCode(200);
    }
}
package com.bhavesh.product.resource;

import com.bhavesh.product.entity.Product;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testCreateProduct() {
        given()
            .contentType(ContentType.JSON)
            .body("{\"name\":\"Mouse\",\"description\":\"Wireless Mouse\",\"price\":500,\"quantity\":5}")
        .when()
            .post("/products")
        .then()
            .statusCode(200)
            .body("name", is("Mouse"))
            .body("price", is(500f));
    }

    @Test
    public void testGetAllProducts() {
        given()
        .when()
            .get("/products")
        .then()
            .statusCode(200);
    }

    @Test
    public void testGetProductByIdNotFound() {
        given()
        .when()
            .get("/products/9999")
        .then()
            .statusCode(404);
    }
}

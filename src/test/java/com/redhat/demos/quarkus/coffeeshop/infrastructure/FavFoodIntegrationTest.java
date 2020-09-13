package com.redhat.demos.quarkus.coffeeshop.infrastructure;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FavFoodIntegrationTest {

    @Test
    public void testPostFavFoodOrder() {
        given()
                .when()
                .post("/api/favfood")
                .then()
                .statusCode(202);
    }

}

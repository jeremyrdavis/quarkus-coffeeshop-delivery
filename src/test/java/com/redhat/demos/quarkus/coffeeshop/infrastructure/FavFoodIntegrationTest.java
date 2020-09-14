package com.redhat.demos.quarkus.coffeeshop.infrastructure;

import com.redhat.demos.quarkus.coffeeshop.domain.favfood.FavFoodLineItem;
import com.redhat.demos.quarkus.coffeeshop.domain.favfood.FavFoodOrder;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class FavFoodIntegrationTest {

    @Test
    public void testPostFavFoodOrder() {
        given()
                .when()
                .contentType(ContentType.JSON)
                .body(mockFavFoodOrder())
                .post("/api/favfood")
                .then()
                .statusCode(202);
    }

    private FavFoodOrder mockFavFoodOrder() {
        FavFoodOrder favFoodOrder = new FavFoodOrder();
        favFoodOrder.setCustomerName("Lemmy");
        favFoodOrder.setLineItems(mockFavFoodLineItems());
        return favFoodOrder;
    }

    private List<FavFoodLineItem> mockFavFoodLineItems() {
        FavFoodLineItem favFoodLineItem = new FavFoodLineItem();
        favFoodLineItem.setItem("ESPRESSO_DOUBLE");
        favFoodLineItem.setQuantity(1);
        favFoodLineItem.setItemId(UUID.randomUUID().toString());
        return Arrays.asList(favFoodLineItem);
    }

}

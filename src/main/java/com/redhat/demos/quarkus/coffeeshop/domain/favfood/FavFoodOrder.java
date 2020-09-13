package com.redhat.demos.quarkus.coffeeshop.domain.favfood;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;

@RegisterForReflection
public class FavFoodOrder {

    String id;

    static final String origin = "FavFood";

    String customerName;

    List<FavFoodLineItem> lineItems;

    public static FavFoodOrder createFavFoodOrder(String customerName, List<FavFoodLineItem> lineItems) {
        FavFoodOrder favFoodOrder = new FavFoodOrder();
        favFoodOrder.customerName = customerName;
        favFoodOrder.lineItems = lineItems;
        return favFoodOrder;
    }

    public FavFoodOrder() {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FavFoodOrder.class.getSimpleName() + "[", "]")
                .add("id='" + id + "'")
                .add("customerName='" + customerName + "'")
                .add("lineItems=" + lineItems)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavFoodOrder that = (FavFoodOrder) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(customerName, that.customerName) &&
                Objects.equals(lineItems, that.lineItems);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerName, lineItems);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getOrigin() {
        return origin;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<FavFoodLineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<FavFoodLineItem> lineItems) {
        this.lineItems = lineItems;
    }
}

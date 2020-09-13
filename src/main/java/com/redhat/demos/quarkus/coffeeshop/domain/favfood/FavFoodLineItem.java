package com.redhat.demos.quarkus.coffeeshop.domain.favfood;

import io.quarkus.runtime.annotations.RegisterForReflection;

import java.util.Objects;
import java.util.StringJoiner;
import java.util.UUID;

@RegisterForReflection
public class FavFoodLineItem {

    String itemId;

    String item;

    int quantity;

    public static FavFoodLineItem createLineItem(String item, int quantity) {
        FavFoodLineItem lineItem = new FavFoodLineItem();
        lineItem.itemId = UUID.randomUUID().toString();
        lineItem.item = item;
        lineItem.quantity = quantity;
        return lineItem;
    }

    public FavFoodLineItem() {
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", FavFoodLineItem.class.getSimpleName() + "[", "]")
                .add("itemId='" + itemId + "'")
                .add("item='" + item + "'")
                .add("quantity=" + quantity)
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FavFoodLineItem that = (FavFoodLineItem) o;
        return quantity == that.quantity &&
                Objects.equals(itemId, that.itemId) &&
                Objects.equals(item, that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, item, quantity);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

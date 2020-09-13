package com.redhat.demos.quarkus.coffeeshop.domain;

import com.redhat.demos.quarkus.coffeeshop.domain.favfood.FavFoodOrder;
import com.redhat.quarkus.cafe.domain.Item;
import com.redhat.quarkus.cafe.domain.LineItem;
import com.redhat.quarkus.cafe.domain.OrderInCommand;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import java.util.HashSet;
import java.util.Set;

@ApplicationScoped
public class Order {

    Set<String> beverages;

    Set<String> foods;

    @PostConstruct
    void initializeMenu() {
        beverages = new HashSet<>();
        beverages.add("BLACK_COFFEE");
        beverages.add("COFFEE_WITH_ROOM");
        beverages.add("ESPRESSO");
        beverages.add("DOUBLE_ESPRESSO");
        beverages.add("LATTE");

        foods = new HashSet<>();
        foods.add("CAKEPOP");
        foods.add("CROISSANT");
        foods.add("MUFFIN");
        foods.add("CROISSANT_CHOCOLATE");
    }


    public OrderInCommand processFavFoodOrder(FavFoodOrder favFoodOrder) {

        OrderInCommand orderInCommand = new OrderInCommand();
        orderInCommand.id = favFoodOrder.getId();

        favFoodOrder.getLineItems().forEach(favFoodLineItem -> {
            if (beverages.contains(favFoodLineItem.getItem())) {
                orderInCommand.getBeverages().add(
                        new LineItem(
                                    translateFavFoodLineItem(favFoodLineItem.getItem()),
                                    favFoodOrder.getCustomerName()));
            }else if(foods.contains(favFoodLineItem.getItem())){
                orderInCommand.getKitchenOrders().add(
                        new LineItem(
                                translateFavFoodLineItem(favFoodLineItem.getItem()),
                                favFoodOrder.getCustomerName()));
            }
        });
        return orderInCommand;
    }

    Item translateFavFoodLineItem(String favFoodLineItem) {
        switch (favFoodLineItem) {
            case "COFFEE_BLACK":
                return Item.COFFEE_BLACK;
            case "COFFEE_WITH_ROOM":
                return Item.COFFEE_WITH_ROOM;
            case "CAPPUCCINO":
                return Item.CAPPUCCINO;
            case "ESPRESSO":
                return Item.ESPRESSO;
            case "ESPRESSO_DOUBLE":
                return Item.ESPRESSO_DOUBLE;
            case "CAKEPOP":
                return Item.CAKEPOP;
            case "CROISSANT":
                return Item.CROISSANT;
            case "CROISSANT_CHOCOLATE":
                return Item.CROISSANT_CHOCOLATE;
            case "MUFFIN":
                return Item.MUFFIN;
            default:
                return Item.ESPRESSO;
        }
    }
}

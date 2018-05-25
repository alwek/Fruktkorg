package com.alican.controller;

import com.alican.model.Basket;
import com.alican.model.Fruit;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
public class BasketControllerTest {
    private static BasketController basketController = new BasketController();

    @Test
    public void getBasketsTest(){
        for(Basket basket : basketController.getBaskets()){
            System.out.println(basket.getLocation().getLocationId() + " " + basket.getLocation().getLocationName());
            System.out.println(basket.getBasketId() + " " + basket.getBasketName());
            for(Fruit fruit : basket.getFruits()){
                System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
            }//for fruit
        }
    }

    @Test
    public void getBasketsFromLocationTest(){
        String name = "Solna 1A";
        for(Basket basket : basketController.getBaskets(name)){
            System.out.println(basket.getLocation().getLocationId() + " " + basket.getLocation().getLocationName());
            System.out.println(basket.getBasketId() + " " + basket.getBasketName());
            for(Fruit fruit : basket.getFruits()){
                System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
            }//for fruit
        }
    }

    @Test
    public void getBasket(){
        String locationName = "Solna 1A";
        String basketName = "Korg 1";
        Basket basket = basketController.getBasket(basketName, locationName);

        System.out.println(basket.getLocation().getLocationId() + " " + basket.getLocation().getLocationName());
        System.out.println(basket.getBasketId() + " " + basket.getBasketName());
        for(Fruit fruit : basket.getFruits()){
            System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
        }
    }

    @Test
    public void addBasket(){
        boolean isSuccess = basketController.addBasket("Korg 3", "Solna 1A");
        Assert.assertEquals(true, isSuccess);
    }

    @Test
    public void removeBasket(){
        boolean isSuccess = basketController.removeBasket("Korg 1", "Solna 1A");
        Assert.assertEquals(true, isSuccess);
    }
}

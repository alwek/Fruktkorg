package com.alican.controller;

import com.alican.model.Fruit;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
public class FruitControllerTest {
    private static FruitController fruitController = new FruitController();

    @Test
    public void getFruits(){
        for(Fruit fruit : fruitController.getFruits()){
            System.out.println(fruit.getBasket().getLocation().getLocationId() + " " + fruit.getBasket().getLocation().getLocationName());
            System.out.println(fruit.getBasket().getBasketId() + " " + fruit.getBasket().getBasketName());
            System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
        }
    }

    @Test
    public void getFruitsFromBasketTest(){
        String locationName = "Solna 1A";
        String basketName = "Korg 1";
        for(Fruit fruit : fruitController.getFruits(basketName, locationName)){
            System.out.println(fruit.getBasket().getLocation().getLocationId() + " " + fruit.getBasket().getLocation().getLocationName());
            System.out.println(fruit.getBasket().getBasketId() + " " + fruit.getBasket().getBasketName());
            System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
        }
    }

    @Test
    public void getFruitsFromLocationTest(){
        String locationName = "Solna 1A";
        for(Fruit fruit : fruitController.getFruits(locationName)){
            System.out.println(fruit.getBasket().getLocation().getLocationId() + " " + fruit.getBasket().getLocation().getLocationName());
            System.out.println(fruit.getBasket().getBasketId() + " " + fruit.getBasket().getBasketName());
            System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
        }
    }

    @Test
    public void getFruitTest(){
        String locationName = "Solna 1A";
        String basketName = "Korg 1";
        String fruitName = "Banana";
        Fruit fruit = fruitController.getFruit(fruitName, basketName, locationName);
        System.out.println(fruit.getBasket().getLocation().getLocationId() + " " + fruit.getBasket().getLocation().getLocationName());
        System.out.println(fruit.getBasket().getBasketId() + " " + fruit.getBasket().getBasketName());
        System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
    }

    @Test
    public void addFruitTest(){
        boolean isSuccess = fruitController.addFruit("Banana", "Korg 1", "Solna 1A");
        Assert.assertEquals(true, isSuccess);
    }

    @Test
    public void removeFruitTest(){
        boolean isSuccess = fruitController.removeFruit("Banana", "Korg 1", "Solna 1A");
        Assert.assertEquals(true, isSuccess);
    }
}

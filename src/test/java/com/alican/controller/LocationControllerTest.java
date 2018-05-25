package com.alican.controller;

import com.alican.model.Basket;
import com.alican.model.Fruit;
import com.alican.model.Location;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
public class LocationControllerTest {
    private static LocationController locationController = new LocationController();

    @Test
    public void getLocationsTest(){
        for(Location location : locationController.getLocations()){
            System.out.println(location.getLocationId() + " " + location.getLocationName());
            for(Basket basket : location.getBaskets()){
                System.out.println(basket.getBasketId() + " " + basket.getBasketName());
                for(Fruit fruit : basket.getFruits()){
                    System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
                }//for fruit
            }//for basket
        }//for location
    }

    @Test
    public void getLocationTest(){
        String name = "Solna 1A";
        Location location = locationController.getLocation(name);

        System.out.println(location.getLocationId() + " " + location.getLocationName());
        for(Basket basket : location.getBaskets()){
            System.out.println(basket.getBasketId() + " " + basket.getBasketName());
            for(Fruit fruit : basket.getFruits()){
                System.out.println(fruit.getFruitId() + " " + fruit.getFruitName());
            }
        }
    }

    @Test
    public void addLocationTest(){
        boolean isSuccess = locationController.addLocation("Solna 1C");
        Assert.assertEquals(true, isSuccess);
    }

    @Test
    public void removeLocationTest(){
        boolean isSuccess = locationController.removeLocation("Solna 1A");
        Assert.assertEquals(true, isSuccess);
    }

}

package com.alican.controller;

import com.alican.dbmanager.FruitDbManager;
import com.alican.model.Fruit;
import java.util.Collection;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
public class FruitController {
    /**
     * Get all available Fruits.
     * @return
     */
    public Collection<Fruit> getFruits(){
        return FruitDbManager.getFruits();
    }

    /**
     * Get all available Fruits at specified Location.
     * @param locationName name of the Location.
     * @return Collection<Fruit>
     */
    public Collection<Fruit> getFruits(String locationName){
        return FruitDbManager.getFruits(locationName.toUpperCase());
    }

    /**
     * Get all available Fruits in Basket at specified Location
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return Collection<Fruit>
     */
    public Collection<Fruit> getFruits(String basketName, String locationName){
        return FruitDbManager.getFruits(basketName.toUpperCase(), locationName.toUpperCase());
    }

    /**
     * Get specific Fruit in a Basket at a Location.
     * @param fruitName name of the Fruit.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return Fruit
     */
    public Fruit getFruit(String fruitName, String basketName, String locationName){
        return FruitDbManager.getFruit(fruitName.toUpperCase(), basketName.toUpperCase(), locationName.toUpperCase());
    }

    /**
     * Add a Fruit into a Basket at a Location
     * @param fruitName name of the Fruit.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return boolean
     */
    public boolean addFruit(String fruitName, String basketName, String locationName){
        return FruitDbManager.addFruit(fruitName.toUpperCase(), basketName.toUpperCase(), locationName.toUpperCase());
    }

    /**
     * Remove a Fruit from a Basket at a Location
     * @param fruitName name of the Fruit.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return boolean
     */
    public boolean removeFruit(String fruitName, String basketName, String locationName){
        return FruitDbManager.removeFruit(fruitName.toUpperCase(), basketName.toUpperCase(), locationName.toUpperCase());
    }
}

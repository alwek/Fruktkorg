package com.alican.controller;

import com.alican.dbmanager.BasketDbManager;
import com.alican.model.Basket;
import java.util.Collection;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
public class BasketController {
    /**
     * Get all available Baskets.
     * @return Collection<Basket>
     */
    public Collection<Basket> getBaskets(){
        return BasketDbManager.getBaskets();
    }

    /**
     * Get all available Baskets at specified Location.
     * @param locationName name of the Location.
     * @return Collection<Basket>
     */
    public Collection<Basket> getBaskets(String locationName){
        return BasketDbManager.getBaskets(locationName.toUpperCase());
    }

    /**
     * Get specific Basket.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return Basket
     */
    public Basket getBasket(String basketName, String locationName){
        return BasketDbManager.getBasket(basketName.toUpperCase(), locationName.toUpperCase());
    }

    /**
     * Add a Basket at a Location.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return boolean
     */
    public boolean addBasket(String basketName, String locationName){
        return BasketDbManager.addBasket(basketName.toUpperCase(), locationName.toUpperCase());
    }

    /**
     * Remove a Basket at a Location.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return boolean
     */
    public boolean removeBasket(String basketName, String locationName){
        return BasketDbManager.removeBasket(basketName.toUpperCase(), locationName.toUpperCase());
    }
}

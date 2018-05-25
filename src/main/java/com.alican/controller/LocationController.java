package com.alican.controller;

import com.alican.dbmanager.LocationDbManager;
import com.alican.model.Location;
import java.util.Collection;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
public class LocationController {
    /**
     * Get all available Locations.
     * @return Collection<Location>
     */
    public Collection<Location> getLocations(){
        return LocationDbManager.getLocations();
    }

    /**
     * Get a specific Location.
     * @param locationName name of the Location.
     * @return Location
     */
    public Location getLocation(String locationName){
        return LocationDbManager.getLocation(locationName.toUpperCase());
    }

    /**
     * Add a Location.
     * @param locationName name of the Location.
     * @return boolean
     */
    public boolean addLocation(String locationName){
        return LocationDbManager.addLocation(locationName.toUpperCase());
    }

    /**
     * Remove a Location.
     * @param locationName name of the Location.
     * @return boolean
     */
    public boolean removeLocation(String locationName){
        return LocationDbManager.removeLocation(locationName.toUpperCase());
    }
}

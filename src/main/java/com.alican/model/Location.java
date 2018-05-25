package com.alican.model;

import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
@Entity
@Table(name = "locations")
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locationId")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private int locationId;

    @Column(name = "locationName")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private String locationName;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Basket> baskets = new ArrayList<>();

    public Location(){}

    public Location(int locationId, String locationName, Collection<Basket> baskets) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.baskets = baskets;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public Collection<Basket> getBaskets() {
        return baskets;
    }

    public void setBaskets(Collection<Basket> baskets) {
        this.baskets = baskets;
    }
}

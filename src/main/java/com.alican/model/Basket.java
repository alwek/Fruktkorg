package com.alican.model;

import org.hibernate.annotations.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alican Bircan on 2018-05-14
 * Good luck, Commander!
 */
@Entity
@Table(name = "baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "basketId")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private int basketId;

    @Column(name = "basketName")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private String basketName;

    @OneToMany(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @LazyCollection(LazyCollectionOption.FALSE)
    @Fetch(value = FetchMode.SUBSELECT)
    private Collection<Fruit> fruits = new ArrayList<>();

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private int locationId;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Location location;

    public Basket(){}

    public Basket(int basketId, String basketName, Collection<Fruit> fruits, int locationId, Location location) {
        this.basketId = basketId;
        this.basketName = basketName;
        this.fruits = fruits;
        this.locationId = locationId;
        this.location = location;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public String getBasketName() {
        return basketName;
    }

    public void setBasketName(String basketName) {
        this.basketName = basketName;
    }

    public Collection<Fruit> getFruits() {
        return fruits;
    }

    public void setFruits(Collection<Fruit> fruits) {
        this.fruits = fruits;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}

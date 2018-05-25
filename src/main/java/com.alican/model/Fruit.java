package com.alican.model;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;

/**
 * Created by Alican Bircan on 2018-05-14
 * Good luck, Commander!
 */
@Entity
@Table(name = "fruits")
public class Fruit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fruitId")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private int fruitId;

    @Column(name = "fruitName")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private String fruitName;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private int basketId;

    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private Basket basket;

    public Fruit(){}

    public Fruit(int fruitId, String fruitName, int basketId, Basket basket) {
        this.fruitId = fruitId;
        this.fruitName = fruitName;
        this.basketId = basketId;
        this.basket = basket;
    }

    public int getFruitId() {
        return fruitId;
    }

    public void setFruitId(int fruitId) {
        this.fruitId = fruitId;
    }

    public String getFruitName() {
        return fruitName;
    }

    public void setFruitName(String fruitName) {
        this.fruitName = fruitName;
    }

    public int getBasketId() {
        return basketId;
    }

    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}

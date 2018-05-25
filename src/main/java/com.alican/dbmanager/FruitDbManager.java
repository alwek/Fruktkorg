package com.alican.dbmanager;

import com.alican.model.Basket;
import com.alican.model.Fruit;
import com.alican.model.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by Alican Bircan on 2018-05-24
 * Good luck, Commander!
 */
public class FruitDbManager {
    private static Session sessionObj;

    /**
     * Get all available Fruits.
     * @return Collection<Fruit>
     */
    public static Collection<Fruit> getFruits(){
        Collection<Fruit> fruits = null;
        try{
            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            fruits = sessionObj.createQuery("from Fruit").list();

            sessionObj.getTransaction().commit();
        }
        catch(HibernateException hibernateException){
            hibernateException.printStackTrace();
        }
        catch (Exception e){
            if(null != sessionObj.getTransaction())
                sessionObj.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if(sessionObj != null)
                sessionObj.close();
        }

        return fruits;
    }

    /**
     * Get all available Fruits at specified Location.
     * @param locationName name of the location.
     * @return Collection<Fruit>
     */
    public static Collection<Fruit> getFruits(String locationName){
        Collection<Fruit> fruits = null;
        try{
            Location location = LocationDbManager.getLocation(locationName);
            Collection<Basket> baskets = BasketDbManager.getBaskets(location.getLocationName());
            ArrayList<Integer> basketIds = new ArrayList<>();
            for(Basket basket : baskets){
                basketIds.add(basket.getBasketId());
            }

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            fruits = sessionObj.createQuery("from Fruit where basketId in(:ids)")
                    .setParameter("ids", basketIds)
                    .list();

            sessionObj.getTransaction().commit();
        }
        catch(HibernateException hibernateException){
            hibernateException.printStackTrace();
        }
        catch (Exception e){
            if(null != sessionObj.getTransaction())
                sessionObj.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if(sessionObj != null)
                sessionObj.close();
        }

        return fruits;
    }

    /**
     * Get all available Fruits in a Basket at specified Location.
     * @param basketName name of the basket.
     * @param locationName name of the location.
     * @return Collection<Fruit>
     */
    public static Collection<Fruit> getFruits(String basketName, String locationName){
        Collection<Fruit> fruits = null;
        try{
            Location location = LocationDbManager.getLocation(locationName);
            Basket basket = BasketDbManager.getBasket(basketName, location.getLocationName());

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            fruits = sessionObj.createQuery("from Fruit where basketId = :basketId")
                    .setParameter("basketId", basket.getBasketId())
                    .list();

            sessionObj.getTransaction().commit();
        }
        catch(HibernateException hibernateException){
            hibernateException.printStackTrace();
        }
        catch (Exception e){
            if(null != sessionObj.getTransaction())
                sessionObj.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if(sessionObj != null)
                sessionObj.close();
        }

        return fruits;
    }

    /**
     * Get specific Fruit.
     * @param fruitName name of the fruit.
     * @param basketName name of the basket.
     * @param locationName name of the location.
     * @return Fruit
     */
    public static Fruit getFruit(String fruitName, String basketName, String locationName){
        Fruit fruit = null;
        try{
            Location location = LocationDbManager.getLocation(locationName);
            Basket basket = BasketDbManager.getBasket(basketName, location.getLocationName());

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            fruit = (Fruit) sessionObj.createQuery("from Fruit where basketId = :basketId and fruitName = :fruitName")
                    .setParameter("basketId", basket.getBasketId())
                    .setParameter("fruitName", fruitName)
                    .list()
                    .get(0);

            sessionObj.getTransaction().commit();
        }
        catch(HibernateException hibernateException){
            hibernateException.printStackTrace();
        }
        catch (Exception e){
            if(null != sessionObj.getTransaction())
                sessionObj.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if(sessionObj != null)
                sessionObj.close();
        }

        return fruit;
    }

    /**
     * Add a Fruit into a Basket at a Location.
     * @param fruitName name of the Fruit.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return boolean
     */
    public static boolean addFruit(String fruitName, String basketName, String locationName){
        boolean isSuccess = true;
        try{
            Location location = LocationDbManager.getLocation(locationName);
            Basket basket = BasketDbManager.getBasket(basketName, location.getLocationName());

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();;

            Fruit fruit = new Fruit();
            fruit.setFruitName(fruitName);
            fruit.setBasket(basket);
            fruit.setBasketId(basket.getBasketId());

            sessionObj.save(fruit);
            sessionObj.getTransaction().commit();
        }
        catch(HibernateException hibernateException){
            isSuccess = false;
            hibernateException.printStackTrace();
        }
        catch (Exception e){
            isSuccess = false;
            if(null != sessionObj.getTransaction())
                sessionObj.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if(sessionObj != null)
                sessionObj.close();
        }

        return isSuccess;
    }

    /**
     * Remove a Fruit in a Basket at a Location.
     * @param fruitName name of the Fruit.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return boolean
     */
    public static boolean removeFruit(String fruitName, String basketName, String locationName){
        boolean isSuccess = true;
        try{
            Fruit fruit = getFruit(fruitName, basketName, locationName);

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            sessionObj.createQuery("delete from Fruit where fruitId = :fruitId")
                    .setParameter("fruitId", fruit.getFruitId())
                    .executeUpdate();

            //sessionObj.remove(fruit);
            sessionObj.getTransaction().commit();
        }
        catch(HibernateException hibernateException){
            isSuccess = false;
            hibernateException.printStackTrace();
        }
        catch (Exception e){
            isSuccess = false;
            if(null != sessionObj.getTransaction())
                sessionObj.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if(sessionObj != null)
                sessionObj.close();
        }

        return isSuccess;
    }
}

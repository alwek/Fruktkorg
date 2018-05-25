package com.alican.dbmanager;

import com.alican.model.Basket;
import com.alican.model.Fruit;
import com.alican.model.Location;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import java.util.Collection;

/**
 * Created by Alican Bircan on 2018-05-24
 * Good luck, Commander!
 */
public class BasketDbManager {
    private static Session sessionObj;

    /**
     * Get all available Baskets.
     * @return Collection<Basket>
     */
    public static Collection<Basket> getBaskets(){
        Collection<Basket> baskets = null;
        try{
            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            baskets = sessionObj.createQuery("from Basket").list();

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

        return baskets;
    }

    /**
     * Get all available Baskets at specified Location.
     * @param locationName name of the Location.
     * @return Collection<Basket>
     */
    public static Collection<Basket> getBaskets(String locationName){
        Collection<Basket> baskets = null;
        try{
            Location location = LocationDbManager.getLocation(locationName);

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            baskets = sessionObj.createQuery("from Basket where locationId = :locationId")
                    .setParameter("locationId", location.getLocationId())
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

        return baskets;
    }

    /**
     * Get specific Basket.
     * @param basketName name of the basket.
     * @param locationName name of the location.
     * @return Basket
     */
    public static Basket getBasket(String basketName, String locationName){
        Basket basket = null;
        try{
            Location location = LocationDbManager.getLocation(locationName);

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            basket = (Basket) sessionObj.createQuery("from Basket where locationId = :locationId and basketName = :basketName")
                    .setParameter("locationId", location.getLocationId())
                    .setParameter("basketName", basketName)
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

        return basket;
    }

    /**
     * Add a Basket to a Location.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return boolean
     */
    public static boolean addBasket(String basketName, String locationName){
        boolean isSuccess = true;
        try{
            Location location = LocationDbManager.getLocation(locationName);

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();;

            Basket basket = new Basket();
            basket.setBasketName(basketName);
            basket.setLocation(location);
            basket.setLocationId(location.getLocationId());

            sessionObj.save(basket);
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
     * Remove a Basket at a Location.
     * @param basketName name of the Basket.
     * @param locationName name of the Location.
     * @return boolean
     */
    public static boolean removeBasket(String basketName, String locationName){
        boolean isSuccess = true;
        try{
            Basket basket = getBasket(basketName, locationName);

            //We first need to remove the baskets's foreign key references in fruits
            for(Fruit fruit : basket.getFruits()){
                if(!FruitDbManager.removeFruit(fruit.getFruitName(), basket.getBasketName(), locationName))
                    throw new Exception("Could not delete fruit");
            }

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            sessionObj.createQuery("delete from Basket where basketId = :basketId")
                    .setParameter("basketId", basket.getBasketId())
                    .executeUpdate();

            //sessionObj.remove(basket);
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

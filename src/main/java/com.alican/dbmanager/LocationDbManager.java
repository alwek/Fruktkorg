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
public class LocationDbManager {
    private static Session sessionObj;

    /**
     * Get all available Locations.
     * @return Collection<Location>
     */
    public static Collection<Location> getLocations(){
        Collection<Location> locations = null;
        try{
            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            locations = sessionObj.createQuery("from Location").list();

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

        return locations;
    }

    /**
     * Get specific Location.
     * @param locationName name of the location.
     * @return Location
     */
    public static Location getLocation(String locationName){
        Location location = null;
        try{
            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            location = (Location) sessionObj.createQuery("from Location where locationName = :locationName")
                    .setParameter("locationName", locationName)
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

        return location;
    }

    /**
     * Add a Location.
     * @param locationName name of the Location.
     * @return boolean
     */
    public static boolean addLocation(String locationName){
        boolean isSuccess = true;
        try{
            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            Location location = new Location();
            location.setLocationName(locationName);
            sessionObj.save(location);

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
     * Remove a Location.
     * @param locationName name of the Location.
     * @return boolean
     */
    public static boolean removeLocation(String locationName){
        boolean isSuccess = true;
        try{
            Location location = getLocation(locationName);

            //We first need to remove the location's foreign key references in basket and fruits.
            for(Basket basket : location.getBaskets()){
                for(Fruit fruit : basket.getFruits()){
                    if(!FruitDbManager.removeFruit(fruit.getFruitName(), basket.getBasketName(), locationName))
                        throw new Exception("Could not delete fruit");
                }
                if(!BasketDbManager.removeBasket(basket.getBasketName(), locationName))
                    throw new Exception("Could not delete basket");
            }

            sessionObj = DatabaseUtil.buildSessionFactory().openSession();
            sessionObj.beginTransaction();

            sessionObj.remove(location);
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

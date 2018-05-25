package com.alican.dbmanager;

import com.alican.model.Basket;
import com.alican.model.Fruit;
import com.alican.model.Location;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
public class DatabaseUtil {
    /**
     * Build the session's factory.
     * @return SessionFactory
     */
    protected static SessionFactory buildSessionFactory(){
        //Creating configuration instance & passing hibernate configuration file
        Configuration configObj = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Fruit.class)
                .addAnnotatedClass(Basket.class)
                .addAnnotatedClass(Location.class);
                //.addResource("src/main/resources/Location.hbm.xml")
                //.addResource("src/main/resources/Basket.hbm.xml")
                //.addResource("src/main/resources/Fruit.hbm.xml");

        //Since hibernate version 4, ServiceRegistry is being used
        ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
                .applySettings(configObj.getProperties())
                .build();

        //Creating hibernate SessionFactory instance
        return configObj.buildSessionFactory(serviceRegistryObj);
    }
}

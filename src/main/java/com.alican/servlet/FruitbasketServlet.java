package com.alican.servlet;

import com.alican.controller.BasketController;
import com.alican.controller.FruitController;
import com.alican.controller.LocationController;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by Alican Bircan on 2018-05-17
 * Good luck, Commander!
 */
public class FruitbasketServlet extends HttpServlet{
    private static FruitController fruitController = new FruitController();
    private static BasketController basketController = new BasketController();
    private static LocationController locationController = new LocationController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>EVRY Fruktkorg Servlet</h1>");
        out.println("</body>");
        out.println("</html>");
        if(request.getParameterNames().hasMoreElements())
            handleRequests(request, out);
    }

    private void handleRequests(HttpServletRequest request, PrintWriter out){
        Enumeration<String> parameterNames = request.getParameterNames();

        while(parameterNames.hasMoreElements()){
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);

            if(paramName.equalsIgnoreCase("getLocations") && paramValues[0].isEmpty())
                getLocations(out);
            else if(paramName.equalsIgnoreCase("getLocations") && paramValues.length == 1)
                getLocation(out, paramValues[0]);
            else if(paramName.equalsIgnoreCase("addLocation") && paramValues.length == 1)
                addLocation(out, paramValues[0]);
            else if(paramName.equalsIgnoreCase("removeLocation") && paramValues.length == 1)
                removeLocation(out, paramValues[0]);
            else if(paramName.equalsIgnoreCase("getBaskets") && paramValues[0].isEmpty())
                getBaskets(out);
            else if(paramName.equalsIgnoreCase("getBaskets") && paramValues.length == 1)
                getBaskets(out, paramValues[0]);
            else if(paramName.equalsIgnoreCase("getBasket") && paramValues.length == 2)
                getBasket(out, paramValues[0], paramValues[1]);
            else if(paramName.equalsIgnoreCase("addBasket") && paramValues.length == 2)
                addBasket(out, paramValues[0], paramValues[1]);
            else if(paramName.equalsIgnoreCase("removeBasket") && paramValues.length == 2)
                removeBasket(out, paramValues[0], paramValues[1]);
            else if(paramName.equalsIgnoreCase("getFruits") && paramValues[0].isEmpty())
                getFruits(out);
            else if(paramName.equalsIgnoreCase("getFruits") && paramValues.length == 1)
                getFruits(out, paramValues[0]);
            else if(paramName.equalsIgnoreCase("getFruits") && paramValues.length == 2)
                getFruits(out, paramValues[0], paramValues[1]);
            else if(paramName.equalsIgnoreCase("getFruit") && paramValues.length == 3)
                getFruit(out, paramValues[0], paramValues[1], paramValues[2]);
            else if(paramName.equalsIgnoreCase("addFruit") && paramValues.length == 3)
                addFruit(out, paramValues[0], paramValues[1], paramValues[2]);
            else if(paramName.equalsIgnoreCase("removeFruit") && paramValues.length == 3)
                removeFruit(out, paramValues[0], paramValues[1], paramValues[2]);
            else
                out.println("Found no method with that name");
        }
    }

    //LOCATIONS
    private void getLocations(PrintWriter out){
        locationController.getLocations().forEach(l ->
                out.println(l.getLocationName()));
    }

    private void getLocation(PrintWriter out, String locationName){
        out.println(locationController.getLocation(locationName).getLocationName());
    }

    private void addLocation(PrintWriter out, String locationName){
        out.println(locationController.addLocation(locationName));
    }

    private void removeLocation(PrintWriter out, String locationName){
        out.println(locationController.removeLocation(locationName));
    }

    //BASKETS
    private void getBaskets(PrintWriter out){
        basketController.getBaskets().forEach(b ->
                out.println(b.getBasketName()));
    }

    private void getBaskets(PrintWriter out, String locationName){
        basketController.getBaskets(locationName).forEach(b ->
                out.println(b.getBasketName()));
    }

    private void getBasket(PrintWriter out, String basketName, String locationName){
        out.println(basketController.getBasket(basketName, locationName).getBasketName());
    }

    private void addBasket(PrintWriter out, String basketName, String locationName){
        out.println(basketController.addBasket(basketName, locationName));
    }

    private void removeBasket(PrintWriter out, String basketName, String locationName){
        out.println(basketController.removeBasket(basketName, locationName));
    }

    //FRUITS
    private void getFruits(PrintWriter out){
        fruitController.getFruits().forEach(f ->
                out.println(f.getFruitName()));
    }

    private void getFruits(PrintWriter out, String locationName){
        fruitController.getFruits(locationName).forEach(f ->
                out.println(f.getFruitName()));
    }

    private void getFruits(PrintWriter out, String basketName, String locationName){
        fruitController.getFruits(basketName, locationName).forEach(f ->
                out.println(f.getFruitName()));
    }

    private void getFruit(PrintWriter out, String fruitName, String basketName, String locationName){
        out.println(fruitController.getFruit(fruitName, basketName, locationName));
    }

    private void addFruit(PrintWriter out, String fruitName, String basketName, String locationName){
        out.println(fruitController.addFruit(fruitName, basketName, locationName));
    }

    private void removeFruit(PrintWriter out, String fruitName, String basketName, String locationName){
        out.println(fruitController.removeFruit(fruitName, basketName, locationName));
    }
}

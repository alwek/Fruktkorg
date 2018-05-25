package com.alican.servlet;

import com.alican.controller.FruitController;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Created by Alican Bircan on 2018-05-25
 * Good luck, Commander!
 */
public class FruitServlet  extends HttpServlet{
    private static FruitController fruitController = new FruitController();

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

        while(parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            String[] paramValues = request.getParameterValues(paramName);

            if(paramName.equalsIgnoreCase("getFruits") && paramValues[0].isEmpty())
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

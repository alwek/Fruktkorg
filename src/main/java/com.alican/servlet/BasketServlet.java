package com.alican.servlet;

import com.alican.controller.BasketController;
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
public class BasketServlet extends HttpServlet{
    private static BasketController basketController = new BasketController();

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

            if(paramName.equalsIgnoreCase("getBaskets") && paramValues[0].isEmpty())
                getBaskets(out);
            else if(paramName.equalsIgnoreCase("getBaskets") && paramValues.length == 1)
                getBaskets(out, paramValues[0]);
            else if(paramName.equalsIgnoreCase("getBasket") && paramValues.length == 2)
                getBasket(out, paramValues[0], paramValues[1]);
            else if(paramName.equalsIgnoreCase("addBasket") && paramValues.length == 2)
                addBasket(out, paramValues[0], paramValues[1]);
            else if(paramName.equalsIgnoreCase("removeBasket") && paramValues.length == 2)
                removeBasket(out, paramValues[0], paramValues[1]);
            else
                out.println("Found no method with that name");
        }
    }

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
}

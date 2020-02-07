package com.alican.servlet;

import com.alican.controller.LocationController;
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
public class LocationServlet extends HttpServlet {
    private static LocationController locationController = new LocationController();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Fruktkorg Servlet</h1>");
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

            if(paramName.equalsIgnoreCase("getLocations") && paramValues[0].isEmpty())
                getLocations(out);
            else if(paramName.equalsIgnoreCase("getLocations") && paramValues.length == 1)
                getLocation(out, paramValues[0]);
            else if(paramName.equalsIgnoreCase("addLocation") && paramValues.length == 1)
                addLocation(out, paramValues[0]);
            else if(paramName.equalsIgnoreCase("removeLocation") && paramValues.length == 1)
                removeLocation(out, paramValues[0]);
            else
                out.println("Found no method with that name");
        }
    }

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
}

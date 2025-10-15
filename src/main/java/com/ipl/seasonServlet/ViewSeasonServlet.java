package com.ipl.seasonServlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

import com.google.gson.Gson;

import com.ipl.serviceIpl.SeasonServiceImpl;
import com.ipl.serviceIpl.InterSeasonService;

public class ViewSeasonServlet extends HttpServlet {
   
    InterSeasonService service = new SeasonServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("--Inside the view all seasons---)");

        // Set content type to JSON
        response.setContentType("application/json;charset=UTF-8");

        // Get the PrintWriter to send JSON response
        PrintWriter out = response.getWriter();

        try {
            List<String> seasons = service.viewSeason();
            
            String json = new Gson().toJson(seasons);
            
            // Send the JSON response
            out.println(json);
        } catch (Exception e) {
            e.printStackTrace();
            // Return an error message in case of failure
            out.println("error in get season servlet" );
        } finally {
            out.close();
        }
    }
}


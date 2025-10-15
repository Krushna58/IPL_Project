package com.ipl.playerServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import com.ipl.entity.PlayersEntity;
import com.ipl.serviceIpl.PlayersServiceImpl;
import com.ipl.serviceIpl.InterPlayersService;

// update
 public class PlayerUpdateServlet extends HttpServlet{

	InterPlayersService service = new PlayersServiceImpl();
    private Gson gson = new Gson();


    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try {
            // Parse JSON directly into PlayersEntity
            PlayersEntity player = gson.fromJson(request.getReader(), PlayersEntity.class);
            
            System.out.println("player entity "+ player);

            // Call  service
            int updateSuccess = service.updatePlayer(player);
            

            if (updateSuccess > 0 ) {
              
                response.getWriter().write("<h2>Player updated successfully!</h2>");
                response.getWriter().println("<p>  <a href='players.html'>Back to Menu</a></p>");
            } else {
              
                response.getWriter().write("<h2>Failed to update player.</h2>");
                response.getWriter().println("<p>  <a href='players.html'>Back to Menu</a></p>");
            }

        } catch (JsonSyntaxException e) {
            response.getWriter().write("<h3>Invalid JSON format.</h3>");
            response.getWriter().println("<p>  <a href='players.html'>Back to Menu</a></p>");
        } catch (Exception e) {
            response.getWriter().write("<h3>Error processing request: " + e.getMessage() + "</h3>");
            response.getWriter().println("<p>  <a href='players.html'>Back to Menu</a></p>");
        }
    }
}

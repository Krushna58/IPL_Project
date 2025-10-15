package com.ipl.teamServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ipl.entity.TeamEntity;
import com.ipl.serviceIpl.TeamServiceImpl;
import com.ipl.serviceIpl.InterTeamService;



public class TeamAddServlet extends HttpServlet {

        InterTeamService service = new TeamServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Get and process input (convert to uppercase like in your method)
        String name = request.getParameter("name").trim().toUpperCase();
        String city = request.getParameter("city").trim().toUpperCase();
        int season = 0;
          response.setContentType("text/html");

        try {
            season = Integer.parseInt(request.getParameter("season").trim());
        } catch (NumberFormatException e) {
            response.getWriter().println("Invalid season input.");
            return;
        }

        // Create TeamEntity object
        TeamEntity team = new TeamEntity(name, city, season);
        try{
        
          int id =  service.addTeam(team);  // pass the entity to TeamService 
          // System.out.println("id = " +id);
          
          if(id > 0){
          // Display or store the result
      
        response.getWriter().println("<h2>Team Created:</h2>");
        response.getWriter().println("<p>Name: " + team.getT_Name() + "</p>");
        response.getWriter().println("<p>City: " + team.getCity() + "</p>");
        response.getWriter().println("<p>Season: " + team.getSeason() + "</p>");
         response.getWriter().println("<p>  <a href='team.html'>Back to Menu</a></p>");
      
          } else{
           response.getWriter().println("<h2>Team Not created!!! Please enter valid season</h2>");
          response.getWriter().println("<p>  <a href='team.html'>Back to Menu</a></p>");
          }
          
          
        } catch(Exception e){
            e.printStackTrace();
        
        }

        
        
      
    }
}


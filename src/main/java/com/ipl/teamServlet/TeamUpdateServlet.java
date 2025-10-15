package com.ipl.teamServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;


import com.ipl.entity.TeamEntity;
import com.ipl.serviceIpl.TeamServiceImpl;
import com.ipl.serviceIpl.InterTeamService;


public class TeamUpdateServlet extends HttpServlet{

	// service class obj	   
InterTeamService service = new TeamServiceImpl();
private Gson gson = new Gson();

protected void doPut(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        response.setContentType("text/html");
        
        try{
          
            // pase json directly into TeamEntity
            TeamEntity team = gson.fromJson(request.getReader(), TeamEntity.class);
            
            int updateSuccess = service.validateTeamUpdate(team);
            
            
            if(updateSuccess > 0){
              response.getWriter().write("<h2>Team updated successfully!!</h2>");
            } else{
              response.getWriter().write("<h2>Failed to update Team! Re-enter valid team id and season</h2>");
            }
        } catch (JsonSyntaxException e){
            response.getWriter().write("Invalid json for update team");
        } catch (Exception e){
              response.getWriter().write("Error - " + e.getMessage());
          }

	}

}

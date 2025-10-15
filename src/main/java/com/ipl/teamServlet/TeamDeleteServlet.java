
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


public class TeamDeleteServlet extends HttpServlet{

	// service class obj
	InterTeamService service = new TeamServiceImpl();


protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
              response.setContentType("text/html");

	try{

		int season = Integer.parseInt(request.getParameter("season").trim());
		String name = request.getParameter("name").trim().toUpperCase();
		
		// pass to entity
		TeamEntity team = new TeamEntity(name,season);
		
		// call service and pass entity
		int rows = service.deleteTeam(team);

		

		if(rows > 0){
			response.getWriter().println("<h1>Team deleted successfully </h1>");
			
		} else{
			response.getWriter().println("<h2>Failed to Delete Team!!!!!! Please enter the correct season</h2>");
		}

	} catch (NumberFormatException e){
		response.getWriter().println("Invalid number format.");
	} catch(Exception e){
	//	e.printStackTrace();
		response.getWriter().println("Error in delete team " + e.getMessage());
		
	}
}


}

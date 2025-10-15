package com.ipl.playerServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ipl.entity.PlayersEntity;
import com.ipl.serviceIpl.PlayersServiceImpl;
import com.ipl.serviceIpl.InterPlayersService;

// add player
 public class PlayerAddServlet extends HttpServlet{

	InterPlayersService service = new PlayersServiceImpl();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	// get input
	int t_id =0;
	int inns = 0;
	int notOut = 0;
	int runs = 0;
	int hScore = 0;
	int matches = 0;
	try{
	t_id =  Integer.parseInt(request.getParameter("t_id").trim());
	inns = Integer.parseInt(request.getParameter("inns").trim());
	notOut = Integer.parseInt(request.getParameter("notOut").trim());
	runs  =  Integer.parseInt(request.getParameter("runs").trim());
	hScore = Integer.parseInt(request.getParameter("hScore").trim());
	matches = Integer.parseInt(request.getParameter("matches").trim());	

	} catch (NumberFormatException e) {
		response.getWriter().println("invalid input.");
	
	}

	String pName = request.getParameter("pName").trim().toUpperCase();
	String role = request.getParameter("role").trim().toUpperCase();
		
	// create PlayersEntity obj
	PlayersEntity player = new PlayersEntity(t_id,pName,role,inns,notOut,runs,hScore,matches);
	
	// call player service method and pass entity
	 int id = service.insertPlayer(player);
	
	response.setContentType("text/html");
	if(id > 0){
		response.getWriter().println("<h2> player added successfully!!</h2>");
		 response.getWriter().println("<p>  <a href='players.html'>Back to Menu</a></p>");
		
	} else{
		 response.getWriter().println("<h2>failed to add Player. Enter correct Team ID</h2>");
		 response.getWriter().println("<p>  <a href='players.html'>Back to Menu</a></p>");
	}

	
	}

}

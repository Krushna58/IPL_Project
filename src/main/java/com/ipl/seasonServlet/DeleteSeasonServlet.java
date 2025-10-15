package com.ipl.seasonServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.ipl.serviceIpl.SeasonServiceImpl;
import com.ipl.serviceIpl.InterSeasonService;


public class DeleteSeasonServlet extends HttpServlet{
	InterSeasonService service = new SeasonServiceImpl();

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

	response.setContentType("text/html");
	PrintWriter out = response.getWriter();

	try{
		// get the Season 
		String seasonParam = request.getParameter("season");
		if (seasonParam == null || seasonParam.trim().isEmpty()){
			out.println("<h2>Season can not be null and empty!!</h2>");
		}
		
	
	 int season = Integer.parseInt(seasonParam);
	
	int rows = service.deleteSeason(season);
	
	if(rows > 0){
		out.println("<h3> Season " + season + "deleted successfully");
		out.println("<a href='index.html'>Back to Main </a>");
		}
	
	} catch (NumberFormatException e){

		out.println("<h3>Error : Seasn must be a valid number </h3>");
		out.println("<a href='index.html'>Back to Main Menu</a>");
	}
	
	}
}


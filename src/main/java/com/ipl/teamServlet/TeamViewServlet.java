package com.ipl.teamServlet;

import com.ipl.entity.TeamEntity;
import com.ipl.serviceIpl.TeamServiceImpl;
import com.ipl.serviceIpl.InterTeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;


public class TeamViewServlet extends HttpServlet {
    private InterTeamService service = new TeamServiceImpl();

    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<TeamEntity> teams = service.viewTeams();

        response.setContentType("text/html");

        // HTML Header
        response.getWriter().println("<html><head><title>Team List</title></head><body>");
        response.getWriter().println("<h2>All Teams</h2>");

        // Table Header
        response.getWriter().println("<table border='1' cellpadding='8' cellspacing='0'>");
        response.getWriter().println("<tr>");
        response.getWriter().println("<th>T_ID</th>");
        response.getWriter().println("<th>T_Name</th>");
        response.getWriter().println("<th>City</th>");
        response.getWriter().println("<th>Season</th>");
        response.getWriter().println("</tr>");

        // Table Rows
        if (teams != null && !teams.isEmpty()) {
            for (TeamEntity team : teams) {
                response.getWriter().println("<tr>");
                response.getWriter().println("<td>" + team.getT_ID() + "</td>");
                response.getWriter().println("<td>" + team.getT_Name() + "</td>");
                response.getWriter().println("<td>" + team.getCity() + "</td>");
                response.getWriter().println("<td>" + team.getSeason() + "</td>");
                response.getWriter().println("</tr>");
            }
        } else {
            response.getWriter().println("<tr><td colspan='4'>No teams found...!!</td></tr>");
        }

        response.getWriter().println("</table>");
        response.getWriter().println("<br><a href='team.html'>Back to Menu</a>");
        response.getWriter().println("</body></html>");
    }
}


package com.ipl.playerServlet;

import com.ipl.entity.PlayersEntity;
import com.ipl.serviceIpl.PlayersServiceImpl;
import com.ipl.serviceIpl.InterPlayersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

// view players

public class PlayerViewServlet extends HttpServlet {

    private InterPlayersService service = new PlayersServiceImpl();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<PlayersEntity> players = service.viewPlayers();

        response.setContentType("text/html");

        // header
        response.getWriter().println("<html><head><title>Player List</title></head><body>");
        response.getWriter().println("<h2>All Players</h2>");

        // table header
        response.getWriter().println("<table border='1' cellpadding='8' cellspacing='0'>");

        response.getWriter().println("<tr>");
        response.getWriter().println("<th>Player ID</th>");
        response.getWriter().println("<th>Team ID</th>");
        response.getWriter().println("<th>Player Name</th>");
        response.getWriter().println("<th>Role</th>");
        response.getWriter().println("<th>Innings</th>");
        response.getWriter().println("<th>Not Out</th>");
        response.getWriter().println("<th>Runs</th>");
        response.getWriter().println("<th>Highest Score</th>");
        response.getWriter().println("<th>Matches Played</th>");
        response.getWriter().println("<th>Action</th>");
        response.getWriter().println("</tr>");

        // Table Rows
        if (players != null && !players.isEmpty()) {
            for (PlayersEntity player : players) {
                response.getWriter().println("<tr>");
                response.getWriter().println("<td>" + player.getP_ID() + "</td>");
                response.getWriter().println("<td>" + player.getT_ID() + "</td>");
                response.getWriter().println("<td>" + player.getP_Name() + "</td>");
                response.getWriter().println("<td>" + player.getRole() + "</td>");
                response.getWriter().println("<td>" + player.getInns() + "</td>");
                response.getWriter().println("<td>" + player.getNot_Out() + "</td>");
                response.getWriter().println("<td>" + player.getRuns() + "</td>");
                response.getWriter().println("<td>" + player.getHighest_Score() + "</td>");
                response.getWriter().println("<td>" + player.getMatches() + "</td>");

                //  Delete Button
                response.getWriter().println("<td>");
                response.getWriter().println("<form action='PlayerDeleteServlet' method='post' style='display:inline;'>");
                response.getWriter().println("<input type='hidden' name='P_ID' value='" + player.getP_ID() + "'/>");
                response.getWriter().println("<input type='submit' value='Delete' onclick='return confirm(\"Are you sure you want to delete this player?\");'/>");
                response.getWriter().println("</form>");
                response.getWriter().println("</td>");

                response.getWriter().println("</tr>");
            }

        } else {
            response.getWriter().println("<tr><td colspan='10'>No players found!</td></tr>");
        }

        response.getWriter().println("</table>");
        response.getWriter().println("<br> <a href='players.html'>Back to Menu</a>");
        response.getWriter().println("</body></html>");
    }
}


package com.ipl.teamServlet;

import com.google.gson.Gson;
import com.ipl.entity.TeamEntity;
import com.ipl.serviceIpl.TeamServiceImpl;
import com.ipl.serviceIpl.InterTeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListTeamServlet extends HttpServlet {

    private InterTeamService service = new TeamServiceImpl();

  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        try {
            // list of all teams
            List<TeamEntity> teams = service.viewTeams();

            // Create a map: TeamName -> TeamID
            Map<String, Integer> teamMap = new HashMap<>();
            for (TeamEntity t : teams) {
                teamMap.put(t.getT_Name(), t.getT_ID());
            }

            // Convert map to JSON
            String json = new Gson().toJson(teamMap);

            PrintWriter out = response.getWriter();
            out.print(json);
            out.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Failed to fetch teams");
        }
    }
}


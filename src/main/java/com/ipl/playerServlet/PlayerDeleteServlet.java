package com.ipl.playerServlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.ipl.serviceIpl.PlayersServiceImpl;
import com.ipl.serviceIpl.InterPlayersService;


public class PlayerDeleteServlet extends HttpServlet {

    private InterPlayersService service = new PlayersServiceImpl();

    //  Used by PlayerViewServlet
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        handleDelete(request, response);  
    }

    // Used by JavaScript
   
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        handleDelete(request, response);  
    }

    //  Shared delete logic
    private void handleDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");

        String idParam = request.getParameter("P_ID");

        if (idParam == null || idParam.isEmpty()) {
            response.getWriter().println("<h2>Player ID is required!</h2>");
            return;
        }

        try {
            int playerId = Integer.parseInt(idParam);
            int result = service.deletePlayerById(playerId);

            if (result > 0) {
                response.getWriter().println("<h2>Player deleted successfully!</h2>");
            } else {
                response.getWriter().println("<h2>Player not found or delete failed.</h2>");
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("<h2>Invalid Player ID format!</h2>");
        }

        response.getWriter().println("<br><a href='PlayerViewServlet'>Back to Player List</a>");
    }
}


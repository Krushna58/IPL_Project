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


public class DeleteTeamById extends HttpServlet {

    private InterTeamService service = new TeamServiceImpl();

   
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        // === Get parameters ===
        String teamIdParam = request.getParameter("t_id");
     

        try {
            int teamId = Integer.parseInt(teamIdParam);

            // === Call service method ===
            int result = service.deleteTeamById(teamId);

            // === Send response ===
            if (result > 0) {
                response.getWriter().println("<h3 style='color:green;'> Team deleted successfully!</h3>");
            } else {
                response.getWriter().println("<h3 style='color:red;'>failed to delete team. Re-enter correct Team ID.</h3>");
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("<h3 style='color:red;'> Invalid input format for Team ID or Season!</h3>");
            
        } catch (Exception e) {
              //  e.printStackTrace();
            response.getWriter().println("<h3 style='color:red;'> failed to delete team.(exception)</h3>");
        }
    }



}

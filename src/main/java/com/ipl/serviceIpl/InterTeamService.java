package com.ipl.serviceIpl;
import com.ipl.entity.TeamEntity;
import java.util.*;

public interface InterTeamService{
	      // abstract method 
	      int addTeam(TeamEntity entity);
	      List<TeamEntity> viewTeams();	
	      int deleteTeam(TeamEntity entity);
	      int deleteTeamById(int teamId);
	      int validateTeamUpdate(TeamEntity team);
	      Map<String, String> viewAllTeamsFromLdap();
	    
	   /*   int updateTeamName(TeamEntity entity);
	      int updateCityName(TeamEntity entity);
	      
	      void viewAllTeamsFromLdap();
	      */
	      
}

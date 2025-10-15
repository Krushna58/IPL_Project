package com.ipl.serviceIpl;
import com.ipl.dbRepo.*;
import java.util.*;
import com.ipl.entity.TeamEntity;
import com.ipl.serviceIpl.*;

public class TeamServiceImpl implements InterTeamService {

    	SqlTeamsRepo sqlRepo = new SqlTeamsRepo();
    	LdapTeamsRepo ldapRepo = new LdapTeamsRepo();
      Scanner sc = new Scanner(System.in);
      

      // add team
public int addTeam(TeamEntity entity) {
    // validate Team Name
    if (entity.getT_Name().isEmpty()) {
        System.out.println("Team Name cannot be empty!");
        return 0; // indicate failure
    }

    // validate City
    if (entity.getCity().isEmpty()) {
        System.out.println("City cannot be empty!");
        return 0;
    }

    // validate Season using sqlRepo
    if (!sqlRepo.getValidSeason(entity.getSeason())) {
        System.out.println("Invalid season! Please provide a valid season.");
        return 0;
    }

    // if everything is valid, pass to repo
    return sqlRepo.addTeam(entity);
}



    //end ---------------------------------------------------------------------
    // view Teams 
    public List<TeamEntity> viewTeams() {
        // Call the method and get list of teams
       // List<TeamEntity> teams = sqlRepo.viewTeams();
       
       return sqlRepo.viewTeams();
    }
    
    //--------------------------------------------------
    // update team 
    public int validateTeamUpdate(TeamEntity team) {
    if (team.getT_ID() <= 0) {
        System.out.println("Invalid T_ID.");
        return 0;
    }
    if (team.getT_Name() == null || team.getT_Name().trim().isEmpty()) {
        System.out.println(" Team name cannot be empty.");
        return 0;
    }
    if (team.getCity() == null || team.getCity().trim().isEmpty()) {
        System.out.println(" City cannot be empty.");
        return 0;
    }
    if (!sqlRepo.getValidSeason(team.getSeason())) {
        System.out.println("Invalid season.");
        return 0;
    }

    return sqlRepo.updateTeam(team);  // valid, can be passe to repo will return the int T_ID
}
    
    //-----------------------END-----------------------------------------------
    //
        public void updateTeamName() {
        int season = 0;
        String oldName = "";

        // Get valid season
        while (true) {
            try {
                System.out.println("Enter Season:");
                season = Integer.parseInt(sc.nextLine().trim());
                if (sqlRepo.getValidSeason(season)) {
                    break;
                }
                System.out.println("Invalid season. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Input integer value only!");
            }
        }

        // Get valid old team name
        while (true) {
            System.out.println("Enter old Team Name: ");
            oldName = sc.nextLine().trim();
            if (oldName.isEmpty()) {
                System.out.println("Input cannot be empty!! Type valid string");
                continue;
            }
            if (sqlRepo.getValidTeamInSeason(oldName, season)) {
                break;
            }
            System.out.println("Team not found. Re-enter the correct team name.");
        }

        // Get new team name
        System.out.println("Enter New Team Name: ");
        String newName = sc.nextLine().trim();

        // Call service
        String msg = sqlRepo.updateTeamName(season, oldName, newName);
        System.out.println(msg);
    }
    
    //--------------------------------------------------------------------
    
    // update city name 
    public void updateCityName(){
        int season = 0;
        String oldName = "";
        String newCity = "";

        // === Take Season ===
        while (true) {
            try {
                System.out.println("Enter Season:");
                season = Integer.parseInt(sc.nextLine().trim());
                if (sqlRepo.getValidSeason(season)) {
                    break;
                }
                System.out.println("Invalid season. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter integer value only!");
            }
        }

        // === Take old Team Name ===
        while (true) {
            System.out.println("Enter old Team Name: ");
            oldName = sc.nextLine().trim();

            if (oldName.isEmpty()) {
                System.out.println("Input cannot be empty!! Type valid string");
                continue;
            }

            if (sqlRepo.getValidTeamInSeason(oldName, season)) {
                break;
            }
            System.out.println("Team not found. Re-enter the correct team name");
        }

        // === Take new City ===
        System.out.println("Enter new City Name: ");
        newCity = sc.nextLine().trim();

        // === Call service method ===
        String msg = sqlRepo.updateCityName(season, oldName, newCity);
        System.out.println(msg);
    }
    
    //---------------------------------------------------------------------
    // delete team using t-name and season 
      public int deleteTeam(TeamEntity team) {
       
    // Validate season
    if (!sqlRepo.getValidSeason(team.getSeason())) {
        System.out.println(" Invalid season.");
        return 0;
    }

    // Validate team name
    if (team.getT_Name() == null || team.getT_Name().isEmpty()) {
        System.out.println(" Team name cannot be empty.");
        return 0;
    }

    if (!sqlRepo.getValidTeamInSeason(team.getT_Name(), team.getSeason())) {
        System.out.println(" Team not found in the given season.");
        return 0;
    }

        // === Call service method ===
        return sqlRepo.deleteTeam(team);
       
    }
    
    //===========================================
    // Delete team using Team ID and Season
	public int deleteTeamById(int teamId) {

    // === Basic validation ===
    if (teamId <= 0) {
        System.out.println("Invalid Team ID. It must be greater than 0.");
        return 0;
    }
    // === Call repo
    return sqlRepo.deleteTeamById(teamId);
}

    
    //----------------------------------------------------------
    //Ldap view teams
      public Map<String,String> viewAllTeamsFromLdap() {
      
          Map<String, String> teams = ldapRepo.viewAllTeamsFromLdap();
	      return teams;
}


    //---------------------------------------------------
    
    
}


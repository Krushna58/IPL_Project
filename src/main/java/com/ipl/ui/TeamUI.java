package com.ipl.ui;
	
import com.ipl.serviceIpl.HelperMethods;
import com.ipl.serviceIpl.InterTeamService;
import com.ipl.serviceIpl.TeamServiceImpl;
import com.ipl.serviceIpl.PlayersServiceImpl;

import java.util.*;
import com.ipl.entity.TeamEntity;

import com.ipl.serviceIpl.InterTeamService;
	
public class TeamUI{
	
	       HelperMethods helperMethods = new HelperMethods();
	       InterTeamService service = new TeamServiceImpl();
	       Scanner sc = new Scanner(System.in);
	       
	    
	       
//------Option display------------------------------------------------    
public void handleTeamOperations() {
 TeamUI teamUI = new TeamUI();
               
    		  
                  int choice = -1;
                  boolean run = true;

    while (run) {
        System.out.println(
            "\n========= Team Menu ================\n" +
            "Enter 1.  Add Team\n" +
            "Enter 2.  View Teams\n" +
            "Enter 3. To update everything in Team\n" +
            "Enter 4.  Delete Team\n" +
            "Enter 5.  View all teams in Ldap\n" +
           // "Enter 6.  Update City\n" +
            "Enter 0.  Back to Main Menu"
        );

        while (true) {
            try {
                System.out.print("Enter choice: ");
                choice = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        switch (choice) {
            case 1:
                teamUI.addTeam();
                break;

            case 2:
                teamUI.displayTemas();
                break;

            case 3:
               teamUI.updateTeam();
                break;

            case 4:
               teamUI.deleteTeam();
                break;

            case 5:
             teamUI.viewAllTeamsFromLdap();
               break;
               
            case 0:
                run = false;
                System.out.println("Returning to main menu...");
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

   
	//-------------------------
	// add team 
	public void addTeam(){
	
	  TeamEntity team = helperMethods.takeTeamInput();     //helper method for take user input
           service.addTeam(team);  // pass the entity to TeamService 
	    }
	    
	//------------------------
	    // display team-----------------------
	    public void displayTemas(){
	    
	     List<TeamEntity> teams = service.viewTeams();
	     
    // Print teams
        if (teams.isEmpty()) {
            System.out.println("No teams found.");
        } else {
            System.out.println("======= Teams List =========");
            for (TeamEntity t : teams) {
                System.out.println("ID: " + t.getT_ID() + ", Name: " + t.getT_Name() + ", City: " + t.getCity() + ", Season: " + t.getSeason());
            }
        }
        
	  }
	  //--------------------------------------
	  // update team (update all)
	  public void updateTeam(){
	          
	           TeamEntity entity = helperMethods.takeTeamUpdateInput();
	          int msg = service.validateTeamUpdate(entity);
	          
	          System.out.println(msg);
	    }
	    
	    //-------------------------------------
	    
	    // delete team  using (T_Name, Season)
	    public void deleteTeam(){
	        TeamEntity team = helperMethods.takeDeleteInput(); 
	        int msg=  service.deleteTeam(team);
	        System.out.println("affected number of rows - " + msg);
	    }
	    
	    // display all teams from ldap()---------------------------------
	    public void viewAllTeamsFromLdap(){
	     System.out.println("view all teams in Ldap");
	        Map<String, String> teams  = service.viewAllTeamsFromLdap();
	    
          if (teams == null || teams.isEmpty()) {
              System.out.println("No teams found in LDAP.");
        } else {
              System.out.println("=========== Teams in LDAP ============");
        for (Map.Entry<String, String> entry : teams.entrySet()) {
              System.out.println("Team Name: " + entry.getKey() + " | " + entry.getValue());
        }
    }
  
	        
	    }
	    
}

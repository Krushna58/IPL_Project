package com.ipl.serviceIpl;
import com.ipl.entity.PlayersEntity;
import com.ipl.dbRepo.*;
import com.ipl.dbConnection.*;
import java.util.*;
import java.sql.*;

public class PlayersServiceImpl implements InterPlayersService{

    PlayersRepo repo = new PlayersRepo();
    SqlTeamsRepo sqlRepo = new SqlTeamsRepo();
    Scanner sc = new Scanner(System.in);



// view all players 
 public  List<PlayersEntity> viewPlayers() {
        
         return repo.viewPlayers();
  
}
//---------------------------------------------------------------------
// view player by team and season
public List<PlayersEntity> viewPlayersByIdAndSeason(int t_id, int season){
    
        if(t_id < 0){
          return null;
          }
          
          if(season < 0 ){
          return null;
          }
          
         return repo.viewPlayersByIdAndSeason(t_id,season);
}
  
//--------------------------------------------------------------
public int insertPlayer(PlayersEntity entity) {
    // === Basic Validation ===
    if (entity == null) {
        System.out.println("Player entity is null.");
        return 0;
    }

    if (entity.getT_ID() <= 0) {
        System.out.println("Invalid Team ID. It must be greater than 0.");
        return 0;
    }

    if (entity.getP_Name() == null || entity.getP_Name().trim().isEmpty()) {
        System.out.println("Player name cannot be empty.");
        return 0;
    }

    if (entity.getRole() == null || entity.getRole().trim().isEmpty()) {
        System.out.println("Role cannot be empty.");
        return 0;
    }

    
    if (entity.getInns() < 0 || entity.getNot_Out() < 0 || entity.getRuns() < 0 ||
        entity.getHighest_Score() < 0 || entity.getMatches() < 0) {
        System.out.println("Player stats cannot be negative.");
        return 0;
    }

    // === If Valid, insert via repo ===
     return repo.insertPlayer(entity);
  
}

    //----------------------------------------------------------------
    public int deletePlayerById(int P_ID){
      return repo.deletePlayerById(P_ID);
      
    }
    
    
/*    //-----(not in use)--------------------------------------------------------------------------
     public void deletePlayer() {
        int season = 0;
        String oldName = "";
        int T_ID = 0;
        String pName = "";

        // === Take Season ===
        while (true) {
            try {
                System.out.println("Enter Season:");
                season = Integer.parseInt(sc.nextLine().trim());
                if (sqlRepo.getValidSeason(season)) break;
                System.out.println("Invalid season. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Enter integer value only!");
            }
        }

        // === Take Team Name ===
        while (true) {
            System.out.println("Enter Team Name: ");
            oldName = sc.nextLine().trim();
            if (oldName.isEmpty()) {
                System.out.println("Input cannot be empty!! Type valid string");
                continue;
            }
            if (sqlRepo.getValidTeamInSeason(oldName, season)) break;
            System.out.println("Team not found. Re-enter the correct team name");
        }

        // === Get Team ID ===
        T_ID = sqlRepo.getTeamId(season, oldName);

        // === Take Player Name ===
        System.out.println("Enter Player Name: ");
        pName = sc.nextLine().trim();

        // === Call service method ===
        if (repo.deletePlayer(season, T_ID, pName)) {
            System.out.println("Player removed successfully");
        } else {
            System.out.println("Failed to remove player!");
        }
    }
    //---------------------------------------------------------------------------------
*/    

public int updatePlayer(PlayersEntity entity) {
    
    if (entity == null) {
        System.out.println(" Player entity is null.");
        return 0;
    }

    if (entity.getP_ID() <= 0) {
        System.out.println("Invalid Player ID. It must be greater than 0 for update.");
        return 0;
    }

    if (entity.getT_ID() <= 0) {
        System.out.println("Invalid Team ID. It must be greater than 0.");
        return 0;
    }

    if (entity.getP_Name() == null || entity.getP_Name().trim().isEmpty()) {
        System.out.println("Player name cannot be empty.");
        return 0;
    }

    if (entity.getRole() == null || entity.getRole().trim().isEmpty()) {
        System.out.println("Role cannot be empty.");
        return 0;
    }

    if (entity.getInns() < 0 || entity.getNot_Out() < 0 || entity.getRuns() < 0 ||
        entity.getHighest_Score() < 0 || entity.getMatches() < 0) {
        System.out.println("Player stats cannot be negative.");
        return 0;
    }

   // call repo method for update
    return repo.updatePlayer(entity);  
}

    
    //-------------------------------------------------------------------
    
        public void updatePlayerName() {
        int season = 0;
        String oldName = "";
        int T_ID = 0;

        // === Take Season ===
        while (true) {
            try {
                System.out.println("Enter Season:");
                season = Integer.parseInt(sc.nextLine().trim());
                if (sqlRepo.getValidSeason(season)) break;
                System.out.println("Invalid season. Try again.");
            } catch (NumberFormatException e) {
                System.out.println("Enter integer value only!");
            }
        }

        // === Take Team Name ===
        while (true) {
            System.out.println("Enter Team Name: ");
            oldName = sc.nextLine().trim();
            if (oldName.isEmpty()) {
                System.out.println("Input cannot be empty!! Type valid string");
                continue;
            }
            if (sqlRepo.getValidTeamInSeason(oldName, season)) break;
            System.out.println("Team not found. Re-enter the correct team name");
        }

        // === Get Team ID ===
        T_ID = sqlRepo.getTeamId(season, oldName);

        // === Take old & new player names ===
        System.out.println("Enter old player Name:");
        String oldPlayerName = sc.nextLine().trim();

        System.out.println("Enter new player Name:");
        String newPlayerName = sc.nextLine().trim();

        // === Call service method ===
        if (repo.updatePlayerName(season, T_ID, oldPlayerName, newPlayerName)) {
            System.out.println("Player name updated successfully");
        } else {
            System.out.println("Failed to update player name!");
        }
    }
    
    //----------------------------------------------------------------------
    


}

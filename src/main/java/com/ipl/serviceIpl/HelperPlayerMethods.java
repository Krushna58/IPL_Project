package com.ipl.serviceIpl;
import java.util.Scanner;
import com.ipl.entity.PlayersEntity;

public class HelperPlayerMethods{

        Scanner sc = new Scanner(System.in);
        
        
 // take input for insert new player------------------------------------------
public PlayersEntity takePlayerInput() {
    

    // === Take Team ID directly from user ===
    int T_ID = 0;
    while (true) {
        try {
            System.out.println("Enter Team ID (T_ID):");
            T_ID = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter an integer for Team ID.");
        }
    }

    // === Take Player Details ===
    System.out.println("Enter Player Name: ");
    String pName = sc.nextLine().trim();

    System.out.println("Enter Role (Batsman, Bowler, All-rounder, etc.):");
    String role = sc.nextLine().trim();

    int inns = 0;
    while (true) {
        try {
            System.out.println("Enter total number of Innings: ");
            inns = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    int notOut = 0;
    while (true) {
        try {
            System.out.println("Enter total number of Not Outs: ");
            notOut = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    int runs = 0;
    while (true) {
        try {
            System.out.println("Enter total Runs: ");
            runs = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    int highestScore = 0;
    while (true) {
        try {
            System.out.println("Enter Highest Score: ");
            highestScore = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    int matches = 0;
    while (true) {
        try {
            System.out.println("Enter number of Matches played: ");
            matches = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    // === Return the PlayerEntity ===
    return new PlayersEntity(T_ID, pName, role, inns, notOut, runs, highestScore, matches);
}


//===============================================================
// take input for update existing player ------------------------------------------
public PlayersEntity takePlayerUpdateInput() {

    // === Take Player ID (for update) ===
    int P_ID = 0;
    while (true) {
        try {
            System.out.println("Enter Player ID (P_ID) to update:");
            P_ID = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter an integer for Player ID.");
        }
    }

    // === Take Team ID directly from user ===
    int T_ID = 0;
    while (true) {
        try {
            System.out.println("Enter Team ID (T_ID):");
            T_ID = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter an integer for Team ID.");
        }
    }

    // === Take Player Details ===
    System.out.println("Enter Player Name: ");
    String pName = sc.nextLine().trim();

    System.out.println("Enter Role (Batsman, Bowler, All-rounder, etc.):");
    String role = sc.nextLine().trim();

    int inns = 0;
    while (true) {
        try {
            System.out.println("Enter total number of Innings: ");
            inns = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    int notOut = 0;
    while (true) {
        try {
            System.out.println("Enter total number of Not Outs: ");
            notOut = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    int runs = 0;
    while (true) {
        try {
            System.out.println("Enter total Runs: ");
            runs = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    int highestScore = 0;
    while (true) {
        try {
            System.out.println("Enter Highest Score: ");
            highestScore = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    int matches = 0;
    while (true) {
        try {
            System.out.println("Enter number of Matches played: ");
            matches = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }

    // === Return the PlayerEntity (with P_ID for update) ===
    return new PlayersEntity(P_ID, T_ID, pName, role, inns, notOut, runs, highestScore, matches);
}



//-------------------------------------------------------------
public int takePlayerID(){

  int P_ID = 0;
    while (true) {
        try {
           System.out.println("Insert player id which you have to delete");
            P_ID = Integer.parseInt(sc.nextLine().trim());
            break;
        } catch (NumberFormatException e) {
            System.out.println("Enter a valid integer.");
        }
    }
    return P_ID;
}

//===========================================================
        
        
/*
// for print players by team---------------------------------------------
        public void takePlayerInput() {
        System.out.print("Enter Season: ");
        int season = 0;
        while (true) {
            try {
                season = Integer.parseInt(sc.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter an integer for season:");
            }
        }

        System.out.print("Enter Team Name: ");
        String teamName = "";
        while (true) {
            teamName = sc.nextLine().trim();
            if (!teamName.isEmpty()) {
                break;
            }
            System.out.println("Team name cannot be empty. Please enter again:");
        }

        return new PlayerInput(teamName, season);
    }
    */
    
    //----------------------------------------------------------




}

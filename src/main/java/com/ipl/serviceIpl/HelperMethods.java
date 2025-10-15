package com.ipl.serviceIpl;
import com.ipl.entity.TeamEntity;
import java.util.*;

// helper mehtods for user input
public class HelperMethods{

Scanner sc = new Scanner(System.in);
		
	// helper method: input and returns TeamEntity
public TeamEntity takeTeamInput() {
    System.out.println("Enter Team Name: ");
    String name = sc.nextLine().trim().toUpperCase(); ;

    System.out.println("Enter City Name: ");
    String city = sc.nextLine().trim().toUpperCase();

    System.out.println("Enter Season: ");
    int season = 0;
    try {
        season = Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e) {
       
        System.out.println("Invalid season input");
    }

    return new TeamEntity(name, city, season);
}


//  take all inputs for Team table and return TeamEntity
public TeamEntity takeTeamUpdateInput() {
int t_id = -1;
try {
    System.out.print("Enter Team ID (T_ID): ");
    t_id = Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e) {
       
        System.out.println("Invalid season input");
    }
    
    System.out.print("Enter New Team Name: ");
    String name = sc.nextLine().trim().toUpperCase();

    System.out.print("Enter New City: ");
    String city = sc.nextLine().trim().toUpperCase();

    System.out.println("Enter Season: ");
    int season = 0;
    try {
        season = Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e) {
       
        System.out.println("Invalid season input");
    }

    // return directly without validation
    return new TeamEntity(t_id, name, city, season);
}

//------------------------------------------------------------------
// for delete team 
public TeamEntity takeDeleteInput() {
    int season = 0;
    try{
    System.out.println("Enter Season: ");
     season = Integer.parseInt(sc.nextLine().trim());
    } catch (NumberFormatException e){
      System.out.println("Invalid input");    
    }
    System.out.println("Enter Team Name: ");
    String teamName = sc.nextLine().trim().toUpperCase();

    return new TeamEntity(teamName, season);    //  using (T_Name, Season) constructor
}
//---------------------------------


}

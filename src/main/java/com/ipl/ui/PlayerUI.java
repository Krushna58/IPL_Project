package com.ipl.ui;
import com.ipl.serviceIpl.PlayersServiceImpl;
import com.ipl.serviceIpl.InterPlayersService;
import java.util.*;
import com.ipl.entity.*;
import com.ipl.serviceIpl.HelperPlayerMethods;

public class PlayerUI{

         InterPlayersService pService = new PlayersServiceImpl();
        HelperPlayerMethods helperPM = new HelperPlayerMethods();
         
         Scanner sc = new Scanner(System.in);

public void handlePlayerOperations() {
PlayerUI playerUI = new PlayerUI();
    
    int choice = -1;
    boolean run = true;

    while (run) {
        System.out.println(
            "\n========= Player Menu ================\n" +
            "Enter 1.  View Players\n" +
            "Enter 2.  Add New Player\n" +
            "Enter 3.  Delete Player\n" +
            "Enter 4.  Update \n" +
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
             
              playerUI.viewPlayers();
                break;

            case 2:
               //pService.insertPlayer();
               playerUI.insertPlayer();
                break;

            case 3:
               // pService.deletePlayer();
               playerUI.deletePlayerById();
                break;

            case 4:
               // pService.updatePlayerName();
               playerUI.updatePlayer();
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

public void viewPlayers(){
         List<PlayersEntity> pList  = pService.viewPlayers();
          
      
      // ======== Print Players ======================
        if (pList.isEmpty()) {
            System.out.println("No Player found!!!!!!!!");
        } else {
            System.out.println("=========== Players List ===========");
            for (PlayersEntity p : pList) {
                System.out.println(p);
            }
        }
    
}


public void insertPlayer(){
        PlayersEntity entity = helperPM.takePlayerInput();
          int P_Id = pService.insertPlayer(entity);
        System.out.println("Player id -" + P_Id);
}

public void deletePlayerById(){
        int P_ID = helperPM.takePlayerID();
         int msg = pService.deletePlayerById(P_ID);
         System.out.println("deleted player- " + msg);
         
    }
    
    public void updatePlayer(){
         PlayersEntity entity = helperPM.takePlayerUpdateInput();
          int id = pService.updatePlayer(entity);
          System.out.println("updated successfully -" + id);
    }

}

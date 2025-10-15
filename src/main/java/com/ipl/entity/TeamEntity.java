
package com.ipl.entity;

public class TeamEntity {
    private int T_ID;
    private String T_Name;
    private String City;
    private int Season;

    // Default constructor
    public TeamEntity() {
    }

    // Constructor for insert 
    public TeamEntity(String T_Name, String City, int Season) {
        this.T_Name = T_Name;
        this.City = City;
        this.Season = Season;
    }

    // Constructor 
    public TeamEntity(int T_ID, String T_Name, String City, int Season) {
        this.T_ID = T_ID;
        this.T_Name = T_Name;
        this.City = City;
        this.Season = Season;
    }
    // constructor delete
    public TeamEntity(String T_Name,int Season){
    this.T_Name = T_Name;
    this.Season = Season;
    
    }

//	public TeamEntity(int T_ID,String T_Name, String City, int Season)
    // Getters
    public int getT_ID() {
        return T_ID;
    }

    public String getT_Name() {
        return T_Name;
    }

    public String getCity() {
        return City;
    }

    public int getSeason() {
        return Season;
    }

    // Setters
    public void setT_ID(int T_ID) {
        this.T_ID = T_ID;
    }

    public void setT_Name(String T_Name) {
        this.T_Name = T_Name;
    }

    public void setCity(String City) {
        this.City = City;
    }

    public void setSeason(int Season) {
        this.Season = Season;
    }

 		   // toString method for display
    		
    		public String toString() {
       			 return "TeamEntity || " +
               					 "T_ID=" + T_ID +
              						  ", T_Name='" + T_Name + "||" +
               							 ", City='" + City + "||" +
               								 ", Season=" + Season;
    }
}

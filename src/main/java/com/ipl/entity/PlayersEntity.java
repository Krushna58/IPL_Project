package com.ipl.entity;

public class PlayersEntity {

    private int P_ID;            // Player ID (Primary Key, Auto Increment)
    private int T_ID;            // Team ID (Foreign Key)
    private String P_Name;       // Player Name
    private String Role;         // Role (Batsman, Bowler, All-rounder, etc.)
    private int Inns;            // Innings
    private int Not_Out;         // Not Out
    private int Runs;            // Runs
    private int Highest_Score;   // Highest Score
    private int Matches;         // Matches Played

    // Default constructor
    public PlayersEntity() {
    }
    
    // Constructor for update 
public PlayersEntity(int P_ID, int T_ID, String P_Name, String Role, int Inns, int Not_Out, int Runs, int Highest_Score, int Matches) {
    this.P_ID = P_ID;
    this.T_ID = T_ID;
    this.P_Name = P_Name;
    this.Role = Role;
    this.Inns = Inns;
    this.Not_Out = Not_Out;
    this.Runs = Runs;
    this.Highest_Score = Highest_Score;
    this.Matches = Matches;
}

    
    // Constructor for insert
    public PlayersEntity(int T_ID, String P_Name, String Role, int Inns, int Not_Out, int Runs, int Highest_Score, int Matches) {
        this.T_ID = T_ID;
        this.P_Name = P_Name;
        this.Role = Role;
        this.Inns = Inns;
        this.Not_Out = Not_Out;
        this.Runs = Runs;
        this.Highest_Score = Highest_Score;
        this.Matches = Matches;
    }
    // Getters and Setters
    public int getP_ID() {
        return P_ID;
    }

    public void setP_ID(int p_ID) {
        P_ID = p_ID;
    }

    public int getT_ID() {
        return T_ID;
    }

    public void setT_ID(int t_ID) {
        T_ID = t_ID;
    }

    public String getP_Name() {
        return P_Name;
    }

    public void setP_Name(String p_Name) {
        P_Name = p_Name;
    }

    public String getRole() {
        return Role;
    }

    public void setRole(String role) {
        Role = role;
    }

    public int getInns() {
        return Inns;
    }

    public void setInns(int inns) {
        Inns = inns;
    }

    public int getNot_Out() {
        return Not_Out;
    }

    public void setNot_Out(int not_Out) {
        Not_Out = not_Out;
    }

    public int getRuns() {
        return Runs;
    }

    public void setRuns(int runs) {
        Runs = runs;
    }

    public int getHighest_Score() {
        return Highest_Score;
    }

    public void setHighest_Score(int highest_Score) {
        Highest_Score = highest_Score;
    }

    public int getMatches() {
        return Matches;
    }

    public void setMatches(int matches) {
        Matches = matches;
    }

    public String toString() {
        return "|| P_ID = " + P_ID +
                "|| T_ID = " + T_ID +
                "|| P_Name = " + P_Name +
                "|| Role = " + Role +
                "|| Inns = " + Inns +
                "|| Not_Out = " + Not_Out +
                "|| Runs = " + Runs +
                "|| Highest_Score = " + Highest_Score +
                "|| Matches = " + Matches ;
    }
}


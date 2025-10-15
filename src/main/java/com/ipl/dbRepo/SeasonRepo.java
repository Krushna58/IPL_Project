package com.ipl.dbRepo;

import java.sql.*;
import java.util.*;
import com.ipl.dbConnection.SqlConnection;


public class SeasonRepo{
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	private final SqlConnection db = new SqlConnection();
	

public List<String> viewSeason() {
    String query = "SELECT * FROM Season";
    List<String> seasons = new ArrayList<>();

    try {
        conn = db.getConnection();
        ps = conn.prepareStatement(query);
        rs = ps.executeQuery();

        while (rs.next()) {
           
            String season = rs.getString("Season");
            seasons.add(season);   // Add to the list
        }

    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    return seasons;
}



public int addSeason(int season){

    String query = "INSERT INTO Season (Season) VALUES (?)";
    int rows = 0;
    try {
        conn = db.getConnection();
        ps = conn.prepareStatement(query);
        conn.setAutoCommit(false);

        ps.setInt(1, season);

        rows = ps.executeUpdate();
        if (rows > 0) {
            conn.commit();
            return rows;
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return rows;
}


public int deleteSeason(int season) {
    String query = "DELETE FROM Season WHERE Season = ?";
    int rows = 0;
    try {
        conn = db.getConnection();
        ps = conn.prepareStatement(query);
        conn.setAutoCommit(false);

        ps.setInt(1, season);

        rows = ps.executeUpdate();
        if (rows > 0) {
            conn.commit();
            return rows;
        }
    } catch (Exception e) {
        e.printStackTrace();
        try {
            if (conn != null) {
                conn.rollback();  // rollback 
            }
        } catch (SQLException se) {
            se.printStackTrace();
        }
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return rows;
}



}

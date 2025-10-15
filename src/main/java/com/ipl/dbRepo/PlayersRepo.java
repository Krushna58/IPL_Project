package com.ipl.dbRepo;
import com.ipl.dbConnection.*;
import com.ipl.entity.PlayersEntity;

import java.util.*;
import java.sql.*;

// players sql opration 
public class PlayersRepo{

  
   private final SqlConnection db = new SqlConnection();
      
   
   
   //  View all players  ========================================================================================
		public List<PlayersEntity> viewPlayers() {
		List<PlayersEntity> pList = new ArrayList<>();
		 	Connection conn = null;
    		PreparedStatement ps = null;
    		ResultSet rs = null;

    		String query = "SELECT * from Players";

    		try {
        		conn = db.getConnection();
        		ps = conn.prepareStatement(query);

        		rs = ps.executeQuery();

        		while (rs.next()) {
            			PlayersEntity players = new PlayersEntity();
            			players.setP_ID(rs.getInt("P_ID"));
            			players.setT_ID(rs.getInt("T_ID"));
            			players.setP_Name(rs.getString("P_Name"));
            			players.setRole(rs.getString("Role"));
            			players.setInns(rs.getInt("Inns"));
            			players.setNot_Out(rs.getInt("Not_Out"));
            			players.setRuns(rs.getInt("Runs"));
            			players.setHighest_Score(rs.getInt("Highest_Score"));
            			players.setMatches(rs.getInt("Matches"));

            			pList.add(players);
        		}

    		} catch (Exception e) {
			            e.printStackTrace();
    		} finally {
        		try {
            			if (rs != null) rs.close();
            			if (ps != null) ps.close();
            			if (conn != null) conn.close();
        		} catch (SQLException e) {
            	    e.printStackTrace();
        		}
    		}

    		return pList;
		}
	//----------------------------------------------------------------------------------------------------------------------
   
   
   
  		
//  View players by team ===============================================================================================================
		public List<PlayersEntity> viewPlayersByIdAndSeason(int t_id, int season) {
		List<PlayersEntity> pList = new ArrayList<>();

    		String query = "SELECT p.P_ID, p.T_ID, p.P_Name, p.Role, p.Inns, p.Not_Out, " +
                  		 "p.Runs, p.Highest_Score, p.Matches, t.T_Name, t.City, t.Season " +
                   		"FROM Players p JOIN Teams t ON p.T_ID = t.T_ID " +
                   		"WHERE t.T_ID = ? AND t.Season = ?";

    	 	Connection conn = null;
    		PreparedStatement ps = null;
    		ResultSet rs = null;

    		try {
        		conn = db.getConnection();
        		ps = conn.prepareStatement(query);

        		// set parameters
        		ps.setInt(1, t_id);
        		ps.setInt(2, season);

        		rs = ps.executeQuery();

        		while (rs.next()) {
            			PlayersEntity players = new PlayersEntity();
            			players.setP_ID(rs.getInt("P_ID"));
            			players.setT_ID(rs.getInt("T_ID"));
            			players.setP_Name(rs.getString("P_Name"));
            			players.setRole(rs.getString("Role"));
            			players.setInns(rs.getInt("Inns"));
            			players.setNot_Out(rs.getInt("Not_Out"));
            			players.setRuns(rs.getInt("Runs"));
            			players.setHighest_Score(rs.getInt("Highest_Score"));
            			players.setMatches(rs.getInt("Matches"));

            			pList.add(players);
        		}

    		} catch (Exception e) {
                e.printStackTrace();
    		} finally {
        		try {
            			if (rs != null) rs.close();
            			if (ps != null) ps.close();
            			if (conn != null) conn.close();
        		} catch (SQLException e) {
            		
                e.printStackTrace();
        		}
    		}

    		return pList;
		}
	//----------------------------------------------------------------------------------------------------------------------
	
//======================================================================			

// insert new player insertPlayer(entity);
public int insertPlayer(PlayersEntity player) {
    String sql = "INSERT INTO Players (T_ID, P_Name, Role, Inns, Not_Out, Runs, Highest_Score, Matches) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    int generatedKey = 0;

    try (Connection conn = db.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

        ps.setInt(1, player.getT_ID());
        ps.setString(2, player.getP_Name());
        ps.setString(3, player.getRole());
        ps.setInt(4, player.getInns());
        ps.setInt(5, player.getNot_Out());
        ps.setInt(6, player.getRuns());
        ps.setInt(7, player.getHighest_Score());
        ps.setInt(8, player.getMatches());

        int rows = ps.executeUpdate();

        if (rows > 0) {
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    generatedKey = rs.getInt(1);  
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return generatedKey;  //  0 if not inserted
}


//==========================================================================


//==========================================================================
	// delete player and return true
		public boolean deletePlayer(int season,int teamID,String pName){
		String sql = "Delete p from Players p join Teams t on p.T_ID = t.T_ID where t.Season = ? and p.T_ID = ? and p.P_Name = ?";
			
		try(Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1,season);
			ps.setInt(2,teamID);
			ps.setString(3,pName);

			int rows = ps.executeUpdate();

			if(rows > 0){
				return true;	
			}
		
		} catch (SQLException e){
			e.printStackTrace();	
		}
		return false;
		
		}

//========================================================================================

//==========================================================================
	// delete player and return true
		public int deletePlayerById(int id){
		String sql = "Delete from Players where P_ID = ?";
			int rows =0;
		try(Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
			ps.setInt(1,id);
			rows = ps.executeUpdate();

			if(rows > 0){
				return rows;	
			}
		
		} catch (SQLException e){
			e.printStackTrace();	
		}
		return rows;
		
		}

//========================================================================================

// update existing player by P_ID
public int updatePlayer(PlayersEntity player) {
    String sql = "UPDATE Players SET T_ID = ?, P_Name = ?, Role = ?, Inns = ?, Not_Out = ?, Runs = ?, Highest_Score = ?, Matches = ? WHERE P_ID = ?";
    int updatedPlayerId = 0;

    try (Connection conn = db.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, player.getT_ID());
        ps.setString(2, player.getP_Name());
        ps.setString(3, player.getRole());
        ps.setInt(4, player.getInns());
        ps.setInt(5, player.getNot_Out());
        ps.setInt(6, player.getRuns());
        ps.setInt(7, player.getHighest_Score());
        ps.setInt(8, player.getMatches());
        ps.setInt(9, player.getP_ID());  

        int rows = ps.executeUpdate();
        if (rows > 0) {
            updatedPlayerId = player.getP_ID();  
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return updatedPlayerId;  // return P_ID
}



//------------------------------------------------

//========================================================================================

		public boolean updatePlayerName(int season,int teamID,String oldPlayerName, String newPlayerName){
			String sql = "UPDATE Players p JOIN Teams t ON p.T_ID= t.T_ID SET p.P_Name = ? WHERE t.Season = ? AND p.T_ID= ? AND p.P_Name = ?";
			
			try(Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)){
			
				ps.setString(1, newPlayerName);
				ps.setInt(2, season);
				ps.setInt(3, teamID);
				ps.setString(4, oldPlayerName);
		
				int rows = ps.executeUpdate();
				
				if(rows > 0){
					return true;	
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return false;
		
		}
//=============================================================================================
}

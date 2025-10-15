package com.ipl.dbRepo;
import com.ipl.dbConnection.SqlConnection;
import com.ipl.entity.TeamEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.*;
import com.ipl.dbConnection.LdapConnection;
import javax.naming.directory.*;
import javax.naming.NamingException;
import javax.naming.NamingEnumeration;

public class SqlTeamsRepo{

			private final SqlConnection db = new SqlConnection();
			private final LdapConnection lc = new LdapConnection(); 
			private final LdapTeamsRepo ldapTeamsRepo = new LdapTeamsRepo();

    			//Add a new team-------------------------------------------------------------
			 public int addTeam(TeamEntity team) {
		  int generatedId = -1;
				String query = "INSERT INTO Teams (T_Name, City, Season) VALUES (?, ?, ?)";
	                        Connection conn = null;
                         	PreparedStatement ps = null;
                         	ResultSet rs = null;				
        			try{
				conn = db.getConnection();
				ps = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				conn.setAutoCommit(false);

            			ps.setString(1, team.getT_Name());
            			ps.setString(2, team.getCity());
           			  ps.setInt(3, team.getSeason());

            			int rows = ps.executeUpdate();

				   
          			 if (rows > 0) {
					ResultSet generatedKeys = ps.getGeneratedKeys();
					if(generatedKeys.next()){
						generatedId = generatedKeys.getInt(1);
					
        	                        if(ldapTeamsRepo.addTeamInLdap(team,generatedId)){
	                                        System.out.println("Team added successfully in LDAP");
                                       		 conn.commit();
                                        	return generatedId;
                               		 	}
					} 
            			}
				//return "failed to add team";
   			 } catch (SQLException e){
				System.out.println(e.getMessage());
				try{
					conn.rollback();
				} catch(SQLException e1){
					System.out.println(e1.getMessage());
				}
			} catch (NamingException e){
				System.out.println(e.getMessage());
				try{
					conn.rollback();	
				} catch (SQLException e1){
					System.out.println(e1.getMessage());
				}
			}finally{
			           try {
  //                                       if (rs != null) rs.close();
                                         if (ps != null) ps.close();
                                         if (conn != null) conn.close();
                                 } catch (Exception e) {
                                         e.printStackTrace();
                                         }

			
			}
			return generatedId;
			}
//--------------------------------------------------------------------------------------------------------------------------------
// update team table details 
public int updateTeam(TeamEntity team) {
    Connection conn = null;
    PreparedStatement ps = null;
    int rows = 0;

    try {
        conn = db.getConnection();
        conn.setAutoCommit(false);

        String updateQuery = "UPDATE Teams SET T_Name = ?, City = ?,Season = ? WHERE T_ID = ?";
        ps = conn.prepareStatement(updateQuery);

        ps.setString(1, team.getT_Name());
        ps.setString(2, team.getCity());
         ps.setInt(3, team.getSeason());
        ps.setInt(4, team.getT_ID());
       

         rows = ps.executeUpdate();
        

        if (rows > 0) {
            conn.commit();
            return rows;  
        } else {
            return rows;  //
        }
    } catch (SQLException e) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException rollbackEx) {
            rollbackEx.printStackTrace();
        }
        e.printStackTrace();
        return rows;
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
  
}


//--------------------------------------------------------------------------------------------------------------------------------

    			//  View all teams return list
    			public List<TeamEntity> viewTeams() {
    			List<TeamEntity> teams = new ArrayList<>();
    			String query = "SELECT T_ID, T_Name, City, Season FROM Teams";

    			try (Connection conn = db.getConnection();
         		PreparedStatement ps = conn.prepareStatement(query);
         		ResultSet rs = ps.executeQuery()) {

        		while (rs.next()) {
            		// Create entity using setters
            		TeamEntity team = new TeamEntity();
            		team.setT_ID(rs.getInt("T_ID"));
            		team.setT_Name(rs.getString("T_Name"));
            		team.setCity(rs.getString("City"));
            		team.setSeason(rs.getInt("Season"));

            		teams.add(team);
        		}

    			} catch (Exception e) {
        		System.out.println("Error viewing teams: " + e.getMessage());
    			}

    			return teams;  
			}


//------------------------------------------------------------------------------------------------------------------------------------------------
//------------------------------------------------------------------------------------------------------------------------------------------------
			// Update team name in sql and ldap
			public String updateTeamName(int season, String oldName, String newName) {
    			Connection conn = null;
    			PreparedStatement ps = null;
    			ResultSet rs = null;

    			try {
        		// Get connection
        		conn = db.getConnection();

        		// ====== Update Team Name ======
        		String updateQuery = "UPDATE Teams SET T_Name = ? WHERE T_Name = ? AND Season = ?";
			conn.setAutoCommit(false);
        		ps = conn.prepareStatement(updateQuery);
        		ps.setString(1, newName);
        		ps.setString(2, oldName);
        		ps.setInt(3, season);

        		int rows = ps.executeUpdate();
			System.out.println(rows);
			String generatedId= getTeamIdByName(oldName);
        		if (rows > 0) {
				if(ldapTeamsRepo.updateCnInLdap(oldName,newName,generatedId)){
			
				conn.commit();
          		 // System.out.println(" Team name updated from " + oldName + " to " + newName + " in Season " + season);
				return "Team Name Updated successfully";
				}
        		}

    			} catch (NamingException e) {
				e.printStackTrace();
				try{
					conn.rollback();
				} catch(SQLException e1){
					e1.printStackTrace();
				}
    			}catch (SQLException sqlE){
				sqlE.printStackTrace();
				try{
					conn.rollback();
				} catch(SQLException sqlE1) {
				
					sqlE1.printStackTrace();
				}
				
		
			} 
			
			finally {
        			try {
 //           				if (rs != null) rs.close();
            				if (ps != null) ps.close();
            				if (conn != null) conn.close();
        			} catch (Exception e) {
					e.printStackTrace();	
        				}
    			}
			return "failed to update ";
	
			}


//-----------------------------------------------------------------------------------------------------------------------


		// Method to validate and return true in a season if not found return false
			public boolean getValidTeamInSeason(String teamName, int season) {
    			Connection conn = null;
    			PreparedStatement ps = null;
    			ResultSet rs = null;

    			try {
        			conn = db.getConnection();
        			String query = "SELECT T_Name FROM Teams WHERE T_Name = ? AND Season = ?";
        			ps = conn.prepareStatement(query);
        			ps.setString(1, teamName);
        			ps.setInt(2, season);
        			rs = ps.executeQuery();

        		if (rs.next()) {
				return true;
        		} 

    			} catch (Exception e) {
			e.printStackTrace();
    			} finally {
        			try {
            				if (rs != null) rs.close();
            				if (ps != null) ps.close();
            				if (conn != null) conn.close();
        			} catch (Exception e) {
					e.printStackTrace();
       	 			}
    			}
			return false;
			}

//---------------------------------------------------------------------
			// Method to valid Season and return true if prasent
			public boolean getValidSeason(int season) {
    			Connection conn = null;
    			PreparedStatement ps = null;
    			ResultSet rs = null;

    			try {
        			conn = db.getConnection();
        			String query = "SELECT Season FROM Season WHERE Season = ?";
        			ps = conn.prepareStatement(query);
        			ps.setInt(1, season);
        			rs = ps.executeQuery();

        			if (rs.next()) {
				return true;
       	 			} 

    			} catch (Exception e) {
        			System.out.println("Error checking season: " + e.getMessage());
        			return false;
    			} finally {
        			try {
            				if (rs != null) rs.close();
            				if (ps != null) ps.close();
            				if (conn != null) conn.close();
        			} catch (Exception e) {
            				System.out.println("Error closing resources: " + e.getMessage());
        			}
    			}
			return false;
			}


//------------------------------------------------------
// Method to get T_ID by Team Name and return as String
    public String getTeamIdByName(String teamName) throws SQLException {
        String teamId = null;

        // Get connection from your DBConnection class
        Connection conn = db.getConnection();

        String query = "SELECT T_ID FROM Teams WHERE T_Name = ?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, teamName);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    teamId = rs.getString("T_ID");
		
                }
		
            }
        }
	System.out.println(teamId + "teamid in getTeanByName method");
        return teamId; // will return null if no match found
    }
//-----------------------------------------------------------

    			//  Update city name
    			public String updateCityName(int season, String teamName, String newCity) {
			String query = "UPDATE Teams SET City = ? WHERE T_Name = ? AND Season = ?";
			Connection conn = null;
			PreparedStatement ps = null;
       	 		try{
				conn = db.getConnection();
				ps = conn.prepareStatement(query);
            			ps.setString(1, newCity);
            			ps.setString(2, teamName);
            			ps.setInt(3, season);

            		int rows = ps.executeUpdate();
            		if (rows > 0) {
               			// System.out.println(" Updated city for team: " + teamName);
				
					 return "City name updated successfully";
				
            		} 
        		} catch (Exception e) {
				e.printStackTrace();
        		}
			return "Failed to update city name";
    			}


//========================================================================================================================================

    			// Delete team using name and season--------------------------------------------------
    		public int deleteTeam(TeamEntity team) {
			Connection conn = null;
			PreparedStatement ps = null;
        		try{
            			conn = db.getConnection();
				
				ps = conn.prepareStatement("DELETE FROM Teams WHERE T_Name = ? AND Season = ?");
				conn.setAutoCommit(false);

				          ps.setString(1, team.getT_Name());
                  ps.setInt(2, team.getSeason());

            			int rows = ps.executeUpdate();
            			if (rows > 0) {
            			// if(ldapTeamsRepo.deleteTeamFromLdapByCn(team.getT_Name())){
						          conn.commit();
						          return rows;
					
						        //  }
				
            		}
                         } catch (SQLException e){
                                System.out.println(e.getMessage());
                                try{
                                	conn.rollback();
                                } catch(SQLException e1){
                                        System.out.println(e1.getMessage());
                                }
                        }
			  /*	 catch (NamingException e){
                                System.out.println(e.getMessage());
                                try{
                                        conn.rollback();
                                } catch (SQLException e1){
                                        System.out.println(e1.getMessage());
                                }
			
                        }
			*/

			              	finally{
                                   try {
                                   //      if (rs != null) rs.close();
                                         if (ps != null) ps.close();
                                         if (conn != null) conn.close();
                                    } catch (Exception e) {
                                         e.printStackTrace();
                                         }

                        }
		       	
			      return 0;
    			}
    
    
//====================================================================
// Delete team by Team ID and Season (in-use)
public int deleteTeamById(int teamId) {
    Connection conn = null;
    PreparedStatement ps = null;
    int rows = 0;

    try {
        conn = db.getConnection();
        conn.setAutoCommit(false);

        String sql = "DELETE FROM Teams WHERE T_ID = ?";
        ps = conn.prepareStatement(sql);

        ps.setInt(1, teamId);
       

        rows = ps.executeUpdate();

        if (rows > 0) {
              conn.commit();
              if(ldapTeamsRepo.deleteTeamFromLdap(teamId)){
              return rows;
            }
        } else {
            conn.rollback();
            System.out.println("No team found for given T_ID and Season.");
        }

    } catch (Exception e) {
        e.printStackTrace();
        
        try {
            if (conn != null) conn.rollback();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    } finally {
        try {
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    return 0;

   
}



//=========================================================
			// return team id 
		public int getTeamId(int season, String teamName) {
    			int teamId = -1;

    			String sql = "SELECT T_ID FROM Teams WHERE Season = ? AND T_Name = ?";

    			try (Connection conn = db.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {

        			ps.setInt(1, season);
        			ps.setString(2, teamName);

        			try (ResultSet rs = ps.executeQuery()) {     
            				if (rs.next()) {
                				teamId = rs.getInt("T_ID");
            				}
        			}
    			} catch (SQLException e) {
        			e.printStackTrace();
    			}

    			return teamId;
			}		
			
//----------------------------------
}

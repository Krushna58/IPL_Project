package com.ipl.dbConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class SqlConnection{

			public static final String URL = "jdbc:mysql://localhost:3306/IPL_Project";
		//	 public static final String URL = "jdbc:mysql://localhost:3306/ipl";

		
			public static final String User = "krushnab";
			public static final String Password = "root";
	
			// method return the mysql connection
			public Connection getConnection() {
			Connection conn = null;
	
			try{	
			    Class.forName("com.mysql.cj.jdbc.Driver");
				// get conection 
				conn = DriverManager.getConnection(URL,User,Password);
		
	  } catch (ClassNotFoundException e) {
        System.out.println("MySQL JDBC Driver not found.");
        e.printStackTrace();
    } catch (SQLException e) {
        System.out.println("Database connection failed.");
        e.printStackTrace();
    }
	
			return conn;
	}

}

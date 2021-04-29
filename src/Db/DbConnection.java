package Db;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;

import java.sql.SQLException;



public class DbConnection {

		private static final String DB_CONNECTION = "jdbc:oracle:thin:@//myoracle12c.senecacollege.ca/oracle12c";
		public static final String PORT = "1521"; // Database_Port_Number
		public static final String SERVICE_NAME = "oracle12c";
		
	    // Database credentials
		public static final String USERID = "cjv805_202a18" ;
		public static final String PASSWORD = "22995234";
		 
	   public Connection getConnection(){
		   Connection conn = null;
			try {
				// load the driver
				Driver driver = new oracle.jdbc.driver.OracleDriver();
				DriverManager.registerDriver(driver);
				
				conn = DriverManager.getConnection(DB_CONNECTION, USERID, PASSWORD);
		
			}
			
			 catch (SQLException e) {
				e.printStackTrace();
				System.out.println("Exception: " + e.getMessage());
			}
			
			return conn;
			
	   }

	} 


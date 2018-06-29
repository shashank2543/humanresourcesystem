package com.girnar.humanResource.Jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class DataBaseOfEmployees {
	public static String JDBC_DRIVER ;
	public static String TARGET_DB_URL;
	public static String OUTPUT_FOLDER_PATH ;
	public static String INFORMATION_DB_URL ;
	public static String USER ;
	public static String PASS ;
	Connection connection = null;
	public static ResourceBundle bundle = null;
	
	DataBaseOfEmployees(){
		 
		 bundle=  ResourceBundle.getBundle("db");
		 JDBC_DRIVER = bundle.getString("JDBC_DRIVER");
		 TARGET_DB_URL = bundle.getString("TARGET_DB_URL");
		 USER = bundle.getString("USER");
		 PASS = bundle.getString("PASS");
		 
		  try{
			    
				connection = getDbTxConnection(TARGET_DB_URL, USER, PASS);
				//connection.setAutoCommit(false);
			    }catch(Exception e){
			    	e.printStackTrace();
			    }
	 }
	private Connection getDbTxConnection(String dbUrl, String username, String password) 
	{
		Connection conn = null;
		 try{
		      Class.forName(JDBC_DRIVER);
		      System.out.println("Connecting to database... ");
		      conn = DriverManager.getConnection(dbUrl,username,password);
		     }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }
		 return conn;
	}
	public  Connection getConnection() {
		return connection;
	}
	

}

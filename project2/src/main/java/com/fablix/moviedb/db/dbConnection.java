package com.fablix.moviedb.db;
import java.sql.*;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class dbConnection {
	private static final String configFile = "dbcp.properties";  
	private static final Log log = LogFactory.getLog(dbConnection.class);  
	private static DataSource dataSource;

//	
//	private static final String URL = "jdbc:mysql://127.0.0.1:3306/moviedb";
//	private static final String USER = "root";
//	private static final String PASSWORD = "1993zhangtianle";
	
//	private static Connection connection = null;
	static {  
		  try {  

			  BasicDataSource ds = new BasicDataSource();
			  ds.setDriverClassName("com.mysql.jdbc.Driver");
			  ds.setUsername("root");
			  ds.setPassword("123456");
			  ds.setUrl("jdbc:mysql://127.0.0.1:3306/moviedb");
			  ds.setMaxActive(30);
			  ds.setMaxIdle(10);
			  ds.setMaxWait(1000);
			  dataSource = ds;
			  
			   
		  } catch (Exception e) {  
			   log.error("initial connection to database failed£º" + e);  
		  }  
	}  
		
	public static final Connection getConnection(){			
			Connection conn = null;  		
			try {
				// Incorporate mySQL driver
				conn = dataSource.getConnection();
				conn.setAutoCommit(false);
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				log.error("fail to connect to the database: " + e);
				//System.err.println(e.getSQLState());
//				System.out.println("An error occured when connecting to database!");
			} 
			
			return conn;
	}
	
	public static void rsstmtClose(ResultSet rs, Statement ps)
	{
		if (rs!=null){
			try{
				rs.close();
				rs=null;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Close ResultSet failed!");
			}
		}
		
		if (ps!=null){
			try{
				ps.close();
				ps=null;
			}catch(SQLException e){
				e.printStackTrace();
				System.out.println("Close Statement failed!");
			}
		}
	}
	
	public static void connectionClose(Connection conn){
		try {  
			   if (conn != null && !conn.isClosed()) {  
			    conn.setAutoCommit(true);  
			    conn.close();  
			   }  
		}catch (SQLException e) {  
			   log.error("fail to close the connection: " + e);  
		}  
			   
	}
	
	
	public static boolean ifUserRight(String username){
		
		if (username.equals("root")){
			return true;
		}else{
			return false;
		}
	
	}
	
	public static boolean ifPWDRight(String password){
		
		if (password.equals("1993zhangtianle")){
			return true;
		}else{
			return false;
		}
	
	}

}

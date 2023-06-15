package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import metier.chercheur;

public class Logindao {
	
public boolean login(chercheur c) throws ClassNotFoundException{
		
		Connection conn=Connexion.getConnection();
		boolean status = false;
        Class.forName("com.mysql.jdbc.Driver");
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("select * from chercheur where username = ? and password = ? "); 
            preparedStatement.setString(1,c.getUsername());
            preparedStatement.setString(2,c.getPassword());

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
            	System.out.println("username  : " + rs.getString("username"));
            	status = true;
            }
           
        } 
         catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		return status;
	}
	public String role(chercheur c) throws ClassNotFoundException{
		Connection conn=Connexion.getConnection();
		String role = null;
		try{
            PreparedStatement preparedStatement = conn.prepareStatement("select profil from chercheur where username = ? and password = ? "); 
            preparedStatement.setString(1,c.getUsername());
            preparedStatement.setString(2,c.getPassword());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
            	role = rs.getString("profil");	
				System.out.println("valide");
            }
		}
		catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		
		return role;
		
		
	}
	
	public String username(chercheur c) throws ClassNotFoundException{
		Connection conn=Connexion.getConnection();
		String username = null;
		try{
            PreparedStatement preparedStatement = conn.prepareStatement("select username from chercheur where username = ? and password = ? "); 
            preparedStatement.setString(1,c.getUsername());
            preparedStatement.setString(2,c.getPassword());
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next()) {
            	username = rs.getString("username");	
				System.out.println("valide");
            }
		}
		catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
		
		return username;
		
		
	}
	
	
	
	 private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	           if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable test = ex.getCause();
	                while (test != null) {
	                    System.out.println("Cause: " + test);
	                    test = test.getCause();
	                }
	            }
	        }
	    }
}
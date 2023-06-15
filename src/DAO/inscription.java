package DAO;

import java.sql.SQLException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import metier.chercheur;

public class inscription {

	public int register(chercheur chercheur) throws ClassNotFoundException {
	     Connection connection=Connexion.getConnection();
	
	        int result = 0;

	        

	        try {
	        		
	            // Step 2:Create a statement using connection object
	            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO chercheur(username,prenom, email,tel,Profil,password,image) VALUES(?,?,?,?,?,?,?)");
	       
	            preparedStatement.setString(1, chercheur.getUsername());
	            System.out.println("1");
	            preparedStatement.setString(2, chercheur.getPrenom());
	            preparedStatement.setString(3, chercheur.getEmail());
	            preparedStatement.setString(4, chercheur.getTel());
	            preparedStatement.setString(5, chercheur.getProfil());
	            preparedStatement.setString(6, chercheur. getPassword());
	            preparedStatement.setString(7, chercheur. getImage());
	            
	            System.out.println("valide");
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            result = preparedStatement.executeUpdate();

	        } catch (SQLException e) {
	        	System.out.println("NO");
	            // process sql exception
	            printSQLException(e);
	        }
	        return result;
	    }

	    private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.out.println("NO2");
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
}

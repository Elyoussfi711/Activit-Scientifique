package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import metier.Laboratoire;
import metier.axe_equipe;
import metier.chercheur;


public class GestionAxeEquipe {
	Connection conn =Connexion.getConnection(); 
	
	/************* add axe equipe par admin************/
	 
	public void addAxeEquipe(axe_equipe a) { 
		try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO axe_equipe(nom_axe_equipe) VALUES(?)");
			ps.setString(1, a.getNom_axe_equipe());
			ps.executeUpdate();
			ps.close();
			System.out.println("valide");
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
	}
	/**************** Liste axe equipe ****************/
	
	public List<axe_equipe> axe_equipe() {
		
		List<axe_equipe> axe_equipes=new ArrayList<axe_equipe>();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from axe_equipe");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				axe_equipe axe_eq=new axe_equipe();
				axe_eq.setId_axe_equipe(rs.getInt("id_axe_equipe"));
				axe_eq.setNom_axe_equipe(rs.getString("nom_axe_equipe"));
				axe_equipes.add(axe_eq);
				System.out.println(axe_eq);
				System.out.println("valide");
			}
			ps.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return axe_equipes;
	}
	/*****    delete axe_equipe   ********/
	public boolean deleteAxeEquipe(int id) throws SQLException{
		boolean rowDelete ;
		
		try (
			PreparedStatement ps= conn.prepareStatement("DELETE FROM axe_equipe where id_axe_equipe = ?");){
			ps.setInt(1, id);
			rowDelete=ps.executeUpdate()>0;
			ps.close();
		}
			return rowDelete ;
		
		}
	/***************** geter l' id par le nom**********/
	
     public axe_equipe getAxe_equipe(String nom_axe_equipe) {

        axe_equipe eqii=new axe_equipe();
   try {
	             PreparedStatement ps= conn.prepareStatement("select id_axe_equipe from axe_equipe where   nom_axe_equipe =  ?");
	             ps.setString(1, nom_axe_equipe);
	             ResultSet rs = ps.executeQuery();
	            if  (rs.next()) {
		               eqii.setNom_axe_equipe(rs.getString("nom_axe_equipe"));
		               System.out.println("in");
		               ps.close();
	                   }
                   }catch (SQLException e) {
	                  e.printStackTrace();
                   }
              return eqii;
                  }
     
     
     
     
     /************* gerer les info d'axe equipe par id  pour modification ***************/
 	
 	/*public axe_equipe getAxe_equipe(int id_axe_equipe) {
 		
 		axe_equipe axe=new axe_equipe();
 		
 		try {
 			PreparedStatement ps= conn.prepareStatement("select * from axe_equipe where id_axe_equipe = ?");
 			ps.setInt(1, id_axe_equipe);
 			ResultSet rs = ps.executeQuery();
 			System.out.println(ps);
 			
                if  (rs.next()) {
 				
 				axe.setId_axe_equipe(rs.getInt("id_axe_equipe"));
 				axe.setNom_axe_equipe(rs.getString("nom_axe_equipe"));
 			    
 			System.out.println("valide modification");
 				ps.close();
 			}
 			
 		}catch (SQLException e) {
 			 e.printStackTrace();
 			
 		}
 		return axe;
 		
 		
 	}*/
 	public axe_equipe getAxe_equipe(int id) { //ok
		// TODO Auto-generated method stub
		axe_equipe axe_eq=new axe_equipe();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from axe_equipe where id_axe_equipe = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				axe_eq.setId_axe_equipe(rs.getInt("id_axe_equipe"));
				axe_eq.setNom_axe_equipe(rs.getString("nom_axe_equipe"));
				System.out.println("valideeee");
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return axe_eq;
	}
 	
 	
 	
	 /*******    modifier axe equipe ********/
       
  	/*public boolean updateAxeEquipe(axe_equipe eq) throws SQLException  {
		 boolean rowUpdated ;
		try (
			     PreparedStatement ps= conn.prepareStatement("UPDATE axe_equipe SET nom_axe_equipe=? where id=?");){
			ps.setString(1, eq.getNom_axe_equipe());
			ps.setInt(2,eq.getId_axe_equipe());
			
			rowUpdated=ps.executeUpdate()>0;
			ps.close();
			}
		
		return rowUpdated;
	}*/
	
   
	/************   modifier 2 axe equipe       *************/
     public axe_equipe updateAxe_equipe(axe_equipe a) { //ok
	  try {
		   PreparedStatement ps= conn.prepareStatement("UPDATE axe_equipe SET nom_axe_equipe=? WHERE id_axe_equipe=?");
		   ps.setString(1,a.getNom_axe_equipe());
		   ps.setInt(2, a.getId_axe_equipe());
		   ps.executeUpdate();
		     ps.close();
		    System.out.println("valide");
	    }
	    catch (SQLException e) {
	 	   e.printStackTrace();
	      }
	    return a;
}
     /************   get nom_axeequipe par id_axe_equipe            **********/
 	public String getNomAxeEquipe(int idaxeequipe) {
		
		String axe=null;
		try {
			PreparedStatement ps= conn.prepareStatement("select nom_axe_equipe from axe_equipe where id_axe_equipe = ?");
			ps.setInt(1  , idaxeequipe );
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				axe=rs.getString("nom_axe_equipe");
				
				ps.close();
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return axe;
	}
     
     
}
















	

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Laboratoire;
import metier.axe_equipe;

public class GestionLaboratoire {
  
	Connection conn =Connexion.getConnection();
	  
	
	public int addlaboratoire(Laboratoire lab) {
		
		int reslt=0;
		try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO laboratoire(nom_Labo,chef_Labo) VALUES(?,?)");
			ps.setString(1, lab.getNom_Labo());
			ps.setString(2, lab.getChef_Labo());
			ps.executeUpdate();
			ps.close();
			System.out.println("valide");
		}
		catch (SQLException ex) {
			 ex.printStackTrace();
		}
		return reslt;
		
	}
	public List<Laboratoire> listlabo() {
		
		List<Laboratoire> labos=new ArrayList<Laboratoire>();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from laboratoire");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Laboratoire l = new Laboratoire();
				l.setId_Laboratoire(rs.getInt("id_Laboratoire"));
				l.setNom_Labo(rs.getString("nom_Labo"));
				l.setChef_Labo(rs.getString("chef_Labo"));
				labos.add(l);	
				System.out.println(l);
				System.out.println("valide");
			}
		   ps.close();
		
		}catch(SQLException e){
			e.printStackTrace();
		}
		return labos;
	}

	
	
	public Laboratoire getId_Laboratoire(String nom) {
		// TODO Auto-generated method stub
		Laboratoire l=new Laboratoire();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from laboratoire where nom_Labo = ?");
			ps.setString(1, nom);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				
				l.setId_Laboratoire(rs.getInt("id_laboratoire"));
				l.setNom_Labo(rs.getString("nom_labo"));
				l. setChef_Labo(rs.getString("chef_labo"));
				System.out.println("valide");
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return l;
	}
	public boolean deleteLaboratoire(int id) throws SQLException{
		boolean rowDelete ;
		
		try (
			PreparedStatement ps= conn.prepareStatement("DELETE FROM laboratoire where id_Laboratoire = ?");){
			ps.setInt(1, id);
			rowDelete=ps.executeUpdate()>0;
			ps.close();
		}
			return rowDelete ;
		
		}
	
	/*******   geter les information de laboratoire     *****/
	
	public Laboratoire getLaboratoire(int id) {
	
		Laboratoire l=new Laboratoire();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from laboratoire where id_Laboratoire = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				l.setId_Laboratoire(rs.getInt("id_laboratoire"));
				l.setNom_Labo(rs.getString("nom_labo"));
				l.setChef_Labo(rs.getString("chef_labo"));
				System.out.println("valide");
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return l;
	}
	
	/********  modifier laboratoire        *********/
	
	   public Laboratoire updateLaboratoire(Laboratoire lab) { //ok
			  try {
				   PreparedStatement ps= conn.prepareStatement("update laboratoire SET nom_Labo = ?, chef_Labo = ?" + " where id_Laboratoire = ?");
				   ps.setString(1,lab.getNom_Labo());
				   ps.setString(2,lab.getChef_Labo());
				   ps.setInt(3, lab.getId_Laboratoire());
				   ps.executeUpdate();
				     ps.close();
				    System.out.println("valide update Laboratoire");
			    }
			    catch (SQLException e) {
			 	   e.printStackTrace();
			      }
			    return lab;
		}
		
}
	 


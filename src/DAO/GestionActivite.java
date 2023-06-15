package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Equipe;
import metier.Laboratoire;
import metier.activitesc;
import metier.axe_equipe;
import metier.chercheur;

public class GestionActivite {
	Connection conn =Connexion.getConnection();
	/**********      ajourter les activites     ***************/
	public void addActivite(activitesc c) {
		
		
		try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO activitesc(type_ac,a_bstract,auteur,date,titre,type_pub,type_sout,budget,initule,jury,lieu,niveau,type_enc,description,duree,idchercheur,titre_enc,justificatif,image) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			//ps.setBlob(1, c.getJustificatif());
			ps.setString(1, c.getType_ac());
			ps.setString(2, c.getA_bstract());
			ps.setString(3, c.getAuteur());
			ps.setString(4, c.getDate());
			ps.setString(5, c.getTitre());
			ps.setString(6, c.getType_pub());
			ps.setString(7, c.getType_sout());
			ps.setString(8, c.getBudget());
			ps.setString(9, c.getInitule());
			ps.setString(10, c.getJury());
			ps.setString(11, c.getLieu());
			ps.setString(12, c.getNiveau());
			ps.setString(13, c.getType_enc());
			ps.setString(14, c.getDescription());
			ps.setString(15, c.getDuree());
			ps.setInt(16, c.getIdchercheur());
			ps.setString(17, c.getTitre_enc());
			ps.setBlob(18, c.getJustificatif());
			ps.setString(19, c.getImage());
			ps.executeUpdate();
			ps.close();
			System.out.println("valide");
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		
	}
	 /******** geter id par le nom******/
	public int getIdChercheur(String username) {
		
		int ch=0;
		try {
			PreparedStatement ps= conn.prepareStatement("select idchercheur from chercheur where username = ?");
			ps.setString(1  , username );
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				ch=rs.getInt("idchercheur");
				
				ps.close();
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return ch;
	}
	/************ liste laboratoire           ************/
	 public List<Laboratoire> Listlaboratoire(){
		  List<Laboratoire> lab=new ArrayList<Laboratoire>();
		  try {
			  PreparedStatement ps= conn.prepareStatement("select nom_Labo from laboratoire");
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  Laboratoire labo=new Laboratoire();
				  labo.setNom_Labo(rs.getString("nom_Labo"));
				  lab.add(labo);
				  System.out.println("valide a sahbi");
				  System.out.println(lab);
			  }
			  ps.close();
		  }
			catch(SQLException e){
				e.printStackTrace();
		  }
		 return lab;
	 } 
	  /************  liste equipe               ***********/
	 public List<Equipe> ListEquipe(){
		  List<Equipe> equ=new ArrayList<Equipe>();
		  try {
			  PreparedStatement ps= conn.prepareStatement("select nom_equipe from equipe");
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  Equipe equi=new Equipe();
				  equi.setNom_equipe(rs.getString("nom_equipe"));
				  equ.add(equi);
				  System.out.println("valide a kolighaaa");
				  System.out.println(equ);
			  }
			  ps.close();
		  }
			catch(SQLException e){
				e.printStackTrace();
		  }
		 return equ;
	 }
	 
     /**************** Liste publication ****************/
		public List<activitesc> listepublication() {
			
			List<activitesc> activi=new ArrayList<activitesc>();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='publication' ");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					activitesc ac=new activitesc();
					ac.setIdactivitesc(rs.getInt("idactivitesc"));
					ac.setType_ac(rs.getString("type_ac"));
					ac.setType_pub(rs.getString("type_pub"));
					ac.setAuteur(rs.getString("auteur"));
					ac.setTitre(rs.getString("titre"));
					ac.setDate(rs.getString("date"));
					ac.setIdchercheur(rs.getInt("idchercheur"));
					ac.setDescription(rs.getString("description"));
					ac.setImage(rs.getString("image"));
					//ac.setJustificatif(rs.getBlob("justificatif"));
					activi.add(ac);
					System.out.println(ac);
					System.out.println("valide");
				}
				ps.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return activi;
		}
		/**********    getid from liste          *******/
		
		public int  getidchercheur() {
			int idchercheur=0;
			List<activitesc> ac=listepublication();
			for(int i=0 ; i<ac.size();i++) {
				 idchercheur=ac.get(i).getIdchercheur();
			}
			return idchercheur;
		}
				
				
			
			
			
		
		
		
		
		/*****    delete publication     ********/
		
		public boolean deletePublication(int id) throws SQLException{
			boolean rowDelete ;
			
			try (
				PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
				ps.setInt(1, id);
				rowDelete=ps.executeUpdate()>0;
				ps.close();
			}
				return rowDelete ;
			
			} 
		/**************** Liste projet de rechercher ****************/
		
		public List<activitesc> listeProjet() {
			
			List<activitesc> activiteprojet=new ArrayList<activitesc>();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='projet de recherche' ");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					activitesc ac2=new activitesc();
					
					ac2.setDate(rs.getString("date"));
					ac2.setTitre(rs.getString("titre"));
					ac2.setBudget(rs.getString("budget"));
					ac2.setDuree(rs.getString("duree"));
					ac2.setIdchercheur(rs.getInt("idchercheur"));
					ac2.setIdactivitesc(rs.getInt("idactivitesc"));
					ac2.setDescription(rs.getString("description"));
				
					//ac.setJustificatif(rs.getBlob("justificatif"));
					activiteprojet.add(ac2);
					System.out.println(ac2);
					System.out.println("valide");
				}
				ps.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return activiteprojet;
		}   
		
		
		
		
/**************** Liste projet de conference ****************/
		
		public List<activitesc> listeConference() {
			
			List<activitesc> activiteconference=new ArrayList<activitesc>();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='conference' ");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					activitesc ac3=new activitesc();
					ac3.setType_ac(rs.getString("type_ac"));
					ac3.setIdactivitesc(rs.getInt("idactivitesc"));
					ac3.setInitule(rs.getString("initule"));
					ac3.setDate(rs.getString("date"));
					ac3.setIdchercheur(rs.getInt("idchercheur"));
					ac3.setLieu(rs.getString("lieu"));
					ac3.setDescription(rs.getString("description"));
					ac3.setImage(rs.getString("image"));
					//ac.setJustificatif(rs.getBlob("justificatif"));
					activiteconference.add(ac3);
					System.out.println(ac3);
					System.out.println("valide");
				}
				ps.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return activiteconference;
		}
		
		
/**************** Liste  soutenance ****************/
		
		public List<activitesc> listeSoutenenace() {
			
			List<activitesc> activitesoutenance=new ArrayList<activitesc>();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='soutenance' ");
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					activitesc ac4=new activitesc();
					ac4.setIdactivitesc(rs.getInt("idactivitesc"));
					ac4.setType_sout(rs.getString("type_sout"));
					ac4.setInitule(rs.getString("initule"));
					ac4.setType_ac(rs.getString("type_ac"));
					ac4.setDate(rs.getString("date"));
					ac4.setLieu(rs.getString("lieu"));
					ac4.setJury(rs.getString("jury"));
					ac4.setIdchercheur(rs.getInt("idchercheur"));
					ac4.setDescription(rs.getString("description"));
					ac4.setImage(rs.getString("image"));
					//ac.setJustificatif(rs.getBlob("justificatif"));
					activitesoutenance.add(ac4);
				    System.out.println(ac4);
					System.out.println("valide soutenance");
				}
				ps.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return activitesoutenance;
		}
		
		/*******  modifier soutenance           ***********/
		
		
		  public activitesc updateSoutenance(activitesc act) { //ok
			  try {
				   PreparedStatement ps= conn.prepareStatement("UPDATE activitesc SET type_sout=? ,initule=?,date=?,lieu=?,jury=?,description=? WHERE idactivitesc=? and type_ac='soutenance");
				   
				   ps.setString(1,act.getType_sout());
				   ps.setString(2, act.getInitule());
				   ps.setString(3, act.getDate());
				   ps.setString(4, act.getLieu());
				   ps.setString(5, act.getJury());
				   ps.setString(6, act.getDescription());
				   
				   ps.executeUpdate();
				     ps.close();
				    System.out.println("valide update soutenance");
			    }
			    catch (SQLException e) {
			 	   e.printStackTrace();
			      }
			    return act;
		}
		  
		  /************* gerer les info de soutenance par id activitesc ***************/
			
			public activitesc getSoutenance(int id) {
				
				activitesc acc=new activitesc();
				
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ? ");
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					System.out.println(ps);
					
		               if  (rs.next()) {
		            	acc.setIdactivitesc(rs.getInt("idactivitesc"));
						acc.setType_sout(rs.getString("type_sout"));
						acc.setInitule(rs.getString("initule"));
						acc.setDate(rs.getString("date"));
						acc.setLieu(rs.getString("lieu"));
						acc.setJury(rs.getString("jury"));
						acc.setDescription(rs.getString("description"));
						
						System.out.println("salit getter d les infs");
					   
						ps.close();
					}
					
				}catch (SQLException e) {
					 e.printStackTrace();
					
				}
				return acc;
				
				
			}
			
			
			/**************      getusername par idchercheur           *********/
			
			
			public String getUsername(int idchercheur) {
				
				String ch=null;
				try {
					PreparedStatement ps= conn.prepareStatement("select username from chercheur where idchercheur = ?");
					ps.setInt(1  , idchercheur );
					ResultSet rs = ps.executeQuery();
					if  (rs.next()) {
						ch=rs.getString("username");
						
						ps.close();
					}
				}
				catch (SQLException e) {
					 e.printStackTrace();
				}
				return ch;
			}
			
			/*****    delete soutenance     ********/
			
			public boolean deletesoutenance(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			/*********    delete conference           ***********/
			
			public boolean deleteconference(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			
			/***********     delet projet de recherche              *********/
			public boolean deleteproject(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			
			
			/***************  liste publication docteur               ***********/
	

			public List<activitesc> listepublicationdocteur(int idchercheur) {
				
				List<activitesc> activi2=new ArrayList<activitesc>();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='publication' and idchercheur=? ");
					ps.setInt(1  , idchercheur );
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						activitesc ac=new activitesc();
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setType_ac(rs.getString("type_ac"));
						ac.setType_pub(rs.getString("type_pub"));
						ac.setAuteur(rs.getString("auteur"));
						ac.setTitre(rs.getString("titre"));
						ac.setDate(rs.getString("date"));
						ac.setIdchercheur(rs.getInt("idchercheur"));
						ac.setDescription(rs.getString("description"));
						ac.setImage(rs.getString("image"));
						//ac.setJustificatif(rs.getBlob("justificatif"));
						activi2.add(ac);
						System.out.println(ac);
						System.out.println("valide");
					}
					ps.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				return activi2;
			}
			
			
			
			
			/***************  liste soutenance docteur               ***********/
			

			public List<activitesc> listesoutenancedocteur(int idchercheur) {
				
				List<activitesc> activi3=new ArrayList<activitesc>();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='soutenance' and idchercheur=? ");
					ps.setInt(1  , idchercheur );
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						activitesc ac=new activitesc();
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setType_ac(rs.getString("type_ac"));
						ac.setType_sout(rs.getString("type_sout"));
						ac.setInitule(rs.getString("initule"));
						ac.setDate(rs.getString("date"));
						ac.setLieu(rs.getString("lieu"));
						ac.setJury(rs.getString("jury"));
						
						ac.setIdchercheur(rs.getInt("idchercheur"));
						ac.setDescription(rs.getString("description"));
						ac.setImage(rs.getString("image"));
						
						//ac.setJustificatif(rs.getBlob("justificatif"));
						activi3.add(ac);
						System.out.println(ac);
						System.out.println("valide");
					}
					ps.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				return activi3;
			}
			
			
	/***************  liste conference docteur               ***********/
			

			public List<activitesc> listeconferencedocteur(int idchercheur) {
				
				List<activitesc> activi4=new ArrayList<activitesc>();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='conference' and idchercheur=? ");
					ps.setInt(1  , idchercheur );
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						activitesc ac=new activitesc();
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setInitule(rs.getString("initule"));
						ac.setDate(rs.getString("date"));
						ac.setLieu(rs.getString("lieu"));
						ac.setDescription(rs.getString("description"));
						ac.setImage(rs.getString("image"));
						
						
						ac.setIdchercheur(rs.getInt("idchercheur"));
						
						//ac.setJustificatif(rs.getBlob("justificatif"));
						activi4.add(ac);
						System.out.println(ac);
						System.out.println("valide");
					}
					ps.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				return activi4;
			}
			
			
/***************  liste publication doctorant               ***********/
			
			
			

			public List<activitesc> listepublicationdoctorant(int idchercheur) {
				
				List<activitesc> activi2=new ArrayList<activitesc>();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='publication' and idchercheur=? ");
					ps.setInt(1  , idchercheur );
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						activitesc ac=new activitesc();
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setType_ac(rs.getString("type_ac"));
						ac.setType_pub(rs.getString("type_pub"));
						ac.setAuteur(rs.getString("auteur"));
						ac.setTitre(rs.getString("titre"));
						ac.setDate(rs.getString("date"));
						ac.setIdchercheur(rs.getInt("idchercheur"));
						ac.setDescription(rs.getString("description"));
						ac.setImage(rs.getString("image"));
						//ac.setJustificatif(rs.getBlob("justificatif"));
						activi2.add(ac);
						System.out.println(ac);
						System.out.println("valide");
					}
					ps.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				return activi2;
			}
			
	/***************  liste conference doctorant               ***********/
			

			public List<activitesc> listeconferencedoctorant(int idchercheur) {
				
				List<activitesc> activi4=new ArrayList<activitesc>();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='conference' and idchercheur=? ");
					ps.setInt(1  , idchercheur );
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						activitesc ac=new activitesc();
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setInitule(rs.getString("initule"));
						ac.setDate(rs.getString("date"));
						ac.setLieu(rs.getString("lieu"));
						ac.setDescription(rs.getString("description"));
						ac.setImage(rs.getString("image"));
						
						
						ac.setIdchercheur(rs.getInt("idchercheur"));
						ac.setImage(rs.getString("image"));
						
						//ac.setJustificatif(rs.getBlob("justificatif"));
						activi4.add(ac);
						System.out.println(ac);
						System.out.println("valide");
					}
					ps.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				return activi4;
			}
			
/***************  liste soutenance docteur               ***********/
			

			public List<activitesc> listesoutenancedoctorant(int idchercheur) {
				
				List<activitesc> activi3=new ArrayList<activitesc>();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='soutenance' and idchercheur=? ");
					ps.setInt(1  , idchercheur );
					ResultSet rs = ps.executeQuery();
					while (rs.next()) {
						activitesc ac=new activitesc();
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setType_ac(rs.getString("type_ac"));
						ac.setType_sout(rs.getString("type_sout"));
						ac.setInitule(rs.getString("initule"));
						ac.setDate(rs.getString("date"));
						ac.setLieu(rs.getString("lieu"));
						ac.setJury(rs.getString("jury"));
						
						ac.setIdchercheur(rs.getInt("idchercheur"));
						ac.setDescription(rs.getString("description"));
						ac.setImage(rs.getString("image"));
						//ac.setJustificatif(rs.getBlob("justificatif"));
						activi3.add(ac);
						System.out.println(ac);
						System.out.println("valide");
					}
					ps.close();
				}
				catch(SQLException e){
					e.printStackTrace();
				}
				return activi3;
			}
			
			public boolean deletepublicationdocteur(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			public boolean deletesoutenancedocteur(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			public boolean deleteconferencedocteur(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			public boolean deletepublicationdoctorant(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			public boolean deletesoutenancedoctorant(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			public boolean deleteconferencedoctorant(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			
		     public activitesc updatepublicationdocteur(activitesc a) { //ok
		   	  try {
		   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET titre = ?, auteur = ? , date=? , type_pub=? , description=?  " + " where idactivitesc = ?");
		   		   ps.setString(1,a.getTitre());
		   		   ps.setString(2, a.getAuteur());
		   		   
		   		 ps.setString(3, a.getDate());
		   		 ps.setString(4, a.getType_pub());
		   		 ps.setString(5, a.getDescription());
		   		ps.setInt(6, a.getIdactivitesc());
		   		   ps.executeUpdate();
		   		     ps.close();
		   		    System.out.println("valide");
		   	    }
		   	    catch (SQLException e) {
		   	 	   e.printStackTrace();
		   	      }
		   	    return a;
		   }
		     
		 	public activitesc getpublicationdocteur(int id) {
		 		
				activitesc ac=new activitesc();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					if  (rs.next()) {
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setAuteur(rs.getString("auteur"));
						ac.setTitre(rs.getString("titre"));
						ac.setDate(rs.getString("date"));
						ac.setType_pub(rs.getString("type_pub"));
						ac.setDescription(rs.getString("description"));
						System.out.println("valide");
					}
				}
				catch (SQLException e) {
					 e.printStackTrace();
				}
				return ac;
			}
		 	
		 	
	public activitesc getsoutenancedocteur(int id) {
		 		
				activitesc ac=new activitesc();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					if  (rs.next()) {
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setType_sout(rs.getString("type_sout"));
						ac.setInitule(rs.getString("initule"));
						ac.setDate(rs.getString("date"));
						ac.setLieu(rs.getString("lieu"));
						ac.setJury(rs.getString("jury"));
						ac.setDescription(rs.getString("description"));
						System.out.println("valide");
					}
				}
				catch (SQLException e) {
					 e.printStackTrace();
				}
				return ac;
			}
	
	   public activitesc updatesoutenancedocteur(activitesc a) { //ok
		   	  try {
		   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET type_sout = ?, initule = ? , date=? , lieu=? , jury=? , description=?  " + " where idactivitesc = ?");
		   		   ps.setString(1,a.getType_sout());
		   		   ps.setString(2, a.getInitule());
		   		   
		   		 ps.setString(3, a.getDate());
		   		 ps.setString(4, a.getLieu());
		   		 ps.setString(5, a.getJury());
		   		 ps.setString(6, a.getDescription());
		   		ps.setInt(7, a.getIdactivitesc());
		   		   ps.executeUpdate();
		   		     ps.close();
		   		    System.out.println("valide");
		   	    }
		   	    catch (SQLException e) {
		   	 	   e.printStackTrace();
		   	      }
		   	    return a;
		   }
	   public activitesc getconferencedocteur(int id) {
	 		
			activitesc ac=new activitesc();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					ac.setIdactivitesc(rs.getInt("idactivitesc"));
					
					ac.setInitule(rs.getString("initule"));
					ac.setDate(rs.getString("date"));
					ac.setLieu(rs.getString("lieu"));
					
					ac.setDescription(rs.getString("description"));
					System.out.println("valide");
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return ac;
		}
	   
	   public activitesc updateconferencedocteur(activitesc a) { //ok
		   	  try {
		   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET initule = ? , date=? , lieu=? , description=?  " + " where idactivitesc = ?");
		   		  
		   		   ps.setString(1, a.getInitule());
		   		   
		   		 ps.setString(2, a.getDate());
		   		 ps.setString(3, a.getLieu());
		   		
		   		 ps.setString(4, a.getDescription());
		   		ps.setInt(5, a.getIdactivitesc());
		   		   ps.executeUpdate();
		   		     ps.close();
		   		    System.out.println("valide");
		   	    }
		   	    catch (SQLException e) {
		   	 	   e.printStackTrace();
		   	      }
		   	    return a;
		   }
	   
	   
	   public activitesc getconferencedoctorant(int id) {
	 		
			activitesc ac=new activitesc();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					ac.setIdactivitesc(rs.getInt("idactivitesc"));
					
					ac.setInitule(rs.getString("initule"));
					ac.setDate(rs.getString("date"));
					ac.setLieu(rs.getString("lieu"));
					
					ac.setDescription(rs.getString("description"));
					System.out.println("valide");
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return ac;
		}
	   public activitesc updateconferencedoctorant(activitesc a) { //ok
		   	  try {
		   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET initule = ? , date=? , lieu=? , description=?  " + " where idactivitesc = ?");
		   		  
		   		   ps.setString(1, a.getInitule());
		   		   
		   		 ps.setString(2, a.getDate());
		   		 ps.setString(3, a.getLieu());
		   		
		   		 ps.setString(4, a.getDescription());
		   		ps.setInt(5, a.getIdactivitesc());
		   		   ps.executeUpdate();
		   		     ps.close();
		   		    System.out.println("valide");
		   	    }
		   	    catch (SQLException e) {
		   	 	   e.printStackTrace();
		   	      }
		   	    return a;
		   }
	   
	   public activitesc getsoutenancedoctorant(int id) {
	 		
			activitesc ac=new activitesc();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					ac.setIdactivitesc(rs.getInt("idactivitesc"));
					ac.setType_sout(rs.getString("type_sout"));
					ac.setInitule(rs.getString("initule"));
					ac.setDate(rs.getString("date"));
					ac.setLieu(rs.getString("lieu"));
					ac.setJury(rs.getString("jury"));
					ac.setDescription(rs.getString("description"));
					System.out.println("valide");
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return ac;
		}
	   
	   public activitesc updatesoutenancedoctorant(activitesc a) { //ok
		   	  try {
		   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET type_sout = ?, initule = ? , date=? , lieu=? , jury=? , description=?  " + " where idactivitesc = ?");
		   		   ps.setString(1,a.getType_sout());
		   		   ps.setString(2, a.getInitule());
		   		   
		   		 ps.setString(3, a.getDate());
		   		 ps.setString(4, a.getLieu());
		   		 ps.setString(5, a.getJury());
		   		 ps.setString(6, a.getDescription());
		   		ps.setInt(7, a.getIdactivitesc());
		   		   ps.executeUpdate();
		   		     ps.close();
		   		    System.out.println("valide");
		   	    }
		   	    catch (SQLException e) {
		   	 	   e.printStackTrace();
		   	      }
		   	    return a;
		   }
	   
	   public activitesc getpublicationdoctorant(int id) {
	 		
			activitesc ac=new activitesc();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					ac.setIdactivitesc(rs.getInt("idactivitesc"));
					ac.setAuteur(rs.getString("auteur"));
					ac.setTitre(rs.getString("titre"));
					ac.setDate(rs.getString("date"));
					ac.setType_pub(rs.getString("type_pub"));
					ac.setDescription(rs.getString("description"));
					System.out.println("valide");
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return ac;
		}
	    public activitesc updatepublicationdoctorant(activitesc a) { //ok
		   	  try {
		   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET titre = ?, auteur = ? , date=? , type_pub=? , description=?  " + " where idactivitesc = ?");
		   		   ps.setString(1,a.getTitre());
		   		   ps.setString(2, a.getAuteur());
		   		   
		   		 ps.setString(3, a.getDate());
		   		 ps.setString(4, a.getType_pub());
		   		 ps.setString(5, a.getDescription());
		   		ps.setInt(6, a.getIdactivitesc());
		   		   ps.executeUpdate();
		   		     ps.close();
		   		    System.out.println("valide");
		   	    }
		   	    catch (SQLException e) {
		   	 	   e.printStackTrace();
		   	      }
		   	    return a;
		   }
	    
			
		

		public List<activitesc> listepublicationenseignant(int idchercheur) {
			
			List<activitesc> activi2=new ArrayList<activitesc>();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='publication' and idchercheur=? ");
				ps.setInt(1  , idchercheur );
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					activitesc ac=new activitesc();
					ac.setIdactivitesc(rs.getInt("idactivitesc"));
					ac.setType_ac(rs.getString("type_ac"));
					ac.setType_pub(rs.getString("type_pub"));
					ac.setAuteur(rs.getString("auteur"));
					ac.setTitre(rs.getString("titre"));
					ac.setDate(rs.getString("date"));
					ac.setIdchercheur(rs.getInt("idchercheur"));
					ac.setDescription(rs.getString("description"));
					ac.setImage(rs.getString("image"));
					
					//ac.setJustificatif(rs.getBlob("justificatif"));
					activi2.add(ac);
					System.out.println(ac);
					System.out.println("valide");
				}
				ps.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return activi2;
		}
		public List<activitesc> listesoutenanceenseignant(int idchercheur) {
			
			List<activitesc> activi3=new ArrayList<activitesc>();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from activitesc where type_ac='soutenance' and idchercheur=? ");
				ps.setInt(1  , idchercheur );
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					activitesc ac=new activitesc();
					ac.setIdactivitesc(rs.getInt("idactivitesc"));
					ac.setType_ac(rs.getString("type_ac"));
					ac.setType_sout(rs.getString("type_sout"));
					ac.setInitule(rs.getString("initule"));
					ac.setDate(rs.getString("date"));
					ac.setLieu(rs.getString("lieu"));
					ac.setJury(rs.getString("jury"));
					
					ac.setIdchercheur(rs.getInt("idchercheur"));
					ac.setDescription(rs.getString("description"));
					ac.setImage(rs.getString("image"));
					
					//ac.setJustificatif(rs.getBlob("justificatif"));
					activi3.add(ac);
					System.out.println(ac);
					System.out.println("valide");
				}
				ps.close();
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			return activi3;
		}
		  public activitesc getpublicationenseignant(int id) {
		 		
				activitesc ac=new activitesc();
				try {
					PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
					ps.setInt(1, id);
					ResultSet rs = ps.executeQuery();
					if  (rs.next()) {
						ac.setIdactivitesc(rs.getInt("idactivitesc"));
						ac.setAuteur(rs.getString("auteur"));
						ac.setTitre(rs.getString("titre"));
						ac.setDate(rs.getString("date"));
						ac.setType_pub(rs.getString("type_pub"));
						ac.setDescription(rs.getString("description"));
						System.out.println("valide");
					}
				}
				catch (SQLException e) {
					 e.printStackTrace();
				}
				return ac;
			}
		  public activitesc updatepublicationenseignant(activitesc a) { //ok
		   	  try {
		   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET titre = ?, auteur = ? , date=? , type_pub=? , description=?  " + " where idactivitesc = ?");
		   		   ps.setString(1,a.getTitre());
		   		   ps.setString(2, a.getAuteur());
		   		   
		   		 ps.setString(3, a.getDate());
		   		 ps.setString(4, a.getType_pub());
		   		 ps.setString(5, a.getDescription());
		   		ps.setInt(6, a.getIdactivitesc());
		   		   ps.executeUpdate();
		   		     ps.close();
		   		    System.out.println("valide");
		   	    }
		   	    catch (SQLException e) {
		   	 	   e.printStackTrace();
		   	      }
		   	    return a;
		   }
			public boolean deletepublicationenseignant(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			public boolean deletesoutenanceenseignant(int id) throws SQLException{
				boolean rowDelete ;
				
				try (
					PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
					ps.setInt(1, id);
					rowDelete=ps.executeUpdate()>0;
					ps.close();
				}
					return rowDelete ;
				
				}
			
			   public activitesc getsoutenanceenseignant(int id) {
			 		
					activitesc ac=new activitesc();
					try {
						PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
						ps.setInt(1, id);
						ResultSet rs = ps.executeQuery();
						if  (rs.next()) {
							ac.setIdactivitesc(rs.getInt("idactivitesc"));
							ac.setType_sout(rs.getString("type_sout"));
							ac.setInitule(rs.getString("initule"));
							ac.setDate(rs.getString("date"));
							ac.setLieu(rs.getString("lieu"));
							ac.setJury(rs.getString("jury"));
							ac.setDescription(rs.getString("description"));
							System.out.println("valide");
						}
					}
					catch (SQLException e) {
						 e.printStackTrace();
					}
					return ac;
				}
			   
			   public activitesc updatesoutenanceenseignant(activitesc a) { //ok
				   	  try {
				   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET type_sout = ?, initule = ? , date=? , lieu=? , jury=? , description=?  " + " where idactivitesc = ?");
				   		   ps.setString(1,a.getType_sout());
				   		   ps.setString(2, a.getInitule());
				   		   
				   		 ps.setString(3, a.getDate());
				   		 ps.setString(4, a.getLieu());
				   		 ps.setString(5, a.getJury());
				   		 ps.setString(6, a.getDescription());
				   		ps.setInt(7, a.getIdactivitesc());
				   		   ps.executeUpdate();
				   		     ps.close();
				   		    System.out.println("valide");
				   	    }
				   	    catch (SQLException e) {
				   	 	   e.printStackTrace();
				   	      }
				   	    return a;
				   }
			   

				public int getIdchercheur(int idactivitesc ) {
					int idcher=0;
					try {
						PreparedStatement ps=conn.prepareStatement("select idchercheur from equipe where idactivitesc= ?");
						ps.setInt(1, idactivitesc);
						ResultSet rs = ps.executeQuery();
						if  (rs.next()) {
							idcher=rs.getInt("idchercheur");
							System.out.println(idcher);
							System.out.println("valide");
							ps.close();
						}
						
						
					}catch (SQLException e) {
						 e.printStackTrace();
					}
					return idcher;
				}
				
				public boolean deletepublication(int id) throws SQLException{
					boolean rowDelete ;
					
					try (
						PreparedStatement ps= conn.prepareStatement("DELETE FROM activitesc where idactivitesc = ?");){
						ps.setInt(1, id);
						rowDelete=ps.executeUpdate()>0;
						ps.close();
					}
						return rowDelete ;
					
					}
				 public activitesc updatepublication(activitesc a) { //ok
				   	  try {
				   		   PreparedStatement ps= conn.prepareStatement("update activitesc SET titre = ?, auteur = ? , date=? , type_pub=? , description=?  " + " where idactivitesc = ?");
				   		   ps.setString(1,a.getTitre());
				   		   ps.setString(2, a.getAuteur());
				   		   
				   		 ps.setString(3, a.getDate());
				   		 ps.setString(4, a.getType_pub());
				   		 ps.setString(5, a.getDescription());
				   		ps.setInt(6, a.getIdactivitesc());
				   		   ps.executeUpdate();
				   		     ps.close();
				   		    System.out.println("valide");
				   	    }
				   	    catch (SQLException e) {
				   	 	   e.printStackTrace();
				   	      }
				   	    return a;
				   }
				 public activitesc getpublication(int id) {
				 		
						activitesc ac=new activitesc();
						try {
							PreparedStatement ps= conn.prepareStatement("select * from activitesc where idactivitesc = ?");
							ps.setInt(1, id);
							ResultSet rs = ps.executeQuery();
							if  (rs.next()) {
								ac.setIdactivitesc(rs.getInt("idactivitesc"));
								ac.setAuteur(rs.getString("auteur"));
								ac.setTitre(rs.getString("titre"));
								ac.setDate(rs.getString("date"));
								ac.setType_pub(rs.getString("type_pub"));
								ac.setDescription(rs.getString("description"));
								System.out.println("valide");
							}
						}
						catch (SQLException e) {
							 e.printStackTrace();
						}
						return ac;
					}
				 	
				 	
			
			
			
			
			

}

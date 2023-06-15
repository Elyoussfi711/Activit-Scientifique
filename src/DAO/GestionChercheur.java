package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.chercheur;
import web.model;
import metier.Equipe;
import metier.activitesc;
import metier.axe_equipe;
import metier.chercheur;

public class GestionChercheur {
	Connection conn =Connexion.getConnection();
	
	/***************** liste des chercheur dans table d'admin**********/
	
	public static List<chercheur> listechercheur() {
		Connection conn=Connexion.getConnection();
		System.out.println("22222");
		List<chercheur> cher=new ArrayList<chercheur>();
	
		
		try {
			PreparedStatement ps= conn.prepareStatement("select * from chercheur where username!='admin'");
			
			ResultSet rs = ps.executeQuery();
			
		
			
			while ((rs.next())) {
				System.out.println("0000");
				chercheur c = new chercheur();
				c.setIdchercheur(rs.getInt("idchercheur"));	
				c.setUsername(rs.getString("username"));
				c.setPrenom(rs.getString("prenom"));
				c.setEmail(rs.getString("email"));
				c.setTel(rs.getString("tel"));
				c.setProfil(rs.getString("profil"));
				c.setPassword(rs.getString("password"));
				c.setIdequipe(rs.getInt("idequipe"));
				c.setImage(rs.getString("image"));
 				c.setActivite_pedagogique(rs.getString("activite_pedagogique"));
     			c.setResponsabiliter(rs.getString("responsabiliter"));
				cher.add(c);
			
				
			}
			
			ps.close();
			
		}catch(SQLException e){
				e.printStackTrace();
		}
		
		return cher;
		
	}
	
	
	
	 /*********** add chercheur par l'admin ************/
	
	public int addchercheur(chercheur c ) {
		int result=0;
		  try {
			  PreparedStatement ps= conn.prepareStatement("INSERT INTO chercheur(username,prenom,email,password,tel,profil,idequipe,image) VALUES(?,?,?,?,?,?,?,?)");
			    ps.setString(1, c.getUsername());
				ps.setString(2, c.getPrenom());
				ps.setString(3, c.getEmail());
				ps.setString(4, c.getPassword());
				ps.setString(5,c.getTel());
				ps.setString(6, c.getProfil());
				ps.setInt(7, c.getIdequipe());
				ps.setString(8, c.getImage());
				
				ps.executeUpdate();
				ps.close();
				System.out.println("valiiide");
				
		}catch (SQLException e) {
			 e.printStackTrace();
			  
		  }
		return result;

	}
/*********** add idequipe in table chercheur ************/
	
	public int addIdequipe(chercheur c) {
		int result=0;
		  try {
			  PreparedStatement ps= conn.prepareStatement("INSERT INTO chercheur(idequipe) VALUES(?)");
			    ps.setInt(1, c.getIdequipe());
				ps.executeUpdate();
				ps.close();
				System.out.println("valiiide add id equipe");
				
		}catch (SQLException e) {
			 e.printStackTrace();
			  
		  }
		return result;

	}
	/************* gerer les info de chercheur par id  pour modification ***************/
	
	public chercheur getChercheur(int id) {
		
		chercheur c=new chercheur();
		
		try {
			PreparedStatement ps= conn.prepareStatement("select * from chercheur where idchercheur = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
		
			
               if  (rs.next()) {
				
				c.setIdchercheur(rs.getInt("idchercheur"));
				c.setUsername(rs.getString("username"));
			    c.setPrenom(rs.getString("prenom"));
				c.setEmail(rs.getString("email"));
			    c.setPassword(rs.getString("password"));
			    c.setTel(rs.getString("tel"));
				c.setProfil(rs.getString("profil"));
				c.setIdequipe(rs.getInt("idequipe"));
				c.setImage(rs.getString("image"));
			System.out.println("valide");
				ps.close();
			}
			
		}catch (SQLException e) {
			 e.printStackTrace();
			
		}
		return c;
		
		
	}
	  /***************** modifer chercheur *******************/
    public chercheur updateChercheur(chercheur ch) { //ok
	  try {
		   PreparedStatement ps= conn.prepareStatement("UPDATE chercheur SET username=? ,password=?,prenom=?,email=?,tel=?,profil=?,idequipe=?,image=? WHERE idchercheur=?");
		   System.out.println("Modif en cours");
		   ps.setString(1,ch.getUsername());
		   ps.setString(2, ch.getPassword());
		   ps.setString(3, ch.getPrenom());
		   ps.setString(4, ch.getEmail());
		   ps.setString(5, ch.getTel());
		   ps.setString(6, ch.getProfil());
		   ps.setInt(7, ch.getIdequipe());
		   ps.setInt(8, ch.getIdchercheur());
		   ps.setString(9, ch.getImage());
		   
		   
		   ps.executeUpdate();
		     ps.close();
		    System.out.println("valide update chercheur");
	    }
	    catch (SQLException e) {
	 	   e.printStackTrace();
	      }
	    return ch;
}	
	
	/***************** supprimer chercheur ***********/
	
	public boolean deleteChercheur(int id) throws SQLException{
		boolean rowDelete ;
		
		try (
			PreparedStatement ps= conn.prepareStatement("DELETE FROM chercheur where idchercheur = ?");){
			ps.setInt(1, id);
			rowDelete=ps.executeUpdate()>0;
			ps.close();
		}
			return rowDelete ;
		
		}
	
   public List<Equipe> listNom_Equipe() {
		
		List<Equipe> equipee=new ArrayList<Equipe>();
		try {
			PreparedStatement ps= conn.prepareStatement("select nom_equipe from equipe");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Equipe eqi = new Equipe();
				eqi.setNom_equipe(rs.getString("nom_equipe"));
				equipee.add(eqi);	
				System.out.println("valide");
			}
			ps.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return equipee;
	}
     /**************  liste chercheur par equipe              ****************/
   
	public List<chercheur> listChercheurparEq(int idEq) {

		List<chercheur> cherchs=new ArrayList<chercheur>();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from chercheur where profil!='admin' and idequipe= ?");
			ps.setInt(1, idEq);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				chercheur c = new chercheur();
				c.setIdchercheur(rs.getInt("idchercheur"));
				c.setUsername(rs.getString("username"));
				c.setPrenom(rs.getString("prenom"));
				c.setEmail(rs.getString("email"));
				c.setProfil(rs.getString("profil"));
				cherchs.add(c);	
				System.out.println("valide");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return cherchs;
	}
	
	             /*********    liste nom esquipe par id chercheur *******/
	
	public List<Equipe> listNomEquipe(int idchercheur) {

		List<Equipe> equipes=new ArrayList<Equipe>();
		try {
			PreparedStatement ps= conn.prepareStatement("select nom_equipe from equipe where  idchercheur= ?");
			ps.setInt(1, idchercheur);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Equipe eq=new Equipe();
				eq.setIdequipe(rs.getInt("idequipe"));
				eq.setChef_equipe(rs.getString("chef_equipe"));
				eq.setNom_equipe(rs.getString("nom_equipe"));
				equipes.add(eq);	
				System.out.println("valide");
			}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return equipes;
	}
	
	 /**************  get id equipe par id chercheur        *************/
	
	public int getIdEquipe(int idchercheur ) {
		int ideq=0;
		try {
			PreparedStatement ps=conn.prepareStatement("select idequipe from equipe where idchercheur= ?");
			ps.setInt(1, idchercheur);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				ideq=rs.getInt("idequipe");
				System.out.println(ideq);
				System.out.println("valide");
				ps.close();
			}
			
			
		}catch (SQLException e) {
			 e.printStackTrace();
		}
		return ideq;
	}
	
	
	
	/******             *******/
	
	public chercheur getCher(int id) {
		List<chercheur> cherchs=listechercheur();
		chercheur c=new chercheur();
		for(int i=0;i<cherchs.size();i++) {
			
			if(cherchs.get(i).getIdchercheur()==id) {
				System.out.println("testtt again");
				c=cherchs.get(i);
				break;
			}
		}
		return c;
		
	}
	
	
	public chercheur getCherEq(int id) {
		List<chercheur> cherchs=listechercheur();
		chercheur c=new chercheur();
		for(int i=0;i<cherchs.size();i++) {
			System.out.println("testtt");
			if(cherchs.get(i).getIdchercheur()==id) {
				
				c=cherchs.get(i);
				break;
			}
		}
		return c;
		
	}
	
	public chercheur getCherParNom(String nom) {
		List<chercheur> cherchs=listechercheur();
		chercheur c=new chercheur();
		System.out.println("lolito");
		for(int i=0;i<cherchs.size();i++) {
			System.out.println("testtt");
			if(cherchs.get(i).getUsername().equals(nom)) {
				System.out.println(cherchs.get(i).getUsername());
				
				c=cherchs.get(i);
				break;
			}
		}
		return c;
		
	}
	
	
	
	public chercheur getIdequipe(int id) {
		List<chercheur> cherchs=listechercheur();
	
		chercheur c=new chercheur();
		for(int i=0;i<cherchs.size();i++) {
		
			
			if(cherchs.get(i).getIdequipe()==id) {
				
				c=cherchs.get(i);
				break;
			}
		}
		return c;
		
	}
	
	public int addchercheur2(chercheur c ) {
		int result=0;
		  try {
			  PreparedStatement ps= conn.prepareStatement("INSERT INTO chercheur(idequipe) VALUES(?)");
			
				ps.setInt(1, c.getIdequipe());
				ps.executeUpdate();
				ps.close();
				System.out.println("valiiide");
				
		}catch (SQLException e) {
			 e.printStackTrace();
			  
		  }
		return result;

	}
		/*******  ajouter idequipe dans tableau chercheur        *******/
		public void ajouterIdEquipe() {
			GestionEquipe eq =new GestionEquipe();
			List<chercheur> cherchs=listechercheur();
			List<Equipe> equipe=eq.listEquipe();
			  for(int i=0 ; i<equipe.size(); i++) {
				  for(int j=0 ; i<cherchs.size(); j++) {
					  if(equipe.get(i).getIdchercheur()==cherchs.get(i).getIdchercheur()) {
						  int chercheur =cherchs.get(i).getIdequipe();
						   int idequipe =equipe.get(i).getIdequipe();
						   chercheur=idequipe ;
					  }
				  }
			  }
		
	      }
		
		/********     getusername from  chercheur for session scope                ********/
		
		public chercheur getChercheurUsername(String username) {
			// TODO Auto-generated method stub
					chercheur c=new chercheur();
					try {
						PreparedStatement ps= conn.prepareStatement("select * from chercheur where username = ? ");
						ps.setString(1, username);
						ResultSet rs = ps.executeQuery();
						if  (rs.next()) {
							c.setIdchercheur(rs.getInt("idchercheur"));
							c.setUsername(rs.getString("username"));
							c.setPrenom(rs.getString("prenom"));
							c.setEmail(rs.getString("email"));
							c.setPassword(rs.getString("password"));
							c.setTel(rs.getString("tel"));
							c.setProfil(rs.getString("profil"));
							c.setActivite_pedagogique(rs.getString("Responsabilité_pedagogique"));
							c.setResponsabiliter(rs.getString("responsabiliter"));
							//c.setPhoto(rs.getBinaryStream("photo"));	
							c.setJustificatif(rs.getBinaryStream("justificatif"));	
							c.setIdequipe(rs.getInt("idequipe"));
							System.out.println("valide");
						}
					}
					catch (SQLException e) {
						 e.printStackTrace();
					}
					return c;
			
		}
		
		
		/******        getchercheur par nom  pour ajouter equipe ****/ 
		
		public chercheur getChercheur(String username) {  //ok
			// TODO Auto-generated method stub
			chercheur c=new chercheur();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from chercheur where username= ?");
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					
					c.setIdchercheur(rs.getInt("idchercheur"));
					c.setUsername(rs.getString("username"));
					c.setPrenom(rs.getString("prenom"));
					c.setEmail(rs.getString("email_chercheur"));
					c.setPassword(rs.getString("password"));
					c.setTel(rs.getString("tel"));
					c.setRole(rs.getString("role"));
					//c.setActivite_pedagogique(rs.getString("activite_pedagogique"));
					//c.setResponsabilite(rs.getString("responsabilite"));
						
					
					c.setUsername(rs.getString("username"));
					System.out.println("valide");
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return c;
		}
		 /***     update chercheur equipe        ****/
		
		public void updateChercheurEquipe(String username , chercheur c) {
			
			try {
				PreparedStatement ps= conn.prepareStatement("update chercheur SET idequipe = ? where username=?");
				ps.setInt(1, c.getIdequipe());
				ps.setString(2, username);
				ps.executeUpdate();
				ps.close();
				
				
			}catch(Exception e) {
				e.printStackTrace();
				
			}
			}
		
		/********  ajouter responsabiliter par chercheur           **********/
		
		public int addResponsabiliter(chercheur CHER , int id) {
			int result=0;
			  try {
				  PreparedStatement ps= conn.prepareStatement("update chercheur SET responsabiliter=? where idchercheur=? ");
				    ps.setString(1, CHER.getResponsabiliter());
				    ps.setInt(2, id);
				    ps.executeUpdate();
					ps.close();
					System.out.println("valiiide");
					
			}catch (SQLException e) {
				 e.printStackTrace();
				  
			  }
			return result;

		}
		
		
		
		/************    get id chercheur par username            *******/
		public int getIdchercheur(String username) {
			
			int ch=0;
			try {
				PreparedStatement ps= conn.prepareStatement("select idchercheur from chercheur where username=?");
				ps.setString(1  , username );
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					ch=rs.getInt("idchercheur");
					System.out.println("in");
					ps.close();
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return ch;
		}
		
		
		/******  Add Activiter pedagogique dans chercheur       ******/
		
		public int addActiviterpedagogique(chercheur ch , int id) {
			int result=0;
			  try {
				  PreparedStatement ps= conn.prepareStatement("update chercheur SET activite_pedagogique=? where idchercheur=?");
				    ps.setString(1, ch.getActivite_pedagogique());
				    ps.setInt(2, id);
				    ps.executeUpdate();
					ps.close();
					System.out.println("valiiide");
					
			}catch (SQLException e) {
				 e.printStackTrace();
				  
			  }
			return result;

		}
		
		
		/**************    profil enseignant               **************/
	 	public chercheur Profilenseignant(int idchercheur) { //ok
			// TODO Auto-generated method stub
			chercheur cher=new chercheur();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from chercheur where idchercheur = ?");
				ps.setInt(1, idchercheur);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					cher.setIdchercheur(rs.getInt("idchercheur"));
					cher.setUsername(rs.getString("username"));
					cher.setPrenom(rs.getString("prenom"));
					cher.setEmail(rs.getString("email"));
					cher.setProfil(rs.getString("profil"));
					cher.setTel(rs.getString("tel"));
					cher.setPassword(rs.getString("password"));
					cher.setResponsabiliter(rs.getString("responsabiliter"));
					cher.setImage(rs.getString("image"));
					cher.setActivite_pedagogique(rs.getString("activite_pedagogique"));
					
					System.out.println("valideeee");
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return cher;
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
		/******  get username par equipe            ****************/
		public String getUsernamee(int idequipe) {
			
			String ch=null;
			try {
				PreparedStatement ps= conn.prepareStatement("select username from chercheur where idequipe = ?");
				ps.setInt(1  , idequipe );
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
		
		
		public chercheur getchercheur(int idchercheur) {
	 		
			chercheur ac=new chercheur();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from chercheur where idchercheur = ?");
				ps.setInt(1, idchercheur);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					ac.setIdchercheur(rs.getInt("idchercheur"));
					ac.setUsername(rs.getString("username"));
					ac.setPrenom(rs.getString("prenom"));
					ac.setProfil(rs.getString("profil"));
					ac.setTel(rs.getString("tel"));
					ac.setEmail(rs.getString("email"));
					ac.setNomcher(rs.getString("nomcher"));
					ac.setImage(rs.getString("image"));
					System.out.println("valide");
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return ac;
		}
}
		
	
	

		
		
		

	




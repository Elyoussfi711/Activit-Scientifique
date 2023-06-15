package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import metier.Laboratoire;
import metier.activitesc;
import metier.axe_equipe;
import metier.chercheur;
import metier.Equipe;

public class GestionEquipe {
	Connection conn =Connexion.getConnection(); 
		

	/***************** ajouter equipe par l'admin**********/
	public void  addEquipe(Equipe e) {
		
		try {
			PreparedStatement ps= conn.prepareStatement("INSERT INTO equipe(nom_equipe,chef_equipe,id_Laboratoire,id_axe_equipe,idchercheur,nomcher1,nomcher2,nomcher3) VALUES(?,?,?,?,?,?,?,?)");
			ps.setString(1, e.getNom_equipe());
			ps.setString(2, e.getChef_equipe());
			ps.setInt(3,    e.getId_Laboratoire());
			ps.setInt(4,    e.getId_axe_equipe());
			ps.setInt(5,    e.getIdchercheur());
			ps.setString(6,    e.getNomcher1());
			ps.setString(7,    e.getNomcher2());
			ps.setString(8,    e.getNomcher3());
			
			ps.executeUpdate();
			ps.close();
			
		}
		catch (SQLException ex) {
			 ex.printStackTrace();
		}
		
		
	}

	/************* liste equipe **********/
	
	public List<Equipe> listEquipe() {
		
		List<Equipe> equipe=new ArrayList<Equipe>();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from equipe");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Equipe eq = new Equipe();
				eq.setIdequipe(rs.getInt("idequipe"));
				eq.setNom_equipe(rs.getString("nom_equipe"));
				eq.setChef_equipe(rs.getString("chef_equipe"));
				eq.setId_Laboratoire(rs.getInt("id_Laboratoire"));
				eq.setId_Laboratoire(rs.getInt("id_axe_equipe"));
				eq.setIdchercheur(rs.getInt("idchercheur"));
				eq.setLaboname(rs.getString("laboname"));
				equipe.add(eq);	
			
			}
			ps.close();
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return equipe;
	}
	/************ list chercheur dans add equipe*****************/
	
	 public List<chercheur> Listchercheur(){
		  List<chercheur> ch=new ArrayList<chercheur>();
		  try {
			  PreparedStatement ps= conn.prepareStatement("select * from chercheur where username!= 'admin'");
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  chercheur chi=new chercheur();
				  chi.setUsername(rs.getString("username"));
				  ch.add(chi);
				  System.out.println("valide a sahbi");
				  //System.out.println(ch);
			  }
			  ps.close();
		  }
			catch(SQLException e){
				e.printStackTrace();
		  }
		  
		 return ch;
	 }
	 
	 /********* liste des enseignants dans add equipe ***********/
	 
	 public List<chercheur> ListEnseignant(){
		 List<chercheur> ens =new ArrayList();
		 try {
			 PreparedStatement ps= conn.prepareStatement("select username from chercheur where profil='enseignant' ");
			  ResultSet rs = ps.executeQuery();
			  while(rs.next()) {
				  chercheur cho=new chercheur();
				  cho.setUsername(rs.getString("username"));
				  ens.add(cho);
				  System.out.println("valide a sahbi");
				  System.out.println(ens);
			  }
			  ps.close();
		 }catch(SQLException e){
				e.printStackTrace();
		 }
		 return ens ;
	 }
	
	 
	 /*********** liste laboratoire dans add equipe ********/
	 
	 
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
	 
	 
	 /*********************** list axe_equipe dans add equipe ****************/
	 
	 
	 
	   public List<axe_equipe> axeequipe(){
		   List<axe_equipe> laxe=new ArrayList<axe_equipe>();
		   try {
			   PreparedStatement ps= conn.prepareStatement("select * from axe_equipe");
			   ResultSet rs = ps.executeQuery();
			   while( rs.next()) {
				   axe_equipe axe=new axe_equipe();
				   axe.setNom_axe_equipe(rs.getString("nom_axe_equipe"));
				   laxe.add(axe);
				   System.out.println("valider wee");
				   System.out.println(laxe);
				   
			   }
			   ps.close();
		   }catch(SQLException e){
				e.printStackTrace();
			   
		   }
		   return laxe;
	   }
	
	
	/****************  getter l'equipe par id      *****************/
	
	
	public Equipe getEquipe(int id) {
		
		Equipe eq=new Equipe();
		try {
			PreparedStatement ps= conn.prepareStatement("select * from equipe where idequipe = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				eq.setIdequipe(rs.getInt("idequipe"));
				eq.setNom_equipe(rs.getString("nom_equipe"));
				eq.setChef_equipe(rs.getString("chef_equipe"));
				eq.setNomcher1(rs.getString("nomcher1"));
				eq.setNomcher2(rs.getString("nomcher2"));
				eq.setNomcher3(rs.getString("nomcher3"));
				
				System.out.println("in");
				ps.close();
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return eq;
	}

	


	 /********************* geter id laboratoire**************/
	
	
	public int getIdLaboratoire(String nom_Labo) {
		
		int la=0;
		try {
			PreparedStatement ps= conn.prepareStatement("select id_Laboratoire from laboratoire where nom_Labo = ?");
			ps.setString(1, nom_Labo);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				la=rs.getInt("id_Laboratoire");
				System.out.println("in");
				ps.close();
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return la;
	}
	
	/**************** geter id chercheur*******************/
	
	public int getIdChercheur(String username) {
		
		int ch=0;
		try {
			PreparedStatement ps= conn.prepareStatement("select idchercheur from chercheur where username = ?");
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
	
	/********** get id_axe_equipe ********/
	public int getIdAxe_equipe(String nom_axe_equipe) {
		
		int ch=0;
		try {
			PreparedStatement ps= conn.prepareStatement("select id_axe_equipe from axe_equipe where nom_axe_equipe = ?");
			ps.setString(1  , nom_axe_equipe );
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				ch=rs.getInt("id_axe_equipe");
				System.out.println("in");
				ps.close();
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return ch;
	}
	/******    geter nom laboratoire          **************/
	public String getNom_Laboratoire(int id) {
		// TODO Auto-generated method stub
		String nom=null;
		try {
			PreparedStatement ps= conn.prepareStatement("select * from laboratoire where id_laboratoire = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				nom=rs.getString("nom_labo");
				System.out.println(nom);
				System.out.println("valide");
				ps.close();
			}
		}
		catch (SQLException e) {
			 e.printStackTrace();
		}
		return nom;
	}
	/******      geter nom d'axe quipe par id    ***********/
	public String getNom_axe_equipe(int id ) {
		String nom=null;
		try {
			PreparedStatement ps=conn.prepareStatement("select * from axe_equipe where id_axe_equipe= ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				nom=rs.getString("nom_axe_equipe");
				System.out.println(nom);
				System.out.println("valide");
				ps.close();
			}
			
			
		}catch (SQLException e) {
			 e.printStackTrace();
		}
		return nom;
	}
	/***************** liste des chercheur dans table d'admin**********/
	
	public List<Equipe> listeEquipe() {
		Connection conn=Connexion.getConnection();
		
		System.out.println("liste equipe download");
		
		List<Equipe> equipes=new ArrayList<Equipe>();
	
		
		try {
			PreparedStatement ps= conn.prepareStatement("select * from equipe ");
			
			ResultSet rs = ps.executeQuery();
			
			System.out.println(rs);
			
			while ((rs.next())) {
				System.out.println("0000");
				Equipe eq  = new Equipe();
				eq.setIdequipe(rs.getInt("idequipe"));	
				eq.setNom_equipe(rs.getString("nom_equipe"));
				eq.setChef_equipe(rs.getString("chef_equipe"));
				eq.setId_axe_equipe(rs.getInt("id_axe_equipe"));
				eq.setIdchercheur(rs.getInt("idchercheur"));
				eq.setId_Laboratoire(rs.getInt("id_Laboratoire"));
				eq.setLaboname(rs.getString("laboname"));
				eq.setNomcher1(rs.getString("nomcher1"));
				eq.setNomcher2(rs.getString("nomcher2"));
				eq.setNomcher3(rs.getString("nomcher3"));
//				c.setActivite_pedagogique(rs.getString("activite_pedagogique"));
//				c.setResponsabilite(rs.getString("responsabilite"));
				equipes.add(eq);
				System.out.println(eq);
				
			}
			
			ps.close();
			
		}catch(SQLException e){
				e.printStackTrace();
		}
		
		return equipes;
		
	}
	/***  get nom_equipe par idequipe          ***********/
	
	public String nomEq(int id_chercheur) {
		List<Equipe> equipe=new ArrayList<Equipe>();
		equipe=listEquipe();
		String nom_equipe="Aucune Equipe";
		for(int i=0;i<equipe.size();i++) {
			if(equipe.get(i).getIdequipe()==id_chercheur)
				nom_equipe=equipe.get(i).getNom_equipe();
				
		}
		
		return nom_equipe;
	}
	 /**************  get id equipe par id chercheur        *************/
	
	public int getIdEquipe(int idcher ) {
		int ideq=0;
		try {
			PreparedStatement ps=conn.prepareStatement("select idequipe from equipe where idchercheur= ?");
			ps.setInt(1, idcher);
			ResultSet rs = ps.executeQuery();
			if  (rs.next()) {
				ideq=rs.getInt("idequipe");
				System.out.println(ideq);
				
				ps.close();
			}
			
			
		}catch (SQLException e) {
			 e.printStackTrace();
		}
		return ideq;
	}
	
	
	
	
	public int idEqu(String nom) {
		List<Equipe> equipe=new ArrayList<Equipe>();
		int id=0;
		for(int i=0;i<equipe.size();i++) {
			if(equipe.get(i).getNom_equipe().equals(nom)) {
				id=equipe.get(i).getIdequipe();
				break;
				
			}
		}
		return id;
	}
	
	
	/**********   ajouter idequipe dans chercheur par nom            ******/
	
	 public void  idEquipeDansCher(int idchercheur) {
		 GestionChercheur ch=new GestionChercheur();
		 
		Equipe eq=new Equipe();
		chercheur chere=new chercheur();
		 List<chercheur> cher=ch.listechercheur() ;
		     for(int j=0 ; j<cher.size() ; j++) {
				  
				  if(cher.get(j).getIdchercheur()==idchercheur) {
					  int idequipe=eq.getIdequipe();
					  chere.setIdequipe(idequipe);
					  
				}
			  }
			 
		 }
	 /***  get id equipe par nomequipe             ********/
	 
		public int getIdEquipe(String nom_equipe) {
			
			int eq=0;
			try {
				PreparedStatement ps= conn.prepareStatement("select idequipe from equipe where nom_equipe = ?");
				ps.setString(1  , nom_equipe );
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					eq=rs.getInt("idequipe");
					
					ps.close();
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return eq;
		}
		public int idEquipeNom(String nom) {
			List<Equipe> list=listEquipe();
			int e=0;
			for(int i=0;i<list.size();i++) {
				if(list.get(i).getNom_equipe().equals(nom)) {
					e=list.get(i).getIdequipe();
					break;
				}
			}
			return e;
		}
		/********     get nom_equipe par id_equipe       **********/
		
		public String getNomEquipe(int idequipe ) {
			String nom_equipe=null;
			try {
				PreparedStatement ps=conn.prepareStatement("select nom_equipe from equipe where idequipe= ?");
				ps.setInt(1, idequipe);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					nom_equipe=rs.getString("nom_equipe");
					System.out.println(nom_equipe);
					System.out.println("valide");
					ps.close();
				}
				
				
			}catch (SQLException e) {
				 e.printStackTrace();
			}
			return nom_equipe;
		}
		/*********   getnom labo par idlaboratoire               **********/
		public String getNomLaboratoire(int idlabo) {
			
			String labo=null;
			try {
				PreparedStatement ps= conn.prepareStatement("select nom_Labo from laboratoire where id_Laboratoire = ?");
				ps.setInt(1  , idlabo );
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					labo=rs.getString("nom_Labo");
					
					ps.close();
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return labo;
		}
		/********      supprimer equipe               **************/
		public boolean deleteequipe(int id) throws SQLException{
			boolean rowDelete ;
			
			try (
				PreparedStatement ps= conn.prepareStatement("DELETE FROM equipe where idequipe = ?");){
				ps.setInt(1, id);
				rowDelete=ps.executeUpdate()>0;
				ps.close();
			}
				return rowDelete ;
			
			}
		public Equipe getEquipee(int id) {
	 		
			Equipe equi=new Equipe();
			chercheur cher=new chercheur();
			axe_equipe ewe=new axe_equipe();
			Laboratoire labo=new Laboratoire();
			try {
				PreparedStatement ps= conn.prepareStatement("select * from Equipe where idequipe = ?");
				ps.setInt(1, id);
				ResultSet rs = ps.executeQuery();
				if  (rs.next()) {
					equi.setIdequipe(rs.getInt("idequipe"));
					equi.setNom_equipe(rs.getString("nom_equipe"));
					//cher.setInitule(rs.getString("initule"));
				
					
					System.out.println("valide");
				}
			}
			catch (SQLException e) {
				 e.printStackTrace();
			}
			return equi;
		}
		
		   public Equipe updateequipe(Equipe a) { //ok
			   	  try {
			   		   PreparedStatement ps= conn.prepareStatement("update equipe SET nom_equipe = ?, chef_equipe = ? , nomcher1=? , nomcher2=? , nomcher3=? , id_Laboratoire=? ,id_axe_equipe=?    " + " where idequipe = ?");
			   	     	 ps.setString(1,a.getNom_equipe());
			   		   
			   		   ps.setString(2,a.getChef_equipe());
			   		  
			   		   ps.setString(3,a.getNomcher1());
			   		   ps.setString(4,a.getNomcher2());
			   		   ps.setString(5,a.getNomcher3());
			   		   ps.setInt(6,a.getId_Laboratoire());
			   		   ps.setInt(7,a.getId_axe_equipe());
			   	
			   		   
			   		
			   		   ps.executeUpdate();
			   		     ps.close();
			   		    System.out.println("valide");
			   	    }
			   	    catch (SQLException e) {
			   	 	   e.printStackTrace();
			   	      }
			   	    return a;
			   }
		   
		
	 
	 
		}

	

	
	
	
	
	
	
	
   


	


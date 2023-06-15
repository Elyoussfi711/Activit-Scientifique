package metier;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import DAO.GestionChercheur;
import DAO.GestionEquipe;

import java.io.Serializable;
public class chercheur implements Serializable{
 public InputStream getPhoto() {
		return photo;
	}
	public void setPhoto(InputStream photo) {
		this.photo = photo;
	}
	public InputStream getJustificatif() {
		return justificatif;
	}
	public void setJustificatif(InputStream justificatif) {
		this.justificatif = justificatif;
	}
	public ArrayList<activitesc> getActivite_sc() {
		return activite_sc;
	}
	public void setActivite_sc(ArrayList<activitesc> activite_sc) {
		this.activite_sc = activite_sc;
	}
public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
private int idchercheur;
 
 private String prenom;
 private String tel;
 private String profil;
 private String password;
private String activite_pedagogique;
private String nomcher ;
private String image;
 



 public String getImage() {
	return image;
}
public void setImage(String image) {
	this.image = image;
}
public chercheur(int idchercheur, String prenom, String tel, String profil, String password,
		String activite_pedagogique, String nomcher, String responsabiliter, String role, InputStream photo,
		InputStream justificatif, String email, int idequipe, String username, ArrayList<activitesc> activite_sc,
		int idEquipe2, String image) {
	super();
	this.idchercheur = idchercheur;
	this.prenom = prenom;
	this.tel = tel;
	this.profil = profil;
	this.password = password;
	this.activite_pedagogique = activite_pedagogique;
	this.nomcher = nomcher;
	this.responsabiliter = responsabiliter;
	this.role = role;
	this.photo = photo;
	this.justificatif = justificatif;
	this.email = email;
	this.idequipe = idequipe;
	this.username = username;
	this.activite_sc = activite_sc;
	idEquipe = idEquipe2;
	this.image=image;
}
public String getNomcher() {
	return nomcher;
}
public void setNomcher(String nomcher) {
	this.nomcher = nomcher;
}

public String getActivite_pedagogique() {
	return activite_pedagogique;
}
public void setActivite_pedagogique(String activite_pedagogique) {
	this.activite_pedagogique = activite_pedagogique;
}
private String responsabiliter;

public String getResponsabiliter() {
	return responsabiliter;
}
public void setResponsabiliter(String responsabiliter) {
	this.responsabiliter = responsabiliter;
}
private String role ;
 private InputStream photo;
 private  InputStream justificatif;
 private String email;
 private int idequipe ;
 private String username;
 
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getIdequipe() {
	return idequipe;
}
public void setIdequipe(int idequipe) {
	this.idequipe = idequipe;
}




public String getPrenom() {
	return prenom;
}
public void setPrenom(String prenom) {
	this.prenom = prenom;
}

public String getPassword() {
	return password;
}
public chercheur() {
	super();
	// TODO Auto-generated constructor stub
}
public void setPassword(String password) {
	this.password = password;
}

private ArrayList<activitesc> activite_sc; 
 private int idEquipe ;
 
 
public int getIdchercheur() {
	return idchercheur;
}
public void setIdchercheur(int idchercheur) {
	this.idchercheur = idchercheur;
}


public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}


public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public int getIdEquipe() {
	return idEquipe;
}
public void setIdEquipe(int idEquipe) {
	this.idEquipe = idEquipe;
}


@Override
public String toString() {
	return "chercheur [idchercheur=" + idchercheur + ", prenom=" + prenom + ", tel=" + tel + ", profil=" + profil
			+ ", password=" + password + ", activite_pedagogique=" + activite_pedagogique + ", nomcher=" + nomcher
			+ ", responsabiliter=" + responsabiliter + ", role=" + role + ", photo=" + photo + ", justificatif="
			+ justificatif + ", email=" + email + ", idequipe=" + idequipe + ", username=" + username + ", activite_sc="
			+ activite_sc + ", idEquipe=" + idEquipe + "]";
}
public String cherParId() {
	List<Equipe> Eq=new ArrayList<Equipe>();
	GestionEquipe c=new GestionEquipe();
	Eq=c.listeEquipe();
	String nom="Aucune Equipe";
	for(int i=0;i<Eq.size();i++) {
		if(Eq.get(i).getIdequipe()==idequipe)
			nom=Eq.get(i).getNom_equipe();
	}
	return nom;
	
	
}




}


 
 



package metier;

import java.io.Serializable;
import java.util.ArrayList;

public class axe_labo implements Serializable {
	private int id_axe_labo;
	private String nom_axe_labo;
	private ArrayList<Laboratoire> les_labo_c;
	
	public axe_labo(int id_axe_labo, String nom_axe_labo, int id_Laboratoire, ArrayList<Laboratoire> les_labo_c) {
		super();
		this.id_axe_labo = id_axe_labo;
		this.nom_axe_labo = nom_axe_labo;
		this.les_labo_c = les_labo_c;
	}
	public int getId_axe_labo() {
		return id_axe_labo;
	}
	public void setId_axe_labo(int id_axe_labo) {
		this.id_axe_labo = id_axe_labo;
	}
	public String getNom_axe_labo() {
		return nom_axe_labo;
	}
	public void setNom_axe_labo(String nom_axe_labo) {
		this.nom_axe_labo = nom_axe_labo;
	}
	
	public ArrayList<Laboratoire> getLes_labo_c() {
		return les_labo_c;
	}
	public void setLes_labo_c(ArrayList<Laboratoire> les_labo_c) {
		this.les_labo_c = les_labo_c;
	}
	public int getIdaxe_labo() {
		return id_axe_labo;
	}
	public void setIdaxe_labo(int idaxe_labo) {
		this.id_axe_labo = idaxe_labo;
	}
	
	public axe_labo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "axe_labo [id_axe_labo=" + id_axe_labo + ", nom_axe_labo=" + nom_axe_labo + ", les_labo_c=" + les_labo_c
				+ "]";
	}

	
	
	

}

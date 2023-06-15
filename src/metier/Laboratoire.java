package metier;

import java.util.ArrayList;

public class Laboratoire {
   private int id_Laboratoire;
   private String nom_Labo;
   private String chef_Labo;
   private ArrayList<Equipe> les_equipes;
	private ArrayList<axe_labo> les_axes_labo;
	public int getId_Laboratoire() {
		return id_Laboratoire;
	}
	public void setId_Laboratoire(int id_Laboratoire) {
		this.id_Laboratoire = id_Laboratoire;
	}
	public String getNom_Labo() {
		return nom_Labo;
	}
	public Laboratoire(int id_Laboratoire, String nom_Labo, String chef_Labo, ArrayList<Equipe> les_equipes,
			ArrayList<axe_labo> les_axes_labo) {
		super();
		this.id_Laboratoire = id_Laboratoire;
		this.nom_Labo = nom_Labo;
		this.chef_Labo = chef_Labo;
		this.les_equipes = les_equipes;
		this.les_axes_labo = les_axes_labo;
	}
	public void setNom_Labo(String nom_Labo) {
		this.nom_Labo = nom_Labo;
	}
	@Override
	public String toString() {
		return "Laboratoire [id_Laboratoire=" + id_Laboratoire + ", nom_Labo=" + nom_Labo + ", chef_Labo=" + chef_Labo
				+ ", les_equipes=" + les_equipes + ", les_axes_labo=" + les_axes_labo + "]";
	}
	public Laboratoire() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getChef_Labo() {
		return chef_Labo;
	}
	public void setChef_Labo(String chef_Labo) {
		this.chef_Labo = chef_Labo;
	}
	public ArrayList<Equipe> getLes_equipes() {
		return les_equipes;
	}
	public void setLes_equipes(ArrayList<Equipe> les_equipes) {
		this.les_equipes = les_equipes;
	}
	public ArrayList<axe_labo> getLes_axes_labo() {
		return les_axes_labo;
	}
	public void setLes_axes_labo(ArrayList<axe_labo> les_axes_labo) {
		this.les_axes_labo = les_axes_labo;
	}
}
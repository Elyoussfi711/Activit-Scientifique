package metier;

import java.io.Serializable;
import java.util.ArrayList;

public class axe_equipe implements Serializable{
  private int id_axe_equipe;
  private String nom_axe_equipe;
  private ArrayList<Equipe> les_equipes_c; 
  
public ArrayList<Equipe> getLes_equipes_c() {
	return les_equipes_c;
}

public void setLes_equipes_c(ArrayList<Equipe> les_equipes_c) {
	this.les_equipes_c = les_equipes_c;
}

public axe_equipe() {
	super();
	// TODO Auto-generated constructor stub
}

public String getNom_axe_equipe() {
	return nom_axe_equipe;
}

public void setNom_axe_equipe(String nom_axe_equipe) {
	this.nom_axe_equipe = nom_axe_equipe;
}

@Override
public String toString() {
	return "axe_equipe [id_axe_equipe=" + id_axe_equipe + ", nom_axe_equipe=" + nom_axe_equipe + ", les_equipes_c="
			+ les_equipes_c + "]";
}

public int getId_axe_equipe() {
	return id_axe_equipe;
}

public axe_equipe(String nom_axe_equipe) {
	super();
	
	this.nom_axe_equipe = nom_axe_equipe;
	
}

public void setId_axe_equipe(int id_axe_equipe) {
	this.id_axe_equipe = id_axe_equipe;
}


}

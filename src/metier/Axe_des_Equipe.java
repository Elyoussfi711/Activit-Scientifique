package metier;

import java.io.Serializable;

public class Axe_des_Equipe implements Serializable {
 private int id_axe_equipe;
 private int idequipe;
public int getId_axe_equipe() {
	return id_axe_equipe;
}
public void setId_axe_equipe(int id_axe_equipe) {
	this.id_axe_equipe = id_axe_equipe;
}
public int getIdequipe() {
	return idequipe;
}
public void setIdequipe(int idequipe) {
	this.idequipe = idequipe;
}
@Override
public String toString() {
	return "Axe_des_Equipe [id_axe_equipe=" + id_axe_equipe + ", idequipe=" + idequipe + "]";
}
public Axe_des_Equipe() {
	super();
	// TODO Auto-generated constructor stub
}
public Axe_des_Equipe(int id_axe_equipe, int idequipe) {
	super();
	this.id_axe_equipe = id_axe_equipe;
	this.idequipe = idequipe;
}
}

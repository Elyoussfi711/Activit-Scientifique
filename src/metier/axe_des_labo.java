package metier;

public class axe_des_labo {
 private int id_Laboratoire ;
  private int id_axe_labo;
public int getId_Laboratoire() {
	return id_Laboratoire;
}
public void setId_Laboratoire(int id_Laboratoire) {
	this.id_Laboratoire = id_Laboratoire;
}
public int getId_axe_labo() {
	return id_axe_labo;
}
public void setId_axe_labo(int id_axe_labo) {
	this.id_axe_labo = id_axe_labo;
}
@Override
public String toString() {
	return "axe_des_labo [id_Laboratoire=" + id_Laboratoire + ", id_axe_labo=" + id_axe_labo + "]";
}
public axe_des_labo() {
	super();
	// TODO Auto-generated constructor stub
}
}

package metier;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipe implements Serializable{

	private int idequipe ;
	private String nom_equipe;
	private String chef_equipe ;
	private int id_Laboratoire ;
	private ArrayList<axe_equipe> les_axes_equipes;
	private ArrayList<chercheur> les_chercheeurs;
	private int id_axe_equipe ;
	private int idchercheur ;
	private String laboname;
	private String axename;
	private String nomcher1;
	private String nomcher2;
	private String nomcher3;
	private String nomcher4;
	public String getNomcher1() {
		return nomcher1;
	}
	public void setNomcher1(String nomcher1) {
		this.nomcher1 = nomcher1;
	}
	public String getNomcher2() {
		return nomcher2;
	}
	public void setNomcher2(String nomcher2) {
		this.nomcher2 = nomcher2;
	}
	public String getNomcher3() {
		return nomcher3;
	}
	public void setNomcher3(String nomcher3) {
		this.nomcher3 = nomcher3;
	}
	public String getNomcher4() {
		return nomcher4;
	}
	public void setNomcher4(String nomcher4) {
		this.nomcher4 = nomcher4;
	}
	
	
	
	
	public Equipe(int idequipe, String nom_equipe, String chef_equipe, int id_Laboratoire,
			ArrayList<axe_equipe> les_axes_equipes, ArrayList<chercheur> les_chercheeurs, int id_axe_equipe,
			int idchercheur, String laboname, String axename, String nomcher1, String nomcher2, String nomcher3,
			String nomcher4) {
		super();
		this.idequipe = idequipe;
		this.nom_equipe = nom_equipe;
		this.chef_equipe = chef_equipe;
		this.id_Laboratoire = id_Laboratoire;
		this.les_axes_equipes = les_axes_equipes;
		this.les_chercheeurs = les_chercheeurs;
		this.id_axe_equipe = id_axe_equipe;
		this.idchercheur = idchercheur;
		this.laboname = laboname;
		this.axename = axename;
		this.nomcher1 = nomcher1;
		this.nomcher2 = nomcher2;
		this.nomcher3 = nomcher3;
		this.nomcher4 = nomcher4;
	}
	public String getAxename() {
		return axename;
	}
	public void setAxename(String axename) {
		this.axename = axename;
	}
	public String getLaboname() {
		return laboname;
	}

	public void setLaboname(String laboname) {
		this.laboname = laboname;
	}
	public int getIdchercheur() {
		return idchercheur;
	}
	public void setIdchercheur(int idchercheur) {
		this.idchercheur = idchercheur;
	}
	public int getId_axe_equipe() {
		return id_axe_equipe;
	}
	public void setId_axe_equipe(int id_axe_equipe) {
		this.id_axe_equipe = id_axe_equipe;
	}
	public int getIdequipe() {
		return idequipe;
	}
	public Equipe() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Equipe [idequipe=" + idequipe + ", nom_equipe=" + nom_equipe + ", chef_equipe=" + chef_equipe
				+ ", id_Laboratoire=" + id_Laboratoire + ", les_axes_equipes=" + les_axes_equipes + ", les_chercheeurs="
				+ les_chercheeurs + ", id_axe_equipe=" + id_axe_equipe + ", idchercheur=" + idchercheur + ", laboname="
				+ laboname + ", axename=" + axename + ", nomcher1=" + nomcher1 + ", nomcher2=" + nomcher2
				+ ", nomcher3=" + nomcher3 + ", nomcher4=" + nomcher4 + "]";
	}
	public void setIdequipe(int idequipe) {
		this.idequipe = idequipe;
	}
	public String getNom_equipe() {
		return nom_equipe;
	}
	public void setNom_equipe(String nom_equipe) {
		this.nom_equipe = nom_equipe;
	}
	public String getChef_equipe() {
		return chef_equipe;
	}
	public void setChef_equipe(String chef_equipe) {
		this.chef_equipe = chef_equipe;
	}
	public int getId_Laboratoire() {
		return id_Laboratoire;
	}
	public void setId_Laboratoire(int id_Laboratoire) {
		this.id_Laboratoire = id_Laboratoire;
	}
	public ArrayList<axe_equipe> getLes_axes_equipes() {
		return les_axes_equipes;
	}
	public void setLes_axes_equipes(ArrayList<axe_equipe> les_axes_equipes) {
		this.les_axes_equipes = les_axes_equipes;
	}
	public ArrayList<chercheur> getLes_chercheeurs() {
		return les_chercheeurs;
	}
	public void setLes_chercheeurs(ArrayList<chercheur> les_chercheeurs) {
		this.les_chercheeurs = les_chercheeurs;
	}
	
	public String equipo() {
		
		nom_equipe=nom_equipe+"esss";
		System.out.println("hhhhhhhhhhhhhh");
		return nom_equipe;
	}
	
}

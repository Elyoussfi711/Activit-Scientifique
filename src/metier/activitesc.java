package metier;

import java.io.InputStream;
import java.sql.Date;

import com.mysql.cj.jdbc.Blob;

public class activitesc {

	private int idactivitesc;	/*id de l'activite scientifique */
	private InputStream justificatif; 			/* justificatif de tous les activites */
	private String type_ac;	/*type d' activites */
	private String a_bstract;	/*pub*/
	private String date;	/*pub /par aux sou/par aux conf */
	private String titre;	/*pub / projet */
	private String type_pub;  /*type de publication */
	private String auteur; /*pub*/
	private String type_sout;	/*type de participation aux soutenance */
	private String budget;	/* projet*/
	private String lieu; /*sou / conference    */
	private String description; /*projet / soutenance / encadrement  /pub   /   conference */
	private String duree;	/*projet */
	private String initule;	/*par aux sou/par aux conf/encadrement */
	private String jury;
	private String niveau;/*par aux sou */
	private String type_enc;
	private String type_responsabilite;
	private String type_activite_pedagogique;
	private int idchercheur ;
	private String titre_enc;
	private String image;
	public activitesc(int idactivitesc, InputStream justificatif, String type_ac, String a_bstract, String date,
			String titre, String type_pub, String auteur, String type_sout, String budget, String lieu,
			String description, String duree, String initule, String jury, String niveau, String type_enc,
			String type_responsabilite, String type_activite_pedagogique, int idchercheur, String titre_enc,
			String image) {
		super();
		this.idactivitesc = idactivitesc;
		this.justificatif = justificatif;
		this.type_ac = type_ac;
		this.a_bstract = a_bstract;
		this.date = date;
		this.titre = titre;
		this.type_pub = type_pub;
		this.auteur = auteur;
		this.type_sout = type_sout;
		this.budget = budget;
		this.lieu = lieu;
		this.description = description;
		this.duree = duree;
		this.initule = initule;
		this.jury = jury;
		this.niveau = niveau;
		this.type_enc = type_enc;
		this.type_responsabilite = type_responsabilite;
		this.type_activite_pedagogique = type_activite_pedagogique;
		this.idchercheur = idchercheur;
		this.titre_enc = titre_enc;
		this.image = image;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getTitre_enc() {
		return titre_enc;
	}
	public void setTitre_enc(String titre_enc) {
		this.titre_enc = titre_enc;
	}
	public activitesc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getIdactivitesc() {
		return idactivitesc;
	}
	public void setIdactivitesc(int idactivitesc) {
		this.idactivitesc = idactivitesc;
	}
	public InputStream getJustificatif() {
		return justificatif;
	}
	public void setJustificatif(InputStream justificatif) {
		this.justificatif = justificatif;
	}
	public String getType_ac() {
		return type_ac;
	}
	public void setType_ac(String type_ac) {
		this.type_ac = type_ac;
	}
	public String getA_bstract() {
		return a_bstract;
	}
	public void setA_bstract(String a_bstract) {
		this.a_bstract = a_bstract;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getType_pub() {
		return type_pub;
	}
	public void setType_pub(String type_pub) {
		this.type_pub = type_pub;
	}
	public String getAuteur() {
		return auteur;
	}
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}
	public String getType_sout() {
		return type_sout;
	}
	public void setType_sout(String type_sout) {
		this.type_sout = type_sout;
	}
	public String getBudget() {
		return budget;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuree() {
		return duree;
	}
	public void setDuree(String duree) {
		this.duree = duree;
	}
	public String getInitule() {
		return initule;
	}
	public void setInitule(String initule) {
		this.initule = initule;
	}
	public String getJury() {
		return jury;
	}
	public void setJury(String jury) {
		this.jury = jury;
	}
	public String getNiveau() {
		return niveau;
	}
	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}
	public String getType_enc() {
		return type_enc;
	}
	public void setType_enc(String type_enc) {
		this.type_enc = type_enc;
	}
	public String getType_responsabilite() {
		return type_responsabilite;
	}
	public void setType_responsabilite(String type_responsabilite) {
		this.type_responsabilite = type_responsabilite;
	}
	public String getType_activite_pedagogique() {
		return type_activite_pedagogique;
	}
	public void setType_activite_pedagogique(String type_activite_pedagogique) {
		this.type_activite_pedagogique = type_activite_pedagogique;
	}
	public int getIdchercheur() {
		return idchercheur;
	}
	public void setIdchercheur(int idchercheur) {
		this.idchercheur = idchercheur;
	}
	@Override
	public String toString() {
		return "activitesc [idactivitesc=" + idactivitesc + ", justificatif=" + justificatif + ", type_ac=" + type_ac
				+ ", a_bstract=" + a_bstract + ", date=" + date + ", titre=" + titre + ", type_pub=" + type_pub
				+ ", auteur=" + auteur + ", type_sout=" + type_sout + ", budget=" + budget + ", lieu=" + lieu
				+ ", description=" + description + ", duree=" + duree + ", initule=" + initule + ", jury=" + jury
				+ ", niveau=" + niveau + ", type_enc=" + type_enc + ", type_responsabilite=" + type_responsabilite
				+ ", type_activite_pedagogique=" + type_activite_pedagogique + ", idchercheur=" + idchercheur
				+ ", titre_enc=" + titre_enc + ", image=" + image + "]";
	}
	
	
}

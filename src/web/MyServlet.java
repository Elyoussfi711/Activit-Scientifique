package web;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.sql.Blob;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import java.util.ArrayList;
import java.util.List;

import DAO.*;
import metier.ChercheurPDF;
import metier.Equipe;
import metier.Laboratoire;
import metier.activitesc;
import metier.axe_equipe;
import metier.chercheur;


@MultipartConfig

@WebServlet(urlPatterns = {"/LogIn","/register","/logout","/Docteurpage",
		"/listechercheur","/ajouterchercheur","/deletechercheur","/editformechercheur","/updateChercheur",
		"/ajouterequipe" ,"/listeequipe",
		"/ajouterlaboratoire","/deletelaboratoire","/editformelaboratoire","/updatelaboratoire","/listelaboratoire", 
		"/ajouteraxeequipe" ,"/deleteAxeEquipe","/editformeaxeequipe","/updateaxeequipe","/listeaxeequipe",
		"/ajouterpublication" ,"/listepublication","/deletepublication","/publicationdoctorant","/publicationenseignant","/updateprofildoctorant","/editprofildoctorant",
		"/ajoutersoutenance" ,"/listesoutenance","/editformesoutenance","/updatesoutenance","/soutenancedoctorant","/soutenanceenseignant","/deletesoutenance",
		"/ajouterconference","/listeconference","/conferencedoctorant","/publicationdocteur","/soutenancedocteur","/conferencedocteur","/habilitationdocteur",
		"/deleteconference","/listepublicationdocteur","/listesoutenancedocteur","/listeconferencedocteur",
		"/ajouterproject","/listeproject","/projetderechercheenseignant","/encadrementenseignant","/profildocteur","/editprofildocteur","/updateprofildocteur",
		"/deleteproject","/listepublicationdoctorant","/listeconferencedoctorant","/listesoutenancedoctorant","/editformpublicationdocteur","/updatepublicationdocteur",
		"/responsabiliteenseignant","/activitepedagogiqueenseignant","/profilenseignant","/profildoctorant","/updateprofilenseignant","/editprofilenseignant",
		"/deletepublicationdocteur","/deletesoutenancedocteur","/deleteconferencedocteur","/deletepublicationdoctorant","/deletesoutenancedoctorant","/deleteconferencedoctorant",
		"/editformsoutenancedocteur","/updatesoutenancedocteur","/editformconferencedocteur","/updateconferencedocteur",
		"/editformpublicationdoctorant","/updatepublicationdoctorant","/editformsoutenancedoctorant","/updatesoutenancedoctorant",
		"/editformconferencedoctorant","/updateconferencedoctorant","/listepublicationenseignant","/listesoutenanceenseignant",
		"/profilechercheuradmin","/deleteequipe","/updateequipe","/editequipe","/editformpublication","/updatepublication",
		"/deletepublicationenseignant","/updatepublicationenseignant","/editformpublicationenseignant","/deletesoutenanceenseignant","/editformsoutenanceenseignant",
		"/updatesoutenanceenseignant","/AddImage",
		"/pdf","/Doctorantpage","/Enseignantpage","/adminpage"})
		
public class MyServlet extends HttpServlet {
	private model md ;
	private Logindao logindao;
	private inscription chercheur; 
	private GestionChercheur c ; 
	private GestionLaboratoire l;
	private GestionAxeEquipe ax;
	private GestionEquipe eqi ;
	private GestionActivite acti ;
	
	
	
    public MyServlet() {
        super();
        }
    	private static final long serialVersionUID = 1L;

    	/**
    	 * @see HttpServlet#HttpServlet()
    	 */
    	
    public void init() {
    	 md=new model();
		 logindao = new Logindao();
		 chercheur=new inscription();
		 c=new GestionChercheur();
		 l=new GestionLaboratoire();
		 ax=new GestionAxeEquipe();
		 eqi=new GestionEquipe();
	     acti=new  GestionActivite();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.print("Path : " + request.getServletPath());
		System.out.println();
		
		/************ Log In ************/
		
		if (request.getServletPath().equals("/LogIn")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			System.out.println("username :" + username);
			System.out.println("password :" + password);
			
			chercheur loginC = new chercheur();
			loginC.setUsername(username);
			loginC.setPassword(password);
			Connection conn=Connexion.getConnection();
        
			try{
				if(logindao.login(loginC)) {
					if(logindao.username(loginC).equals("admin")) {
					HttpSession session=request.getSession();
					chercheur cher=new chercheur();
					cher=c.getChercheurUsername(username);
					session.setAttribute("username",cher.getUsername());
					session.setAttribute("prenom",cher.getPrenom());
					session.setAttribute("profile",cher.getProfil());
					
					response.sendRedirect("adminpage");  
					
					
					}else 
						if(logindao.role(loginC).equals("docteur")) {
							HttpSession session=request.getSession();
							chercheur cher=new chercheur();
							cher=c.getChercheurUsername(username);
							session.setAttribute("username",cher.getUsername());
							session.setAttribute("prenom",cher.getPrenom());
							session.setAttribute("profile",cher.getProfil());
							response.sendRedirect("Docteurpage"); 
						}else 
							if(logindao.role(loginC).equals("doctorant")) {
								HttpSession session=request.getSession();
								chercheur cher=new chercheur();
								cher=c.getChercheurUsername(username);
								session.setAttribute("username",cher.getUsername());
								session.setAttribute("prenom",cher.getPrenom());
								session.setAttribute("profile",cher.getProfil());
								
								request.getRequestDispatcher("Doctorantpage").forward(request, response);
						  }else 
							if(logindao.role(loginC).equals("enseignant")) {
								HttpSession session=request.getSession();
								chercheur cher=new chercheur();
								cher=c.getChercheurUsername(username);
								session.setAttribute("username",cher.getUsername());
								session.setAttribute("prenom",cher.getPrenom());
								session.setAttribute("profile",cher.getProfil());
								request.getRequestDispatcher("Enseignantpage").forward(request, response);
								
							}
	            }else {
	            	request.setAttribute("mdpInco", "Email ou mot de passe Incorrect");
					this.getServletContext().getRequestDispatcher("/LogIn.jsp").forward(request, response);
					
	            }
		
			}catch (ClassNotFoundException e) {
	          e.printStackTrace();
	         
	        }
		
			/** response.sendRedirect("test.jsp"); */
			
			
			/***************** Inscription************************/
			
		}		
		else if (request.getServletPath().equals("/register")) {
	   String username=request.getParameter("username");
	   System.out.println("nom :" + username);
	   String email=request.getParameter("email");
	   System.out.println("email :" + email);
	   String prenom=request.getParameter("prenom");
	   String telephone=request.getParameter("tel");
	   String password=request.getParameter("password");
	   String profil=request.getParameter("profil");
	   String repeatpassword=request.getParameter("repeatpassword");
	   System.out.println("prenom :" + prenom);
	   System.out.println("tel :" + telephone);
	   System.out.println("password :" + password); 
	   System.out.println("repeatpassword :" + repeatpassword);
	   System.out.println("profil :" + profil);
	   Part file=request.getPart("image");
	   System.out.println("file :" + file);
	  
	   String imageFileName=file.getSubmittedFileName();  // get selected image file name
		System.out.println("Selected Image File Name : "+imageFileName);
		String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
		
		System.out.println("Upload Path : "+uploadPath);
		
		// Uploading our selected image into the images folder
		try{
		
		
		FileOutputStream fos=new FileOutputStream(uploadPath);
		InputStream is=file.getInputStream();
		
		byte[] data=new byte[is.available()];
		is.read(data);
		fos.write(data);
		fos.close();
		
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		   
				chercheur cher=new chercheur();
				cher.setUsername(username);
				cher.setEmail(email);
				cher.setPrenom(prenom);
				cher.setTel(telephone);
			    cher.setProfil(profil);
			    cher.setPassword(password);
			    cher.setImage(imageFileName);
			 if(password.equals(repeatpassword)) {
				try {
					  
				 	if(chercheur.register(cher)!=0) {
			      		HttpSession session = request.getSession();
			              // session.setAttribute("username",username);
			              response.sendRedirect("home.jsp");
			          } else {
			      
			              HttpSession session = request.getSession();
			           
			          }
					 
			      } catch (ClassNotFoundException e) {
			          e.printStackTrace();
			          
			      }
		   }else {
			   request.setAttribute("mdpInco", "Verifier votre Informations");
				this.getServletContext().getRequestDispatcher("/register.jsp").forward(request, response);
		   }
	   
				
		/********************ajouter chercheur*******************/
				
		}else 
			if(request.getServletPath().equals("/ajouterchercheur")) {
				String username=request.getParameter("username");
				System.out.println("nom :" + username);
				String prenom=request.getParameter("prenom");
				System.out.println("prenom :" + prenom);
				String email=request.getParameter("email");
				System.out.println("email :" + email);
				String password=request.getParameter("password");
				System.out.println("password :" + password);
				String profil=request.getParameter("profil");
				System.out.println("profil :" + profil);
				String tel=request.getParameter("tel");
				System.out.println("tel :" + tel);
				
				//int idequipe =md.getIdequipe();
				
				Part file=request.getPart("image");
				
				String imageFileName=file.getSubmittedFileName();  // get selected image file name
				System.out.println("Selected Image File Name : "+imageFileName);
				String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
				
				System.out.println("Upload Path : "+uploadPath);
				
				// Uploading our selected image into the images folder
				
				try
				{
				
				FileOutputStream fos=new FileOutputStream(uploadPath);
				InputStream is=file.getInputStream();
				
				byte[] data=new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
				
				}
				
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
				
				
			    chercheur addch=new chercheur();
				addch.setUsername(username);
				addch.setEmail(email);
				addch.setPrenom(prenom);
				addch.setTel(tel);
			    addch.setProfil(profil);
			    addch.setPassword(password);
			    addch.setImage(imageFileName);
			    //addch.setIdequipe(md.getIdequipe());
			  
			    
			    try {
					c.addchercheur(addch);
					request.setAttribute("mdpInco", "ajout de chercheur avec succes");
					request.setAttribute("mdpInco1", "vous avez ajouter un chercheur");
					
					this.getServletContext().getRequestDispatcher("/ajouterchercheur.jsp").forward(request, response);

				}
				catch (Exception e) {
					response.sendRedirect("page_404.jsp");
					e.printStackTrace();
			          
			      }
			    /**************  supprimer chercheur        **********************/
			}else 
				if(request.getServletPath().equals("/deletechercheur")) {
					int idchercheur=Integer.parseInt(request.getParameter("idchercheur"));
					try {
						c.deleteChercheur(idchercheur);
						response.sendRedirect("listechercheur");

					}catch(Exception e) {
						response.sendRedirect("page_404.jsp");
						e.printStackTrace();
						
					}
					
					/********     edit forme chercheur par chercheur lui meme        *************/
			}else 
					if(request.getServletPath().equals("/editformechercheur")) {
										  System.out.println(1222);
				     System.out.println("ranidkhlt chercheur");
						
					int idchercheur = Integer.parseInt(request.getParameter("idchercheur"));
					System.out.println("id chercheur  =" + idchercheur);
					chercheur ntoma=c.getCher(idchercheur);
					System.out.println(ntoma.getEmail());
					request.setAttribute("ntoma", ntoma);
					System.out.println(request.getParameter("idchercheur"));
					this.getServletContext().getRequestDispatcher("/modifyformchercheur.jsp").forward(request, response);
						
						/*****************    modifier chercheur par chercheur lui meme    *************/
						
					}else 
						if(request.getServletPath().equals("/updateChercheur")) {
								  System.out.println("12345567899");
					  
					  
						chercheur cher = new chercheur(); 
						
						
						System.out.println(request.getParameter("idchercheur"));
						 cher.setIdchercheur(Integer.parseInt(request.getParameter("idchercheur")));
						 cher.setUsername(request.getParameter("username"));
						 cher.setPassword(request.getParameter("password"));
						 cher.setPrenom(request.getParameter("prenom"));
						 cher.setEmail(request.getParameter("email"));
						 cher.setProfil(request.getParameter("profil"));
						 cher.setTel(request.getParameter("tel"));
					  
						System.out.println(cher);
					    c.updateChercheur(cher);
						
						
					    this.getServletContext().getRequestDispatcher("/listechercheur").forward(request, response);
					  
				System.out.println("hello matha makers");
				
				/********************liste chercheur  *******************/
		}else 
			
			if(request.getServletPath().equals("/listechercheur")) {
				List<chercheur> chercheurs=c.listechercheur();
				request.setAttribute("Chercheurs", chercheurs);
				
				
				
				for(int i=0 ; i<chercheurs.size() ; i++) {              
					int idequipe=chercheurs.get(i).getIdequipe();
					System.out.println(idequipe);
					String nom_equipe=eqi.getNomEquipe(idequipe);
					System.out.println(nom_equipe);
					//listusernames.add(username);
					
					chercheurs.get(i).setNomcher(nom_equipe);
					System.out.println("*****************");
					System.out.println(chercheurs);
					System.out.println("*****************");
					//activites.add(username);
				   // activites.add(username);
				}
				request.getRequestDispatcher("listechercheur.jsp").forward(request, response);
				
				
				
				/********************ajouter  Laboratoire*******************/
				
		}else 
			if(request.getServletPath().equals("/ajouterlaboratoire")) {
				
				String nom_labo=request.getParameter("nom_Labo");
				 System.out.println("nom_labo :" + nom_labo);
				
				 String chef_labo=request.getParameter("chef_Labo");
				 System.out.println("chef_labo :" + chef_labo);
				 
				 

				 Laboratoire labo=new Laboratoire();
				 labo.setNom_Labo(nom_labo);
				 labo.setChef_Labo(chef_labo);
				 System.out.println("sssss");
				 try {
						l.addlaboratoire(labo);
						request.setAttribute("mdpInco", "ajout de Laboratoire avec succee");
						
						this.getServletContext().getRequestDispatcher("/adminpage.jsp").forward(request, response);

					}
					catch (Exception e) {
						response.sendRedirect("page_404.jsp");
						e.printStackTrace();
                      }
				 
				 /************ supprimer laboratoire *********/
			    }else 
			    	
			    	if(request.getServletPath().equals("/deletelaboratoire")) {
			    		int id_Laboratoire=Integer.parseInt(request.getParameter("id_Laboratoire"));
			    		
			    		System.out.println(id_Laboratoire);
			    		System.out.println("ahllllllen a bruder");
						try {
							l.deleteLaboratoire(id_Laboratoire);
							response.sendRedirect("listelaboratoire");

						}catch(Exception e) {
							response.sendRedirect("page_404.jsp");
							e.printStackTrace();
							
						}
			    /********************lister  Laboratoire dans l'admin*******************/
	}else 
		  if(request.getServletPath().equals("/listelaboratoire")) {
			  List<Laboratoire> labo=l.listlabo();
				request.setAttribute("laboratoire", labo);
				request.getRequestDispatcher("listelaboratoire.jsp").forward(request, response);
				
				
				/************     edit forme Laboratoire             ************/
				
		  }else 
			  if(request.getServletPath().equals("/editformelaboratoire")) {
				  
				  System.out.println(1222);
				  System.out.println("ranidkhlt laboratoire");
						
					int id_Laboratoire = Integer.parseInt(request.getParameter("id_Laboratoire"));
					System.out.println("id laboratoire  :" + id_Laboratoire);
					Laboratoire nta=l.getLaboratoire(id_Laboratoire);
					request.setAttribute("nta", nta);
					this.getServletContext().getRequestDispatcher("/modifyformlaboratoire.jsp").forward(request, response);
				  
				  /***********  update laboratoire              ************/
			  }else 
				  
				  if(request.getServletPath().equals("/updatelaboratoire")) {
					  System.out.println("12345567899");
					  
					  
						Laboratoire lab = new Laboratoire(); 
						
						 lab.setId_Laboratoire(Integer.parseInt(request.getParameter("id_Laboratoire")));
						 lab.setNom_Labo(request.getParameter("nom_Labo"));
						 lab.setChef_Labo(request.getParameter("chef_Labo"));
						
					  
						System.out.println(lab);
					    l.updateLaboratoire(lab);
						
						
					    this.getServletContext().getRequestDispatcher("/listelaboratoire").forward(request, response);
					  
				
				
				 /********************Ajouter Axe Equipe*******************/
				
				
		 }else 
			  if(request.getServletPath().equals("/ajouteraxeequipe")) {
				  
				  System.out.println("hhhhhh");
				  String nom_Axe_Equipe=request.getParameter("nom_axe_equipe");
				  System.out.println("nom axe equipe : " + nom_Axe_Equipe);
				  axe_equipe axe=new axe_equipe();
				  axe.setNom_axe_equipe(nom_Axe_Equipe);
				  System.out.println("sssss");
				     try {
				    	 ax.addAxeEquipe(axe);
				    	 request.setAttribute("mdpInco", "ajout d'axe equipe avec succee");
							this.getServletContext().getRequestDispatcher("/ajouteraxeequipe.jsp").forward(request, response);
				     }catch (Exception e) {
							response.sendRedirect("page_404.jsp");
							e.printStackTrace();
				     }
				     /***************   liste axe equipe   admin          *******************/
				     
			  }else 
				  if(request.getServletPath().equals("/listeaxeequipe")) {
					  System.out.println("salut");
					  List<axe_equipe> axe_equipe=ax.axe_equipe();
						request.setAttribute("axeequipe", axe_equipe);
						request.getRequestDispatcher("listeproject").forward(request, response);
					 	
					  /**************** supprimer  axe equipe *****************/
					
				  }else 
					  if(request.getServletPath().equals("/deleteAxeEquipe")) {
						  int id_axe_equipe=Integer.parseInt(request.getParameter("id_axe_equipe"));
							try {
								ax.deleteAxeEquipe(id_axe_equipe);
								response.sendRedirect("listeaxeequipe");

							}catch(Exception e) {
								response.sendRedirect("page_404.jsp");
								e.printStackTrace();
								
							}
					  } 
		              /*********************       schow edit forme axe equipe  (edit servlet)             ****************/
					  else 
						  if(request.getServletPath().equals("/editformeaxeequipe")){
							  
							  System.out.println(1222);
									System.out.println("ranidkhlt");
									
								int id_axe_equipe = Integer.parseInt(request.getParameter("id_axe_equipe"));
								System.out.println("id axe equipe  :" + id_axe_equipe);
								axe_equipe ana=ax.getAxe_equipe(id_axe_equipe);
								request.setAttribute("ana", ana);
								this.getServletContext().getRequestDispatcher("/modifyformaxeequipe.jsp").forward(request, response);
								
							}
							
							  /***************    modifier axe equipe   (modifier ch)         *************/
						  else 
								if(request.getServletPath().equals("/updateaxeequipe")) {
									
									axe_equipe axe = new axe_equipe(); 

										
										 axe.setId_axe_equipe(Integer.parseInt(request.getParameter("id_axe_equipe")));
										 axe.setNom_axe_equipe(request.getParameter("nom_axe_equipe"));
										
									    //em.setPhoto(request.getParameter("photo"));
										System.out.println(axe);
									    ax.updateAxe_equipe(axe);
									    
									    this.getServletContext().getRequestDispatcher("/listeaxeequipe").forward(request, response);
									
									
						/***********        log out                **************/
						}else 
							if(request.getServletPath().equals("/logout")) {
								HttpSession session=request.getSession(); 
								session.removeAttribute("username");
								session.invalidate();  
								response.sendRedirect("home.jsp");	  
								System.out.println("dayz log out");
								
						  
				 /*********** Ajouter equipe par admin *************/
		  }else
					if(request.getServletPath().equals("/ajouterequipe")) {
					
						String nom_equipe=request.getParameter("nom_equipe");
						System.out.println("nom equipe :" + nom_equipe);
						
						
						 
						String nom_Labo=request.getParameter("nom_Labo");
						System.out.println("nom laboratoire :" + nom_Labo);
						
						
						
						String chef_equipe = request.getParameter("chef_equipe");
						System.out.println("chef equipe :" +chef_equipe );
						
						
						
						String nom_cher1=request.getParameter("username1");
						System.out.println("nom chercheur :" + nom_cher1);
						
						String nom_cher2=request.getParameter("username2");
						System.out.println("nom chercheur :" + nom_cher2);
						
						String nom_cher3=request.getParameter("username3");
						System.out.println("nom chercheur :" + nom_cher3);
						
						
					    String nom_axe_equi = request.getParameter("nom_axe_equipe");
					    System.out.println("nom axe_equipe " + nom_axe_equi);			     
						
					     
					     int id_Labo=eqi.getIdLaboratoire(nom_Labo);
					     int id_axe_equipe=eqi.getIdAxe_equipe(nom_axe_equi);
					      int idchercheur=eqi.getIdChercheur(nom_cher1);
					    
					     int idequipe=c.getIdEquipe(idchercheur);
					     
					     int idequipe2=eqi.getIdEquipe(nom_equipe);
					    
					     //md.setIdequipe(idequipe);
					  /*   IdEquipeDansCher(idequipe);
					     
					     eqi.idEquipeDansCher(idchercheur);*/
					     
								
								
								
								//int idlabo = Integer.parseInt(request.getParameter("idlabo"));
								
								//String AX[]=request.getParameterValues("Axeseq");
							
							
								
					     
					      Equipe equi=new Equipe();
					      
					     equi.setNom_equipe(nom_equipe);
					     equi.setChef_equipe(chef_equipe);
					     equi.setId_axe_equipe(id_axe_equipe);
					     equi.setId_Laboratoire(id_Labo);
					     equi.setIdchercheur(idchercheur);
					     equi.setNomcher1(nom_cher1);
					     equi.setNomcher2(nom_cher2);
					     equi.setNomcher3(nom_cher3);
					     
					     
					     
					     
					     
					     List<axe_equipe> axe=eqi.axeequipe();
							request.setAttribute("axeequipe", axe);
							
							
						 List<chercheur> cher=eqi.Listchercheur();
						    request.setAttribute("chercheures", cher);
						   
						    
						 List<Laboratoire> lab =eqi. Listlaboratoire();
						 request.setAttribute("laboratoires", lab);
						
						    
						 List<chercheur> ens =eqi.ListEnseignant();
						 request.setAttribute("enseignant", ens);
						 
		
						try {
							eqi.addEquipe(equi );
							request.setAttribute("mdpInco", "ajout equipe avec succes");
							int id=eqi.idEquipeNom(nom_equipe);
							
							
							chercheur cherc=c.getCherParNom(chef_equipe);
							System.out.println("smiti "+cherc.getUsername());
							cherc.setIdequipe(id);
							c.updateChercheur(cherc);
							
							
							chercheur cherc1=c.getCherParNom( nom_cher1);
							System.out.println("smiti "+cherc1.getUsername());
							cherc1.setIdequipe(id);
							c.updateChercheur(cherc1);
							
							chercheur cherc2=c.getCherParNom( nom_cher2);
							System.out.println("smiti "+cherc2.getUsername());
							cherc2.setIdequipe(id);
							c.updateChercheur(cherc2);
							
							
							chercheur cherc3=c.getCherParNom( nom_cher3);
							System.out.println("smiti "+cherc3.getUsername());
							cherc3.setIdequipe(id);
							c.updateChercheur(cherc3);
							
							
							this.getServletContext().getRequestDispatcher("/ajouterequipe.jsp").forward(request, response);
							
				 
						}catch(Exception e) {
							response.sendRedirect("page_404.jsp");
							e.printStackTrace();	
						}
	
						/******     lister equipe par admin        ***********/
					}else 
						if(request.getServletPath().equals("/listeequipe")) {
							System.out.println("hello word");
							  List<Equipe> equipe=eqi.listeEquipe();
								request.setAttribute("equipes", equipe);
								
								
								
								for(int i=0 ; i<equipe.size() ; i++) {
									int idlaboratoire=equipe.get(i).getId_Laboratoire();
									System.out.println(idlaboratoire);
									String nom_laboratoire=eqi.getNomLaboratoire(idlaboratoire);
									System.out.println(nom_laboratoire);
									//listusernames.add(username);
									
									equipe.get(i).setLaboname(nom_laboratoire);
									System.out.println("*****************");
									System.out.println(equipe);
									System.out.println("*****************");
									//activites.add(username);
								   // activites.add(username);
								}
								
								
								
								for(int i=0 ; i<equipe.size() ; i++) {
									int id_axe_equipe=equipe.get(i).getId_axe_equipe();
									System.out.println(id_axe_equipe);
									String nom_axe_equipe=ax.getNomAxeEquipe(id_axe_equipe);
									System.out.println(nom_axe_equipe);
									//listusernames.add(username);
									
									equipe.get(i).setAxename(nom_axe_equipe);
									System.out.println("*****************");
									System.out.println(equipe);
									System.out.println("*****************");
									//activites.add(username);
								   // activites.add(username);
								}
								
								
								List<chercheur> chercheurs=c.listechercheur();
								
								
								for(int i=0 ; i<chercheurs.size() ; i++) {
									
									int idequipe=chercheurs.get(i).getIdequipe();
									System.out.println("id equipe : " +idequipe);
									
									String nomchercheur=c.getUsernamee(idequipe);
									System.out.println("username :" + nomchercheur);
								 
									equipe.get(i).setNomcher1(nomchercheur);
									System.out.println(equipe);
									
								}
								
								request.getRequestDispatcher("listeequipe.jsp").forward(request, response);
						
                        /********           ajouter publication          **********/
		
					 }else 
						if(request.getServletPath().equals("/ajouterpublication")) {
							String titre_pub=request.getParameter("titre");
							System.out.println("tite publication :" + titre_pub);
							
							String type_publication=request.getParameter("type_pub");
							System.out.println("type publication: " +type_publication );
							
							String date=request.getParameter("date");
							System.out.println("date publication: " +date );
							
							String auteur=request.getParameter("auteur");
							System.out.println("auteur: "  +  auteur);
							
							String description=request.getParameter("description");
							System.out.println("description publication: " + description);
						
							String username=request.getParameter("username");
							System.out.println("nom chercheur  :" + username);
							
							
							
							
							
							
							int idchercheur=acti.getIdChercheur(username);
							
							activitesc actiPub=new activitesc();
							actiPub.setType_ac("publication");
							actiPub.setTitre(titre_pub);
							actiPub.setAuteur(auteur);
							actiPub.setDate(date);
							actiPub.setType_pub(type_publication);
						  	actiPub.setDescription(description);					  	
						  	actiPub.setIdchercheur(idchercheur);
						 
						  	
						    System.out.println("type activites publication" );
							/*
						  	 List<chercheur> cher=eqi.Listchercheur();
							    request.setAttribute("chercheures", cher);*/
							
							 try {
									acti.addActivite(actiPub);
									request.setAttribute("mdpInco", "Vous avez ajouter une publication");
									this.getServletContext().getRequestDispatcher("/ajouterpublication.jsp").forward(request, response);
									
						 
								}catch(Exception e) {
									response.sendRedirect("page_404.jsp");
									e.printStackTrace();	
								}
							 
							 
							 /*************     publication doctorant              *********/
							 
							
						 }else 
							if(request.getServletPath().equals("/publicationdoctorant")) {
								String titre_pub=request.getParameter("titre");
								System.out.println("tite publication :" + titre_pub);
								
								String type_publication=request.getParameter("type_pub");
								System.out.println("type publication: " +type_publication );
								
								String date=request.getParameter("date");
								System.out.println("date publication: " +date );
								
								String auteur=request.getParameter("auteur");
								System.out.println("auteur: "  +  auteur);
								
								String description=request.getParameter("description");
								System.out.println("description publication: " + description);
								
								Part file=request.getPart("image");
								
								String imageFileName=file.getSubmittedFileName();  // get selected image file name
								System.out.println("Selected Image File Name : "+imageFileName);
								String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
								
								System.out.println("Upload Path : "+uploadPath);
								
								// Uploading our selected image into the images folder
								
								try
								{
								
								FileOutputStream fos=new FileOutputStream(uploadPath);
								InputStream is=file.getInputStream();
								
								byte[] data=new byte[is.available()];
								is.read(data);
								fos.write(data);
								fos.close();
								
								}
								
								catch(Exception e)
								{
									e.printStackTrace();
								}
								
								
								activitesc actiPub2=new activitesc();
								actiPub2.setType_ac("publication");
								actiPub2.setTitre(titre_pub);
								actiPub2.setAuteur(auteur);
								actiPub2.setDate(date);
								actiPub2.setType_pub(type_publication);
							  	actiPub2.setDescription(description);
							  	actiPub2.setImage(imageFileName);
							  	
							    System.out.println("type activites publication" );
							    String username=request.getParameter("username");
							    int idchercheur2=eqi.getIdChercheur(username);
							    int idchercheur = idchercheur2;
								System.out.println("id chercheur  =" + idchercheur);
								chercheur ntoma=c.getCher(idchercheur);
								request.setAttribute("ntoma", ntoma);
								
								actiPub2.setIdchercheur(idchercheur);
								
							  	 
								
								 try {
										acti.addActivite(actiPub2);
										request.setAttribute("addequi", "ajouter equipe");
										this.getServletContext().getRequestDispatcher("/publicationdoctorant.jsp").forward(request, response);
										
							 
									}catch(Exception e) {
										response.sendRedirect("page_404.jsp");
										e.printStackTrace();	
									}
							 
							}else 
								if(request.getServletPath().equals("/publicationenseignant")) {
									
									String titre_pub=request.getParameter("titre");
									System.out.println("tite publication :" + titre_pub);
									
									String type_publication=request.getParameter("type_pub");
									System.out.println("type publication: " +type_publication );
									
									String date=request.getParameter("date");
									System.out.println("date publication: " +date );
									
									String auteur=request.getParameter("auteur");
									System.out.println("auteur: "  +  auteur);
									
									String description=request.getParameter("description");
									System.out.println("description publication: " + description);
									
									Part file=request.getPart("image");
									
									String imageFileName=file.getSubmittedFileName();  // get selected image file name
									System.out.println("Selected Image File Name : "+imageFileName);
									String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
									
									System.out.println("Upload Path : "+uploadPath);
									
									// Uploading our selected image into the images folder
									
									try
									{
									
									FileOutputStream fos=new FileOutputStream(uploadPath);
									InputStream is=file.getInputStream();
									
									byte[] data=new byte[is.available()];
									is.read(data);
									fos.write(data);
									fos.close();
									
									}
									
									catch(Exception e)
									{
										e.printStackTrace();
									}
									
									
									
									
									
									
									activitesc actiPub3=new activitesc();
									actiPub3.setType_ac("publication");
									actiPub3.setTitre(titre_pub);
									actiPub3.setAuteur(auteur);
									actiPub3.setDate(date);
									actiPub3.setType_pub(type_publication);
								  	actiPub3.setDescription(description);
								  	actiPub3.setImage(imageFileName);
								  	
								    System.out.println("type activites publication" );
								    String username=request.getParameter("username") ;
								    int idchercheur2=eqi.getIdChercheur(username);
								    
								    int idchercheur = idchercheur2;
									System.out.println("id chercheur  =" + idchercheur);
									chercheur ntoma=c.getCher(idchercheur);
									request.setAttribute("ntoma", ntoma);
									
									actiPub3.setIdchercheur(idchercheur);
								
									
									chercheur cher2=c.getchercheur(idchercheur);
									request.setAttribute("cher2", cher2);
									
									 try {
											acti.addActivite(actiPub3);
											request.setAttribute("addequi", "ajouter publication ");
											this.getServletContext().getRequestDispatcher("/publicationenseignant.jsp").forward(request, response);
											
								 
										}catch(Exception e) {
											response.sendRedirect("page_404.jsp");
											e.printStackTrace();	
										}
									
								}else 
									if(request.getServletPath().equals("/encadrementenseignant")) {
										
										
										String titre_enc=request.getParameter("titre_enc");
										System.out.println("tite encadrement:" + titre_enc);
										
										String initule=request.getParameter("initule");
										System.out.println("initule encadrement: " +initule );
										
										String type_enc=request.getParameter("type_enc");
										System.out.println("type encadrement: " +type_enc );
										
										String date=request.getParameter("date");
										System.out.println("date encadrement: " +date );
										
										String auteur=request.getParameter("auteur");
										System.out.println("auteur: "  +  auteur);
										
										String description=request.getParameter("description");
										System.out.println("description encadrement: " + description);
										
										
										
										activitesc actiPub4=new activitesc();
										actiPub4.setType_ac("Encadrement");
										actiPub4.setTitre_enc(titre_enc);
									//	actiPub4.setAuteur(auteur);
										actiPub4.setDate(date);
										actiPub4.setType_enc(type_enc);
										actiPub4.setInitule(initule);
									  	actiPub4.setDescription(description);					  	
									  	
									    System.out.println("type activites publication" );
									    String username=request.getParameter("username") ;
									    int idchercheur2=eqi.getIdChercheur(username);
									    
									    int idchercheur = idchercheur2;
										System.out.println("id chercheur  =" + idchercheur);
										chercheur ntoma=c.getCher(idchercheur);
										request.setAttribute("ntoma", ntoma);
										
										actiPub4.setIdchercheur(idchercheur);
										 try {
												acti.addActivite(actiPub4);
												request.setAttribute("addequi", "ajouter encadrement ");
												this.getServletContext().getRequestDispatcher("/encadrementenseignant.jsp").forward(request, response);
												
									 
											}catch(Exception e) {
												response.sendRedirect("page_404.jsp");
												e.printStackTrace();	
											}
							 
							 
							 /********     liste publications     **********/
						}
						else 
							if(request.getServletPath().equals("/listepublication")) {
								System.out.println("hello word");
								  List<activitesc> activites=acti.listepublication();
									request.setAttribute("activitess",activites);
									
									for(int i=0 ; i<activites.size() ; i++) {
										int idchercheur=activites.get(i).getIdchercheur();
										System.out.println(idchercheur);
										String username=acti.getUsername(idchercheur);
										System.out.println(username);
										//listusernames.add(username);
										
										activites.get(i).setA_bstract(username);
										System.out.println("*****************");
										System.out.println(activites);
										System.out.println("*****************");
										//activites.add(username);
									   // activites.add(username);
									}
									
									
									
									
									request.getRequestDispatcher("listepublication.jsp").forward(request, response);
								/*******      liste publication dans admin sous forme de fb          ********/
							}
							
							else 
								if(request.getServletPath().equals("/adminpage")) {
									
									  List<activitesc> activites=acti.listepublication();
										request.setAttribute("acti",activites);
										
										 List<String> listusernames = new ArrayList<>();
										 
										 

											
											for(int i=0 ; i<activites.size() ; i++) {
												int idchercheur=activites.get(i).getIdchercheur();
												System.out.println(idchercheur);
												String username=acti.getUsername(idchercheur);
												System.out.println(username);
											//	listusernames.add(username);
												
												activites.get(i).setA_bstract(username);
												System.out.println("*****************");
												System.out.println(activites);
												System.out.println("*****************");
												//activites.add(username);
											   // activites.add(username);
											}
										
										 List<activitesc> activiteconfer=acti.listeConference();
											request.setAttribute("conference",activiteconfer);
										
											
											for(int i=0 ; i<activiteconfer.size() ; i++) {
												int idchercheur=activiteconfer.get(i).getIdchercheur();
												System.out.println(idchercheur);
												String username=acti.getUsername(idchercheur);
												System.out.println(username);
												listusernames.add(username);
												
												activiteconfer.get(i).setA_bstract(username);
												System.out.println("*****************");
												System.out.println(activiteconfer);
												System.out.println("*****************");
												//activites.add(username);
											   // activites.add(username);
											}
											 List<activitesc> activitesout=acti.listeSoutenenace();
												request.setAttribute("soutenance",activitesout);
												
												for(int i=0 ; i<activitesout.size() ; i++) {
													int idchercheur=activitesout.get(i).getIdchercheur();
													System.out.println(idchercheur);
													String username=acti.getUsername(idchercheur);
													System.out.println(username);
													listusernames.add(username);
													
													activitesout.get(i).setA_bstract(username);
													System.out.println("*****************");
													System.out.println(activitesout);
													System.out.println("*****************");
													//activites.add(username);
												   // activites.add(username);
												}
										
										request.getRequestDispatcher("adminpage.jsp").forward(request, response);
									
										/**********   liste publication des chercheur dans la page admin sous forme de fb           ********/
								}else 
									
									if(request.getServletPath().equals("/Doctorantpage")) {
										
										 List<activitesc> activites=acti.listepublication();
											request.setAttribute("acti",activites);
											
											 List<String> listusernames = new ArrayList<>();
											 

												
												for(int i=0 ; i<activites.size() ; i++) {
													int idchercheur=activites.get(i).getIdchercheur();
													System.out.println(idchercheur);
													String username=acti.getUsername(idchercheur);
													System.out.println(username);
													listusernames.add(username);
													
													activites.get(i).setA_bstract(username);
													System.out.println("*****************");
													System.out.println(activites);
													System.out.println("*****************");
													//activites.add(username);
												   // activites.add(username);
												}
											
											 List<activitesc> activiteconfer=acti.listeConference();
												request.setAttribute("conference",activiteconfer);
												
												
											
												 List<activitesc> activitesout=acti.listeSoutenenace();
													request.setAttribute("soutenance",activitesout);
											
											request.getRequestDispatcher("Doctorantpage.jsp").forward(request, response);
										
										
									}else 
										if(request.getServletPath().equals("/Enseignantpage")) {
											
											 List<activitesc> activites=acti.listepublication();
											 request.setAttribute("acti",activites);
											
											 List<String> listusernames = new ArrayList<>();
											 

												
												for(int i=0 ; i<activites.size() ; i++) {
													int idchercheur=activites.get(i).getIdchercheur();
													System.out.println(idchercheur);
													String username=acti.getUsername(idchercheur);
													System.out.println(username);
													listusernames.add(username);
													
													activites.get(i).setA_bstract(username);
													System.out.println("*****************");
													System.out.println(activites);
													System.out.println("*****************");
													//activites.add(username);
												   // activites.add(username);
												}
												
												
												
												// int idchercheur=request.getParameter("idchercheur");
												 
												/*int idchercheur = Integer.parseInt(request.getParameter("idchercheur"));
												System.out.println("idchercheur :" + idchercheur);
												String username=acti.getUsername(idchercheur);
												
												request.setAttribute("username", username);*/
												
											//	System.out.println(activites[idactivitesc]);
												
												
												
												
												 List<activitesc> activiteconfer=acti.listeConference();
													request.setAttribute("conference",activiteconfer);
												
													 List<activitesc> activitesout=acti.listeSoutenenace();
														request.setAttribute("soutenance",activitesout);
												
												request.getRequestDispatcher("Enseignantpage.jsp").forward(request, response);
										
										
		                           /************     supprimer publication    **********/
	                     }else 
		                           if(request.getServletPath().equals("/deletepublication")) {
			                           int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
				                          try {
				                                 	acti.deletePublication(idactivitesc);
					                                response.sendRedirect("listepublication");

		                                	}catch(Exception e) {
					                            response.sendRedirect("page_404.jsp");
					                            e.printStackTrace();
					
				                       }
		                        }
		                           else 
		                        	   if(request.getServletPath().equals("/projetderechercheenseignant")) {
		                        		   String titre=request.getParameter("titre");
											System.out.println("titre  :" + titre);
											
											String budget=request.getParameter("budget");
											System.out.println("budget : "+budget);
											
											String date=request.getParameter("date");
											System.out.println("date Projet : "+date);
												
											String description=request.getParameter("description");
											System.out.println("description :"+description);
											
											String duree=request.getParameter("duree");
											System.out.println("duree " + duree);
											
											String username=request.getParameter("username");
											System.out.println("budget de projet :"+username);
											
											
											List<chercheur> cher=eqi.Listchercheur();
										    request.setAttribute("chercheures", cher);
										    
										    int idchercheur=acti.getIdChercheur(username);
										    
										    activitesc actiProjet=new activitesc();
										    actiProjet.setType_ac("projet de recherche");
										    actiProjet.setTitre(titre);
										    actiProjet.setBudget(budget);
										    actiProjet.setDate(date);
										    actiProjet.setDescription(description);
										    actiProjet.setDuree(duree);
										    actiProjet.setIdchercheur(idchercheur);
										    
										    
										    
										    
											 try {
													acti.addActivite(actiProjet);
													request.setAttribute("ajouterproject", "ajouter projet de recherche");
													this.getServletContext().getRequestDispatcher("/projetderechercheenseignant.jsp").forward(request, response);
													
										 
												}catch(Exception e) {
													response.sendRedirect("page_404.jsp");
													e.printStackTrace();	
												}
		                        	   }
		                        /*****       ajouter soutenance            ***********/
						else  
							if(request.getServletPath().equals("/ajoutersoutenance")) {
							
								String type_sout=request.getParameter("type_sout");
								System.out.println("type soutenance :" + type_sout);
								
								String date = request.getParameter("date");
								System.out.println("date :" + date);
								
								String initule=request.getParameter("initule");
								System.out.println("initule :" + initule);
								
								String lieu=request.getParameter("lieu");
								System.out.println("lieu :" + lieu);
								
								String jury=request.getParameter("jury");
								System.out.println("jury : " + jury);
								
								String description=request.getParameter("description");
								System.out.println("decription : " + description);
								
								String username=request.getParameter("username");
								
								
							/*	Part justificatif = request.getPart("justificatif");
								InputStream   JustificatifInputStream=null;
							
									JustificatifInputStream = justificatif.getInputStream();*/
								
								List<chercheur> cher=eqi.Listchercheur();
							    request.setAttribute("chercheures", cher);
							    
							    
								int idchercheur=acti.getIdChercheur(username);
								
								activitesc actiSout=new activitesc();
								actiSout.setType_sout(type_sout);
								actiSout.setDate(date);
								actiSout.setInitule(initule);
								actiSout.setLieu(lieu);
								actiSout.setJury(jury);
								actiSout.setDescription(description);
								actiSout.setIdchercheur(idchercheur);
								actiSout.setType_ac("soutenance");
								//actiSout.setJustificatif(JustificatifInputStream);
								
								 try {
										acti.addActivite(actiSout);
										 request.setAttribute("mdpInco", "ajout Soutenance avec succee");
										this.getServletContext().getRequestDispatcher("/ajoutersoutenance.jsp").forward(request, response);
										
							 
									}catch(Exception e) {
										response.sendRedirect("page_404.jsp");
										e.printStackTrace();	
									}
								 
								 
							}else 
								if(request.getServletPath().equals("/soutenanceenseignant")) {
									String type_sout=request.getParameter("type_sout");
									System.out.println("type soutenance :" + type_sout);
									
									String date = request.getParameter("date");
									System.out.println("date :" + date);
									
									String initule=request.getParameter("initule");
									System.out.println("initule :" + initule);
									
									String lieu=request.getParameter("lieu");
									System.out.println("lieu :" + lieu);
									
									String jury=request.getParameter("jury");
									System.out.println("jury : " + jury);
									
									String description=request.getParameter("description");
									System.out.println("decription : " + description);
									
									String username=request.getParameter("username");
									int idchercheur=acti.getIdChercheur(username);
									Part file=request.getPart("image");
									
									String imageFileName=file.getSubmittedFileName();  // get selected image file name
									System.out.println("Selected Image File Name : "+imageFileName);
									String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
									
									System.out.println("Upload Path : "+uploadPath);
									
									// Uploading our selected image into the images folder
									
									try
									{
									
									FileOutputStream fos=new FileOutputStream(uploadPath);
									InputStream is=file.getInputStream();
									
									byte[] data=new byte[is.available()];
									is.read(data);
									fos.write(data);
									fos.close();
									
									}
									
									catch(Exception e)
									{
										e.printStackTrace();
									}
									
									activitesc actiSout=new activitesc();
									actiSout.setType_sout(type_sout);
									actiSout.setDate(date);
									actiSout.setInitule(initule);
									actiSout.setLieu(lieu);
									actiSout.setJury(jury);
									actiSout.setDescription(description);
									actiSout.setIdchercheur(idchercheur);
									actiSout.setImage(imageFileName);
									actiSout.setType_ac("soutenance");
									
									
									 try {
											acti.addActivite(actiSout);
											 request.setAttribute("mdpInco", "ajout Soutenance avec succee");
											this.getServletContext().getRequestDispatcher("/soutenanceenseignant.jsp").forward(request, response);
											
								 
										}catch(Exception e) {
											response.sendRedirect("page_404.jsp");
											e.printStackTrace();	
										}
									
									
									
								 /************     liste soutanance             ***********/
								}else 
									if(request.getServletPath().equals("/listesoutenance")) {
										System.out.println("hello liste soutenance");
										 List<activitesc> activitesout=acti.listeSoutenenace();
											request.setAttribute("soutenance",activitesout);
											
											
											for(int i=0 ; i<activitesout.size() ; i++) {
												int idchercheur=activitesout.get(i).getIdchercheur();
												System.out.println(idchercheur);
												String username=acti.getUsername(idchercheur);
												System.out.println(username);
												//listusernames.add(username);
												
												activitesout.get(i).setA_bstract(username);
												System.out.println("*****************");
												System.out.println(activitesout);
												System.out.println("*****************");
												//activites.add(username);
											   // activites.add(username);
											}
											request.getRequestDispatcher("listesoutenance.jsp").forward(request, response);
										
									/*********    editformeSoutenance             **********/
									}else 
										if(request.getServletPath().equals("/editformesoutenance")) {
											
											System.out.println("heelooo edit forme soutenance");
											
											
											int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
											System.out.println("idactivitesc :" + idactivitesc);
											activitesc home=acti.getSoutenance(idactivitesc);
											request.setAttribute("home", home);
											this.getServletContext().getRequestDispatcher("/modifyformsoutenance.jsp").forward(request, response);
											
											/***********   modifier soutenance             **********/
										/*}else 
											if(request.getServletPath().equals("/updatesoutenance")) {
												
												System.out.println("heelooo update soutenance");
												
													
												
												activitesc activite = new activitesc(); 
												
												activite.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
												activite.setType_sout(request.getParameter("type_sout"));
												activite.setInitule(request.getParameter("initule"));
												activite.setDate(request.getParameter("date"));
												activite.setLieu(request.getParameter("lieu"));
												activite.setJury(request.getParameter("jury"));
												activite.setDescription(request.getParameter("description"));
												//em.setPhoto(request.getParameter("photo"));
												
												acti.updateSoutenance(activite);
												
												
											    this.getServletContext().getRequestDispatcher("/listesoutenance").forward(request, response);*/
								
											    /*********** soutenance doctorant            *********/
											    
											}else 
												if(request.getServletPath().equals("/soutenancedoctorant")) {
													
													
													String type_sout=request.getParameter("type_sout");
													System.out.println("type soutenance :" + type_sout);
													
													String date = request.getParameter("date");

													System.out.println("date :" + date);
													
													String initule=request.getParameter("initule");
													System.out.println("initule :" + initule);
													
													String lieu=request.getParameter("lieu");
													System.out.println("lieu :" + lieu);
													
													String jury=request.getParameter("jury");
													System.out.println("jury : " + jury);
													
													String description=request.getParameter("description");
													System.out.println("decription : " + description);
													
													Part file=request.getPart("image");
													
													String imageFileName=file.getSubmittedFileName();  // get selected image file name
													System.out.println("Selected Image File Name : "+imageFileName);
													String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
													
													System.out.println("Upload Path : "+uploadPath);
													
													// Uploading our selected image into the images folder
													
													try
													{
													
													FileOutputStream fos=new FileOutputStream(uploadPath);
													InputStream is=file.getInputStream();
													
													byte[] data=new byte[is.available()];
													is.read(data);
													fos.write(data);
													fos.close();
													
													}
													
													catch(Exception e)
													{
														e.printStackTrace();
													}
													
													
													
												
													
									
													
													activitesc actiSout=new activitesc();
													actiSout.setType_sout(type_sout);
													actiSout.setDate(date);
													actiSout.setInitule(initule);
													actiSout.setLieu(lieu);
													actiSout.setJury(jury);
													actiSout.setDescription(description);
													actiSout.setImage(imageFileName);
													
													actiSout.setType_ac("soutenance");
													String username=request.getParameter("username") ;
												    int idchercheur2=eqi.getIdChercheur(username);
												    
												    int idchercheur = idchercheur2;
													System.out.println("id chercheur  =" + idchercheur);
													chercheur ntoma=c.getCher(idchercheur);
													request.setAttribute("ntoma", ntoma);
													
													actiSout.setIdchercheur(idchercheur);
													//actiSout.setJustificatif(JustificatifInputStream);
													
													 try {
															acti.addActivite(actiSout);
															request.setAttribute("ajouterSoutenance", "ajouter soutenance");
															this.getServletContext().getRequestDispatcher("/soutenancedoctorant.jsp").forward(request, response);
															
												 
														}catch(Exception e) {
															response.sendRedirect("page_404.jsp");
															e.printStackTrace();	
														}
													
													
											    
											    
											    
								/*********     ajouter conference               *********/
						}	
							else 
								if(request.getServletPath().equals("/ajouterconference")) {
									String initule=request.getParameter("initule");
									System.out.println("initule :" + initule);
									
									String date=request.getParameter("date");
									System.out.println("date :" + date);
								
									String lieu=request.getParameter("lieu");
									System.out.println("lieu :" + lieu);
									
									String description=request.getParameter("description");
									System.out.println("decription : " + description);
									
								    String username=request.getParameter("username");
								    System.out.println("nom chercheur" + username);
									
									List<chercheur> cher=eqi.Listchercheur();
								    request.setAttribute("chercheures", cher);
								    
								    
								    int idchercheur=acti.getIdChercheur(username);
								    		
									activitesc actiConf=new activitesc();
									actiConf.setDate(date);
									actiConf.setInitule(initule);
									actiConf.setLieu(lieu);
									actiConf.setDescription(description);
									actiConf.setIdchercheur(idchercheur);
									actiConf.setType_ac("conference");
									
									//actiConf.setJustificatif(justificatif);
									
									
									/*	Part justificatif = request.getPart("justificatif");
										InputStream   JustificatifInputStream=null;
									
											JustificatifInputStream = justificatif.getInputStream();*/
								 try {
									acti.addActivite(actiConf);
									request.setAttribute("mdpInco", "ajout de conference avec succes");
									this.getServletContext().getRequestDispatcher("/ajouterconference.jsp").forward(request, response);
									
						 
								}catch(Exception e) {
									response.sendRedirect("page_404.jsp");
									e.printStackTrace();	
								}
		               }
		
		
								else 
									if(request.getServletPath().equals("/conferencedoctorant")) {
										String initule=request.getParameter("initule");
										System.out.println("initule :" + initule);
										
										String date=request.getParameter("date");
										System.out.println("date :" + date);
									
										String lieu=request.getParameter("lieu");
										System.out.println("lieu :" + lieu);
										
										String description=request.getParameter("description");
										System.out.println("decription : " + description);
										
										String username=request.getParameter("username") ; 
										int idchercheur=eqi.getIdChercheur(username);
										Part file=request.getPart("image");
										
										String imageFileName=file.getSubmittedFileName();  // get selected image file name
										System.out.println("Selected Image File Name : "+imageFileName);
										String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
										
										System.out.println("Upload Path : "+uploadPath);
										
										// Uploading our selected image into the images folder
										
										try
										{
										
										FileOutputStream fos=new FileOutputStream(uploadPath);
										InputStream is=file.getInputStream();
										
										byte[] data=new byte[is.available()];
										is.read(data);
										fos.write(data);
										fos.close();
										
										}
										
										catch(Exception e)
										{
											e.printStackTrace();
										}
										
										activitesc actiConf5=new activitesc();
										actiConf5.setInitule(initule);
										actiConf5.setDate(date);
										actiConf5.setLieu(lieu);
										actiConf5.setDescription(description);
										actiConf5.setIdchercheur(idchercheur);
										actiConf5.setImage(imageFileName);
										actiConf5.setType_ac("conference");
										 try {
												acti.addActivite(actiConf5);
												request.setAttribute("mdpInco", "ajout de conference avec succes");
												this.getServletContext().getRequestDispatcher("/conferencedoctorant.jsp").forward(request, response);
												
									 
											}catch(Exception e) {
												response.sendRedirect("page_404.jsp");
												e.printStackTrace();	
											}
										
										
									
	                          /**************     liste conference                   *****************/
							}	else 
									if(request.getServletPath().equals("/listeconference")) {
										System.out.println("hello liste conference");
										 List<activitesc> activiteconfer=acti.listeConference();
											request.setAttribute("conference",activiteconfer);
											
											for(int i=0 ; i<activiteconfer.size() ; i++) {
												int idchercheur=activiteconfer.get(i).getIdchercheur();
												System.out.println(idchercheur);
												String username=acti.getUsername(idchercheur);
												System.out.println(username);
												//listusernames.add(username);
												
												activiteconfer.get(i).setA_bstract(username);
												System.out.println("*****************");
												System.out.println(activiteconfer);
												System.out.println("*****************");
												//activites.add(username);
											   // activites.add(username);
											}
											
											
											
											request.getRequestDispatcher("listeconference.jsp").forward(request, response);
										
									}	
									/*******     ajouter projet          ************/
								else 
									
									if(request.getServletPath().equals("/ajouterproject")) {
										String titre=request.getParameter("titre");
										System.out.println("titre  :" + titre);
										
										String budget=request.getParameter("budget");
										System.out.println("budget : "+budget);
										
										String date=request.getParameter("date");
										System.out.println("date Projet : "+date);
											
										String description=request.getParameter("description");
										System.out.println("description :"+description);
										
										String duree=request.getParameter("duree");
										System.out.println("duree " + duree);
										
										String username=request.getParameter("username");
										System.out.println("budget de projet :"+username);
										
										
										List<chercheur> cher=eqi.ListEnseignant();
									    request.setAttribute("chercheures", cher);
									    
									    int idchercheur=acti.getIdChercheur(username);
									    
									    activitesc actiProjet=new activitesc();
									    actiProjet.setType_ac("projet de recherche");
									    actiProjet.setTitre(titre);
									    actiProjet.setBudget(budget);
									    actiProjet.setDate(date);
									    actiProjet.setDescription(description);
									    actiProjet.setDuree(duree);
									    actiProjet.setIdchercheur(idchercheur);
									    
									    
									    
									    
										 try {
												acti.addActivite(actiProjet);
												request.setAttribute("mdpInco", "ajout de projet de rechercheur avec succes");
												this.getServletContext().getRequestDispatcher("/ajouterproject.jsp").forward(request, response);
												
									 
											}catch(Exception e) {
												response.sendRedirect("page_404.jsp");
												e.printStackTrace();	
											}
										 /***********   liste projet         ************/
									}else
										if(request.getServletPath().equals("/listeproject")) {
											
											System.out.println("hello liste projet");
											  List<activitesc> activiteproject=acti.listeProjet();
												request.setAttribute("listeproject",activiteproject);
												
												
												for(int i=0 ; i<activiteproject.size() ; i++) {
													int idchercheur=activiteproject.get(i).getIdchercheur();
													System.out.println(idchercheur);
													String username=acti.getUsername(idchercheur);
													System.out.println(username);
													//listusernames.add(username);
													
													activiteproject.get(i).setA_bstract(username);
													System.out.println("*****************");
													System.out.println(activiteproject);
													System.out.println("*****************");
													//activites.add(username);
												   // activites.add(username);
												}
												request.getRequestDispatcher("listeproject.jsp").forward(request, response);
											
										}
		                                 /***************** pdf  liste chercheur *********/
										if(request.getServletPath().equals("/pdf")) {
											try {
												response.setContentType("application/pdf");
												DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
												String currentDateTime = dateFormatter.format(new java.util.Date());
												String headerKey = "Content-Disposition";
												String headerValue = "attachment; filename=liste " + currentDateTime + ".pdf";
												response.setHeader(headerKey, headerValue);
												List<chercheur> listUsers = c.listechercheur();
											 	ChercheurPDF exporter = new ChercheurPDF(listUsers);
											 	exporter.export(response);
											}
											catch(Exception e) {
												response.sendRedirect("page_404.jsp");
												e.printStackTrace();
											}
										}
										/**************   responsabiliter enseignant               ***************/
										
										else if(request.getServletPath().equals("/responsabiliteenseignant")) {
											
											String responsabiliter=request.getParameter("responsabiliter");
											System.out.println("responsabiliter enseignant :" + responsabiliter);
											
											String username=request.getParameter("username");
											System.out.println("username de responsable :" + username);
											
											int idchercheur=c.getIdchercheur(username);
											
											chercheur CHER=new chercheur();
											  CHER.setResponsabiliter(responsabiliter);
											  System.out.println("sssss");
											     try {
											    	 c.addResponsabiliter(CHER, idchercheur);
											    	 System.out.println("je suis un ereure");
											    	 request.setAttribute("mdpInco", "ajout responsabiliter enseignant  avec succee");
														this.getServletContext().getRequestDispatcher("/responsabiliteenseignant.jsp").forward(request, response);
											     }catch (Exception e) {
														response.sendRedirect("page_404.jsp");
														e.printStackTrace();
											     }
											}
										
										else 
											if(request.getServletPath().equals("/activitepedagogiqueenseignant")) {
											
												
												String activiter_pedagogique=request.getParameter("activite_pedalogique");
												System.out.println("activiter pedagogique enseignant  :" + activiter_pedagogique);
												
												String username=request.getParameter("username");
												System.out.println("username de responsable :" + username);
												
												int idchercheur=c.getIdchercheur(username);
												
												chercheur CHER2=new chercheur();
												  CHER2.setActivite_pedagogique(activiter_pedagogique);
												  System.out.println("sssss");
												     try {
												    	 c.addActiviterpedagogique(CHER2, idchercheur);
												    	 request.setAttribute("mdpInco", "ajout responsabiliter enseignant  avec succee");
															this.getServletContext().getRequestDispatcher("/activitepedagogiqueenseignant.jsp").forward(request, response);
												     }catch (Exception e) {
															response.sendRedirect("page_404.jsp");
															e.printStackTrace();
												     }
												
											}
											else 
												if(request.getServletPath().equals("/profilenseignant")) {
													
													System.out.println("hello profil enseignant");
													
													/*String username1 =request.getParameter("username");
													System.out.println("username :" + username1);
													
													int idchercheur=c.getIdchercheur(username1);
													System.out.println("idchercheur :" + idchercheur);*/
													
													String username=request.getParameter("username");
													System.out.println("username de profile :" + username);
													
													int idchercheur=c.getIdchercheur(username);
													System.out.println("id chercheur : " + chercheur);
													
													chercheur cher=c.Profilenseignant(idchercheur);
													
													request.setAttribute("cher", cher);
													this.getServletContext().getRequestDispatcher("/profilenseignant.jsp").forward(request, response);
													
												}
										/***********      edit profile enseignant                *******/
										
												else if(request.getServletPath().equals("/editprofilenseignant")) {
													
													 System.out.println(1222);
													  System.out.println("ranidkhlt profile enseignant");
															
														int idchercheur = Integer.parseInt(request.getParameter("idchercheur"));
														System.out.println("id chercheur  =" + idchercheur);
														
														chercheur ntoma=c.getCher(idchercheur);
														System.out.println(ntoma.getEmail());
														request.setAttribute("ntoma", ntoma);
														System.out.println(request.getParameter("idchercheur"));
														this.getServletContext().getRequestDispatcher("/modifyformprofilenseignant.jsp").forward(request, response);
													
												}
										/**********      update profile enseignant                  **********/
										
												else 
													if(request.getServletPath().equals("/updateprofilenseignant")) {
														  System.out.println("12345567899");
														  
														     
															chercheur cher = new chercheur(); 
															
															
															System.out.println(request.getParameter("idchercheur"));
															 cher.setIdchercheur(Integer.parseInt(request.getParameter("idchercheur")));
															 cher.setUsername(request.getParameter("username"));
															 cher.setPassword(request.getParameter("password"));
															 cher.setPrenom(request.getParameter("prenom"));
															 
															 cher.setEmail(request.getParameter("email"));
															 cher.setTel(request.getParameter("tel"));
															 cher.setProfil(request.getParameter("profil"));
															 cher.setResponsabiliter(request.getParameter("responsabiliter"));
															 cher.setActivite_pedagogique(request.getParameter("activite_pedagogique"));
															 cher.setTel(request.getParameter("tel"));
														  
															System.out.println(cher);
														    c.updateChercheur(cher);
															
															
														    this.getServletContext().getRequestDispatcher("/profilenseignant").forward(request, response);
														  
													System.out.println("hello matha makers");
														
													}
										
										
										/***********   profile doctorant                  **********/
												else if(request.getServletPath().equals("/profildoctorant")) {
                                                    System.out.println("hello profil doctorant");
													
													/*String username1 =request.getParameter("username");
													System.out.println("username :" + username1);
													
													int idchercheur=c.getIdchercheur(username1);
													System.out.println("idchercheur :" + idchercheur);*/
													
													String username=request.getParameter("username");
													System.out.println("username de profile :" + username);
													
													int idchercheur=c.getIdchercheur(username);
													System.out.println("id chercheur : " + chercheur);
													
													chercheur cher2=c.Profilenseignant(idchercheur);
													request.setAttribute("cher2", cher2);
													this.getServletContext().getRequestDispatcher("/profildoctorant.jsp").forward(request, response);
													
													
												}
										/************     update profile doctorant            ************/
										
												else
													if(request.getServletPath().equals("/updateprofildoctorant")) {
														Part file=request.getPart("image");
														
														String imageFileName=file.getSubmittedFileName();  // get selected image file name
														System.out.println("Selected Image File Name : "+imageFileName);
														String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
														
														System.out.println("Upload Path : "+uploadPath);
														
														// Uploading our selected image into the images folder
														
														try
														{
														
														FileOutputStream fos=new FileOutputStream(uploadPath);
														InputStream is=file.getInputStream();
														
														byte[] data=new byte[is.available()];
														is.read(data);
														fos.write(data);
														fos.close();
														
														}
														
														catch(Exception e)
														{
															e.printStackTrace();
														}
														
														System.out.println("12345567899");
														  
													     
														chercheur cher = new chercheur(); 
														
														
														System.out.println(request.getParameter("idchercheur"));
														 cher.setIdchercheur(Integer.parseInt(request.getParameter("idchercheur")));
														 cher.setUsername(request.getParameter("username"));
														 cher.setPassword(request.getParameter("password"));
														 cher.setPrenom(request.getParameter("prenom"));
														 cher.setTel(request.getParameter("tel"));
														 cher.setEmail(request.getParameter("email"));
														 cher.setProfil(request.getParameter("profil"));
														 cher.setImage(imageFileName);
														
														 cher.setTel(request.getParameter("tel"));
													  
														System.out.println(cher);
													    c.updateChercheur(cher);
														
														
													    this.getServletContext().getRequestDispatcher("/profildoctorant").forward(request, response);
													  
												System.out.println("hello matha makers");
													}
										/*************   edit forme profil doctorant              ************/
										
													else if(request.getServletPath().equals("/editprofildoctorant")) {
														
														 System.out.println(1222);
														  System.out.println("ranidkhlt profile doctorant");
																
															int idchercheur = Integer.parseInt(request.getParameter("idchercheur"));
															System.out.println("id chercheur  =" + idchercheur);
															
															chercheur ntoma=c.getCher(idchercheur);
															System.out.println(ntoma.getEmail());
															request.setAttribute("ntoma", ntoma);
															System.out.println(request.getParameter("idchercheur"));
															this.getServletContext().getRequestDispatcher("/modifyformprofildoctorant.jsp").forward(request, response);
														
													}
										/***************   docteur page                     *********/
													else if(request.getServletPath().equals("/Docteurpage")) {
														List<activitesc> activites=acti.listepublication();
														request.setAttribute("acti",activites);
														
														
														 List<String> listusernames = new ArrayList<>();
														 

															
															for(int i=0 ; i<activites.size() ; i++) {
																int idchercheur=activites.get(i).getIdchercheur();
																System.out.println(idchercheur);
																String username=acti.getUsername(idchercheur);
																System.out.println(username);
																listusernames.add(username);
																
																activites.get(i).setA_bstract(username);
																System.out.println("*****************");
																System.out.println(activites);
																System.out.println("*****************");
																//activites.add(username);
															   // activites.add(username);
															}
														List<activitesc> activiteconfer=acti.listeConference();
															request.setAttribute("conference",activiteconfer);
														
															 List<activitesc> activitesout=acti.listeSoutenenace();
																request.setAttribute("soutenance",activitesout);
																
																String username =request.getParameter("username");
																int idchercheur=c.getIdchercheur(username);
																
																chercheur cher=c.getchercheur(idchercheur);
																request.setAttribute("cher", cher);
																System.out.println("id chercheur : " + idchercheur);
																
																
																
														request.getRequestDispatcher("Docteurpage.jsp").forward(request, response);
													}
										/************      profil docteur                *************/
													else if(request.getServletPath().equals("/profildocteur")) {
														
														 System.out.println("hello profil doctorant");
															
															/*String username1 =request.getParameter("username");
															System.out.println("username :" + username1);
															
															int idchercheur=c.getIdchercheur(username1);
															System.out.println("idchercheur :" + idchercheur);*/
														 
															
															String username=request.getParameter("username");
															System.out.println("username de profile :" + username);
															
															int idchercheur=c.getIdchercheur(username);
															System.out.println("id chercheur : " + chercheur);
															
															chercheur cher2=c.Profilenseignant(idchercheur);
															
															request.setAttribute("cher2", cher2);
															this.getServletContext().getRequestDispatcher("/profildocteur.jsp").forward(request, response);
														
													}
										/************    publication docteur                          ***************/
													else if(request.getServletPath().equals("/publicationdocteur")) {
														String titre_pub=request.getParameter("titre");
														System.out.println("tite publication :" + titre_pub);
														
														String type_publication=request.getParameter("type_pub");
														System.out.println("type publication: " +type_publication );
														
														String date=request.getParameter("date");
														System.out.println("date publication: " +date );
														
														String auteur=request.getParameter("auteur");
														System.out.println("auteur: "  +  auteur);
														
														String description=request.getParameter("description");
														System.out.println("description publication: " + description);
														
														
														Part file=request.getPart("image");
														
														String imageFileName=file.getSubmittedFileName();  // get selected image file name
														System.out.println("Selected Image File Name : "+imageFileName);
														String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
														
														System.out.println("Upload Path : "+uploadPath);
														
														// Uploading our selected image into the images folder
														
														try
														{
														
														FileOutputStream fos=new FileOutputStream(uploadPath);
														InputStream is=file.getInputStream();
														
														byte[] data=new byte[is.available()];
														is.read(data);
														fos.write(data);
														fos.close();
														
														}
														
														catch(Exception e)
														{
															e.printStackTrace();
														}
														
														
														activitesc actiPub2=new activitesc();
														actiPub2.setType_ac("publication");
														actiPub2.setTitre(titre_pub);
														actiPub2.setAuteur(auteur);
														actiPub2.setDate(date);
														actiPub2.setType_pub(type_publication);
													  	actiPub2.setDescription(description);
													  	actiPub2.setImage(imageFileName);
													  	
													  
													  	
													    System.out.println("type activites publication" );
													    String username=request.getParameter("username") ;
													    int idchercheur2=eqi.getIdChercheur(username);
													    
													    int idchercheur = idchercheur2;
														System.out.println("id chercheur  =" + idchercheur);
														chercheur ntoma=c.getCher(idchercheur);
														request.setAttribute("ntoma", ntoma);
														
														actiPub2.setIdchercheur(idchercheur);
														
													  	 
														
														 try {
																acti.addActivite(actiPub2);
																request.setAttribute("addequi", "ajouter equipe");
																this.getServletContext().getRequestDispatcher("/publicationdocteur.jsp").forward(request, response);
																
													 
															}catch(Exception e) {
																response.sendRedirect("page_404.jsp");
																e.printStackTrace();	
															}
													}
										/**********     soutenance docteur               ************/
													else if(request.getServletPath().equals("/soutenancedocteur")) {
														
														String type_sout=request.getParameter("type_sout");
														System.out.println("type soutenance :" + type_sout);
														
														String date = request.getParameter("date");

														System.out.println("date :" + date);
														
														String initule=request.getParameter("initule");
														System.out.println("initule :" + initule);
														
														String lieu=request.getParameter("lieu");
														System.out.println("lieu :" + lieu);
														
														String jury=request.getParameter("jury");
														System.out.println("jury : " + jury);
														
														String description=request.getParameter("description");
														System.out.println("decription : " + description);
														
													
														Part file=request.getPart("image");
														
														String imageFileName=file.getSubmittedFileName();  // get selected image file name
														System.out.println("Selected Image File Name : "+imageFileName);
														String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
														
														System.out.println("Upload Path : "+uploadPath);
														
														// Uploading our selected image into the images folder
														
														try
														{
														
														FileOutputStream fos=new FileOutputStream(uploadPath);
														InputStream is=file.getInputStream();
														
														byte[] data=new byte[is.available()];
														is.read(data);
														fos.write(data);
														fos.close();
														
														}
														
														catch(Exception e)
														{
															e.printStackTrace();
														}
										
														
														activitesc actiSout=new activitesc();
														actiSout.setType_sout(type_sout);
														actiSout.setDate(date);
														actiSout.setInitule(initule);
														actiSout.setLieu(lieu);
														actiSout.setJury(jury);
														actiSout.setDescription(description);
														actiSout.setImage(imageFileName);
														//actiSout.setJustificatif(JustificatifInputStream);
														
														
														actiSout.setType_ac("soutenance");
														String username=request.getParameter("username") ;
													    int idchercheur2=eqi.getIdChercheur(username);
													    
													    int idchercheur = idchercheur2;
														System.out.println("id chercheur  =" + idchercheur);
														chercheur ntoma=c.getCher(idchercheur);
														request.setAttribute("ntoma", ntoma);
														
														actiSout.setIdchercheur(idchercheur);
														//actiSout.setJustificatif(JustificatifInputStream);
														
														 try {
																acti.addActivite(actiSout);
																request.setAttribute("ajouterSoutenance", "ajouter soutenance");
																this.getServletContext().getRequestDispatcher("/soutenancedocteur.jsp").forward(request, response);
																
													 
															}catch(Exception e) {
																response.sendRedirect("page_404.jsp");
																e.printStackTrace();	
															}
														
													}
										
										/*************** conference docteur                 **************/
													else if(request.getServletPath().equals("/conferencedocteur")) {
														String initule=request.getParameter("initule");
														System.out.println("initule :" + initule);
														
														String date=request.getParameter("date");
														System.out.println("date :" + date);
													
														String lieu=request.getParameter("lieu");
														System.out.println("lieu :" + lieu);
														
														String description=request.getParameter("description");
														System.out.println("decription : " + description);
														
														String username=request.getParameter("username") ; 
														int idchercheur=eqi.getIdChercheur(username);
														Part file=request.getPart("image");
														
														String imageFileName=file.getSubmittedFileName();  // get selected image file name
														System.out.println("Selected Image File Name : "+imageFileName);
														String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
														
														System.out.println("Upload Path : "+uploadPath);
														
														// Uploading our selected image into the images folder
														
														try
														{
														
														FileOutputStream fos=new FileOutputStream(uploadPath);
														InputStream is=file.getInputStream();
														
														byte[] data=new byte[is.available()];
														is.read(data);
														fos.write(data);
														fos.close();
														
														}
														
														catch(Exception e)
														{
															e.printStackTrace();
														}
														
														activitesc actiConf5=new activitesc();
														actiConf5.setInitule(initule);
														actiConf5.setDate(date);
														actiConf5.setLieu(lieu);
														actiConf5.setDescription(description);
														actiConf5.setIdchercheur(idchercheur);
														actiConf5.setImage(imageFileName);
														actiConf5.setType_ac("conference");
														 try {
																acti.addActivite(actiConf5);
																request.setAttribute("mdpInco", "ajout de conference avec succes");
																this.getServletContext().getRequestDispatcher("/conferencedocteur.jsp").forward(request, response);
																
													 
															}catch(Exception e) {
																response.sendRedirect("page_404.jsp");
																e.printStackTrace();	
															}
														
													}
										/************************      edit profil docteur                      *****************/
													else 
														if(request.getServletPath().equals("/editprofildocteur")) {
															
															 System.out.println(1222);
															  System.out.println("ranidkhlt profile docteur");
																	
																int idchercheur = Integer.parseInt(request.getParameter("idchercheur"));
																System.out.println("id chercheur  =" + idchercheur);
																
																chercheur ntoma=c.getCher(idchercheur);
																System.out.println(ntoma.getEmail());
																request.setAttribute("ntoma", ntoma);
																System.out.println(request.getParameter("idchercheur"));
																this.getServletContext().getRequestDispatcher("/modifyformprofildocteur.jsp").forward(request, response);
														}
										 /**************            update profile docteur                       **************/
														else 
															if(request.getServletPath().equals("/updateprofildocteur")) {
																System.out.println("12345567899");
																  
															     
																chercheur cher = new chercheur(); 
																
																
																System.out.println(request.getParameter("idchercheur"));
																 cher.setIdchercheur(Integer.parseInt(request.getParameter("idchercheur")));
																 cher.setUsername(request.getParameter("username"));
																 cher.setPassword(request.getParameter("password"));
																 cher.setPrenom(request.getParameter("prenom"));
																 cher.setEmail(request.getParameter("email"));
																 cher.setProfil(request.getParameter("profil"));
																
																 
																 
																 cher.setTel(request.getParameter("tel"));
															  
																System.out.println(cher);
															    c.updateChercheur(cher);
																
																
															    this.getServletContext().getRequestDispatcher("/profildoctorant").forward(request, response);
															  
														System.out.println("hello matha makers");
																
															}
										/*****************       delete soutenance               **************/
															else 
																if(request.getServletPath().equals("/deletesoutenance")) {
																	  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																		try {
																			acti.deletesoutenance(idactivitesc);
																			response.sendRedirect("listesoutenance");

																		}catch(Exception e) {
																			response.sendRedirect("page_404.jsp");
																			e.printStackTrace();
																			
																		}
																	
																}
									             	/******          delete conference              ***********/
																else 
																	if(request.getServletPath().equals("/deleteconference")) {
																		  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																			try {
																				acti.deleteconference(idactivitesc);
																				response.sendRedirect("listeconference");

																			}catch(Exception e) {
																				response.sendRedirect("page_404.jsp");
																				e.printStackTrace();
																				
																			}
																		
																	}
										
										                      /***********     delete projet de recheche             ************/
										
																	else 
																		if(request.getServletPath().equals("/deleteproject")) {
																			  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																				try {
																					acti.deleteproject(idactivitesc);
																					response.sendRedirect("listeproject");

																				}catch(Exception e) {
																					response.sendRedirect("page_404.jsp");
																					e.printStackTrace();
																					
																				}
																			
																		}
										         /***********************               liste publication docteur                           ********************/
																		else 
																			if(request.getServletPath().equals("/listepublicationdocteur")) {
																				
																				String username=request.getParameter("username");
																				System.out.println("username " + username );
																				
																				int idchercheur =c.getIdchercheur(username);
																				
																				List<activitesc> act=acti.listepublicationdocteur(idchercheur);
																				request.setAttribute("publication", act);
																				
																				chercheur cher=c.getchercheur(idchercheur);
																				request.setAttribute("cher", cher);
																				System.out.println("id chercheur : " + idchercheur);
																				
																				
																				request.getRequestDispatcher("listepublicationdocteur.jsp").forward(request, response);
																			}
										                        /**************       liste soutenance docteur              ***************/
										
																			else 
																				if(request.getServletPath().equals("/listesoutenancedocteur")) {
																					
																					String username=request.getParameter("username");
																					System.out.println("username " + username );
																					
																					int idchercheur =c.getIdchercheur(username);
																					
																					List<activitesc> sout=acti.listesoutenancedocteur(idchercheur);
																					request.setAttribute("soutenance", sout);
																					
																					chercheur cher=c.getchercheur(idchercheur);
																					request.setAttribute("cher", cher);
																					System.out.println("id chercheur : " + idchercheur);
																					
																					
																					request.getRequestDispatcher("listesoutenancedocteur.jsp").forward(request, response);
																				}
										                                       /**************  liste conference docteur        *************/
										
																				else 
																					if(request.getServletPath().equals("/listeconferencedocteur")) {
																						
																						String username=request.getParameter("username");
																						System.out.println("username " + username );
																						
																						int idchercheur =c.getIdchercheur(username);
																						
																						List<activitesc> confer=acti.listeconferencedocteur(idchercheur);
																						request.setAttribute("conference", confer);
																						
																						chercheur cher=c.getchercheur(idchercheur);
																						request.setAttribute("cher", cher);
																						System.out.println("id chercheur : " + idchercheur);
																						
																						
																						request.getRequestDispatcher("listeconferencedocteur.jsp").forward(request, response);
																						
																					}
										
										
																					else 
																						if(request.getServletPath().equals("/listepublicationdoctorant")) {
																							
																							String username=request.getParameter("username");
																							System.out.println("username " + username );
																							
																							int idchercheur =c.getIdchercheur(username);
																							
																							List<activitesc> pub=acti.listepublicationdoctorant(idchercheur);
																							request.setAttribute("publication", pub);
																							
																							
																							request.getRequestDispatcher("listepublicationdoctorant.jsp").forward(request, response);
																							
																						}
										
																						else if(request.getServletPath().equals("/listeconferencedoctorant")) {
																							String username=request.getParameter("username");
																							System.out.println("username " + username );
																							
																							int idchercheur =c.getIdchercheur(username);
																							
																							List<activitesc> con=acti.listeconferencedoctorant(idchercheur);
																							request.setAttribute("conference", con);
																							
																							
																							request.getRequestDispatcher("listeconferencedoctorant.jsp").forward(request, response);
																							
																							
																						}
																						else 
																							if(request.getServletPath().equals("/listesoutenancedoctorant")) {
																								String username=request.getParameter("username");
																								System.out.println("username " + username );
																								
																								int idchercheur =c.getIdchercheur(username);
																								
																								List<activitesc> sout=acti.listesoutenancedoctorant(idchercheur);
																								request.setAttribute("soutenance", sout);
																								
																								
																								request.getRequestDispatcher("listesoutenancedoctorant.jsp").forward(request, response);
																								
																							}
										
																							else if(request.getServletPath().equals("/deletepublicationdocteur")) {
																								  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																									try {
																										acti.deletepublicationdocteur(idactivitesc);
																										response.sendRedirect("Docteurpage");

																									}catch(Exception e) {
																										response.sendRedirect("page_404.jsp");
																										e.printStackTrace();
																										
																									}
																								
																							}
																							else if(request.getServletPath().equals("/deletesoutenancedocteur")) {
																								  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																									try {
																										acti.deletesoutenancedocteur(idactivitesc);
																										response.sendRedirect("Docteurpage");

																									}catch(Exception e) {
																										response.sendRedirect("page_404.jsp");
																										e.printStackTrace();
																										
																									}
																							}
																							else if(request.getServletPath().equals("/deleteconferencedocteur")) {
																								  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																									try {
																										acti.deleteconferencedocteur(idactivitesc);
																										response.sendRedirect("Docteurpage");

																									}catch(Exception e) {
																										response.sendRedirect("page_404.jsp");
																										e.printStackTrace();
																										
																									}
																								
																							}
										
																							else 
																								if(request.getServletPath().equals("/deletepublicationdoctorant")) {
																									  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																										try {
																											acti.deleteconferencedoctorant(idactivitesc);
																											response.sendRedirect("Doctorantpage");

																										}catch(Exception e) {
																											response.sendRedirect("page_404.jsp");
																											e.printStackTrace();
																											
																										}
																									
																								}
																								else if(request.getServletPath().equals("/deletesoutenancedoctorant")) {
																									  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																										try {
																											acti.deleteconferencedoctorant(idactivitesc);
																											response.sendRedirect("Doctorantpage");

																										}catch(Exception e) {
																											response.sendRedirect("page_404.jsp");
																											e.printStackTrace();
																											
																										}
																								}
																								else if(request.getServletPath().equals("/deleteconferencedoctorant")) {
																									  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																										try {
																											acti.deleteconferencedoctorant(idactivitesc);
																											response.sendRedirect("Doctorantpage");

																										}catch(Exception e) {
																											response.sendRedirect("page_404.jsp");
																											e.printStackTrace();
																											
																										}
																								}
																								else 
																									if(request.getServletPath().equals("/editformpublicationdocteur")) {
																									 System.out.println(1222);
																									  System.out.println("ranidkhlt laboratoire");
																											
																										int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
																										System.out.println("id activite  :" + idactivitesc);
																									    activitesc nta=acti.getpublicationdocteur(idactivitesc);
																										request.setAttribute("nta",nta );
																										this.getServletContext().getRequestDispatcher("/modifyformpublicationdocteur.jsp").forward(request, response);
																									
																								}
																								else if(request.getServletPath().equals("/updatepublicationdocteur")) {
																									activitesc pub = new activitesc(); 

																									
																									 pub.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
																									 
																									 pub.setTitre(request.getParameter("titre"));
																									 pub.setAuteur(request.getParameter("auteur"));
																									// pub.setIdactivitesc(request.getParameter("idactivitesc"));
																									 pub.setDate(request.getParameter("date"));
																									 pub.setType_pub(request.getParameter("type_pub"));
																									 pub.setDescription(request.getParameter("description"));
																									 //pub.setTitre(request.getParameter("titre"));
																									
																								    //em.setPhoto(request.getParameter("photo"));
																									System.out.println(pub);
																								    acti.updatepublicationdocteur(pub);
																								    
																								    this.getServletContext().getRequestDispatcher("/Docteurpage").forward(request, response);
																									
																								}
										
										
										
																								else if(request.getServletPath().equals("/editformsoutenancedocteur")) {
																									System.out.println(1222);
																									  System.out.println("ranidkhlt soutenance docteur");
																											
																										int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
																										System.out.println("id activite  :" + idactivitesc);
																									    activitesc nta=acti.getsoutenancedocteur(idactivitesc);
																										request.setAttribute("nta",nta );
																										this.getServletContext().getRequestDispatcher("/modifyformsoutenancedocteur.jsp").forward(request, response);
																								}
										
										
																								else if(request.getServletPath().equals("/updatesoutenancedocteur")) {
																									activitesc sout = new activitesc(); 
                                                                                                    sout.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
																									 
                                                                                                    sout.setType_sout(request.getParameter("type_sout"));
                                                                                                    sout.setInitule(request.getParameter("initule"));
																									// pub.setIdactivitesc(request.getParameter("idactivitesc"));
                                                                                                    sout.setDate(request.getParameter("date"));
                                                                                                    sout.setLieu(request.getParameter("lieu"));
                                                                                                    sout.setJury(request.getParameter("jury"));
                                                                                                    sout.setDescription(request.getParameter("description"));
																									 //pub.setTitre(request.getParameter("titre"));
																									
																								    //em.setPhoto(request.getParameter("photo"));
																									System.out.println(sout);
																								    acti.updatesoutenancedocteur(sout);
																								    
																								    this.getServletContext().getRequestDispatcher("/Docteurpage").forward(request, response);
																		
																								}
										
										
																								else if(request.getServletPath().equals("/editformconferencedocteur")) {
																									System.out.println(1222);
																									  System.out.println("ranidkhlt soutenance docteur");
																											
																										int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
																										System.out.println("id activite  :" + idactivitesc);
																									    activitesc nta=acti.getconferencedocteur(idactivitesc);
																										request.setAttribute("nta",nta );
																										this.getServletContext().getRequestDispatcher("/modifyformconferencedocteur.jsp").forward(request, response);
																								}
										
										                                                        /**********     update conference docteur                         ***************/
																								else if(request.getServletPath().equals("/updateconferencedocteur")) {
																									
																									activitesc sout = new activitesc(); 
                                                                                                    sout.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
																									 
                                                                                                    sout.setType_sout(request.getParameter("type_sout"));
                                                                                                    sout.setInitule(request.getParameter("initule"));
																									// pub.setIdactivitesc(request.getParameter("idactivitesc"));
                                                                                                    sout.setDate(request.getParameter("date"));
                                                                                                    sout.setLieu(request.getParameter("lieu"));
                                                                                                    sout.setJury(request.getParameter("jury"));
                                                                                                    sout.setDescription(request.getParameter("description"));
																									 //pub.setTitre(request.getParameter("titre"));
																									
																								    //em.setPhoto(request.getParameter("photo"));
																									System.out.println(sout);
																								    acti.updateconferencedocteur(sout);
																								    
																								    this.getServletContext().getRequestDispatcher("/Docteurpage").forward(request, response);
																								}
																								else 
																									if(request.getServletPath().equals("/editformpublicationdoctorant")) {
																										System.out.println(1222);
																										  System.out.println("ranidkhlt publication dotorant");
																												
																											int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
																											System.out.println("id activite  :" + idactivitesc);
																										    activitesc nta=acti.getpublicationdoctorant(idactivitesc);
																											request.setAttribute("nta",nta );
																											this.getServletContext().getRequestDispatcher("/modifyformpublicationdoctorant.jsp").forward(request, response);
																									}
																									else 
																										if(request.getServletPath().equals("/updatepublicationdoctorant")) {
																											
																											activitesc pub = new activitesc(); 

																											
																											 pub.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
																											 
																											 pub.setTitre(request.getParameter("titre"));
																											 pub.setAuteur(request.getParameter("auteur"));
																											// pub.setIdactivitesc(request.getParameter("idactivitesc"));
																											 pub.setDate(request.getParameter("date"));
																											 pub.setType_pub(request.getParameter("type_pub"));
																											 pub.setDescription(request.getParameter("description"));
																											 //pub.setTitre(request.getParameter("titre"));
																											
																										    //em.setPhoto(request.getParameter("photo"));
																											System.out.println(pub);
																										    acti.updatepublicationdoctorant(pub);
																										    
																										    this.getServletContext().getRequestDispatcher("/Doctorantpage").forward(request, response);
																							
																											
																										    
																										}
																										else 
																											if(request.getServletPath().equals("/editformsoutenancedoctorant")) {
																												
																												System.out.println(1222);
																												  System.out.println("ranidkhlt publication dotorant");
																														
																													int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
																													System.out.println("id activite  :" + idactivitesc);
																												    activitesc nta=acti.getsoutenancedoctorant(idactivitesc);
																													request.setAttribute("nta",nta );
																													this.getServletContext().getRequestDispatcher("/modifyformsoutenancedoctorant.jsp").forward(request, response);
																											}
																											else 
																												if(request.getServletPath().equals("/updatesoutenancedoctorant")) {
																													
																													activitesc sout = new activitesc(); 
				                                                                                                    sout.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
																													 
				                                                                                                    sout.setType_sout(request.getParameter("type_sout"));
				                                                                                                    sout.setInitule(request.getParameter("initule"));
																													// pub.setIdactivitesc(request.getParameter("idactivitesc"));
				                                                                                                    sout.setDate(request.getParameter("date"));
				                                                                                                    sout.setLieu(request.getParameter("lieu"));
				                                                                                                    sout.setJury(request.getParameter("jury"));
				                                                                                                    sout.setDescription(request.getParameter("description"));
																													 //pub.setTitre(request.getParameter("titre"));
																													
																												    //em.setPhoto(request.getParameter("photo"));
																													System.out.println(sout);
																												    acti.updatesoutenancedoctorant(sout);
																												    
																												    this.getServletContext().getRequestDispatcher("/Doctorantpage").forward(request, response);
																												}
																												else 
																													if(request.getServletPath().equals("/editformconferencedoctorant")) {

																														System.out.println(1222);
																														  System.out.println("ranidkhlt publication dotorant");
																																
																															int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
																															System.out.println("id activite  :" + idactivitesc);
																														    activitesc nta=acti.getconferencedoctorant(idactivitesc);
																															request.setAttribute("nta",nta );
																															this.getServletContext().getRequestDispatcher("/modifyformconferencedoctorant.jsp").forward(request, response);
																														
																													}
																													else 
																														if(request.getServletPath().equals("/updateconferencedoctorant")) {
																															activitesc sout = new activitesc(); 
						                                                                                                    sout.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
																															 
						                                                                                                    sout.setType_sout(request.getParameter("type_sout"));
						                                                                                                    sout.setInitule(request.getParameter("initule"));
																															// pub.setIdactivitesc(request.getParameter("idactivitesc"));
						                                                                                                    sout.setDate(request.getParameter("date"));
						                                                                                                    sout.setLieu(request.getParameter("lieu"));
						                                                                                                    sout.setJury(request.getParameter("jury"));
						                                                                                                    sout.setDescription(request.getParameter("description"));
																															 //pub.setTitre(request.getParameter("titre"));
																															
																														    //em.setPhoto(request.getParameter("photo"));
																															System.out.println(sout);
																														    acti.updateconferencedoctorant(sout);
																														    
																														    this.getServletContext().getRequestDispatcher("/Doctorantpage").forward(request, response);
																														}
																														else 
																															if(request.getServletPath().equals("/listepublicationenseignant")) {
																																String username=request.getParameter("username");
																																System.out.println("username " + username );
																																
																																int idchercheur =c.getIdchercheur(username);
																																
																																List<activitesc> pub=acti.listepublicationenseignant(idchercheur);
																																request.setAttribute("publication", pub);
																																
																																
																																request.getRequestDispatcher("listepublicationenseignant.jsp").forward(request, response);
																															}
																															else 
																																
																															if(request.getServletPath().equals("/listesoutenanceenseignant")) {
																																String username=request.getParameter("username");
																																System.out.println("username " + username );
																																
																																int idchercheur =c.getIdchercheur(username);
																																
																																List<activitesc> sout=acti.listesoutenanceenseignant(idchercheur);
																																request.setAttribute("soutenance", sout);
																																
																																
																																request.getRequestDispatcher("listesoutenanceenseignant.jsp").forward(request, response);
																																}
																															else 
																																if(request.getServletPath().equals("/encadrementenseignant")) {
																																	
																																	String username=request.getParameter("username");
																																	System.out.println("username   : " + username);
																																	
																																	int idchercheur=c.getIdchercheur(username);
																																			
																																	
																																	String titre_enc=request.getParameter("titre_enc");
																																	System.out.println("titre encadre :" + titre_enc);
																																	
																																	String initule=request.getParameter("titre_enc");
																																	System.out.println("initule :" + initule);
																																	
																																	String type_enc=request.getParameter("type_enc");
																																	System.out.println("type enc :" + type_enc);
																																	
																																	String date=request.getParameter("date");
																																	System.out.println("date :" + date);
																																	
																																	String description=request.getParameter("description");
																																	System.out.println("description :" + description);
																																	
																																	Part file=request.getPart("image");
																																	
																																	String imageFileName=file.getSubmittedFileName();  // get selected image file name
																																	System.out.println("Selected Image File Name : "+imageFileName);
																																	String uploadPath="C:\\Users\\acer\\eclipse-workspaceJEE\\Projets4\\WebContent\\imagess\\"+imageFileName;  // upload path where we have to upload our actual image
																																	
																																	System.out.println("Upload Path : "+uploadPath);
																																	
																																	// Uploading our selected image into the images folder
																																	
																																	try
																																	{
																																	
																																	FileOutputStream fos=new FileOutputStream(uploadPath);
																																	InputStream is=file.getInputStream();
																																	
																																	byte[] data=new byte[is.available()];
																																	is.read(data);
																																	fos.write(data);
																																	fos.close();
																																	
																																	}
																																	
																																	catch(Exception e)
																																	{
																																		e.printStackTrace();
																																	}
																																	
																																	activitesc actiPub3=new activitesc();
																																	actiPub3.setType_ac("encadrement");
																																	actiPub3.setTitre_enc(titre_enc);
																																	actiPub3.setInitule(initule);
																																	actiPub3.setType_enc(type_enc);
																																	actiPub3.setDate(date);
																																  	actiPub3.setDescription(description);
																																  	actiPub3.setIdchercheur(idchercheur);
																																  	actiPub3.setImage(imageFileName);
																																  	
																																  	

																																	 try {
																																			acti.addActivite(actiPub3);
																																			request.setAttribute("addequi", "ajouter encadrement  ");
																																			this.getServletContext().getRequestDispatcher("/encadrementenseignant.jsp").forward(request, response);
																																			
																																 
																																		}catch(Exception e) {
																																			response.sendRedirect("page_404.jsp");
																																			e.printStackTrace();	
																																			
																																		}
																																	 
																																	
																																}
										
																																else 
																																	if(request.getServletPath().equals("/updatepublicationenseignant")) {
																																		
																																		activitesc pub = new activitesc(); 

																																		
																																		 pub.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
																																		 
																																		 pub.setTitre(request.getParameter("titre"));
																																		 pub.setAuteur(request.getParameter("auteur"));
																																		// pub.setIdactivitesc(request.getParameter("idactivitesc"));
																																		 pub.setDate(request.getParameter("date"));
																																		 pub.setType_pub(request.getParameter("type_pub"));
																																		 pub.setDescription(request.getParameter("description"));
																																		 //pub.setTitre(request.getParameter("titre"));
																																		
																																	    //em.setPhoto(request.getParameter("photo"));
																																		System.out.println(pub);
																																	    acti.updatepublicationenseignant(pub);
																																	    
																																	    this.getServletContext().getRequestDispatcher("/Enseignantpage").forward(request, response);
																																	}
																																	else 
																																		if(request.getServletPath().equals("/editformpublicationenseignant")) {
																																			System.out.println(1222);
																																			  System.out.println("ranidkhlt publication dotorant");
																																					
																																				int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
																																				System.out.println("id activite  :" + idactivitesc);
																																			    activitesc nta=acti.getpublicationenseignant(idactivitesc);
																																				request.setAttribute("nta",nta );
																																				this.getServletContext().getRequestDispatcher("/modifyformpublicationenseignant.jsp").forward(request, response);
																																		}
																																		else 
																																			if(request.getServletPath().equals("/deletepublicationenseignant")) {
																																				  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																																					try {
																																						acti.deletepublicationenseignant(idactivitesc);
																																						response.sendRedirect("Enseignantpage");

																																					}catch(Exception e) {
																																						response.sendRedirect("page_404.jsp");
																																						e.printStackTrace();
																																						
																																					}
																																			}
																																			else 
																																				if(request.getServletPath().equals("/deletesoutenanceenseignant")) {
																																					  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																																						try {
																																							acti.deletesoutenanceenseignant(idactivitesc);
																																							response.sendRedirect("Enseignantpage");

																																						}catch(Exception e) {
																																							response.sendRedirect("page_404.jsp");
																																							e.printStackTrace();
																																							
																																						}
																																				}
																																				else 
																																					if(request.getServletPath().equals("/updatesoutenanceenseignant")) {
																																						activitesc sout = new activitesc(); 
													                                                                                                    sout.setIdactivitesc(Integer.parseInt(request.getParameter("idactivitesc")));
																																						 
													                                                                                                    sout.setType_sout(request.getParameter("type_sout"));
													                                                                                                    sout.setInitule(request.getParameter("initule"));
																																						// pub.setIdactivitesc(request.getParameter("idactivitesc"));
													                                                                                                    sout.setDate(request.getParameter("date"));
													                                                                                                    sout.setLieu(request.getParameter("lieu"));
													                                                                                                    sout.setJury(request.getParameter("jury"));
													                                                                                                    sout.setDescription(request.getParameter("description"));
																																						 //pub.setTitre(request.getParameter("titre"));
																																						
																																					    //em.setPhoto(request.getParameter("photo"));
																																						System.out.println(sout);
																																					    acti.updatesoutenancedoctorant(sout);
																																					    
																																					    this.getServletContext().getRequestDispatcher("/Enseignantpage").forward(request, response);
																																						
																																					}
																																					else 
																																						if(request.getServletPath().equals("/editformsoutenanceenseignant")) {
																																							System.out.println(1222);
																																							  System.out.println("ranidkhlt publication dotorant");
																																									
																																								int idactivitesc = Integer.parseInt(request.getParameter("idactivitesc"));
																																								System.out.println("id activite  :" + idactivitesc);
																																							    activitesc nta=acti.getsoutenancedoctorant(idactivitesc);
																																								request.setAttribute("nta",nta );
																																								this.getServletContext().getRequestDispatcher("/modifyformsoutenanceenseignant.jsp").forward(request, response);
																																						}
																																						else 
																																							if(request.getServletPath().equals("/updateequipe")) {
																																								
																																								Equipe equi = new Equipe(); 
																																								Laboratoire labo =new Laboratoire();
																																								axe_equipe ax=new axe_equipe();
																																								chercheur cher=new chercheur();
															                                                                                                    equi.setIdequipe(Integer.parseInt(request.getParameter("idequipe")));
																																								 
															                                                                                                    equi.setNom_equipe(request.getParameter("nom_equipe"));
															                                                                                                    equi.setChef_equipe(request.getParameter("chef_equipe"));
																																								// pub.setIdactivitesc(request.getParameter("idactivitesc"));
															                                                                                                    /*labo.setNom_Labo(request.getParameter("Labo"));
															                                                                                                    ax.setNom_axe_equipe(request.getParameter("nom_axe_equipe"));
															                                                                                                    cher.setNomcher(request.getParameter("enseignant"));
															                                                                                                    cher.setNomcher(request.getParameter("username"));
															                                                                                                    cher.setNomcher(request.getParameter("username1"));
															                                                                                                    cher.setNomcher(request.getParameter("username2"));*/
															                                                                                                    
															                                                                                                  
															                                                                                                   
																																								 //pub.setTitre(request.getParameter("titre"));
																																								
																																							    //em.setPhoto(request.getParameter("photo"));
																																								System.out.println(equi);
																																							    eqi.updateequipe(equi);
																																							    
																																							    this.getServletContext().getRequestDispatcher("/listeequipe").forward(request, response);
																																							}
																																							else 
																																								if(request.getServletPath().equals("/deleteequipe")) {
																																									  int idequipe=Integer.parseInt(request.getParameter("idequipe"));
																																										try {
																																											eqi.deleteequipe(idequipe);
																																											response.sendRedirect("listeequipe");
																																											System.out.println("salut tout le monde ");

																																										}catch(Exception e) {
																																											response.sendRedirect("page_404.jsp");
																																											e.printStackTrace();
																																											
																																										}
																																								}
																																								else 
																																									if(request.getServletPath().equals("/profilechercheuradmin")) {
																																										
																																										int idchercheur = Integer.parseInt(request.getParameter("idchercheur"));
																																										 chercheur nta=c.getchercheur(idchercheur);
																																											request.setAttribute("nta",nta );
																																											this.getServletContext().getRequestDispatcher("profilechercheuradmin.jsp").forward(request, response);
																																										 
																																									}else
																																										if(request.getServletPath().equals("/updateequipe")) {
																																											try {
																																											 
																																											  
																																											  
																																												Equipe equii = new Equipe(); 
																																												
																																												equii.setIdequipe(Integer.parseInt(request.getParameter("idequipe")));
																																												equii.setNom_equipe(request.getParameter("nom_equipe"));
																																												equii.setChef_equipe(request.getParameter("chef_Labo"));
																																												equii.setNomcher1(request.getParameter("nomcher1"));
																																												equii.setNomcher2(request.getParameter("nomcher2"));
																																												equii.setNomcher3(request.getParameter("nomcher3"));
																																												String nom_Labo=request.getParameter("nom_Labo");
																																												int id_Laboratoire=eqi.getIdLaboratoire(nom_Labo);
																																												equii.setId_Laboratoire(id_Laboratoire);
																																												String nom_axe_equipe=request.getParameter("nom_axe_equipe");
																																												int id_axe_equipe=eqi.getIdAxe_equipe(nom_axe_equipe);
																																												equii.setId_axe_equipe(id_axe_equipe);
																																												
																																											    
																																												List<axe_equipe> axe=eqi.axeequipe();
																																												request.setAttribute("axeequipe", axe);
																																												
																																												
																																											 List<chercheur> cher=eqi.Listchercheur();
																																											    request.setAttribute("chercheures", cher);
																																											   
																																											    
																																											 List<Laboratoire> lab =eqi. Listlaboratoire();
																																											 request.setAttribute("laboratoires", lab);
																																											
																																											    
																																											 List<chercheur> ens =eqi.ListEnseignant();
																																											 request.setAttribute("enseignant", ens);
																																											
																																											    eqi.updateequipe(equii);
																																											    System.out.println("saluttttttttttttt");
																																												
																																											    this.getServletContext().getRequestDispatcher("/listeequipe").forward(request, response);
																																											}catch(Exception e) {
																																												e.getMessage();
																																											}
																																										}
										                                                                                                                                            /********  modification d'equipe                **************/                                                                                    
																																										else 
																																											if(request.getServletPath().equals("/editequipe")) {
																																												try {
																																												  System.out.println(1222);
																																													System.out.println("ranidkhlt l modification equipe");
																																													
																																												int idequipe = Integer.parseInt(request.getParameter("idequipe"));
																																												System.out.println("idequipe  :" + idequipe);
																																												Equipe ana=eqi.getEquipe(idequipe);
																																												request.setAttribute("ana", ana);
																																												
																																												List<axe_equipe> axe=eqi.axeequipe();
																																												request.setAttribute("axeequipe", axe);
																																												
																																												
																																											 List<chercheur> cher=eqi.Listchercheur();
																																											    request.setAttribute("chercheures", cher);
																																											   
																																											    
																																											 List<Laboratoire> lab =eqi. Listlaboratoire();
																																											 request.setAttribute("laboratoires", lab);
																																											
																																											    
																																											 List<chercheur> ens =eqi.ListEnseignant();
																																											 request.setAttribute("enseignant", ens);
																																											 System.out.println("saluttttttttttttt");
																																												this.getServletContext().getRequestDispatcher("/modifyformequipe.jsp").forward(request, response);
																																											}catch(Exception e ) {
																																												e.getMessage();
																																											}
																																												
																																											}
																																											else 
																																												if(request.getServletPath().equals("/deletepublication")) {
																																													
																																													
																																													  int idactivitesc=Integer.parseInt(request.getParameter("idactivitesc"));
																																														try {
																																															acti.deletepublication(idactivitesc);
																																															response.sendRedirect("listepublication");

																																														}catch(Exception e) {
																																															
																																															e.printStackTrace();
																																															
																																														}
																																												}
																																												
						
																																												

																																									
																																						
																																							
										

																																
																																	
																														
																																
																								
										
										

	}
	
}
	   	


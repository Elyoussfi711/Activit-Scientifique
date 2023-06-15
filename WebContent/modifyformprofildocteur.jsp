<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>profile enseignant</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Google Web Fonts -->
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Heebo:wght@400;500;600;700&display=swap" rel="stylesheet">
    
    <!-- Icon Font Stylesheet -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib1/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="lib1/tempusdominus/css/tempusdominus-bootstrap-4.min.css" rel="stylesheet" />

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css1/bootstrap.min.css" rel="stylesheet">

    <!-- Template Stylesheet -->
    <link href="css1/style.css" rel="stylesheet">
</head>

<body>
<% response.setHeader("cache-Control","no-cache, no-store");
if(session.getAttribute("username")==null){ response.sendRedirect("LogIn.jsp");} %>
    <div class="container-xxl position-relative bg-white d-flex p-0">
        <!-- Spinner Start -->
        <div id="spinner" class="show bg-white position-fixed translate-middle w-100 vh-100 top-50 start-50 d-flex align-items-center justify-content-center">
            <div class="spinner-border text-primary" style="width: 3rem; height: 3rem;" role="status">
                <span class="sr-only">Loading...</span>
            </div>
        </div>
        <!-- Spinner End -->


        <!-- Sidebar Start -->
        <div class="sidebar pe-4 pb-3">
            <nav class="navbar bg-light navbar-light">
                <a href="#" class="navbar-brand mx-4 mb-3">
                    <h3 href="Docteurpage" class="text-primary"><i class="fa fa-hashtag me-2"></i>Bienvenue </h3>
                </a>
                <div class="d-flex align-items-center ms-4 mb-4">
                    <div class="position-relative">
                        <img class="rounded-circle" src="img1/user.jpg" alt="" style="width: 40px; height: 40px;">
                        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
                    <div class="ms-3" name="username">
                         <h6 ><strong>${sessionScope.username} ${sessionScope.prenom}</strong></h6>
                        <h6 name="profil">${sessionScope.profile}</h6>
                    </div>
                </div>
                <a href="profildocteur?username=${sessionScope.username}" class="nav-item nav-link" ></i>VOTRE INFORMATION</a>
                <div class="navbar-nav w-100">
                    <br></br>
                    <center><label style="padding-left=20px: ;"> gestion de publication </label></center>
                    <a href="publicationdocteur.jsp" class="nav-item nav-link" >Ajouter Votre Publication </a>
                    <a href="listepublicationdocteur?username=${sessionScope.username}" class="nav-item nav-link" >Liste  votre  Publication  </a>
                    
                     <center><label style="padding-left=20px: ;"> gestion de soutenance </label></center>
                    <a href="soutenancedocteur.jsp" class="nav-item nav-link">Ajouter votre Soutenance  </a>
                      <a href="listesoutenancedocteur?username=${sessionScope.username}" class="nav-item nav-link">Liste votre Soutenance  </a>
                    
                    <center><label style="padding-left=20px: ;"> gestion de conference </label></center>
                    <a href="conferencedocteur.jsp" class="nav-item nav-link">Ajouter votre Conference</a>
                    <a href="listeconferencedocteur?username=${sessionScope.username}" class="nav-item nav-link">Liste votre Conference</a>


                    
                </div>
            </nav>
        </div>
        <!-- Sidebar End -->
        <!-- Content Start -->
        <div class="content">
            <!-- Navbar Start -->
            <nav class="navbar navbar-expand bg-light navbar-light sticky-top px-4 py-0">
                <a href="index.html" class="navbar-brand d-flex d-lg-none me-4">
                    <h2 class="text-primary mb-0"><i class="fa fa-hashtag"></i></h2>
                </a>
                <a href="#" class="sidebar-toggler flex-shrink-0">
                    <i class="fa fa-bars"></i>
                </a>

                <div class="navbar-nav align-items-center ms-auto">
                    
               <ul class="nav navbar-nav mai-top-nav">
                      <li class="nav-item"><a href="Docteurpage" class="nav-link">Home</a>
                                                </li>
                                               
                                            </ul>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">
                            <img class="rounded-circle me-lg-2" src="img1/user.jpg" alt="" style="width: 40px; height: 40px;">
                               <span class="d-none d-lg-inline-flex">${sessionScope.username} ${sessionScope.prenom}</span>
                        </a>
                        <div class="dropdown-menu dropdown-menu-end bg-light border-0 rounded-0 rounded-bottom m-0">
                            <a href="profildocteur?username=${sessionScope.username}" class="dropdown-item">My Profile</a>
                            
                            <a href="logout" class="dropdown-item">Log Out</a>
                        </div>
                    </div>

                      


                </div>
            </nav>
             
             
            
             <div class="container-fluid">


    <div class="container emp-profile">
              <form action="<%=request.getContextPath()%>/updateprofildocteur?idchercheur=${ntoma.idchercheur}" method="Post">
                                                        <div class="row">
                                                           
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
                                                               </br>
                                                               </br>
                                                                <div class="form-group">
                                                                    <input name="username" type="text" class="form-control" value="${ntoma.username}"/>
                                                                </div>
                                                                </br>
                                                                
                                                                <div class="form-group">
                                                                    <input name="prenom" type="text" class="form-control" value="${ntoma.prenom}"/>
                                                                </div>
                                                                </br>
                                                                <div class="form-group">
                                                                    <input name="email" type="email" class="form-control" value="${ntoma.email}"/>
                                                                </div>
                                                                </br>
                                                                <div class="form-group">
                                                                    <input name="password" type="password" class="form-control" value="${ntoma.password}"/>
                                                                </div>
                                                                </br>
                                                                <div class="form-group">
                                                                    <input name="tel" type="Text" maxlength="10" class="form-control" value="${ntoma.tel}"/>
                                                                </div>
                                                                </br>
                                                              
                                                                
                                                                
                                                               
                                                                
                                                            </div>
                                                            </br>
                                                            <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">

                                                                </br>
                                                                <div class="form-group ">
                                                                     <label>Profil</label>
                                                                     <br><br>
                                                                         <select name="profil" class="form-group col-lg-8" value="${ntoma.profil}">
                                                                         <option value="doctorant">Doctorant</option>
                                                                         <option value="enseignant">Enseignant</option>
                                                                         <option value="docteur">Docteur</option>
                                                                         </select>
                                                                </div>
                                                                
                                                                 </br>
                                                                 </br>
                                                                
                                                            </div>
                                                        </div>
                                                        <div class="row">
                                                            <div class="col-lg-12">
                                                                <div class="payment-adress">
                                                                    <button type="submit" class="btn btn-success ">Confirmer</button>
                                                                </div>
                                                            </div>
                                                            
                                                            <div class="form-group">
                                                                         <input type="hidden" class="form-input" name="id_chercheur" id="name" value="${ntoma.idchercheur}"/>
                            
                                                                         <span toggle="" class="zmdi zmdi-eye field-icon toggle-password"></span>
                            
                                                                 </div>
                                                        </div>
                                                    </form>
    </div>
    </div>
 
    

    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
    <script src="lib1/chart/chart.min.js"></script>
    <script src="lib1/easing/easing.min.js"></script>
    <script src="lib1/waypoints/waypoints.min.js"></script>
    <script src="lib1/owlcarousel/owl.carousel.min.js"></script>
    <script src="lib1/tempusdominus/js1/moment.min.js"></script>
    <script src="lib1/tempusdominus/js1/moment-timezone.min.js"></script>
    <script src="lib1/tempusdominus/js1/tempusdominus-bootstrap-4.min.js"></script>

    <!-- Template Javascript -->
    <script src="js1/main.js"></script>
</body>

</html>
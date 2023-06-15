<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Docteur</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="" name="keywords">
    <meta content="" name="description">

    <!-- Favicon -->
    <link href="img1/favicon.ico" rel="icon">

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

<body style="padding: 0px;">
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
                    <h3 class="text-primary"><i class="fa fa-hashtag me-2"></i>Bienvenue </h3>
                </a>
                <div href="Doctorantpage" class="d-flex align-items-center ms-4 mb-4">
                    <div class="position-relative">
                        <img class="rounded-circle" src="img1/user.jpg" alt="" style="width: 40px; height: 40px;">
                        <div class="bg-success rounded-circle border border-2 border-white position-absolute end-0 bottom-0 p-1"></div>
                    </div>
                    <div class="ms-3" name="username">
                     
                        <h6 ><strong>${sessionScope.username} ${sessionScope.prenom}</strong></h6>
                        <h6 name="profil">${sessionScope.profile}</h6>
                    </div>
                </div>
                <a href="profildocteur?username=${sessionScope.username}" class="nav-item nav-link" ></i>VOTRE INFORMATION </a>
                <div class="navbar-nav w-100">
                    <br></br>
                   <center><label style="padding-left=20px: ;"> gestion de publication </label></center>
                    <a href="publicationdocteur.jsp" class="nav-item nav-link" >Ajouter Votre Publication </a>
                    <a href="listepublicationdocteur?username=${sessionScope.username}" class="nav-item nav-link" >Liste  votre publication  </a>
                    
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
                      <li class="nav-item"><a href="Doctorantpage" class="nav-link">Home</a>
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
            

              <div class="header">
  <h2>Publication des Chercheurs :</h2>
</div>
 <form action="<%=request.getContextPath()%>/Docteurpage" method="Post">
  <c:forEach items="${acti}" var="activites">
<div class="row">
  <div class="leftcolumn">
    <div class="card">
    <h2 >${activites.type_ac}</h2>
    <h5> Nom Chercheur : ${activites.a_bstract}</h5>
      <h4>${activites.type_pub} :</h4>
      <h5>${activites.titre}, ${activites.date}</h5>
    <div  ><img src="imagess/${activites.image}" height ="100" width="100" alt="Image"></div>
      <p>${activites.description}</p>
    </div>
    
  </div>
</div>
</c:forEach>
</form>
        <a href="#" class="btn btn-lg btn-primary btn-lg-square back-to-top"><i class="bi bi-arrow-up"></i></a>
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
    <style>
body {
  font-family: Arial;
  padding: 20px;
  background: #f1f1f1;
}

/* Header/Blog Title */
.header {
  padding: 30px;
  font-size: 40px;
  text-align: center;
  background: white;
}

/* Create two unequal columns that floats next to each other */
/* Left column */
.leftcolumn {
  float: left;
  width: 75%;
}

/* Right column */
.rightcolumn {
  float: left;
  width: 25%;
  padding-left: 20px;
}

/* Fake image */
.fakeimg {
  background-color: #aaa;
  width: 100%;
  padding: 20px;
}

/* Add a card effect for articles */
.card {
  background-color: white;
  padding: 20px;
  margin-top: 20px;
}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
}

/* Footer */
.footer {
  padding: 20px;
  text-align: center;
  background: #ddd;
  margin-top: 20px;
}

/* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other */
@media screen and (max-width: 800px) {
  .leftcolumn, .rightcolumn {
    width: 100%;
    padding: 0;
  }
}
</style>
</body>

</html>
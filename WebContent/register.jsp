<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up </title>

    <!-- Font Icon -->
    <link rel="stylesheet" href="fonts/material-icon/css/material-design-iconic-font.min.css">

    <!-- Main css -->
    <link rel="stylesheet" href="css/style.css">
</head>
<body>

    <div class="main">

        <section class="signup">
            <!-- <img src="images/signup-bg.jpg" alt=""> -->
            <div class="container">
                <div class="signup-content">
                    <form action="<%=request.getContextPath()%>/register" method="Post" enctype="multipart/form-data">
                        <h2 class="form-title"><h2>Create account</h2></h2>
                        <div class="form-group">
                         <p style="color:red;"><c:out value="${mdpInco}"/></p>
                            <input type="text" class="form-input" name="username" id="name" placeholder="Username"/>
                        </div>
                        <div class="form-group">
                            <input type="text" class="form-input" name="prenom" id="lastname" placeholder="LastName"/>
                        </div>
                        
                        <div class="form-group">
                              <input  class="form-input" type="text" minlength="10" maxlength="10" name="tel" placeholder="PhoneNumber                                                                                 (+212)" width=2 />
                              
                        </div>

                        <div class="form-group">
                            <input type="email" class="form-input" name="email" id="email" placeholder="Your Email"/>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-input" name="password" id="password" placeholder="Password"/>
                            <span toggle="#password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>
                        <div class="form-group">
                            <input type="password" class="form-input" name="repeatpassword" id="re_password" placeholder="Repeat your password"/>
                               <span toggle="#re_password" class="zmdi zmdi-eye field-icon toggle-password"></span>
                        </div>

                        <div class="form-group">
                           
                           <div class="value">
                                
                                    <div class="rs-select2 js-select-simple select--no-search">
                                        <select name="profil" class="form-input" placeholder="Profil">
                                            <option disabled="disabled" selected="selected" >Profil</option>
                                            <option>doctorant</option>
                                            <option>docteur</option>
                                            <option>enseignant</option>
                                        </select>
                                        <div class="pe-7s-angle-down"></div>
                                   
                                </div>
                           </div>
                        </div>

                        <div class="form-row">
                            <div class="name">Photo personnelle:</div>
                            <div class="value">
                                <div class="input-group">
                                    <input class="input--style-5"  type="file"  name="image">
                                </div>
                            </div>
                        </div>

                        
                        <div class="form-group"style="padding-top: 20px;" >
                            <input type="submit" name="submit" id="submit" class="form-submit" value="Sign up"/>
                        </div>
                    </form>
                    <p class="loginhere">
                        Have already an account ? <a href="LogIn.jsp" class="loginhere-link">Login here</a><br/>
                       returne to <a href="home.jsp" class="loginhere-link">home</a>
                    </p>
                </div>
            </div>
        </section>

    </div>

    <!-- JS -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="js/main.js"></script>
</body><!-- This templates was made by Colorlib (https://colorlib.com) -->
</html>
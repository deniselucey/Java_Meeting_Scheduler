<%-- 
    Document   : AccountActivation
    Created on : Mar 2, 2015, 7:04:59 PM
    Author     : zolamcdonald
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.Register"%>
<%@page import="teamproject.system.Email" %>

<jsp:useBean id = "register" class="teamproject.system.Register" scope="request"/>
<jsp:useBean id = "emailClass" class="teamproject.system.Email" scope="request"/>


<!DOCTYPE html>
<html lang = "en">
	<head>
	    <meta charset = "utf-8"/>
	    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	    <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
            <link rel="stylesheet" href="../styles/styles.css"/>
            <link rel="stylesheet" href="../styles/menu.css"/>
            <link rel="stylesheet" href="../styles/normalize.css"/>	
            <link rel="stylesheet" href="../styles/example.css">
            <link rel="stylesheet" href="../styles/font-awesome.min.css">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Register Account</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
        <div id="wrapper">
	<header>
            <img src="../Resources/logo.gif" alt="UCC Logo">
	</header>
            
        <nav>
	    <ul>
                <li><a href="../LogIn/Login.jsp">Login</a></li>
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
               <h1>You have activated your account</h1>
               
                   <fieldset>
                       
                        

                        <div>
                            <%--
                                String registeredResult="";
                                boolean emailResult;
                                boolean setConfirmed;
                                String emailSent="";
                                
                                if(register.registerDetailsWithDb() && emailClass.updateDb(register.getEmail())) {
                                    String email = request.getParameter("email");
                                    session.setAttribute("email", email );
                                    registeredResult= "You have registered. ";
                                  
                                   
                                   emailResult = emailClass.sendEmail("UCC TimeTable Registration","Thank you for signing up for UCC TimeTable",email);
                                   if(emailResult){
                                      emailSent = "A confirmation email has been sent to you. ";
                                   }else{
                                      emailSent ="The email confirmation wasn't sent due to an error. ";
                                   }
                                   
                            --%>   
                            <p>You have activated your account.</p>
                            <p><a href="../LogIn/Login.jsp">Login Now</a></p>
                            
                            <%--
                                }else {
                            --%>
                                  
                                  <p> A error has occurred </p>
                            <%--
                                }
                            --%>
                           
                            
                        </div>
     
                    </fieldset>
               
            </section>
        </div>
        <p class = "status"></p>
        
        <footer>
            <p>
            
            </p>
        </footer>
    </div>    
    </body>
</html>

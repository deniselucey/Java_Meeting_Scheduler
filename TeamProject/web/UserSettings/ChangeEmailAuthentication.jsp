<%-- 
    Document   : ChangeEmailAuthentication
    Created on : Mar 4, 2015, 8:15:57 PM
    Author     : zolamcdonald
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.Register"%>
<%@page import="teamproject.system.Email" %>

<jsp:useBean id = "updateSettings" class="teamproject.system.UpdateSettings" scope="request">
<jsp:setProperty name="updateSettings" property="*"/>
</jsp:useBean>
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
            <title>Change Settings</title>
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
               <h1>You have changed your Email</h1>
               <form name = "input" method="POST">
                   <fieldset>
                       
                       
                        <div>
                            <% 
                                
                                boolean emailResult;
                                String email = request.getParameter("email");
                                if(updateSettings.emailCheck(email)){
                                    int userid = (Integer)session.getAttribute("userId");
                                    if(updateSettings.changeEmail(userid) == 1){
                                        
                                        String emailText= " Your email address has been changed"; 
                                        emailResult = emailClass.sendEmail("UCC TimeTable Email Change",emailText,email);
                                           out.println("You have changed your email address"); 
                                    }
                                } else {
                             %>           
                            <%
                               
                            %>
                                  <p> A error has occurred </p>
                            <% 
                                }
                            %>
                            
                            
                           
                           
                        </div>
     
                    </fieldset>
               </form>
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



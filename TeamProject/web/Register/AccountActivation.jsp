<%-- 
    Document   : AccountActivation
    Created on : Mar 2, 2015, 7:04:59 PM
    Author     : zolamcdonald
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="teamproject.system.Email" %>


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
            <title>Account Activation</title>
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
                            
                            <% 
                                String pin = request.getParameter("code");
                                int userId = (Integer.parseInt(request.getParameter("u")));
                                boolean activationResult = emailClass.confirmAccount(pin,userId);
                                
                                if(activationResult){
                                     
                            %>
                            
                                    <p>You have activated your account.</p>
                                    <p><a href="../LogIn/Login.jsp">Login Now</a></p>
                            <%
                                }else{
                            %>
                                    <p> A error has occurred </p>
                            <%
                                }
                            %>
                            
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

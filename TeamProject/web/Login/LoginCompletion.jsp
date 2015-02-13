<%-- 
    Document   : LoginCompletion
    Created on : Feb 9, 2015, 11:14:15 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.Login"%>
<%@ page import ="java.sql.*" %>

<jsp:useBean id = "login" class="teamproject.system.Login" scope="session"/>
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
            
            <title>Login</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
        <div id="wrapper">
	<header>
	</header>
            
       
	
        <div id = "main">
	    <section class = "content">
                
                
                
               <% 
                   
                   String email = login.getEmail();
                   session.setAttribute("email",email );
                   response.sendRedirect("../TimeTable/TimeTable.jsp");
                  
               %>
              
          </section>
        </div>
        <p class = "status"></p>
    </div>
    <footer>
        <p>
          
        </p>
    </footer>
        
    </body>
</html>


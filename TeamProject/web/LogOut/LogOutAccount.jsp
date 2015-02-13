<%-- 
    Document   : Logout
    Created on : Feb 13, 2015, 5:47:50 PM
    Author     : zolamcdonald
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ page import ="java.sql.*" %>


<%@page import="teamproject.system.Logout"%>


<jsp:useBean id = "logout" class="teamproject.system.Logout" scope="request"/>

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
            
            <title>Log Out</title>
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
                session.setAttribute("email", null);
                session.invalidate();
                response.sendRedirect("../Login/Login.html");
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


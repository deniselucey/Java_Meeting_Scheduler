<%-- 
    Document   : SetMeeting
    Created on : Feb 24, 2015, 11:13:48 PM
    Author     : Ciaran Mc Donald
--%>



<%@ page import ="java.sql.*" %>


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
            
            <title>TimeTable</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
       <div id="wrapper">
	<header>
	</header>
            
            <%
                if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
                    response.sendRedirect("../Login/Login.jsp");
            %>
            <%
                } else {
            %>
           
                    <%@page contentType="text/html" pageEncoding="UTF-8"%>
                    <%@page import="teamproject.meeting.Meeting"%>
        
                    <jsp:useBean id="setMeeting" class="teamproject.meeting.Meeting" scope="session">
                    </jsp:useBean>
                
                
                
                    <nav>
                        <ul>
                            <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                            <li><a href="..\Enroll\enroll.html">Enroll/Un-enroll</a></li>
                        </ul>
                    </nav>
	
                    <div id = "main">
	    
                
                
               
                   
                
          
                    </div>
                <%
                   }
                %>  
        
         
            
       
        <p class = "status"></p>
    </div>
    <footer>
        <p>
          
        </p>
    </footer>
        
    </body>
</html>


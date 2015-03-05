<%-- 
    Document   : SemesterView
    Created on : Feb 26, 2015, 7:20:03 PM
    Author     : zolamcdonald
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
            <img src="../Resources/logo.gif" alt="UCC Logo">
	</header>
            
            <%
                if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
                    response.sendRedirect("../LogIn/Login.jsp");
            %>
            <%
                } else {
            %>
           
                    <%@page contentType="text/html" pageEncoding="UTF-8"%>
                    <%@page import="teamproject.system.scheduler.timetable.Timetable"%>
        
                    <%
                        teamproject.system.scheduler.timetable.Timetable timeTable = new teamproject.system.scheduler.timetable.Timetable(30,(Integer)session.getAttribute("userId"));
                    %>
                
                
                    <nav>
                        <ul>
                            <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                            <li><a href="..\Meeting\SetMeeting.jsp">Set Meeting</a></li>
                            <li><a href="..\Enroll\enroll.jsp">Enroll/Un-enroll in Module</a></li>
                            <li><a href="..\UserSettings\Settings.jsp">Settings</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                            
                        </ul>
                    </nav>
	
                    <div id = "main">
	    
                
                
                    
                
                    <% 
                        out.println(timeTable.toHTML(false));
                     %>
                
          
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


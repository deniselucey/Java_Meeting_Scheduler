<%-- 
    Document   : lecturerSemesterView
    Created on : Mar 3, 2015, 9:22:15 PM
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
                        teamproject.system.scheduler.timetable.Timetable timeTable = new teamproject.system.scheduler.timetable.Timetable(12,(Integer)session.getAttribute("userId"));
                    %>
                
                
                    <nav>
                        <ul>
                            <li><a href="..\lecturer\lecturerTimeTable.jsp">Home</a></li>
                            <li><a href="..\lecturer\lecturerSetMeeting.jsp">Set Meeting</a></li>
                            <li><a href="..\lecturer\lecturerEnrollunenroll.jsp">Enroll/Un-enroll in Module</a></li>
                            <li><a href="..\lecturer\lecturerSettings.jsp">Settings</a></li>
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

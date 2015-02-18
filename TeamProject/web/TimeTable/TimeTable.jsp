

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.scheduler.timetable.Timetable"%>
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
        
        <%
            if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
                response.sendRedirect("../Login/Login.jsp");
     
            }else{
        %>
            <jsp:useBean id = "timeTable" class="teamproject.system.scheduler.timetable.Timetable" scope="session"/>
       <%
        }
            
        
        %>  
        
         
      
        
        
        <div id="wrapper">
	<header>
	</header>
            
        <nav>
	    <ul>
                <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                <li><a href="..\Enroll\enroll.html">Enroll/Un-enroll</a></li>
                
            </ul>
	</nav>
	
        <div id = "main">
	    
                
                
                Welcome <%=session.getAttribute("email")%>
                
                <% 
                    
                    Timetable timetable = (Timetable)session.getAttribute("timeTable");
                    out.print(timetable.TabletoHTML());
                 %>
                
          
        </div>
        <p class = "status"></p>
    </div>
    <footer>
        <p>
          
        </p>
    </footer>
        
    </body>
</html>


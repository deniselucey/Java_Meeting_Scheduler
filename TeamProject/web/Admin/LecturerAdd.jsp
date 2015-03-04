<%-- 
    Document   : LecturerAdd
    Created on : Mar 4, 2015, 1:04:16 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*" %>


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
                
                    <nav>
                        <ul>
                            <li><a href="..\Admin\AdminHomePage.jsp">Home</a></li>
                            <li><a href="..\Admin\AdminSettings.jsp">Add Members</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                        </ul>
                    </nav>
	
                    <div id = "main">
	            	<section class = "content">
                       <h1>Add new Lecturer</h1>

                <%@page import="teamproject.user.privilege.LecturerPrivlege"%>


                <jsp:useBean id="LectuerAdd" class="teamproject.user.privilege.LecturerPrivlege" scope="session">
                </jsp:useBean>


                <% 
                   String email = request.getParameter("email");

                    if (LectuerAdd.add(email)) {
                        out.print("You have been successfully created a new lecturer: " + email);
                    } else {
                        out.print("Sorry you failed to add new lecturer: " + email);
                    }
                           
                %>
                <p>
                    <a href="..\Admin\AdminSettings.jsp">Go back.</a>
                </p>
            </section>
                
          
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


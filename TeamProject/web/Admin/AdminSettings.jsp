
<%@page import="teamproject.system.SystemSetting"%>
<%-- 
    Document   : AdminCreateModule
    Created on : Feb 28, 2015, 5:25:03 PM
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
                            <li><a href="..\Admin\EditModules.jsp">Edit Modules</a></li>
                            <li><a href="..\Admin\AdminSetLectures.jsp">Set Lectures</a>
                             <li><a href="..\Admin\BackUp.jsp"> System BackUp</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                        </ul>
                    </nav>
	
                    <div id = "main">
                        <section>
                            <h1>Add new administrator</h1>
                   <form name="input" action="AdminAdd.jsp" method="GET">            
                   <fieldset>
                       <div>
                           <label>Email</label>
                           <input type="text" name="email">
                       </div>
                       
                       
                       <div>
                           <input type="submit" value="Add">
                       </div>
		   </fieldset>			
                </form>     
                        </section>
                        
                        <section>
                            
                        <h1>Add new Lecturer</h1>
                   <form name="input" action="LecturerAdd.jsp" method="GET">            
                   <fieldset>
                       <div>
                           <label>Email</label>
                           <input type="text" name="email">
                       </div>
                       
                       <div>
                           <input type="submit" value="Add">
                       </div>
		   </fieldset>			
                </form>  
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


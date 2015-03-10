<%-- 
    Document   : SystemRestoreAuthentication
    Created on : Mar 5, 2015, 3:17:12 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*" %>
<%@page import="teamproject.system.BackUp"%>


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
                if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")
                        && !((session.getAttribute("admin") != null)  && (Boolean)(session.getAttribute("admin")) ) ) {
                    response.sendRedirect("../LogIn/Login.jsp");
            %>
            <%
                } else {
            %>
                <jsp:useBean id="restore" class="teamproject.system.BackUp" scope="request">
                <jsp:setProperty name="restore" property="*"/>
                </jsp:useBean>

                 
                    <nav>
                        <ul>
                            <li><a href="..\Admin\AdminHomePage.jsp">Home</a></li>
                            <li><a href="..\Admin\EditModules.jsp">Edit Modules</a></li>
                            <li><a href="..\Admin\AdminSetLectures.jsp">Set Lectures</a>
                            <li><a href="..\Admin\AdminSettings.jsp">Add Members</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                           
                        </ul>
                    </nav>
	
                    <div id = "main">
                        
                <section>
                        <h1>System Restore</h1>
                        <form name="input" action="SystemBackUpAuthentication.jsp" method="POST">            
                        <fieldset>
                                
                            <div>
                                <%
//                                    String dbPassword = (String)request.getAttribute("dbPassword");
//                                    String dbUserName = (String)request.getAttribute("dbName");
//                                    String mysql = (String)request.getAttribute("mysql");
//                                    String source = (String)request.getAttribute("source");
//                                    
                                    
                                    
                                    if(restore.restore()){
                                       out.println("Your System has been Restored"); 
                                    }else{
                                        out.println("There was Error Restoring the System");
                                    }
                                  %>
                               
                            </div>
                            
                            <div>
                                
                                <%
//                                    String path = (String)request.getAttribute("path");
                                    restore.restorePreferences();
                                    if(true){
                                       out.println("Your System Preferences has been Restored"); 
                                    }else{
                                        out.println("There was Error Restoring the System Preferences");
                                    }
                                %>
                                    
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


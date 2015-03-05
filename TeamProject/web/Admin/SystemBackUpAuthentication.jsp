<%-- 
    Document   : SystemBackUpAuthentication
    Created on : Mar 5, 2015, 3:16:45 PM
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
                if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
                    response.sendRedirect("../LogIn/Login.jsp");
            %>
            <%
                } else {
            %>
                    <jsp:useBean id="backUp" class="teamproject.system.BackUp" scope="request">
                    <jsp:setProperty name="backUp" property="*"/>
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
                        <h1>System Back Up</h1>
                                  
                        <fieldset>
                                
                            
                            <div>
                                <% 
                                    String mySQLDump= (String)request.getAttribute("mySQLDump");
                                    String dbUserName =(String)request.getAttribute("dbUserName");
                                    String dbPassword = (String)request.getAttribute("dbPassword");
                                    String dbName = (String)request.getAttribute("dbName");
                                    String path =(String)request.getAttribute("path");
                                    String backupResult = (String)request.getAttribute("");
                                    
                                    backupResult = backUp.createBackup(mySQLDump,dbUserName,dbPassword,dbName,path);
                                    if(!backupResult.equals("")){
                                       out.println("Your System has been Backed Up"); 
                                    }else{
                                        out.println("There was Error backing up the System");
                                    }
                                 %>
                                    <p>Your System has been Backed Up</p>
                            </div>
                            
                            <div>
                                <% 
                                    String perferenceBackUpResult ="";
                                    String perferencePath = (String)request.getAttribute("path");
                                    
                                     backUp.backUpPreferences(perferencePath);
                                    if(!perferenceBackUpResult.equals("")){
                                       out.println("Your System Preferences have been Backed Up"); 
                                    }else{
                                        out.println("There was Error backing up the System Preferences");
                                    }
                                
                                %>
                                    
                            </div>   
                            
                            
                        </fieldset>
                        
                           
                       
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


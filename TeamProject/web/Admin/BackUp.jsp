<%-- 
    Document   : BackUp
    Created on : Feb 28, 2015, 9:48:44 PM
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
                            <li><a href="..\Admin\AdminSettings.jsp">Add Members</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                           
                        </ul>
                    </nav>
	
                    <div id = "main">
                        
                <section>
                        <h1>Create System Back Up</h1>
                        <form name="input" action="SystemBackUpAuthentication.jsp" method="POST">            
                        <fieldset>
                                
                            
                                <div>
                                   <label>Database UserName</label>
                                   <input type="text" name="dbUserName">
                               </div>
                            
                            <div>
                                   <label>Database Password</label>
                                   <input type="password" name="dbPassword">
                               </div>
                            
                            
                            <div>
                                   <label>Database Name</label>
                                   <input type="text" name="dbName">
                               </div>
                            <div>
                                   <label>mySQL Dump</label>
                                   <input type="text" name="mySQLDump">
                               </div>
                            
                            
                            <div>
                                   <label>Back Up Path</label>
                                   <input type="text" name="path">
                               </div>
                               
                                <div>
                                   
                                   <input type="submit" name="Create BackUp">
                               </div>
                        </fieldset>
                        </form>
                           
                       
                </section>
                        
                    <section>
                        <h1>Restore System From Back Up</h1>
                        <form name="input" action="SystemRestoreAuthentication.jsp" method="POST">            
                        <fieldset>
                                
                            
                                <div>
                                   <label>Database UserName</label>
                                   <input type="text" name="dbUserName">
                               </div>
                            
                            <div>
                                   <label>Database Password</label>
                                   <input type="password" name="dbPassword">
                               </div>
                            
                            
                            <div>
                                   <label>mySql</label>
                                   <input type="text" name="mysql">
                               </div>
                            
                            
                            <div>
                                   <label>Source</label>
                                   <input type="text" name="source">
                               </div>
                               
                                <div>
                                   
                                   <input type="submit" name="Restore Backup">
                               </div>
                    </fieldset>
                        </form>
                        
                      </section>
                          
                        
                        
                        <section>
                            <h1>Create System Preferences Back up</h1>
                            <form name="input" action="SystemBackUpAuthentication.jsp" method="POST">            
                        <fieldset>
                             <div>
                                   <label>Destination Path</label>
                                   <input type="text" name="path">
                               </div>
                               
                                <div>
                                   
                                   <input type="submit" name="Create BackUp">
                               </div>
                        </fieldset>
                        </form>
                           
                            
                        </section>
                        
                        <section>
                        <h1>Restore System Preferences From Back Up</h1>
                        
                         <form name="input" action="SystemRestoreAuthentication.jsp" method="POST">            
                        <fieldset>
                             <div>
                                   <label>Destination Path</label>
                                   <input type="text" name="path">
                               </div>
                               
                                <div>
                                   
                                   <input type="submit" name="Restore BackUp">
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


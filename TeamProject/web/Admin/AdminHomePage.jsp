<%-- 
    Document   : AdminHomePage
    Created on : Feb 28, 2015, 5:24:21 PM
    Author     : zolamcdonald
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*" %>
<%@page import ="teamproject.system.SystemSetting" %>

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
            <script src="SaveSettings.js"></script>
            <title>Admin Home</title>
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
                            <li><a href="..\Admin\BackUp.jsp">System BackUp</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                        </ul>
                    </nav>
	
                    <div id = "main">
                       <section class = "content">
                        <h1>System Settings</h1>
                       <fieldset>
                        <div>
                <%
                    
                    String key = (String)request.getParameter("key");
                    String value = (String)request.getParameter("value");
                    
                    if(key != null && !key.equals("") && value != null && !value.equals(""))
                    {
                        SystemSetting.initSystemSetting();
                        SystemSetting.setProperty(key, value);
                        SystemSetting.saveSettings();
                        
                     
                    }
                
                %>
                        </div>
                   <div>
                <%
                    
                    
                   }

                    out.print(SystemSetting.toHtmlFormStatic());

                 %>
                    </div>
                       </fieldset>
               </section>
                    </div>
                
        <p class = "status"></p>
    </div>
    <div>
        
    </div>
    <footer>
        <p>
          
        </p>
    </footer>
        
    </body>
</html>


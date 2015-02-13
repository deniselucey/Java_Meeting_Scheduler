<%-- 
    Document   : LoginRetry
    Created on : Feb 9, 2015, 11:10:16 PM
    Author     : zolamcdonald
--%>



<%@page import="teamproject.system.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="login" class="teamproject.system.Login" scope="request"/>



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
           
            <title>Login</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
        <div id="wrapper">
	<header>
	</header>
            
        <nav>
	    <ul>
                <li><a href="Login.html">Login</a></li>
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
               <h1>Login</h1>
               <form name="input" action="LoginAuthentication.jsp" method="POST">            
                   <fieldset>
                        <div>
                            <p><label>Email:</label>
                            <input type = "text" name = "email" value = "<%=login.getEmail()%>"><br>
                            <%= login.getErrorMessage("email")%></p>
                        </div>		
		               
                       <div>					
                           <p><label>Password:</label>
                            <input type = "password" name = "password1" value = "<%= login.getPassword()%>"><br>
                        <%= login.getErrorMessage("password1")%></p>
                       </div>					
			           
                       <div>
                           <input type="submit" value="Log In">
                       </div>
			      
                       
		   </fieldset>			
                </form>
            </section>
        </div>
        <p class = "status"></p>
    </div>
    <footer>
        <p>
          
        </p>
    </footer>
        
    </body>
</html>

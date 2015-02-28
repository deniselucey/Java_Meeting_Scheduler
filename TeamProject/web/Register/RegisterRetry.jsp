<%-- 
    Document   : RegisterRetry
    Created on : Feb 8, 2015, 11:56:32 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.Register"%>
<jsp:useBean id="register" class="teamproject.system.Register" scope="request"/>


    
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
            <img src="../Resources/logo.gif" alt="UCC Logo">
	</header>
            
        <nav>
	    <ul>
                <li><a href="..\LogIn\Login.jsp">Log In</a></li>
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
          
        <form name = "input" action="RegisterAccountAuthentication.jsp" method="POST">
            <fieldset>
                <div>
                <p><label>First Name:</label>
                <input type = "text" name = "firstName" value = "<%= register.getFirstName()%>"><br>
                <%= register.getErrorMessage("firstName")%></p>
                </div>
                
                <div>
                <p><label>Last Name:</label>
                <input type = "text" name = "lastName" value = "<%= register.getLastName()%>"><br>
                <%= register.getErrorMessage("lastName")%></p>
                </div>
                
                <div>
                <p><label>Student Number:</label>
                <input type = "text" name = "studentNumber" value = "<%= register.getStudentNumber()%>"><br>
                <%= register.getErrorMessage("studentNumber")%></p>
                </div>
                
                <div>
                <p><label>Email:</label>
                <input type = "text" name = "email" value = "<%=register.getEmail()%>"><br>
                <%= register.getErrorMessage("email")%>
                <%= register.getErrorMessage("emailUnique")%></p>
                </div>
                
                <div>
                <p><label>Password:</label>
                <input type = "password" name = "password1" value = "<%= register.getPassword1()%>"><br>
                <%= register.getErrorMessage("password1")%></p>
                </div>
                
                <div>
                <p><label>Confirm Password:</label>
                <input type = "password" name = "password2" value = "<%= register.getPassword2()%>"><br>
                <%= register.getErrorMessage("password2")%></p>
                </div>           
                <input type = "submit" value = "Create Account">
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
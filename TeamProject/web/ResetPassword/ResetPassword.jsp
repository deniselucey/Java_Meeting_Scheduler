<%-- 
    Document   : ResetPassword.jsp
    Created on : Feb 7, 2015, 2:26:08 PM
    Author     : zolamcdonald
--%>

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
            <title>Reset Password</title>
    </head>
    
    <body>
        <%
            if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
        %>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
        <div id="wrapper">
	<header>
            <img src="../Resources/logo.gif" alt="UCC Logo">
	</header>
            
        <nav>
	    <ul>
                <li></li>
                
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
                <p>A email has been sent to your inbox to reset your password.</p>
            </section>
        </div>
            
       
        <p class = "status"></p>
    </div>
    <footer>
        <p>
            
        </p>
    </footer>
         <%}else{%>
            <% response.sendRedirect("../TimeTable/TimeTable.jsp"); %>
         <%}%>
    </body>
</html>


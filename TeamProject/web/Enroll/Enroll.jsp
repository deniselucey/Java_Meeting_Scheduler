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
            <title>Enroll</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
        <div id="wrapper">
	<header>
	</header>
            
        <nav>
	    <ul>
                <li><a href="#">Home</a></li>
                <li> <a href="..\Enroll\enroll.html">Enroll/Un-enroll</a></li>
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
               <h1>Enroll</h1>

                <%@page import="teamproject.college.Module"%>
                <%@page import="teamproject.user.people.Student"%>
                <%@page contentType="text/html" pageEncoding="UTF-8"%>

                <%
                    if ((session.getAttribute("userid") == null) || (session.getAttribute("userid") == "")) {
                %>
                You are not logged in<br/>
                <a href="..\Login\Login.html">Please Login</a>
                
                <%} else {
                %>

                <jsp:useBean id="enroll" class="teamproject.user.people.Student" scope="session">
                </jsp:useBean>


                <% 
                    out.print(session.getAttribute("email"));
                    String email = (String) session.getAttribute("email");
                    out.print(enroll.enrollToModule(request.getParameter("module"), email));       
                %>
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

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
        
        <%
            if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
        %>
        You are not logged in<br/>
        <a href="..\LogIn\Login.jsp">Please Login</a>
        <%
            } else {
        %>
                
                   <nav>
                        <ul>
                            <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                            <li><a href="..\Meeting\SetMeeting.jsp">Set Meeting</a></li>
                            <li><a href="..\Enroll\enroll.jsp">Enroll/Un-enroll</a></li>
                            <li><a href="..\TimeTable\SemesterView.jsp">Semester View</a></li>
                            <li><a href="..\UserSettings\Settings.jsp">Settings</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                        </ul>
                    </nav>
	
        <div id = "main">
	    <section class = "content">
               <h1>Enroll</h1>

                <%@page import="teamproject.college.Module"%>
                <%@page import="teamproject.user.people.Student"%>
                <%@page import ="teamproject.system.Email" %>
                <%@page contentType="text/html" pageEncoding="UTF-8"%>


                <jsp:useBean id="enroll" class="teamproject.user.people.Student" scope="session">
                </jsp:useBean>
                
                <jsp:useBean id="enrollEmail" class="teamproject.system.Email" scope="session">
                </jsp:useBean>


                <% 
                    String email = (String) session.getAttribute("email");
                    String emailSubject = "UCC TimeTable Module Enrolled";
                    String emailText = "You have enrolled in "+ request.getParameter("Modules");
                    
                    if (enroll.enrollToModule(request.getParameter("Modules"), email)) {
                        
                        out.print("You have been successfully enrolled to " + request.getParameter("Modules"));
                        enrollEmail.sendEmail(emailSubject, emailText, email);
                        
                    } else {
                        out.print("Sorry module " + request.getParameter("Modules") + " is unavailable.");
                    }
                           
                %>
                <p>
                    <a href="..\Enroll\enroll.jsp">Go back.</a>
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

<%-- 
    Document   : adminConfirmLectures
    Created on : Mar 5, 2015, 12:18:34 AM
    Author     : zolamcdonald
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.Register"%>
<%@page import="teamproject.system.Email" %>

<jsp:useBean id = "register" class="teamproject.system.Register" scope="request"/>
<jsp:useBean id = "emailClass" class="teamproject.system.Email" scope="request"/>


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
            <title>Confirm Meeting</title>
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
                <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                           <li><a href="..\Admin\AdminHomePage.jsp">Home</a></li>
                        <li><a href="..\Admin\EditModules.jsp">Edit Modules</a></li>
                        <li><a href="..\Admin\AdminSettings.jsp">Add Members</a></li>
                        <li><a href="..\Admin\BackUp.jsp">System BackUp</a></li>
                        <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
               
               
                        <div>
                           
                            <% 
                                   
                                String dateString = request.getParameter("dates");
                                teamproject.meeting.Lecture lecture = (teamproject.meeting.Lecture)session.getAttribute("meeting");
                                
                                //out.print("<p>" + meeting + "</p>");
                                boolean success = teamproject.meeting.Lecture.insertMeeting(lecture,dateString);
                                out.print("<p>" + success + "</p>");
                                
                                out.println(lecture.toHTML());
                               
                                 //out.println(registeredResult+ emailSent);
                            %>  
                            
                        </div>
     
            </section>
        </div>
        <p class = "status"></p>
        
        <footer>
            <p>
            
            </p>
        </footer>
    </div>    
    </body>
</html>

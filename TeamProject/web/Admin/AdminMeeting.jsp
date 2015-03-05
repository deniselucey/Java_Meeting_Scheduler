<%-- 
    Document   : AdminMeeting
    Created on : Mar 5, 2015, 12:29:51 AM
    Author     : zolamcdonald
--%>

<%@ page import ="java.sql.*" %>
<%@page import="teamproject.meeting.Meeting"%>
<%@page import="java.util.ArrayList"%>


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
            
            <title>Set Meeting</title>
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
                
                   <jsp:useBean id="setMeeting" class="teamproject.meeting.Meeting" scope="session"></jsp:useBean>
                 
                
                
                
                    <nav>
                        <ul>
                            <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                            <li><a href="..\Enroll\enroll.jsp">Enroll/Un-enroll in Module</a></li>
                            <li><a href="..\TimeTable\SemesterView.jsp">Semester View</a></li>
                            <li><a href="..\UserSettings\Settings.jsp">Settings</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                        </ul>
                    </nav>
	
                <%
                    String meetingString = (String)request.getParameter("meeting");
                   
                    if( meetingString != null)
                    {
                        String[] meetingids = meetingString.split(",");
                        for(String m : meetingids)
                        {
//                            try
//                            {
                                int id = Integer.parseInt(m);
                                Meeting meeting1 = new Meeting(id);
                                out.println(meeting1.toHTML());
//                            }
//                            catch(Exception e)
//                            {
//
//                            }
                        }
                    
                    }
                    
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



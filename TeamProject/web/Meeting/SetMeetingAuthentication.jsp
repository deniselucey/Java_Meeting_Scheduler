<%-- 
    Document   : SetMeetingAuthentication
    Created on : Feb 26, 2015, 12:06:55 PM
    Author     : zolamcdonald
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

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
                    response.sendRedirect("../Login/Login.jsp");
            %>
            <%
                } else {
                    
            %>
                <%@page import="java.time.LocalDate"%>
                <%@page import="teamproject.meeting.Meeting"%>
                <%@page import="teamproject.system.scheduler.Scheduler"%>
                 <nav>
                        <ul>
                            <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                            <li><a href="..\Enroll\enroll.html">Enroll/Un-enroll</a></li>
                            <li><a href="..\TimeTable\SemesterView.jsp">Semester View</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                        </ul>
                    </nav>
            
                <div id = "main">
                        
                        


                    <%   
                        teamproject.meeting.Meeting meeting = new teamproject.meeting.Meeting(
                        request.getParameter("title"),
                        request.getParameter("description"),
                        (int)session.getAttribute("userId"),
                        request.getParameter("addMembersToMeeting"),
                        request.getParameter("groupId_attendees"),      
                        Integer.parseInt(request.getParameter("duration")),      
                        request.getParameter("endDate"),      
                        request.getParameter("location"),
                        Integer.parseInt(request.getParameter("Recurrence")),
                        1,     
                        1);
                        //TODO add privates and piority

                        teamproject.system.scheduler.Scheduler scheduler = new teamproject.system.scheduler.Scheduler(meeting,request.getParameter("startDate"),request.getParameter("endDate"));
                        //Meeting meetingToSchedule, LocalDate startOfRange, LocalDate endOfRange
                        out.println(scheduler.toHTML());

                    %>

                        
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


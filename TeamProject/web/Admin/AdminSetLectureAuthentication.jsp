<%-- 
    Document   : AdminSetMeetingAuthentication
    Created on : Mar 5, 2015, 12:21:56 AM
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
            <script src="SchedulerClickEvent.js"></script>
            <title>Set Meeting</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        <p id="TEST"></p>
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
                <%@page import="java.time.LocalDate"%>
                <%@page import="teamproject.meeting.Meeting"%>
                <%@page import="teamproject.system.scheduler.Scheduler"%>
                <%@page import="teamproject.user.privilege.AdminPrivlege" %>
                <jsp:useBean id="adminPrivlege" class="teamproject.user.privilege.AdminPrivlege" scope="session">
                </jsp:useBean>
                 <nav>
                        <ul>
                            <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                            <li><a href="..\Enroll\enroll.jsp">Enroll/Un-enroll in Module</a></li>
                            <li><a href="..\TimeTable\SemesterView.jsp">Semester View</a></li>
                             <li><a href="..\UserSettings\Settings.jsp">Settings</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                        </ul>
                    </nav>
            
                <div id = "main">
                        
                        


                    <%  
                        String peopleAttending =  request.getParameter("addMembersToMeeting");
//                        if(peopleAttending.equals("") || (request.getParameter("UserAttending") != null && ((String)request.getParameter("UserAttending")).equals("1") ))// .equals("1"))
//                        {
//                                if(!peopleAttending.equals(""))
//                                {
//                                    peopleAttending += ",";
//                                }
//                                peopleAttending += session.getAttribute("userId");
//                        }
                        System.out.println(" People attending " + peopleAttending);
                        String moduleCode = request.getParameter("code");
                        adminPrivlege.addLecturesToModules(moduleCode);
                        int duration = Integer.parseInt(request.getParameter("duration")) ;
                        duration += duration%15==0?0:15-(duration%15);
                        
                        teamproject.meeting.Lecture lecture = new teamproject.meeting.Lecture(
                         request.getParameter("title"),
                        request.getParameter("description"),
                        (Integer)session.getAttribute("userId"),  
                        duration,      
                        request.getParameter("endDate"),      
                        request.getParameter("location"),
                        Integer.parseInt(request.getParameter("Recurrence")),
                        Integer.parseInt(request.getParameter("Piority")),     
                        1,moduleCode);
                        //TODO add privates and piority
                        session.setAttribute("meeting", lecture);
                        teamproject.system.scheduler.Scheduler scheduler = new teamproject.system.scheduler.Scheduler(lecture,request.getParameter("startDate"),request.getParameter("endDate"));
                        //Meeting meetingToSchedule, LocalDate startOfRange, LocalDate endOfRange
                        out.println(scheduler.toHTML());
//(String title, String description, String location, Recurrence repeat, Priority piority, MeetingPrivacy privacy)
        
                    %>
                    
                        
                </div>
                
            
                <%
                }
                %>  
                <div>
                    <input type="submit" value="Confirm Meeting" onclick="Commit()">
                </div>
                
        <p class = "status"></p>
    </div>
                
    <footer>
        <p>
          
        </p>
    </footer>
        
    </body>
</html>

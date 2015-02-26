<%-- 
    Document   : SetMeetingAuthentication
    Created on : Feb 26, 2015, 12:06:55 PM
    Author     : zolamcdonald
--%>

<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.meeting.Meeting"%>
<%@page import="teamproject.system.scheduler.Scheduler"%>
        
<%   
    teamproject.meeting.Meeting meeting = new teamproject.meeting.Meeting(
    request.getParameter("title"),
    request.getParameter("description"),
    (int)session.getAttribute("userId"),
    request.getParameter("addMembersToMeeting"),
    request.getParameter("groupId_attendees"),      
    Integer.parseInt(request.getParameter("duration")),      
    request.getParameter("endDate"),      
    "",
    Integer.parseInt(request.getParameter("Recurrence")),
    1,     
    1);
    //TODO add privates and piority
    
    teamproject.system.scheduler.Scheduler scheduler = new teamproject.system.scheduler.Scheduler(meeting,request.getParameter("startDate"),request.getParameter("endDate"));
    //Meeting meetingToSchedule, LocalDate startOfRange, LocalDate endOfRange
    out.println(scheduler.toHTML());

%>


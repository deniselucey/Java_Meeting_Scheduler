<%-- 
    Document   : SetMeetingAuthentication
    Created on : Feb 26, 2015, 12:06:55 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.meeting.Meeting"%>
<%@page import="teamproject.system.scheduler.Scheduler"%>
        
<%   
    teamproject.meeting.Meeting meeting = new teamproject.meeting.Meeting(
    request.getParameter("title"),
    request.getParameter("description"),
            session.getAttribute("user_id"),
    request.getParameter("peopleId_attendees"),
    request.getParameter("groupId_attendees"),      
    Integer.parseInt(request.getParameter("lenght")),      
    request.getParameter("runs_until"),      
    request.getParameter("location"),
    Integer.parseInt(request.getParameter("repeatEvery")),
    Integer.parseInt(request.getParameter("piority")),      
    Integer.parseInt(request.getParameter("privacyId")));
    
    //Meeting(String title, String description, int hostUserID, String peopleId_attendees,
    //String groupId_attendees, int length, String runs_until, String location, int repeatEvery, int piority, int privacyId) 
%>

<%--<jsp:useBean id="setMeeting" class="teamproject.meeting.Meeting" scope="session">
    
    <%--<jsp:setProperty name="setMeeting" property="title"/>
<jsp:setProperty name="setMeeting" property="description"/>
<jsp:setProperty name="setMeeting" property="Recurrence"/>
</jsp:useBean>
            --%> 
            <%--        
<jsp:useBean id="setMeetingScheduler" class="teamproject.system.scheduler.Scheduler" scope="session">
<jsp:setProperty name="setMeetingScheuler" property=""/>
<jsp:setProperty name="setMeetingScheuler" property=""/>--%>
            <%--</jsp:useBean>--%>
<%-- 
    Document   : SetMeetingAuthentication
    Created on : Feb 26, 2015, 12:06:55 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.meeting.Meeting"%>
<%@page import="teamproject.system.scheduler.Scheduler"%>
        
<jsp:useBean id="setMeeting" class="teamproject.meeting.Meeting" scope="session">
<jsp:setProperty name="setMeeting" property="title"/>
<jsp:setProperty name="setMeeting" property="description"/>
<jsp:setProperty name="setMeeting" property="Recurrence"/>
</jsp:useBean>
                    
<jsp:useBean id="setMeetingScheduler" class="teamproject.system.scheduler.Scheduler" scope="session">
<jsp:setProperty name="setMeetingScheuler" property=""/>
<jsp:setProperty name="setMeetingScheuler" property=""/>
</jsp:useBean>
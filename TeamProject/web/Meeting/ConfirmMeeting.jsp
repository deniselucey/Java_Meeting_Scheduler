<%-- 
    Document   : ConfirmMeeting
    Created on : 28-Feb-2015, 15:07:05
    Author     : drgex_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <p>
            
            <%
                String dateString = request.getParameter("dates");
                teamproject.meeting.Meeting meeting = (teamproject.meeting.Meeting)session.getAttribute("meeting");
                out.print("<p>" + meeting + "</p>");
                boolean success = teamproject.meeting.Meeting.insertMeeting(meeting,dateString);
                out.print("<p>" + success + "</p>");
            %>
        </p>
    </body>
</html>

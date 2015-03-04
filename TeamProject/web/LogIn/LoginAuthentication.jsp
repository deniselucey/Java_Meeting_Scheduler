<%-- 
    Document   : 
    Author     : zolamcdonald
--%>
<%@page import="teamproject.system.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="login" class="teamproject.system.Login" scope="request">
<jsp:setProperty name="login" property="*"/>
</jsp:useBean>


<% 
   boolean checkDb = login.checkDb();
   boolean validateForm = login.validateForm();
   if ( validateForm && checkDb) {
                   int userId = login.getUserId();
                   String email = request.getParameter("email");
                   boolean admin = login.getIsAdmin();
                   int lectureId = login.getIsLecturer();
                   session.setAttribute("lectureId", lectureId);
                  
                   session.setAttribute("lecturer", lectureId != 0 );
                   session.setAttribute("admin", admin);
                   session.setAttribute("userId", userId);
                   session.setAttribute("email", email );
%>
    <jsp:forward page="LoginCompletion.jsp"/>
    
<%
   }else if (validateForm && !checkDb) {
%>
    <jsp:forward page="Login.jsp"/>
<%
   } else{ 
%>
    <jsp:forward page="LoginRetry.jsp"/>

<% } %>
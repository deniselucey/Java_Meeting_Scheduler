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
   if (login.validateForm() && login.checkDb()) {
                   int userId = login.getUserId();
                   String email = request.getParameter("email");
                   boolean admin = login.getIsAdmin();
                   session.setAttribute("admin", admin);
                   session.setAttribute("userId", userId);
                   session.setAttribute("email", email );
%>
    <jsp:forward page="LoginCompletion.jsp"/>
    
<%
   }else if (login.validateForm() && !login.checkDb()) {
%>
    <jsp:forward page="Login.jsp"/>
<%
   } else{ 
%>
    <jsp:forward page="LoginRetry.jsp"/>

<% } %>
   
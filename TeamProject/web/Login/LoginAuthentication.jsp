
<%@page import="teamproject.system.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="login" class="teamproject.system.Login" scope="request">
<jsp:setProperty name="login" property="*"/>
</jsp:useBean>


<% 
   if (login.validateForm() && login.checkDb()) {
%>
    <jsp:forward page="LoginCompletion.jsp"/>
<%
   }else if (login.validateForm() && !login.checkDb()) {
%>
    <jsp:forward page="Login.html"/>
<%
   } else{ 
%>
    <jsp:forward page="LoginRetry.jsp"/>

<% } %>
   

<%@page import="teamproject.system.Login"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="login" class="teamproject.system.Login" scope="request">
<jsp:setProperty name="login" property="*"/>
</jsp:useBean>


<% 
   if (login.validateForm()) {
%>
    <jsp:forward page="LoginCompletion.jsp"/>
<%
   }  else {
%>
    <jsp:forward page="LoginRetry.jsp"/>
<%
   }
%>
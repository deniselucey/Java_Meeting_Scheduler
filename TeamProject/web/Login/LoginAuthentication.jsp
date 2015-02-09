<%@page import="teamproject.system.Register"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="login" class="teamproject.system.Login" scope="request">
<jsp:setProperty name="login" property="*"/>
</jsp:useBean>


<% 
   if (login.validateForm()) {
%>
    <jsp:forward page="RegisterCompletion.jsp"/>
<%
   }  else {
%>
    <jsp:forward page="RegisterRetry.jsp"/>
<%
   }
%>
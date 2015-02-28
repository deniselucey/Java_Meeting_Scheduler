
<%-- 
    Document   : RegisterAccountAuthentication.jsp
    Created on : Feb 5, 2015, 6:22:52 PM
    Author     : zolamcdonald
--%>

<%@page import="teamproject.system.Register"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="register" class="teamproject.system.Register" scope="request">
<jsp:setProperty name="register" property="*"/>
</jsp:useBean>

<% 
   if(register.validateForm()) {
%>
    <jsp:forward page="RegisterCompletion.jsp"/>
<%
   }else {
%>
    <jsp:forward page="RegisterRetry.jsp"/>
<%
   }
%>
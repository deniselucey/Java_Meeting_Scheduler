<%-- 
    Document   : ResetPasswordAuthentication
    Created on : Feb 27, 2015, 9:10:34 PM
    Author     : zolamcdonald
--%>

<%@page import="teamproject.system.Register"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:useBean id="resetPassword" class="teamproject.system.ResetPassword" scope="request">
<jsp:setProperty name="resetPassword" property="*"/>
</jsp:useBean>

<% //needs to be fixed
   if(resetPassword != null ) {
%>
    <jsp:forward page="ResetPasswordCompletion.jsp"/>
<%
   }else {
%>
    <jsp:forward page="NewPassword.jsp"/>
<%
   }
%>
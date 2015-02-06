<%-- 
    Document   : loginAuthentication
    Created on : Feb 5, 2015, 5:55:55 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.io.IOException" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Authentication</title>
    </head>
    <body>
        <jsp:useBean id ="login" class = "teamproject.system.Register"  scope = "session"/>
        
        <jsp:setProperty name = "register" property="*"/>
            
    </body>
</html>

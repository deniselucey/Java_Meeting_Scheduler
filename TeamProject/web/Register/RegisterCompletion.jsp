<%-- 
    Document   : RegisterCompletion
    Created on : Feb 8, 2015, 11:57:13 PM
    Author     : zolamcdonald
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.Register"%>

<jsp:useBean id = "register" class="teamproject.system.Register" scope="request"/>
<html>
    <body>
        
        <label>First Name:</label>
        <jsp:getProperty name = "register" property = "firstName"/>
        <br>
        
        <label>Last Name:</label>
        <jsp:getProperty name = "register" property = "lastName"/>
        <br>
                       
        <label>Student Number:</label>
        <jsp:getProperty name = "register" property = "studentNumber"/>
         <br>
                        
        <label>Email:</label>
        <jsp:getProperty name = "register" property = "email"/>
        <br>
     
    </body>
</html>


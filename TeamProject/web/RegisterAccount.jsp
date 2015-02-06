<%-- 
    Document   : RegisterAccount.jsp
    Created on : Feb 5, 2015, 6:22:52 PM
    Author     : zolamcdonald
--%>

<%@page import="teamproject.system.Register"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Account</title>
    </head>
   
    <body>
        
        <jsp:useBean id ="register" class = "teamproject.system.Register"  scope = "session"/>
        
        <jsp:setProperty name = "register" property="*"/>
        
        <p>
            First Name:  <%= register.getFirstName() %><br>
            Last Name:  <%= register.getLastName() %><br>
            email:  <%= register.getEmail() %><br>
            The password are the same: <%= register.passwordCheck() %><br>
        </p>
                       
                        
                    
        
    </body>

</html>
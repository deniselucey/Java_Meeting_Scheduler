<%-- 
    Document   : RegisterRetry
    Created on : Feb 8, 2015, 11:56:32 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.Register"%>
<jsp:useBean id="register" class="teamproject.system.Register" scope="request"/>

<html> 
    <body>      
        <form name = "input" action="RegisterAccountAuthentication.jsp" method="POST">
            <fieldset>
                <p><label>First Name:</label>
                <input type = "text" name = "firstName" value = "<%= register.getFirstName()%>"><br>
                <%= register.getErrorMessage("firstName")%></p>
                                
                <p><label>Last Name:</label>
                <input type = "text" name = "lastName" value = "<%= register.getLastName()%>"><br>
                <%= register.getErrorMessage("lastName")%></p>
                                    
                <p><label>Student Number:</label>
                <input type = "text" name = "studentNumber" value = "<%= register.getStudentNumber()%>"><br>
                <%= register.getErrorMessage("studentNumber")%></p>
                                
                <p><label>Email:</label>
                <input type = "text" name = "email" value = "<%=register.getEmail()%>"><br>
                <%= register.getErrorMessage("email")%></p>
                              
                <p><label>Password:</label>
                <input type = "password" name = "password1" value = "<%= register.getPassword1()%>"><br>
                <%= register.getErrorMessage("password1")%></p>
                                
                <p><label>Confirm Password:</label>
                <input type = "password" name = "password2" value = "<%= register.getPassword2()%>"><br>
                <%= register.getErrorMessage("password2")%></p>
                            
                <input type = "submit" value = "Create Account">
            </fieldset>
        </form>
    </body>
</html>

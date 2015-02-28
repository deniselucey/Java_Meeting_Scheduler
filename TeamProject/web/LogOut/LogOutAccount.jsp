<%-- 
    Document   : Logout
    Created on : Feb 13, 2015, 5:47:50 PM
    Author     : zolamcdonald
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import ="java.sql.*" %>
<%@page import="teamproject.system.Logout"%>


<jsp:useBean id = "logout" class="teamproject.system.Logout" scope="request"/>

        <%
            if((session.getAttribute("email") != null) || (session.getAttribute("email") != "")) {
        %>
                <%
                session.setAttribute("email", null);
                session.invalidate();
                response.sendRedirect("../LogIn/Login.jsp");
                %> 
               
        <%
            }else {
        %>
                <%response.sendRedirect("../TimeTable/TimeTable.jsp"); %>
        <%
            }
        %>
     
   


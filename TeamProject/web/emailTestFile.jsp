<%-- 
    Document   : email
    Created on : Mar 1, 2015, 10:58:37 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="teamproject.system.Email" %>

<jsp:useBean id = "emailClass" class="teamproject.system.Email" scope="request"/>



<%Boolean emailResult = emailClass.sendEmail("UCC TimeTable Registration","Thank you for signing up for UCC TimeTable","zolamcdonald@gmail.com,112428688@umail.ucc.ie,112428768@umail.ucc.ie");
        out.println(emailResult);
      out.println("You have confirmed your account " +emailClass.confirmAccount("770eaa0ee57a")); 

%>
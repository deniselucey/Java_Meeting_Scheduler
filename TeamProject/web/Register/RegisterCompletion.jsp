<%-- 
    Document   : RegisterCompletion
    Created on : Feb 8, 2015, 11:57:13 PM
    Author     : zolamcdonald
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="teamproject.system.Register"%>
<%@ page import="java.io.*,java.util.*,javax.mail.*"%>
<%@ page import="javax.mail.internet.*,javax.activation.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>

<jsp:useBean id = "register" class="teamproject.system.Register" scope="request"/>


<!DOCTYPE html>
<html lang = "en">
	<head>
	    <meta charset = "utf-8"/>
	    <meta http-equiv='Content-Type' content='text/html; charset=utf-8' />
	    <meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1' />
            <link rel="stylesheet" href="../styles/styles.css"/>
            <link rel="stylesheet" href="../styles/menu.css"/>
            <link rel="stylesheet" href="../styles/normalize.css"/>	
            <link rel="stylesheet" href="../styles/example.css">
            <link rel="stylesheet" href="../styles/font-awesome.min.css">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Register Account</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
        <div id="wrapper">
	<header>
            <img src="../Resources/logo.gif" alt="UCC Logo">
	</header>
            
        <nav>
	    <ul>
                <li><a href="../TimeTable/TimeTable.jsp">TimeTable</a></li>
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
               <h1>You have Registered</h1>
               <form name = "input" action="RegisterAccountAuthentication.jsp" method="POST">
                   <fieldset>
                       
                        <div>
                            <label>First Name:</label>
                            <jsp:getProperty name = "register" property = "firstName"/>
                        </div>
                               
                        <div>   
                            <label>Last Name:</label>
                            <jsp:getProperty name = "register" property = "lastName"/>
                        </div>
         
        
                        <div>
                            <label>Student Number:</label>
                            <jsp:getProperty name = "register" property = "studentNumber"/>
                        </div>
         
                        <div>
                            <label>Email:</label>
                            <jsp:getProperty name = "register" property = "email"/>
                        </div>
        

                        <div>
                            <% 
                                String registeredResult="";
                                String emailResult = "";
                                if(register.registerDetailsWithDb()) {
                                    String email = request.getParameter("email");
                                    session.setAttribute("email", email );
                                    
                                    registeredResult= "You have registered. ";
                                    
                                        // Sender's email ID needs to be mentioned
                                        String sendingEmail = "cs3305GroupProject@gmail.com";

                                        // Sets the host
                                        String host = "smtp.gmail.com";
                                        String port = "mail.smtp.port";

                                        // Gets the system properties object
                                        Properties properties = System.getProperties();

                                        // Setups mail server
                                        properties.setProperty("mail.smtp.host", host);
                                        properties.setProperty("mail.smtp.port", "587");
                                        properties.setProperty("mail.smtp.starttls.enable", "true");
                                        properties.setProperty("mail.smtp.auth", "true");

                                        // Gets the default Session object.
                                        Session mailSession = Session.getDefaultInstance(properties);
                                        try{
                                          
                                            // Creates a default MimeMessage object.
                                            MimeMessage message = new MimeMessage(mailSession);
                                            // Set From: header field of the header.
                                            message.setFrom(new InternetAddress(sendingEmail));
                                            // Set To: header field of the header.
                                            message.addRecipient(Message.RecipientType.TO,
                                                                     new InternetAddress(email));
                                            // Sets the email Subject: header field
                                            message.setSubject("UCC TimeTable Registration");
                                            // Sets the body of the email.
                                            message.setText("Thank you for signing up for UCC TimeTable");
                                            // Sends the email.
                                            Transport.send(message);
                                            //Sets the emailResult to confirm the email has been sent.
                                            emailResult = "A confirmation email has been sent to you. ";
                                        }catch (MessagingException mex) {
                                           mex.printStackTrace();
                                           emailResult = "The email confirmation wasn't sent due to a error.";
                                        }

                   
                            %>   
                            <%
                                }else {
                            %>
                                  <p> A error has occurred </p>
                            <% 
                                }
                            %>
                            <% 
                                 out.println(registeredResult+ emailResult);
                            %>  
                            
                        </div>
     
                    </fieldset>
               </form>
            </section>
        </div>
        <p class = "status"></p>
        
        <footer>
            <p>
            
            </p>
        </footer>
    </div>    
    </body>
</html>



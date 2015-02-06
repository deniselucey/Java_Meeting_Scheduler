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
    <jsp:useBean id ="register" class = "Register"  scope = "session"/>
    <% Register register = new Register(); %>
    <body>
        <div id="wrapper">
	        <header>
                    <h1></h1>
	        </header>
                
                <div id = "main">
	            <section class = "content">
                        
                        <p>First name: <%= register.getFirstName() %></p>
                        
                        <form name = "input"  method="POST">
                        <input type="hidden" name="pageType" value="register"/>
                            <fieldset>
                                <div>
                                    <label>First Name:</label>
                                    <input type="text" name="firstName" value="" />
                                </div>
                                
                                <div>
                                    <label>Last Name:</label>
                                    <input type="text" name="lastName" value="" />
                                </div>
                                
                                
                                   <div>
                                    <label>Email:</label>
                                    <input type="text" name="email" value=" " />
                                </div>
                                
                                
                                <div>
                                    <label>Password:</label>
                                    <input type="password" name="password" value="" />
                                </div>
                                
                                <div>
                                    <label>Confirm Password:</label>
                                    <input type="password" name="ConfirmPassword" value="" />
                                </div>
                                
                                <div>
                                <input type="submit" value="Create Account" />
                                </div>
                             
                            </fieldset>
                        </form>
                        
                    </section>
                </div> 
                 <p class = "status"></p>
	</div>
        <footer>
	       
	</footer>
        
        
    </body>


<%-- 
    Document   : register.jsp
    Created on : Feb 5, 2015, 5:32:53 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register </title>
    </head>
    <body>
        <div id="wrapper">
	        <header>
                    <h1></h1>
	        </header>
	        
                <div id = "main">
	            <section class = "content">
                        
                        <form name = "input" action="register" method="GET">
                            <fieldset>
                                <div>
                                    <label>First Name:</label>
                                    <input type="text" name="firstName" value="" />
                                </div>
                                
                                <div>
                                    <label>Second Name:</label>
                                    <input type="text" name="secondName" value="" />
                                </div>
                                
                                <div>
                                    <label>Student Number:</label>
                                    <input type="text" name="studentNumber" value="" />
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
</html>

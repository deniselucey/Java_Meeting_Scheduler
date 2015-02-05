<%-- 
    Document   : login.jsp
    Created on : Feb 4, 2015, 10:20:02 PM
    Author     : zolamcdonald
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
   
	<body>
            
           
		
            <div id="wrapper">
	        <header>
                    <h1></h1>
	        </header>
	        
	     
	        
	        <div id = "main">
	            <section class = "content">
		       
                        <form name="input" action="#" method="GET">
                            <fieldset>
                                <div>
                                    <label>Email:</label>
                                    <input type="text" name="email">
                                </div>		
		                
                                <div>					
                                    <label>Password: </label>
                                    <input type="password" name="password">
                                </div>					
			             
                                <div>
                                    <input type="submit" value="Submit">
                                </div>
			      
                                <a href="registerform.jsp">Register here</a><br/>
                                <a href="#">Forgotten password?</a>
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

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
            <a id="skiplink" href="#main">Skip to main content</a>
            <a id="user" href="#"></a>
		
            <div id="wrapper">
	        <header>
	        </header>
	        
	        <nav>
                    <ul>
                        <li><a href="#">Login</a></li>
                    </ul>
                </nav>
	        
	        <div id = "main">
	            <section class = "content">
		        <h1>Login:</h1>
                        
                        <form name="input" action="#" method="get">
                            <fieldset>
                                <div>
                                    <label>Email: </label>
                                    <input type="text" name="email">
                                </div>		
		                
                                <div>					
                                    <label>Password: </label>
                                    <input type="password" name="password">
                                </div>					
			             
                                <div>
                                    <input type="submit" value="Submit">
                                </div>
			      
                                <a href="register">Register here</a><br/>
                                <a href="#">Forgotten password?</a>
			    </fieldset>			
                        </form>
                    </section>
                </div> 
                 <p class = "status"></p>
	    </div>
	    <footer>
	        <p>
                   <a href="#">Home</a>  | <a href="#">Edit timetable</a> | <a href="#">View timetable</a> | <a href="#">Settings</a>
                </p>
	    </footer>
	</body>
</html>

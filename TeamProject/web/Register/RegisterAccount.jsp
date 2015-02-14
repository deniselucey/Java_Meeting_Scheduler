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
	</header>
            
        <nav>
	    <ul>
                <li><a href="..\Login\Login.jsp">Login</a></li>
                <li><a href="RegisterAccount.jsp">Register</a></li>
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
               <h1>Register</h1>
               <form name = "input" action="RegisterAccountAuthentication.jsp" method="POST">
                   <fieldset>
                       <div>
                       <label>First Name:</label>
                       <input type="text" name="firstName" value="" /><br>
                       </div>
                       
                       <div>       
                       <label>Last Name:</label>
                       <input type="text" name="lastName" value="" /><br>
                       </div>
                       
                                                    
                        <div>       
                       <label>Student Number:</label>
                       <input type="text" name="studentNumber" value="" /><br>
                       </div>
                       
                                               <div>       

                       <label>Email:</label>
                       <input type="text" name="email" value="" /><br>
               </div>
                       
                                               <div>       

                       <label>Password:</label>
                       <input type="password" name="password1" value="" /><br>
                       </div>
                       
                                               <div>       

                       <label>Confirm Password:</label>
                       <input type="password" name="password2" value="" /><br>
                        </div>      
                      <input type="submit" value="Create Account"><br>
                      <a href="..\Login\Login.jsp">Return to Login</a>
                   </fieldset>
               </form>
            </section>
        </div>
        <p class = "status"></p>
    </div>
    <footer>
        <p>
            
        </p>
    </footer>
        
    </body>
</html>

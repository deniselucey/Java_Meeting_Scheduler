<%-- 
    Document   : Login
    Author     : zolamcdonald
--%>

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
            <title>Login</title>
    </head>
    
    <body>
        <%
            
    //        if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
            if((session.getAttribute("email") == null) || ((String)session.getAttribute("email") == "") || (session.getAttribute("user_id") == null)|| ((int)session.getAttribute("user_id") == 0)  ) {
    
        %>
        
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
        <div id="wrapper">
	<header>
            <img src="../Resources/logo.gif" alt="UCC Logo">
	</header>
            
        <nav>
	    <ul>
                <li></li>
                <li></li>
            </ul>
	</nav>
	
        <div id = "main">
	    <section class = "content">
               <h1>Login</h1>
               <form name="input" action="LoginAuthentication.jsp" method="POST">            
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
                           <input type="submit" value="Log In">
                       </div>
			      
                       <a href="..\Register\RegisterAccount.jsp">Register here</a><br/>
                       <a href="..\ResetPassword\ResetPassword.html">Forgotten password?</a>
		   </fieldset>			
                </form>
            </section>
        </div>
        
         <%}else{%>
            <% response.sendRedirect("../TimeTable/TimeTable.jsp"); %>
         <%}%>

         
        <p class = "status"></p>
    </div>
    <footer>
        <p>
          
        </p>
    </footer>
        
    </body>
</html>


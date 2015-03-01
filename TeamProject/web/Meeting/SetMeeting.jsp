<%-- 
    Document   : SetMeeting
    Created on : Feb 24, 2015, 11:13:48 PM
    Author     : zolamcdonald
--%>



<%@ page import ="java.sql.*" %>
<%@page import="teamproject.meeting.Meeting"%>



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
            
            <title>Set Meeting</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
       <div id="wrapper">
	<header>
            <img src="../Resources/logo.gif" alt="UCC Logo">
	</header>
            
            <%
                if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
                    response.sendRedirect("../LogIn/Login.jsp");
            %>
            <%
                } else {
            %>
                
                   <jsp:useBean id="setMeeting" class="teamproject.meeting.Meeting" scope="session"></jsp:useBean>
                 
                
                
                
                    <nav>
                        <ul>
                            <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                            <li><a href="..\Enroll\EnrollModule.jsp">Enroll in Module</a></li>
                            <li><a href="..\Enroll\Unenroll.jsp">Unenroll in Module</a></li>
                            <li><a href="..\TimeTable\SemesterView.jsp">Semester View</a></li>
                             <li><a href="..\UserSettings\Settings.jsp">Settings</a></li>
                            <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                        </ul>
                    </nav>
	
                    <div id = "main">
                        
                        <section class = "content">
                        <h1>Set Meeting</h1>
                        <form name="input" action="SetMeetingAuthentication.jsp" method="POST">            
                            <fieldset>
                                
                                <div>
                                    <label>Meeting Title:</label>
                                    <input type="text" name="title" value=""/><br>
                                </div>
                                
                                <div>
                                    <label>Meeting description:</label>
                                    <input type="text" name="description" value="" size="35" />
                                </div>
                                
                                <div>
                                    <label>Start Date:</label>
                                    <input type="date" name ="startDate" /> 
                                    
                                </div>
                                
                                <div>
                                    <label>End Date:</label>
                                    <input type="date" name="endDate"/> 
                                    
                                </div>
                        
                                <div>
                                    <label>Duration in Minutes:</label>
                                    <input type="number" name="duration" value="" />
                                    
                                </div>
                                
                        
                        
                                <div>
                                    <label>Meeting Reoccur's:</label>
                                    <%
                                        out.print(setMeeting.RecurrenceHTMLDropDown());
                                    %>
                                </div>
                                
                                <div>
                                    <label>Location:</label>
                                    <input type="text" name="location" value=""/><br>
                                </div>
                                
                                <div>
                                    <label>Add Members: </label>
                                    <input type="text" name="addMembersToMeeting" value="" /><br>
                                    <input type="submit" value="Add Another Member" /><br>
                                </div>
                                
                                
                                    

                                <div>
                                    <input type="submit" value="Search Meeting Times" /><br>
                                </div>
                            </fieldset>			
                        </form>
                        </section>
                    </div>
                
               
                   
                
          
                   
                <%
                }
                %>  
        
         
            
       
        <p class = "status"></p>
    </div>
    <footer>
        <p>
          
        </p>
    </footer>
        
    </body>
</html>


<%-- 
    Document   : AdminSetLectures
    Created on : Mar 4, 2015, 12:39:06 PM
    Author     : zolamcdonald
--%>

<%@page import="teamproject.sql.SqlHandler"%>
<%@page import="teamproject.system.SystemSetting"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import ="java.sql.*" %>

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
            
            <title>TimeTable</title>
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
                        <li><a href="..\Admin\AdminHomePage.jsp">Home</a></li>
                        <li><a href="..\Admin\EditModules.jsp">Edit Modules</a></li>
                        <li><a href="..\Admin\AdminSettings.jsp">Settings</a></li>
                        <li><a href="..\Admin\BackUp.jsp">System BackUp</a></li>
                        <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
                    </ul>
            </nav>
	
                    <div id = "main">
                        
                        <section>
                            <h1>Set Lectures</h1>
               <form name="input" action="AdminCreateModule.jsp" method="GET">            
                   <fieldset>
                       <div>
                           <label>Module</label>
                           <select name="code">
                           
                        <%
                        try{
                           String moduleCode = "";
                           SystemSetting.initSystemSetting();
                           SqlHandler handler1 = new SqlHandler();
                           String sql1 = "SELECT code FROM Module";
                           ResultSet queryResult1 = handler1.runQuery(sql1);
                           
                           queryResult1.first();
                           
                           while (queryResult1.next() ) {
                               moduleCode = queryResult1.getString("code");
                               out.print("<option value=\"");
                               out.print(moduleCode);
                               out.print("\">");
                               out.print(moduleCode);
                           }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }      
                        %>
                           </select>
                       </div>
                       
                      
                       
                       
                       <div>
                            <label>Lecture Title:</label>
                            <input type="text" name="title" value=""/><br>
                        </div>
                                
                                <div>
                                    <label>Lecture description:</label>
                                    <input type="text" name="description" value="" size="34" />
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
                                    <label>Lecture Reoccur's:</label>
                                    <%
                                        out.print(setMeeting.RecurrenceHTMLDropDown());
                                    %>
                                </div>
                                
                                <div>
                                    <label>Location:</label>
                                    <input type="text" name="location" value=""/><br>
                                </div>
                                
                                <div>
                                    <input type="submit" value="Search Lecture Slots" /><br>
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



<%@page import="java.sql.ResultSet"%>
<%@page import="teamproject.system.SystemSetting"%>
<%@page import="teamproject.sql.SqlHandler"%>
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
            <title>Enroll</title>
    </head>
    
    <body>
        <a id="skiplink" href="#main">Skip to main content</a>
	<a id="user" href="#"></a>
        
        <div id="wrapper">
	<header>
	</header>
        <%
            if((session.getAttribute("email") == null) || (session.getAttribute("email") == "")) {
        %>
        You are not logged in<br/>
        <a href="..\Login\Login.jsp">Please Login</a>
        <%
            } else {
        %>   
        <nav>
            <ul>
                <li><a href="..\TimeTable\TimeTable.jsp">Home</a></li>
                <li><a href="..\Meeting\SetMeeting.jsp">Set Meeting</a></li>
                <li><a href="..\Enroll\enroll.jsp">Enroll/Un-enroll</a></li>
                <li><a href="..\TimeTable\SemesterView.jsp">Semester View</a></li>
                <li><a href="..\UserSettings\Settings.jsp">Settings</a></li>
                <li><a href="..\LogOut\LogOutAccount.jsp">Sign Out</a></li>
            </ul>
        </nav>
	
        <div id = "main">
	    <section class = "content">
               <h1>Enroll</h1>
               <form name="input" action="EnrollModule.jsp" method="GET">            
                   <fieldset>
                       <div>
                           <label>Module</label>
                           <select name="Modules">
                           
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
                           <input type="submit" value="Enroll">
                       </div>
		   </fieldset>			
                </form>
               
               <h1>Unenroll</h1>
               <form name="input" action="Unenroll.jsp" method="GET">            
                   <fieldset>
                       <div>
                           <label>Module</label>
                           <select name="Modules">
                           
                        <%
                        try{
                           String moduleCode = "";
                           String email = "";
                           email = (String) session.getAttribute("email");
                           SystemSetting.initSystemSetting();
                           SqlHandler handler2 = new SqlHandler();
                           String sql2 = "SELECT DISTINCT M.code "
                                   + "FROM Module AS M JOIN User AS U JOIN User_has_Module AS H "
                                   + "WHERE U.email = \" "
                                   + email
                                   + "\" AND M.module_id = H.module_id;";
                           ResultSet queryResult2 = handler2.runQuery(sql2);
                           
                           queryResult2.first();
                           
                           while (queryResult2.next() ) {
                               moduleCode = queryResult2.getString("code");
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
                           <input type="submit" value="Unenroll">
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

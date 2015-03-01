package teamproject.user.people;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.college.Module;
import teamproject.sql.SqlHandler;
import teamproject.system.Bugzilla;
import teamproject.system.SystemSetting;

public class Student extends Person {

	private ArrayList<Module> modules;
	private String studentNo;
        private String moduleNo;
        private SqlHandler sqlHandler;
        private boolean enrolled = false;
        private boolean unenrolled = false;

	/**
	 * 
	 * @param module
	 */
	public boolean enrollToModule(String module, String email)
	{
            try{
                SystemSetting.initSystemSetting();
                String query1 = "SELECT module_id FROM Module WHERE code = '" + module + "';";                              
                sqlHandler = new SqlHandler();
                ResultSet queryResult1 = sqlHandler.runQuery(query1);
                
                String query2 = "SELECT user_id FROM User WHERE email = '" + email + "';";                              
                sqlHandler = new SqlHandler();
                ResultSet queryResult2 = sqlHandler.runQuery(query2);
                
                queryResult1.last();
                queryResult2.last();
                
                moduleNo = queryResult1.getString("module_id");
                studentNo = queryResult2.getString("user_id");
                
                String query3 = "INSERT INTO User_has_Module(user_id, module_id)"  
                              + "VALUES('" + studentNo + "','" + moduleNo +"');";
                sqlHandler.runStatement(query3);
                
                enrolled = true;
                
        }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            Bugzilla.reportBug("Issue with enrolling student to module");
        }
         return enrolled;
	}

	/**
	 * 
	 * @param module
	 */
	public boolean unEnrollToModule(String module, String email)
	{
            try{
                SystemSetting.initSystemSetting();
                String query1 = "SELECT module_id FROM Module WHERE code = '" + module + "';";                              
                sqlHandler = new SqlHandler();
                ResultSet queryResult1 = sqlHandler.runQuery(query1);
                
                String query2 = "SELECT user_id FROM User WHERE email = '" + email + "';";                              
                sqlHandler = new SqlHandler();
                ResultSet queryResult2 = sqlHandler.runQuery(query2);
                
                queryResult1.last();
                queryResult2.last();
                
                moduleNo = queryResult1.getString("module_id");
                studentNo = queryResult2.getString("user_id");
                
                String query3 = "DELETE FROM User_has_Module WHERE user_id = '" + studentNo + "' AND module_id = '"
                               + moduleNo + "';";
                //unenrolled = true;
                sqlHandler.runStatement(query3);
            
                unenrolled = true;
                
        }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
            return unenrolled;
	}
        
        public String getStudentNo() {
            return studentNo;
        }
        
        public void setStudentNo(String studentNo) {
            this.studentNo = studentNo;
        }
}
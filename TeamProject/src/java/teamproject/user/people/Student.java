package teamproject.user.people;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.college.Module;
import teamproject.sql.SqlHandler;
import teamproject.system.SystemSetting;

public class Student extends Person {

	private ArrayList<Module> modules;
	private int studentNo;
        private String moduleNo;
        private SqlHandler sqlHandler;
        private boolean enrolled = false;
        private boolean unenrolled = false;

	/**
	 * 
	 * @param module
	 */
	public boolean enrollToModule(String module)
	{
            try{
                SystemSetting.initSystemSetting();
                String query1 = "SELECT module_id FROM Module WHERE code = '" + module + "';";               
                ResultSet queryResult;                
                sqlHandler = new SqlHandler();
                queryResult = sqlHandler.runQuery(query1);
                
                String query2 = "INSERT INTO User_has_Module(user_id, module_id)"  
                              + "VALUES('" + studentNo + "','" + moduleNo +"');";
                sqlHandler.runStatement(query2);
            
                enrolled = true;
                
        }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
         return enrolled;
	}

	/**
	 * 
	 * @param module
	 */
	public boolean unEnrollToModule(String module)
	{
            try{
                SystemSetting.initSystemSetting();
                String query1 = "SELECT module_id FROM Module WHERE code = '" + module + "';";               
                ResultSet queryResult;                
                sqlHandler = new SqlHandler();
                queryResult = sqlHandler.runQuery(query1);
                
                String query2 = "DELETE FROM User_has_Module"  
                              + "WHERE user_id = '" + studentNo + "' AND module_id = '"
                               + moduleNo +"');";
                sqlHandler.runStatement(query2);
            
                unenrolled = true;
                
        }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
            return unenrolled;
	}
        
        public int getStudentNo() {
            return studentNo;
        }
        
        public void setStudentNo(int studentNo) {
            this.studentNo = studentNo;
        }
}
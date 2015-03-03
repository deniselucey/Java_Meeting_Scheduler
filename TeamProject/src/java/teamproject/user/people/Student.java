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
	private int user_id;
        private int moduleNo;
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
                
                ResultSet queryResult2 = sqlHandler.runQuery(query2);
                
                queryResult1.last();
                queryResult2.last();
                
                moduleNo = queryResult1.getInt("module_id");
                user_id = queryResult2.getInt("user_id");
                
                String query3 = "INSERT INTO User_has_Module(user_id, module_id)"  
                              + "VALUES('" + user_id + "','" + moduleNo +"');";
                sqlHandler.runStatement(query3);
                boolean enrollInLecturesResult = enrollInLectures(moduleNo,user_id);
                if(enrollInLecturesResult){
                    enrolled = true;
                }
                
        }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            Bugzilla.reportBug("Issue with enrolling student to module");
        }
         return enrolled;
	}

	/**
	 * 
	 * @param module
     * @param email
     * @return 
	 */
	public boolean unEnrollToModule(String module, String email)
	{
            try{
                SystemSetting.initSystemSetting();
                String query1 = "SELECT module_id FROM Module WHERE code = '" + module + "';";                              
                sqlHandler = new SqlHandler();
                ResultSet queryResult1 = sqlHandler.runQuery(query1);
                
                String query2 = "SELECT user_id FROM User WHERE email = '" + email + "';";                              
                
                ResultSet queryResult2 = sqlHandler.runQuery(query2);
                
                queryResult1.last();
                queryResult2.last();
                
                moduleNo = queryResult1.getInt("module_id");
                user_id = queryResult2.getInt("user_id");
                
                
                String query3 = "DELETE FROM User_has_Module WHERE user_id = '" + user_id + "' AND module_id = '"
                               + moduleNo + "';";
                //unenrolled = true;
                sqlHandler.runStatement(query3);
                boolean unenrollInLecturesResult = unenrollInLectures(moduleNo,user_id);
                if(unenrollInLecturesResult){
                    unenrolled = true;
                }
                
        }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            Bugzilla.reportBug("Issue with unenrolling student to lectures");
            
        }
            return unenrolled;
	}
        
        
        public boolean enrollInLectures(int moduleId, int userId){
            boolean enrolledInLectures = false;
            try{
                SystemSetting.initSystemSetting();
                String statement1 = "INSERT INTO Is_Attending " +
                "SELECT meeting_id, user_id " +
                "FROM Module_has_Lecture JOIN User_has_Module " +
                "ON Module_has_Lecture.module_id = User_has_Module.module_id " +
                "WHERE User_has_Module.module_id ="+moduleId+" AND user_id ="+ userId+";";     
                sqlHandler = new SqlHandler();
                int queryResult = sqlHandler.runStatement(statement1);
            
           }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            Bugzilla.reportBug("Issue with unenrolling student to lectures");
            
            }
            
            
            
            
            return enrolledInLectures;
        }
        
        
        public boolean unenrollInLectures(int module_Id, int userId){
            
            boolean unenrolledInLectures = false;
            
            try{
                SystemSetting.initSystemSetting();
                String query1 = "DELETE FROM Is_Attending "
                        + "WHERE meeting_id IN (SELECT meeting_id FROM Module_has_Lecture WHERE module_id ="
                        +module_Id+")AND user_id =" + userId+";";     
                
                sqlHandler = new SqlHandler();
                int queryResult1 = sqlHandler.runStatement(query1);
            
            
            
            }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            Bugzilla.reportBug("Issue with unenrolling student to module");
            
            }
            
            return unenrolledInLectures;
            
        }
        
        public int getStudentNo() {
            return user_id;
        }
        
        public void setStudentNo(int studentNo) {
            this.user_id = studentNo;
        }
}
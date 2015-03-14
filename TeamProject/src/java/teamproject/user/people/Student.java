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
        private int moduleId;
        private String moduleCode ="";
        private SqlHandler sqlHandler;
        private boolean enrolled = false;
        private boolean unenrolled = false;

	/**
	 * 
	 * @param module
     * @param email
     * @return 
	 */
	public boolean enrollToModule(String moduleCode, String email)
	{
            try{
                SystemSetting.initSystemSetting();
                String query1 = "SELECT module_id FROM Module WHERE code = '" + moduleCode + "';";                              
                sqlHandler = new SqlHandler();
                ResultSet queryResult1 = sqlHandler.runQuery(query1);
                
                String query2 = "SELECT user_id FROM User WHERE email = '" + email + "';";                              
                
                ResultSet queryResult2 = sqlHandler.runQuery(query2);
                
                queryResult1.last();
                queryResult2.last();
                
                moduleId = queryResult1.getInt("module_id");
                user_id = queryResult2.getInt("user_id");
                //moduleCode = queryResult2.getString("code");
                
                String query3 = "INSERT INTO User_has_Module(user_id, module_id)"  
                              + "VALUES(" + user_id + "," + moduleId +");";
                    enrolled = sqlHandler.runStatement(query3) >0 ;
                boolean enrollInLecturesResult = enrollInLectures(moduleId, user_id);
                
                
                
        }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            Bugzilla.reportBug("Issue with enrolling student to module");
        }
         return enrolled;
	}
        
        
        /**
         * When you enroll in a Module it enrolls you in the lectures associated with that module.
         * @param moduleId
         * @param userId
         * @return 
         */
         public boolean enrollInLectures(int moduleId, int userId){
            boolean enrolledInLectures = false;
            try{
                /**
                 * initializes system settings and loads saved settings.
                 */
                SystemSetting.initSystemSetting();
                 /**
                  *  Stores the first statement as a string.
                */
                String statement1 = "INSERT INTO Is_Attending " +
                "SELECT meeting_id, user_id " +
                "FROM Module_has_Lecture JOIN User_has_Module " +
                "ON Module_has_Lecture.module_id = User_has_Module.module_id " +
                "WHERE User_has_Module.module_id ="+moduleId+" AND user_id ="+ userId+";";     
                
                /**
                 * Runs the SQL statement's using the sqlHandler
                 * Stores if the statement was successful or not in variable as
                 * a 1 or 0.
                 */
                int queryResult = sqlHandler.runStatement(statement1);
                
                if(queryResult >0){
                    enrolledInLectures = true;
                }
            
           }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            Bugzilla.reportBug("Issue with unenrolling student to lectures");
            
            }
            
            return enrolledInLectures;
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
                
                moduleId = queryResult1.getInt("module_id");
                user_id = queryResult2.getInt("user_id");
                
                
                String query3 = "DELETE FROM User_has_Module WHERE user_id = '" + user_id + "' AND module_id = '"
                               + moduleId + "';";
                if(unenrollInLectures(moduleId,user_id));
                {
                    unenrolled = sqlHandler.runStatement(query3)>0;                
                }
           
                
        }catch(SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            Bugzilla.reportBug("Issue with unenrolling student to lectures");
            
        }
            return unenrolled;
	}
        
        
       
        /**
         * When you un-enroll in a Module it un-enrolls you in the lectures associated with that module.
         * @param module_Id
         * @param userId
         * @return 
         */
        public boolean unenrollInLectures(int module_Id, int userId){
            
            boolean unenrolledInLectures = false;
            
            try{
                 /**
                   * initializes system settings and loads saved settings.
                  */
                SystemSetting.initSystemSetting();
                /**
                 *  Stores the statement as a string.
                 */
                String query1 = "DELETE FROM Is_Attending "
                        + "WHERE meeting_id IN (SELECT meeting_id FROM Module_has_Lecture WHERE module_id ="
                        +module_Id+")AND user_id =" + userId+";";  
                /**
                 * Runs the SQL statement's using the sqlHandler
                 * Stores if the statement was successful or not in variable as
                 * a 1 or 0.
                 */
                int queryResult1 = sqlHandler.runStatement(query1);
                
                if(queryResult1 > 0){
                    unenrolledInLectures = true;
                }
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
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

/**
 * A student class which provides methods for a student to enroll to modules to
 * receive their timetable slots, also possible for student to un-enroll from any
 * module which they are already enrolled to.
 * @author Denise Lucey - 112700291
 */
public class Student extends Person {

	private ArrayList<Module> modules;
	private String studentNo;
        private String moduleNo;
        private SqlHandler sqlHandler;
        private boolean enrolled = false;
        private boolean unenrolled = false;

	/**
	 * Enrolls a Student user with a valid email address to a module. 
         * It achieves this by adding the user_id of the student and the 
         * module_id of the module into the User_has_Module table.
	 * @param module - code of the module to be enrolled
         * @param email - email address of student making enrollment
         * @return true if student successfully enrolls to module or false
         *         otherwise.
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
	 * Un-enrolls a Student user with a valid email address from a module
         * which they are enrolled to already. 
         * It achieves this by deleting the user_id of the student and the 
         * module_id of the module into the User_has_Module table.
	 * @param module - code of the module to be un-enrolled from
         * @param email - email address of student making enrollment
         * @return true if student successfully un-enrolls from module or false
         *         otherwise.
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
            Bugzilla.reportBug("Issue with unenrolling student to module");
            
        }
            return unenrolled;
	}
        
    /**
     * Returns the Student user id as a String.
     * @return studentNo - number id of student.
     */
    public String getStudentNo() {
            return studentNo;
        }
        
    /**
     * Allows the Student user id to be set.
     * @param studentNo - number id of student
     */
    public void setStudentNo(String studentNo) {
            this.studentNo = studentNo;
        }
}
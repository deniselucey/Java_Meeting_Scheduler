/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teamproject.user.privilege;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.sql.SqlHandler;
import teamproject.system.Bugzilla;
import teamproject.system.SystemSetting;
import teamproject.user.people.Student;

/**
 *
 * @author zolamcdonald
 */
public class LecturerPrivlege {
    private Boolean addLecturer = false;
     
    /**
     * Adds a lecturer to the database.
     * @param email
     * @return 
     */
    public boolean add(String email)
    {
            try{
                /**
                 * initializes system settings and loads saved settings.
                 */
                SystemSetting.initSystemSetting();
                
                /**
                 * initializes the SQL Handler.
                 */
                SqlHandler sqlHandler = new SqlHandler();
                
                /**
                 * Stores the SQL query as a String.
                 */
                String query = "INSERT INTO lecturer SELECT user_id "+
                                "FROM User "+
                                "WHERE email = '" + email + "';";
                /**
                 * Uses the SQLhandlee to run the query of the database.
                 */
                sqlHandler.runStatement(query);
                /**
                 * Sets the boolean to true.
                 */
                addLecturer = true;
                
            }catch(SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                Bugzilla.reportBug("Issue with adding new Lecturer by an admin");
            }
         /**
          * Returns true or false depending on has a lecturer been added to the
          * db or not.
          */
         return addLecturer;
    }
}

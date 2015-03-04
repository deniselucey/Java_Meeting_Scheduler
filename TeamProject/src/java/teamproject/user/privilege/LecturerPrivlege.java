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
    public boolean add(String email)
    {
            try{
                SystemSetting.initSystemSetting();                             
                SqlHandler sqlHandler = new SqlHandler();
                
                String query = "INSERT INTO lecturer SELECT user_id "+
                                "FROM User "+
                                "WHERE email = '" + email + "';";
                sqlHandler.runStatement(query);
            
                addLecturer = true;
                
            }catch(SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                Bugzilla.reportBug("Issue with adding new Lecturer by an admin");
            }
         return addLecturer;
    }
}

package teamproject.user.privilege;

import java.sql.SQLException;
import java.util.jar.Attributes;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.college.Course;
import teamproject.college.Module;
import teamproject.meeting.Meeting;
import teamproject.sql.SqlHandler;
import teamproject.system.Bugzilla;
import teamproject.system.SystemSetting;
import teamproject.user.Group;
import teamproject.user.people.Lecturer;
import teamproject.user.people.Person;
import teamproject.user.people.Student;

/**
 * An Admin class which allows users who act as administrators to create, edit
 * and delete modules. 
 * It also provides a way for administrators to add further administrators.
 * 
 * @author Denise Lucey - 112700291
 */
public class AdminPrivlege extends Privilege {
        private static boolean createdModule = false;
        private static boolean deleteModule = false;
        private static boolean editModule = false;

    /**
     *
     */
    public AdminPrivlege()
    {
        super(true,true);
    }
    
    @Override
    public boolean setAdmin(String email)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public boolean add(String email)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public boolean remove(Group group) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean reserveTimeBlock(Meeting meeting)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addCourse(Course course)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean schdualeHighMeeting(Meeting meeting)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addModule(Course course, Module module)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean addLecture(Module module, Lecturer lecture)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setDataBaseUrl(String url)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setDataBaseName(Attributes.Name name)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean setDataBasePassword(String password)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int clearInactiveUsers(int daysInactive)
    {
        //there a setting for this!.
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /**
     * Allows a module to be created and added to the system.
     * It achieves this by adding an entry in the Module table with the details
     * of the module.
     * 
     * @param module_id - id of the module
     * @param credit - amount of credits the module is worth
     * @param title - title of the module
     * @param code - module code of the module, i.e. CS3505
     * @param description - short description of the teachings of the module.
     * @param year - the year to which the module is taught.
     * @return true if module is successfully created or false otherwise.
     */
    public boolean CreateModule(int module_id, int credit, String title, String code, String description, int year )
    {
            try{
                SystemSetting.initSystemSetting();
                SqlHandler sqlHandler = new SqlHandler();
                String query3 = "INSERT INTO Module(module_id, credit, title, code, description, year)"  
                              + "VALUES(" + module_id + "," + credit + ",'" + title + "', '" + code 
                              +   "','" + description + "'," + year + ");";
                sqlHandler.runStatement(query3);
                
                createdModule = true;
                
            }catch(SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                Bugzilla.reportBug("Issue with creating module by the admin.");
            }
         return createdModule;
    }
        
    /**
     * Allows a module to be deleted from the system.
     * It achieves this by deleting all details of a module from the Module table
     * using its code.
     * @param code - module code of module to be deleted.
     * @return true if module is successfully deleted or false otherwise.
     */
    public boolean DeleteModule(String code)
    {
            try{
                SystemSetting.initSystemSetting();                             
                SqlHandler sqlHandler = new SqlHandler();               
                
                String query = "DELETE FROM Module WHERE code = '" + code + "';";
                sqlHandler.runStatement(query);
            
                deleteModule = true;
                
            }catch(SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                Bugzilla.reportBug("Issue with deleting module by a admin");
            }
         return deleteModule;
    }
        
    /**
     * Allows the details of an active module in the system to be edited.
     * It achieves this by altering its entry in the Module table with the 
     * updated details of the module.
     * 
     * @param module_id - id of the module
     * @param credit - amount of credits the module is worth
     * @param title - title of the module
     * @param code - module code of the module, i.e. CS3505
     * @param description - short description of the teachings of the module.
     * @param year - the year to which the module is taught.
     * @return true if module is successfully edited or false otherwise.
     */
    public boolean EditModule(String code, int credit, String title, String description, int year )
    {
            try{
                SystemSetting.initSystemSetting();                             
                SqlHandler sqlHandler = new SqlHandler();
                
                String query = "UPDATE Module SET credit = " + credit
                        + ", title = '" + title + "'"
                        + ", description = '" + description + "'"
                        + ", year = " + year 
                        + "  WHERE code = '" + code + "';";
                sqlHandler.runStatement(query);
            
                editModule = true;
                
            }catch(SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
                Bugzilla.reportBug("Issue with editing module by a admin");
            }
         return editModule;
    }
}


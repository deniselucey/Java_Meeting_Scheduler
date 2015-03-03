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

public class AdminPrivlege extends Privilege {
        private static boolean createdModule = false;
        private static boolean deleteModule = false;
        private static boolean editModule = false;

    public AdminPrivlege()
    {
        super(true,true);
    }
    
    @Override
    public void setAdmin(String email)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(int Meeting)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Group group)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean add(Person person)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean remove(Person person)
    {
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


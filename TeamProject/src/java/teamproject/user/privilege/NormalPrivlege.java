package teamproject.user.privilege;

import java.io.IOException;
import java.util.jar.Attributes;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import teamproject.college.Course;
import teamproject.college.Module;
import teamproject.meeting.Meeting;
import teamproject.system.SystemSetting;
import teamproject.user.Group;
import teamproject.user.people.Lecturer;
import teamproject.user.people.Person;

public class NormalPrivlege extends Privilege {

    
    public NormalPrivlege()
    {
        super(false,false);
       
    }
     
    private void log()
    {
        Logger logger;
        FileHandler fh;
        try       
        {
            logger = Logger.getLogger("SystemLogger");  
            fh = new FileHandler("SystemLog.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();  
            fh.setFormatter(formatter); 
            logger.severe("Non admin attempted to access Admin Privilege Methodes ");

        } catch (IOException ex) {
            Logger.getLogger(SystemSetting.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SecurityException ex) {
            Logger.getLogger(SystemSetting.class.getName()).log(Level.SEVERE, null, ex);
        }             
    }
    @Override
    public void setAdmin(Person person, boolean value)
    {
        log();
    }

    @Override
    public boolean remove(int Meeting)
    {
        log();
        return false;
    }

    @Override
    public boolean remove(Group group)
    {
        log();
        return false;
    }

    @Override
    public boolean add(Person person)
    {
        log();
        return false;
    }

    @Override
    public boolean remove(Person person)
    {
        log();
        return false;
    }

    @Override
    public boolean reserveTimeBlock(Meeting meeting)
    {
        log();
        return false;
    }

    @Override
    public boolean addCourse(Course course)
    {
        log();
        return false;
    }

    @Override
    public boolean schdualeHighMeeting(Meeting meeting)
    {
        log();
        return false;
    }

    @Override
    public boolean addModule(Course course, Module module)
    {
        log();
        return false;
    }

    @Override
    public boolean addLecture(Module module, Lecturer lecture)
    {
        log();
        return false;
    }

    @Override
    public boolean setDataBaseUrl(String url)
    {
        log();
        return false;
    }

    @Override
    public boolean setDataBaseName(Attributes.Name name)
    {
        log();
        return false;
    }

    @Override
    public boolean setDataBasePassword(String password)
    {
        log();
        return false;
    }

    @Override
    public int clearInactiveUsers(int daysInactive)
    {
        log();
        return 0;
    }

}
package teamproject.system;
import com.j2bugzilla.base.*;
import com.j2bugzilla.rpc.*;

/**
 *
 * @author deniselucey
 */
public class Bugzilla {
    
    private String bugerror;
    private static boolean reported = false;
    
    public static boolean reportBug(String bugerror) { 
        try {
            BugzillaConnector conn = new BugzillaConnector();
            conn.connectTo("http://52.16.9.114/");
            
            LogIn logIn = new LogIn("deniselucey@gmail.com", "cs3505");
            conn.executeMethod(logIn);

            BugFactory factory = new BugFactory();
            Bug bug = factory.newBug()
                .setOperatingSystem("WINDOWS")
                .setPlatform("PC")
                //.setPriority("P1")
                .setProduct("TestProduct")
                .setComponent("TestComponent")
                .setSummary("Broken test bug.")
                .setVersion("unspecified")
                .setDescription("It doesn't work.")
                .createBug();
            
            ReportBug report = new ReportBug(bug);
            
            conn.executeMethod(report);
            
            int id = report.getID();
            reported = true;
            
    
        } catch (ConnectionException e) {
           System.out.print("Exception with connection"); 
        } catch (BugzillaException e) {
            System.out.print("Bugzilla Exception");
            e.printStackTrace();
            e.getMessage();
        }
        
        return reported;
    }
    
}


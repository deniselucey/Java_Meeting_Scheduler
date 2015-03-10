package teamproject.system;

import java.util.HashMap;
import java.util.prefs.Preferences;
import teamproject.user.User;

/**
 * Class used to load,store and edit the system settings.
 * If you want to add a property use {@link Property}. 
 * @author Nigel
 */
public class SystemSetting
{
    static HashMap<String, String> changes;
    static Preferences prefs;
    private static boolean initialized = false;
    private static final String empty = "Empty";
    private static final String isEmpty = "false";
    private static final int argsLength = 2;
    public SystemSetting()
    {
        initSystemSetting();
    }
    public static void main(String[] args)
    {
        initSystemSetting();
        if(args.length == argsLength)
        {
            Property property = Property.getByName(args[0]);
            if(property != null)
            {
                SystemSetting.setProperty(property, args[1], null);
            }
            else
            {
                System.err.println("No property by the name " + args[0]);
            }
        }
        else
        {
            System.err.println("Arguments length not valid. Expected " + argsLength + " got " + args.length + ".");
        }
    }
    
    public static void initSystemSetting()
    { 
        if(!initialized)
        {
            prefs = Preferences.userNodeForPackage(SystemSetting.class.getClass());
            changes = new HashMap<>();
        }
        if(prefs.get(empty, "").equals(""))
        {
            loadDefault();
        }
        initialized = true;
    }
    
    public static String getProperty(String key, String defaultValue)
    {
        return prefs.get(key,defaultValue);
    }
    
    public static String getProperty(Property key, String defaultValue)
    {
        return prefs.get(key.name(),defaultValue);
    }
    
    public static String getChanges(Property key, String defaultValue)
    {
        String result = changes.get(key.name());
        return result == null ? defaultValue : result;
    }
    
    public static void setProperty(Property key, String value, User user)
    {
        if( canAccess(user))
        {
            changes.put(key.name(), value);
        }
    }
    public static void setProperty(String key, String value)
    {
   
            changes.put(key, value);
        
    }
     
     
      /**
     * Test if a user is allowed to run methods of this class.
     * @param user user calling method
     * @return True if user can access setting. 
     */
    private static boolean canAccess(User user)
    {
        //TODO uncomment when method are made##############.
        boolean access = true;// User.getPrivlege().isAdmin();
        if(!access)
        {
            //logger.severe(user.getSelf().getID() + " Attemped to use a System Setting Method");
            
        }
        return access;
    }
    
    public static void saveSettings()
    {
        for(Object pair:changes.entrySet())
        {
            HashMap.Entry<String,String> MP = (HashMap.Entry<String,String>)pair;
            prefs.put(MP.getKey(), MP.getValue());
        }
    }
    
    public static void saveSettings(User user)
    {
        if(canAccess(user))
        {
            saveSettings();
        }
    }
    
    /**
     * Test if user has privilege to run this method, If true sets All Properties to their default values.
     * If false logs that user tried to use admin methods.
     * 
     * @param user use call the method
     */
    public static void loadDefault(User user)
    {
        if( canAccess(user))
        {
           loadDefault();
        }
    }
    
    /**
     * Set All Properties to their default values;
     */
    private static void loadDefault()
    {
        for(Property p : Property.values())
        {
            prefs.put(p.name(), p.getDefaultValue());
            changes.clear();
        }
        prefs.put(empty, isEmpty);
    }
    public static void clearChange(User user)
    {
        if(canAccess(user))
        {
            clearChange();
        }
    }
    
    private static void clearChange()
    {
        changes.clear();
    }
    
    public String toHtmlForm()
    {
        System.out.println(Property.toHTMLForm());
       return Property.toHTMLForm();
    }
    public static String toHtmlFormStatic()
    {
        System.out.println(Property.toHTMLForm());
       return Property.toHTMLForm();
    }
    public static boolean isInitialized() { return initialized; } 
}

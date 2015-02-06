package teamproject.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import teamproject.user.User;

/**
 * Class used to load,store and edit the system settings.
 * If you want to add a property use {@link Property}. 
 * @author Nigel
 */
public class SystemSetting
{
    //properties that the system will be useing
    private static Properties hardProperties;
    //properties that hold the user settings 
    private static Properties softProperties;
    private static OutputStream output;
    private static InputStream input;

    private static String fileName = "config.properties";
    private static String path = "";
    private static String filePath = path + fileName;
    private static final Logger logger = Logger.getLogger("SystemLogger");  
    private static FileHandler fh;  
    private static boolean initialized = false;
    
    
    /**
     * Set All Properties to their default values;
     */
    public static void loadDefault(User user) throws IOException
    {
        if( canAccess(user))
        {
            for(Property p : Property.values())
            {
                hardProperties.setProperty(p.name(), p.getDefaultValue());
                softProperties.setProperty(p.name(), p.getDefaultValue());
            }
        }
    }
    

    /**
     *  Initialized setting class. Tries to load system setting, if that fails 
     *  creates a new one setting file with default settings.
     *  @param reinitialize Should it re initialize properties.
     */ 
    public static void initSystemSetting(User user, boolean reinitialize)
    {
        
      
        if(canAccess(user) && (!initialized || reinitialize))
        {
            hardProperties = new Properties();
            softProperties = new Properties();
            output = null;
            input = null;

            //try to set up a logger to log systerm setting changes
            try  
            {
                fh = new FileHandler("SystemLog.log",true);
                logger.addHandler(fh);
                logger.setUseParentHandlers(false);
                SimpleFormatter formatter = new SimpleFormatter();  
                fh.setFormatter(formatter); 
            } 
            catch (IOException | SecurityException ex) 
            {
                Logger.getLogger(SystemSetting.class.getName()).log(Level.SEVERE, null, ex);
            }

            //tries to load system properties. if it fails it then trys to create a .properties
            try       
            {
                input = new FileInputStream(filePath);
                hardProperties.load(input);
                initialized = true;
            } 
            catch (FileNotFoundException ex) 
            {
                //logs creation of properties
                logger.log(Level.INFO, "{0} Not Found. Trying to create a new {1}\n", new Object[]{fileName, filePath});
                createNewPropertiesFile(user);
            }
            catch (IOException ex)
            {
                Logger.getLogger(SystemSetting.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    } 
    
    private static void createNewPropertiesFile(User user)
    {
        try
        {
            output = new FileOutputStream(filePath);
            loadDefault(user);
            initialized = true;
        } 
        catch (IOException  ex1) 
        {
            Logger.getLogger(SystemSetting.class.getName()).log(Level.SEVERE, null, ex1);
        } 
        finally 
        {
            if (output != null) 
            {
                try 
                {
                    output.close();
                }
                catch (IOException e) 
                {
                    e.printStackTrace();
                }
            }
        }
    }
    
    /**
     * Gets a Property value.
     * @param key property key
     * @param user
     * @return property value of key.
     */
    public static String getProperty(Property key, User user)
    {
        if( canAccess(user))
        {
            return hardProperties.getProperty(key.name());
        }
        return "";
    }
    
     /**
     * Gets a softProperty value.
     * @param key property key
     * @param user
     * @return property value of key.
     */
    public static String getSoftProperty(Property key, User user)
    {
        if( canAccess(user))
        {
            return softProperties.getProperty(key.name());
        }
        return "";
    }
    
    /**
     * Sets a Property value.
     * @param key property to change
     * @param value value to change property to.
     * @param user
     */
    public static void setProperty(Property key, String value, User user)
    {
        if( canAccess(user))
        {
            //uncomment logger when user getId is made ############################
           //TODO logger.info(key.name() + " was set to " + value + " By " + user.getID());
            softProperties.setProperty(key.name(), value);
        }
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

   
    /**
     * Stores soft properties in output file.
     * @throws IOException 
     */
    private static void saveSettings() throws IOException
    {
        output = new FileOutputStream(filePath);
        softProperties.store(output, null); 
    }
    
    public static void saveSettings(User user) throws IOException
    {
        if(canAccess(user))
        {
            saveSettings();
        }
    }

    
    //Getters & Setters
    public static String getFileName(){return fileName;}
    public static String getPath(){return path;}
    public static boolean isInitialized() { return initialized; } 
}

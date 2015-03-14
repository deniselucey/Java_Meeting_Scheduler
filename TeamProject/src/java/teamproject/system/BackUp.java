package teamproject.system;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;


public class BackUp {

	private File BackupFile;
        private ArrayList<File> backupFiles;

        /**
         * 
         * @param mySQLDump
         * @param dbUserName
         * @param dbPassword 
         * @param dbName
         * @param path
         * @return String that either confirms or denies the creation of the database backup.
         */
	public static String createBackup(){//String mySQLDump,String dbUserName, String dbPassword,String dbName, String path){
            SystemSetting.initSystemSetting();
            String mySQLDump = SystemSetting.getProperty(Property.MySQLPath, "")  + "/" +  SystemSetting.getProperty(Property.MySQLDumpFileName, "");
            String dbUserName = SystemSetting.getProperty(Property.DatabaseUser, "");
            String dbPassword = SystemSetting.getProperty(Property.DatabasePassword, "");
            String dbName = SystemSetting.getProperty(Property.DatabaseName, "");
            String path = SystemSetting.getProperty(Property.BackUpPath, "");
            path += "/" + SystemSetting.getProperty(Property.BackUpName, "");
            
            System.out.println("Path :" + path );
            
            String command =  mySQLDump + " -u "+ dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path + ".sql " ;
            System.out.println(command);
            Process processRuntime;
            String sentence = "";
            try{
                System.out.println(command);
                processRuntime = Runtime.getRuntime().exec(command);
                int completedProcess = processRuntime.waitFor();
                if (completedProcess == 0){
                    sentence = "Backup database was created successfully";
                }else{
                    sentence = "Could not create the database backup";
                }
            } catch (IOException | InterruptedException ex){
                Bugzilla.reportBug("Issue with creating backup");
            }
             return sentence;
        }
        
        
        /**
         * 
         * @param mysql
         * @param dbUserName
         * @param dbPassword
         * @param source
         * @return 
         */
	public static boolean restore(){//String mysql, String dbUserName, String dbPassword, String source){
            SystemSetting.initSystemSetting();
            String mysql = SystemSetting.getProperty(Property.MySQLPath, "")  + "/" + SystemSetting.getProperty(Property.MySQLFileName, "");
            String dbUserName = SystemSetting.getProperty(Property.DatabaseUser, "");
            String dbPassword = SystemSetting.getProperty(Property.DatabasePassword, "");
            String source = SystemSetting.getProperty(Property.BackUpPath, "")  + "/" +  SystemSetting.getProperty(Property.BackUpName, "") + ".sql";
            
            String[] restoreCommand = new String[]{mysql, "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + source};
            Boolean isRestored = false;
            Process processRuntime;
            try {
                processRuntime = Runtime.getRuntime().exec(restoreCommand);
                int completedProcess = processRuntime.waitFor();
                if (completedProcess == 0) {
                    isRestored = true;
                } else {    
                }
            } catch (IOException | InterruptedException ex) {
                Bugzilla.reportBug("Issue with restoring");
            }
            return isRestored;
	}
        
       /**
        * 
        * @param path
        * @throws IOException
        * @throws BackingStoreException 
        */
        public static String backUpPreferences(){// throws IOException, BackingStoreException {
            SystemSetting.initSystemSetting();
            String path = SystemSetting.getProperty(Property.BackUpPath, "") + "/" + SystemSetting.getProperty(Property.BackUpName, "") + ".xml";
            
            Preferences root = Preferences.userRoot();
            FileOutputStream preference; 
            try {
                preference = new FileOutputStream(path);
                root.exportSubtree(preference);
                preference.close();
            } catch (FileNotFoundException ex) {
               return "File Not Found " + path;
            } catch (IOException ex) {
               return "Error Read From File";
            } catch (BackingStoreException ex) {
               return "BackingStoreException";
            }
            return "Backing up of System Setting successfully";
            
        }
    
        /**
         * 
         * @param path
         * @throws IOException
         * @throws InvalidPreferencesFormatException 
         */
        public static void restorePreferences() throws IOException, InvalidPreferencesFormatException {
            SystemSetting.initSystemSetting();
            String path = SystemSetting.getProperty(Property.BackUpPath, "")  + "/" +  SystemSetting.getProperty(Property.BackUpName, "") + ".xml";
            
            Preferences root = Preferences.userRoot();
            FileInputStream preference = new FileInputStream(path); 
            Preferences.importPreferences(preference);
            preference.close(); 
        }
}
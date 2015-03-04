package teamproject.system;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
	public static String createBackup(String mySQLDump,String dbUserName, String dbPassword,String dbName, String path){
            String command =  mySQLDump + " -u "+ dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path + " " ;
            Process processRuntime;
            String sentence = " ";
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
	public static boolean restore(String mysql, String dbUserName, String dbPassword, String source){
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
        public static void backUpPreferences(String path) throws IOException, BackingStoreException {
            Preferences root = Preferences.userRoot();
            FileOutputStream preference = new FileOutputStream(path + "backup.xml"); //1337 Hax
            root.exportSubtree(preference);
            preference.close();
        }
    
        /**
         * 
         * @param path
         * @throws IOException
         * @throws InvalidPreferencesFormatException 
         */
        public static void restorePreferences(String path) throws IOException, InvalidPreferencesFormatException {
            Preferences root = Preferences.userRoot();
            FileInputStream preference = new FileInputStream(path + "backup.xml"); //1337 Hax
            Preferences.importPreferences(preference);
            preference.close(); 
        }
}
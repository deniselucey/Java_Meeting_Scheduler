package teamproject.system;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;
import java.util.prefs.BackingStoreException;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;


public class BackUp {

	private File BackupFile;
        private ArrayList<File> backupFiles;
       

        /**
         * Creates a backup File in one does not already exist.
         * @param nameOfFile
         * @return isBackupCreated
         */
	/**public boolean createBackUp(String nameOfFile){
            Boolean isBackupCreated = false;
            BackupFile = new File(nameOfFile + ".backup");
            if(!BackupFile.exists()){
                try {
                    BackupFile.createNewFile();
                    isBackupCreated = true;
                } catch (IOException ex) {
                    Logger.getLogger(BackUp.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            return isBackupCreated; 
	}
        */
        /**
         * Takes the backup file and stores it in the arraylist.
         * @return 
         */
	/**public boolean storeBackUp(){
            Boolean backupStored = false;
            backupFiles = new ArrayList<>();
            //Checks to see if a file exists and then places it in the arraylist.
            if(BackupFile.exists()){
                backupFiles.add(BackupFile);
                backupStored = true;
            }
            return backupStored;
	}*/
        
        /**
         * 
         * @param mySQLDump
         * @param dbUserName
         * @param dbPassword 
         * @param dbName
         * @param path
         * @return String that either confirms or denies the creation of the database backup.
         */
	public String createBackup(String mySQLDump,String dbUserName, String dbPassword,String dbName,String path){
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
         * @param dbUserName
         * @param dbPassword
         * @param path
         * @param source
         * @return 
         */
	public boolean restore(String dbUserName, String dbPassword, String path, String source){
            String[] restoreCommand = new String[]{"mysql ", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + source};
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
        
       
    public void backUpPreferences(String path) throws IOException, BackingStoreException {
        Preferences root = Preferences.userRoot();
        FileOutputStream preference = new FileOutputStream(path + "backup.xml"); //1337 Hax
        root.exportSubtree(preference);
        preference.close();
    }
    
    
    public void restorePreferences(String path) throws IOException, InvalidPreferencesFormatException {
         Preferences root = Preferences.userRoot();
         FileInputStream preference = new FileInputStream(path + "backup.xml"); //1337 Hax
         Preferences.importPreferences(preference);
         preference.close();
    
    }
}
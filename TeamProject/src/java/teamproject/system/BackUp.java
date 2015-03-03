package teamproject.system;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class BackUp {

	private File BackupFile;
        
	private ArrayList<File> logs;
        private ArrayList<File> backupFiles;
       
        
        
        /**
         * Creates a backup File in one does not already exist.
         * @param nameOfFile
         * @return isBackupCreated
         */
	public boolean createBackUp(String nameOfFile){
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
        
        /**
         * Takes the backup file and stores it in the arraylist.
         * @return 
         */
	public boolean storeBackUp(){
            Boolean backupStored = false;
            backupFiles = new ArrayList<>();
            //Checks to see if a file exists and then places it in the arraylist.
            if(BackupFile.exists()){
                backupFiles.add(BackupFile);
                backupStored = true;
            }
            return backupStored;
	}
        
        /**
         * 
         * @param dbUserName
         * @param dbPassword 
         * @param dbName
         * @param path
         * @return String that either confirms or denies the creation of the database backup.
         */
	public String createDateBaseBackup(String dbUserName, String dbPassword,String dbName,String path){
            String command = "mysqldump -u "+ dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path ;
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
                
            }
             return sentence;
        }
        
        
        /**
         * 
         * @return 
         */
	public boolean storeLogfiles(){
            Boolean logFilesStored = false;
            logs = new ArrayList<>();
            /*try{
               if(logFile.exists()){
                    logs.add(logFile);
                    logFilesStored = true;
               }
                
            }catch(Exception ex){
                logFilesStored = false;
            }**/
            return logFilesStored;
	}
        
	/**
	 * 
	 * @param url
         * @return 
	 */
	public boolean loadBackup(String url){
            Boolean backupLoaded = false;
            
            
            return backupLoaded;   
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
            }
            return isRestored;
	}

}
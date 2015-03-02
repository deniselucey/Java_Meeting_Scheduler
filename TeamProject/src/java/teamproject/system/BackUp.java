package teamproject.system;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class BackUp {

	private File BackupFile;
	private ArrayList<File> logs;
        private ArrayList<File> backupFiles;

	public boolean createBackUp(String fileName){
            Boolean isBackupCreated = false;
            BackupFile = new File(fileName + ".backup");
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

	public boolean storeBackUp(){
            Boolean backupStored = false;
            backupFiles = new ArrayList<>();
            if(BackupFile.exists()){
                backupFiles.add(BackupFile);
                backupStored = true;
            }
            return backupStored;
	}

	public String createDateBaseBackup(String dbName,String dbUserName, String dbPassword, String path){
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
            	
	public boolean storeLogfiles(){
            Boolean logFilesStored = false;
            logs = new ArrayList<>();
            Logger logger = Logger.getLogger(BackUp.class.getName());
            
            try{
                if(){
                    
                }
                logFilesStored = true;
            }catch(Exception ex){
                logFilesStored = false;
            }
            return logFilesStored;
	}
        
	/**
	 * 
	 * @param url
	 */
	public boolean loadBackup(String url){
            
            // TODO - implement System.loadBackup
	    throw new UnsupportedOperationException();
            
	}

	public boolean restore(String dbName,String dbUserName, String dbPassword, String path, String source){
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
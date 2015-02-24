package teamproject.system;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.*;

public class BackUp {

	private File BackupFile;
	private ArrayList<File> logs;

	public boolean createBackUp(){
            
            
            
	}

	public boolean storeBackUp(){
            
            
	}

	public String createDateBaseBackup(String dbName,String dbUserName, String dbPassword, String path){
            String executeCommand = "mysqldump -u "+ dbUserName + " -p" + dbPassword + " --add-drop-database -B " + dbName + " -r " + path ;
            Process runtimeProcess;
            String sentence = " ";
            try{
                System.out.println(executeCommand);
                runtimeProcess = Runtime.getRuntime().exec(executeCommand);
                int processComplete = runtimeProcess.waitFor();
                if (processComplete == 0){
                    sentence = "Backup database was created successfully";
                }else{
                    sentence = "Could not create the database backup";
                }
            } catch (IOException | InterruptedException ex){
                
            }
             return sentence;
        }
            	
	public boolean storeLogfiles(){
            
	}

	/**
	 * 
	 * @param url
	 */
	public boolean loadBackup(String url){
            
	}

	public boolean restore(){
            
	}

}
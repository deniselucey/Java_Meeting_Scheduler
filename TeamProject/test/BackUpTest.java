/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import teamproject.system.Property;
import teamproject.system.SystemSetting;
import java.util.prefs.*;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.Ignore;
import teamproject.sql.SqlHandler;
import teamproject.system.BackUp;
import teamproject.user.User;


/**
 *
 * @author user
 */
public class BackUpTest {
    
    public BackUpTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

   
    public void testingBackUpSQL() {
        
        String DBName;
        String Username;
        String Password;
        
        SystemSetting.initSystemSetting();
        DBName = SystemSetting.getProperty(Property.DatabaseName, "");
        Username = SystemSetting.getProperty(Property.DatabaseUser, "");
        Password = SystemSetting.getProperty(Property.DatabasePassword, "");
        
        System.out.println("C:/xampp/mysql/bin/mysqldump.exe -u " + Username + " -p" + Password + " --add-drop-database -B " + DBName + " -r  C:/Users/user/Desktop/test/backup.sql");
        
        try {
            Runtime.getRuntime().exec("C:/xampp/mysql/bin/mysqldump.exe -u " + Username + " -p" + Password + " --add-drop-database -B " + DBName + " -r  C:/Users/user/Desktop/test/backup.sql");
        } catch (IOException ex) {
            Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }
    
    
    public void testingRestoreSQL() {
        
        String DBName;
        String Username;
        String Password;
        
        SystemSetting.initSystemSetting();
        DBName = SystemSetting.getProperty(Property.DatabaseName, "");
        Username = SystemSetting.getProperty(Property.DatabaseUser, "");
        Password = SystemSetting.getProperty(Property.DatabasePassword, "");
        String source = "C:/Users/dggex_000/Desktop/test/backup.sql";
        
        
        
        String[] restoreCmd = new String[]{"C:/xampp/mysql/bin/mysql ", "--user=" + Username, "--password=" + Password, "-e", "source " + source};
        //Runtime.getRuntime().exec("C:/xampp/mysql/bin/mysql.exe -u " + Username + " -p" + Password + " " + DBName + " <  C:/Users/user/Desktop/test/backup.sql");
            
        Process runtimeProcess;
        try {
 
            runtimeProcess = Runtime.getRuntime().exec(restoreCmd);
            int processComplete = runtimeProcess.waitFor();
 
            if (processComplete == 0) {
                System.out.println("Restored successfully!");
            } else {
                System.out.println("Could not restore the backup!");
            }
        } catch (IOException | InterruptedException ex) {
        }

        
    }
   
   @Test
   public void testBackUp(){
       String DBName;
       String Username;
       String Password;
       String mysqldump;
       String mysql;
       String source;
        
       SystemSetting.initSystemSetting();
       DBName = SystemSetting.getProperty(Property.DatabaseName, "");
       Username = SystemSetting.getProperty(Property.DatabaseUser, "");
       Password = SystemSetting.getProperty(Property.DatabasePassword, "");
       source = "C:/Users/drgex_000/Desktop/test/backup.sql";
       mysqldump = "C:/xampp/mysql/bin/mysqldump.exe";
       mysql = "C:/xampp/mysql/bin/mysql.exe";
       
       
       SqlHandler sql = new SqlHandler();
        try {
         sql.runStatement("DELETE FROM schedulerdatabase.course WHERE course_title = 'test';");

         sql.runStatement("INSERT INTO schedulerdatabase.course VALUES(null,'test','test',1);");
       
         ResultSet rs =  sql.runQuery("SELECT * FROM schedulerdatabase.course WHERE course_title = 'test';");
         System.out.println(rs.next());
         assertThat(rs.getString("course_title"), is(equalTo("test")));
        BackUp.createBackup();
        
           try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
           }
        sql.runStatement("DELETE FROM schedulerdatabase.course WHERE course_title = 'test';");
        
         
        BackUp.restore();
        
          try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
           }
          rs =  sql.runQuery("SELECT * FROM schedulerdatabase.course WHERE course_title = 'test';");
           assertThat(rs.next(), is(equalTo(true)));
           assertThat(rs.getString("course_title"), is(equalTo("test")));
           
        } catch (SQLException ex) {
            Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
   }
   @Ignore
   @Test
   public void testingPreference() {
       String testProperty;
       String testValue;
       String emptyString;
       String path;
       
       testProperty = "BlockedEmailServices";
       //testValue = "cmail";
       path = "C:/Users/drgex_000/Desktop/test/";
       emptyString = "";
       
       SystemSetting.initSystemSetting();
       //SystemSetting.setProperty(Property.BlockedEmailServices, testValue, (teamproject.user.User) (User) null);
        try {
            SystemSetting.saveSettings(null);
            BackUp.backUpPreferences();
            
         //   assertThat(SystemSetting.getProperty(Property.BlockedEmailServices, ""), is(equalTo(testValue)));
            
            try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
           }
            
            SystemSetting.initSystemSetting();
            SystemSetting.setProperty(Property.BlockedEmailServices, emptyString, (teamproject.user.User) (User) null);
            
////           try {
////               BackUp.restorePreferences(path);
////            } catch (InvalidPreferencesFormatException ex) {
////               Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
////            }
////            
//           
//            try {
//               Thread.sleep(1000);
//           } catch (InterruptedException ex) {
//               Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
//           }
//            
//            assertThat(SystemSetting.getProperty(Property.BlockedEmailServices, ""), is(equalTo(testValue)));
                       

        } catch (IOException | BackingStoreException ex) {
            Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
       
       
   }
   
    @Ignore
    @Test
    public void testPreferences() throws IOException, BackingStoreException {
        Preferences root = Preferences.userRoot();
        FileOutputStream preference = new FileOutputStream("C:/Users/user/Desktop/test/backup.xml"); //1337 Hax

        
        root.exportSubtree(preference);
        preference.close();
    }
    
   @Ignore
    @Test
    public void testBackUpPreferences() throws IOException, InvalidPreferencesFormatException {
         Preferences root = Preferences.userRoot();
         FileInputStream preference = new FileInputStream("C:/Users/user/Desktop/test/backup.xml"); //1337 Hax
        
         Preferences.importPreferences(preference);
         preference.close();
    
    }
}
   

        
    
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}


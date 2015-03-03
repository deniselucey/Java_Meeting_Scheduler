/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import java.io.PrintStream;
import org.junit.Ignore;


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

    
    @Test
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


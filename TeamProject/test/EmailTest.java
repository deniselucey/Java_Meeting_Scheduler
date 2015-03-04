/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import teamproject.sql.SqlHandler;
import teamproject.system.Email;

/**
 *
 * @author user
 */
public class EmailTest {
    
    public EmailTest() {
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
    public void testEmail() {
        String testNull = null;
        String testPin = "twelvetwelve";
        int testUserId = 9; // Use an existing, already confirmed user_id from User table
        Email test = new Email();
        
        
        SqlHandler sql = new SqlHandler();
        try {
            sql.runStatement("DELETE FROM schedulerdatabase.register WHERE user_id = '"+ testUserId +"';");

            sql.runStatement("INSERT INTO schedulerdatabase.register VALUES(" + testUserId +",false,null,'" + testPin +"');");
       
            ResultSet rs = sql.runQuery("SELECT user_id FROM register WHERE user_id = " + testUserId +" ; ");
            
            System.out.println(rs.next());
            assertThat(rs.getInt("user_id"), is(equalTo(testUserId)));
            
            try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
           }
            
            test.confirmAccount(testPin, testUserId);
            
            
            
            try {
               Thread.sleep(1000);
           } catch (InterruptedException ex) {
               Logger.getLogger(BackUpTest.class.getName()).log(Level.SEVERE, null, ex);
           }

            ResultSet rs1 = sql.runQuery("SELECT user_id FROM register WHERE user_id = " + testUserId +" ; ");
            
            assertThat(rs1.next(), is(equalTo(false)));

           
            
        } catch (SQLException ex) {
            Logger.getLogger(EmailTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        

        
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}

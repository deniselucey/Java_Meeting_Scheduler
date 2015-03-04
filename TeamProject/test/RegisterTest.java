import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import teamproject.system.Register;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;
import teamproject.sql.SqlHandler;
import static teamproject.system.PasswordHash.createHash;
import teamproject.system.SystemSetting;


/**
 *
 * @author Michelle
 */
public class RegisterTest {
    
    public RegisterTest() {
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

    /**
     * Test of registerDetailsWithDb method, of class Register.
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    @Ignore
    @Test
    public void testRegisterDetailsWithDb() throws NoSuchAlgorithmException, InvalidKeySpecException{
        
        int registered1;
        int registered2;
        /**
         * Encrypts the password.
         */
        String password = encrypt();
        
        /**
         * Sets is isRegistered to false.
         */
        boolean isRegistered = false;
        try{
            
            SystemSetting.initSystemSetting();
            String firstName = "Michelle";
            String lastName = "McCarthy";
            String email = "113428768@umail.ucc.ie";
            String studentNumber = "113428768";
            /**
             *  Stores the first statement as a string.
             */
            String query1 = "INSERT INTO User(firstname, secondname, "
                           + "email, password)"  
                    + "VALUES('" + firstName+ "','" + lastName +"','" +
                     email +"','" + password +"');"; 
            
           
            /**
             * Stores the second statement as a string.
             */
            String query2 = "INSERT INTO student(student_id,student_number)" +
                             "VALUE( (SELECT user_id FROM `User` WHERE email = '"+ email + "'),  "
                    + "'"+studentNumber+"');";

            /**
             * Creates a new sqlHandler.
             */
            SqlHandler sqlHandler = new SqlHandler();
            /**
             * Runs the SQL statement's using the sqlHandler
             * Stores if the statement was successful or not in variable as
             * a 1 or 0.
             */
            registered1 = sqlHandler.runStatement(query1);
            registered2 = sqlHandler.runStatement(query2);
            /**
             * If both statements have being successfully run,
             * It sets isRegistered to true.
             */
            if(registered1 == 1 && registered2 == 1){
                System.out.println("Registration has been completed. ");
            }
        }catch(SQLException ex) {
            System.out.println("You are not registered");
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }  
        
    }

    /**
     * Test of isUniqueEmailAddress method, of class Register.
     */
    @Ignore
    @Test
    public void testIsUniqueEmailAddress() {
        try{
            
            SqlHandler sqlHandler = new SqlHandler();
            String email = "112428768@umail.ucc.ie";
            
            String query = "SELECT email "
                    + "FROM User "
                    + "WHERE email ='"+email+"';";
          
            ResultSet queryResult;
            queryResult = sqlHandler.runQuery(query);
         
            //returns false if there are no rows in the ResultSet.
            if (!queryResult.isBeforeFirst() && !queryResult.next() ) 
            {
                //sets isUnquie to true because the email address is not in DB
                System.out.println("email is not in database");
            }
            else{
                System.out.println("email is already in database");
            }
        }catch(SQLException ex) {
            Logger.getLogger(Register.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }

    /**
     * Test of sendValidationEmail method, of class Register.
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.security.spec.InvalidKeySpecException
     */
    @Ignore
    @Test
    public void testSendValidationEmail() throws NoSuchAlgorithmException, InvalidKeySpecException {
        System.out.println("sendValidationEmail");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.sendValidationEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of isAllowedEmailAddress method, of class Register.
     */
    @Ignore
    @Test
    public void testIsAllowedEmailAddress() {
        System.out.println("isAllowedEmailAddress");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.isAllowedEmailAddress();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of isValidEmailAddress method, of class Register.
     */
    
    @Test
    public void testIsValidEmailAddress() {
        boolean isEmailVaild;
        try{
            String email;
            email = "112428768@umail.ucc.ie ";
            InternetAddress internetAddress = new InternetAddress(email);
            internetAddress.validate();
            isEmailVaild = true;
        }catch(AddressException e ){
            System.out.println("Error" );
        }
        
    }

    /**
     * Test of validateForm method, of class Register.
     */
    @Ignore
    @Test
    public void testValidateForm() {
        System.out.println("validateForm");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.validateForm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getUserId method, of class Register.
     */
    @Ignore
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Register instance = new Register();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    private String encrypt() throws NoSuchAlgorithmException, InvalidKeySpecException{
       String password1 = "12345";
       String encryptedPassword;
       encryptedPassword = createHash(password1);
       return encryptedPassword;
    }

}

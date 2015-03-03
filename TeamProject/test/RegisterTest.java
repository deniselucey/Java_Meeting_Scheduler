import teamproject.system.Register;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Ignore;


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
     */
    @Test
    public void testRegisterDetailsWithDb() throws Exception {
        System.out.println("registerDetailsWithDb");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.registerDetailsWithDb();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isUniqueEmailAddress method, of class Register.
     */
    @Test
    public void testIsUniqueEmailAddress() {
        System.out.println("isUniqueEmailAddress");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.isUniqueEmailAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendValidationEmail method, of class Register.
     */
    @Test
    public void testSendValidationEmail() throws Exception {
        System.out.println("sendValidationEmail");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.sendValidationEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isAllowedEmailAddress method, of class Register.
     */
    @Test
    public void testIsAllowedEmailAddress() {
        System.out.println("isAllowedEmailAddress");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.isAllowedEmailAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isValidEmailAddress method, of class Register.
     */
    @Test
    public void testIsValidEmailAddress() {
        System.out.println("isValidEmailAddress");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.isValidEmailAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateForm method, of class Register.
     */
    @Test
    public void testValidateForm() {
        System.out.println("validateForm");
        Register instance = new Register();
        boolean expResult = false;
        boolean result = instance.validateForm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstName method, of class Register.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstNameSupplied = "";
        Register instance = new Register();
        instance.setFirstName(firstNameSupplied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastName method, of class Register.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastNameSupplied = "";
        Register instance = new Register();
        instance.setLastName(lastNameSupplied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Register.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String emailSupplied = "";
        Register instance = new Register();
        instance.setEmail(emailSupplied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStudentNumber method, of class Register.
     */
    @Test
    public void testSetStudentNumber() {
        System.out.println("setStudentNumber");
        int studentNumberSupplied = 0;
        Register instance = new Register();
        instance.setStudentNumber(studentNumberSupplied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword1 method, of class Register.
     */
    @Test
    public void testSetPassword1() {
        System.out.println("setPassword1");
        String passwordOneSupplied = "";
        Register instance = new Register();
        instance.setPassword1(passwordOneSupplied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPassword2 method, of class Register.
     */
    @Test
    public void testSetPassword2() {
        System.out.println("setPassword2");
        String passwordTwoSupplied = "";
        Register instance = new Register();
        instance.setPassword2(passwordTwoSupplied);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setErrors method, of class Register.
     */
    @Test
    public void testSetErrors() {
        System.out.println("setErrors");
        String key = "";
        String msg = "";
        Register instance = new Register();
        instance.setErrors(key, msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstName method, of class Register.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Register instance = new Register();
        String expResult = "";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastName method, of class Register.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Register instance = new Register();
        String expResult = "";
        String result = instance.getLastName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Register.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Register instance = new Register();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword1 method, of class Register.
     */
    @Test
    public void testGetPassword1() {
        System.out.println("getPassword1");
        Register instance = new Register();
        String expResult = "";
        String result = instance.getPassword1();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPassword2 method, of class Register.
     */
    @Test
    public void testGetPassword2() {
        System.out.println("getPassword2");
        Register instance = new Register();
        String expResult = "";
        String result = instance.getPassword2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentNumber method, of class Register.
     */
    @Test
    public void testGetStudentNumber() {
        System.out.println("getStudentNumber");
        Register instance = new Register();
        int expResult = 0;
        int result = instance.getStudentNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getErrorMessage method, of class Register.
     */
    @Test
    public void testGetErrorMessage() {
        System.out.println("getErrorMessage");
        String message = "";
        Register instance = new Register();
        String expResult = "";
        String result = instance.getErrorMessage(message);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserId method, of class Register.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        Register instance = new Register();
        int expResult = 0;
        int result = instance.getUserId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    
    
    
}

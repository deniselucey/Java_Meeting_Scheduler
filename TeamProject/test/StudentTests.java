import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import teamproject.user.people.Student;

/**
 * Tests method of the student class
 * @author Denise Lucey - 112700291
 */
public class StudentTests {
    
    public StudentTests() {
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
     * Test of enrollToModule method, of class Student.
     */
    @Test
    public void testEnrollToModule() {
        System.out.println("enrollToModule");
        String module = "";
        String email = "";
        Student instance = new Student();
        boolean expResult = false;
        boolean result = instance.enrollToModule(module, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unEnrollToModule method, of class Student.
     */
    @Test
    public void testUnEnrollToModule() {
        System.out.println("unEnrollToModule");
        String module = "";
        String email = "";
        Student instance = new Student();
        boolean expResult = false;
        boolean result = instance.unEnrollToModule(module, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStudentNo method, of class Student.
     */
    @Test
    public void testGetStudentNo() {
        System.out.println("getStudentNo");
        Student instance = new Student();
        String expResult = "";
        String result = instance.getStudentNo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStudentNo method, of class Student.
     */
    @Test
    public void testSetStudentNo() {
        System.out.println("setStudentNo");
        String studentNo = "";
        Student instance = new Student();
        instance.setStudentNo(studentNo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}


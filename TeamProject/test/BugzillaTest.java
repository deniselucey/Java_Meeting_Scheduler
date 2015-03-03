
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import teamproject.system.Bugzilla;

/**
 * Testing of reporting errors in code to Bugzilla running on server
 * @author Denise Lucey
 */
public class BugzillaTest {
    
    public BugzillaTest() {
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
     * Test of reportBug method, of class Bugzilla.
     */
    @Test
    public void testReportBug() {
        System.out.println("reportBug");
        String bugerror = "";
        boolean expResult = false;
        boolean result = Bugzilla.reportBug(bugerror);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

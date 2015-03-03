import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import teamproject.meeting.Meeting;
import teamproject.user.people.Lecturer;

/**
 * Tests for Lecture class
 * @author Denise Lucey - 112700291
 */
public class LecturerTest {
    
    public LecturerTest() {
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
     * Test of scheduleMeeting method, of class Lecturer.
     */
    @Test
    public void testScheduleMeeting() {
        System.out.println("scheduleMeeting");
        Meeting meeting = null;
        Lecturer instance = new Lecturer();
        boolean expResult = false;
        boolean result = instance.scheduleMeeting(meeting);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scheduleLab method, of class Lecturer.
     */
    @Test
    public void testScheduleLab() {
        System.out.println("scheduleLab");
        Meeting meeting = null;
        Lecturer instance = new Lecturer();
        boolean expResult = false;
        boolean result = instance.scheduleLab(meeting);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}

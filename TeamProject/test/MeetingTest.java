/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import teamproject.meeting.Meeting;
import teamproject.meeting.MeetingPrivacy;
import teamproject.meeting.MeetingType;
import teamproject.meeting.Recurrence;
import teamproject.sql.SqlHandler;

/**
 *
 * @author drgex_000
 */
public class MeetingTest {
    
    public MeetingTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void testCostructor() throws SQLException
    {
       
        Meeting meeting = new Meeting(4);
        System.out.println(meeting);
    }
    
    @Test
    public void testingGettingIDFromDatabaseMeetingInDatabase() throws SQLException
    {
        Meeting meeting = new Meeting(4);
        System.out.println(meeting);
        int i = meeting.getIdFromDatabase();
        assertThat(i , is(equalTo(meeting.getId())));
    }
    
    @Test
    public void testingGettingIDFromDatabaseMeetingNotInDatabase() throws SQLException
    {
//        Meeting meeting = new Meeting(4);
//        System.out.println(meeting);
//        int i = meeting.getIdFromDatabase();
//        assertThat(i , is(equalTo(meeting.getId())));
    }
        
    @Test
    public void TestConstructerAndEquals()
    {
        Meeting meeting = new Meeting("TestMeeting", "Test Description" , 1, LocalDateTime.of(2015, Month.MARCH, 4, 3, 30), LocalDateTime.of(2015, Month.MARCH, 4, 4,15), 
                LocalDate.of(2015, Month.MARCH, 4), "TestLocation", false, Recurrence.NEVER, MeetingPrivacy.PRIVATE, MeetingType.LOW,1);
        String m = meeting.toSQL();

        SqlHandler sh = new SqlHandler();
        int i;
        Meeting meeting1 = null;
        
        try
        {
            sh.runStatement(m);
            i = meeting.getIdFromDatabase();
            meeting1 = new Meeting(i);
        }
        catch (SQLException ex)
        {
            Logger.getLogger(MeetingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        System.out.println(" Meeting \n"+ meeting + "\n" + meeting1 );
        assertThat(meeting1 , is(not(nullValue())));
        assertThat(meeting , is(not(nullValue())));
        assertThat(meeting.equals(meeting1), is(equalTo(true)));
    }
    
    @Test
    public void TestMeetingExpanding()
    {
        Meeting meeting = new Meeting("TestExpandedMeeting", "Test Description" , 1, LocalDateTime.of(2015, Month.MARCH, 1, 3, 30), LocalDateTime.of(2015, Month.MARCH, 1, 4,15), 
                LocalDate.of(2015, Month.NOVEMBER, 20), "TestLocation", false, Recurrence.NEVER, MeetingPrivacy.PRIVATE, MeetingType.LOW,1);
        
       // Recurrence rec = Recurrence.WEEKLY;
       //ArrayList<LocalDateTime> ldts = rec.findDatesInRange(LocalDateTime.of(2015, Month.JANUARY, 31, 8 , 0), LocalDateTime.of(2015, Month.FEBRUARY, 2, 9 , 0), LocalDate.of(2015, Month.DECEMBER, 31), LocalDate.of(2015, Month.FEBRUARY, 1),  LocalDate.of(2015, Month.MARCH, 1));
        
        ArrayList<Meeting> meetings = Meeting.expandMeeting(meeting, LocalDate.of(2015, Month.MARCH, 1), LocalDate.of(2015, Month.MARCH, 31));
    
        for(Meeting m: meetings)
        {
            System.out.println(m.toString());
        }
    }
}

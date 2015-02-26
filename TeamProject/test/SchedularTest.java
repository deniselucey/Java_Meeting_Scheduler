/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.IntStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import teamproject.meeting.Meeting;
import teamproject.meeting.Recurrence;
import teamproject.system.scheduler.Scheduler;
import teamproject.system.scheduler.Scheduler.RunUntilAfterEndRangeException;

/**
 *
 * @author drgex_000
 */
public class SchedularTest {
    
    public SchedularTest() {
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
    public void testLoadMeeting() throws RunUntilAfterEndRangeException, SQLException
    {
        ArrayList<Integer> peopleId_attendees = new ArrayList<>();
        ArrayList<Integer> groupId_attendees = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> peopleId_attendees.add(i));
        IntStream.range(1, 2).forEach(i -> groupId_attendees.add(i));
        
        Meeting meeting = new Meeting("TestMeetingTitle", "description", 1, peopleId_attendees, groupId_attendees, Duration.ofMinutes(60), LocalDate.of(2015, 1, 23), "Location", Recurrence.NEVER, (byte)1,(byte)1);
        Scheduler s = new Scheduler(meeting,LocalDate.of(2014, Month.DECEMBER, 29),LocalDate.of(2015, Month.JANUARY, 30));
        try {
            ArrayList<Meeting> rs = s.loadMeeting();
            
            rs.stream().forEach(m-> System.out.print(m.toString()));
        } catch (SQLException ex) {
            Logger.getLogger(SchedularTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.print(s);
        
    //String title, String description, int hostUserID, ArrayList<Integer> peopleId_attendees, ArrayList<Integer> groupId_attendees, Duration length, LocalDateTime startDateTime, LocalDate runs_until, String location, Recurrence repeatEvery, byte piority, byte privacyId) {
        
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import teamproject.meeting.Meeting;
import teamproject.system.scheduler.timetable.Timetable;

/**
 *
 * @author user
 */
public class TimetableTest {
    
    public TimetableTest() {
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
    public void Test(){
        Duration duration = Duration.between(LocalDate.of(2015, Month.MARCH, 1).atStartOfDay().plus(Duration.ofHours(8)),LocalDateTime.of(2015, Month.MARCH, 1,8,45));
        int daysIndex = (int) duration.toDays();
        int minutes = (int) (duration.toMinutes() - (daysIndex*24*60));
        System.out.println(minutes + " " + daysIndex);
    }
    
    @Test
    public void testConstructor(){
        ArrayList<Meeting> meetings = new ArrayList<>();
        try {
            meetings.add(new Meeting(1));
            meetings.add(new Meeting(2));
            meetings.add(new Meeting(3));
            Timetable timetable = new Timetable(LocalDate.of(2015, Month.JANUARY, 21), LocalDate.of(2015, Month.JANUARY, 28), meetings);
            System.out.println(timetable.toHTML());
            
        } catch (SQLException ex) {
            Logger.getLogger(TimetableTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testConstor() throws SQLException
    {
        Timetable t = new Timetable();
               t.toHTML();
        
    }
}

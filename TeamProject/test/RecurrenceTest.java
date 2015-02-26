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
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import teamproject.meeting.Meeting;
import teamproject.meeting.Recurrence;

/**
 *
 * @author Nigel
 */
public class RecurrenceTest
{
    
    public RecurrenceTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
    }
    
    @After
    public void tearDown()
    {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    
    @Test
    public void testFindRecurrenceByPeroid()
    {
        //uses finctional programming to test all values of Recurrence 
        //get period of each and uses it to search for a value that matches
        for(Recurrence rec : Recurrence.values())
        {
            assertThat(Recurrence.findByPeriod(rec.getPeriod()).getPeriod(),
                is(equalTo(rec.getPeriod())));
        }
    }
    @Test
    public void testFindDateInRange()
    {
        Recurrence rec = Recurrence.WEEKLY;
        ArrayList<LocalDateTime> ldts = rec.findDatesInRange(LocalDateTime.of(2015, Month.JANUARY, 31, 8 , 0), LocalDateTime.of(2015, Month.FEBRUARY, 2, 9 , 0), LocalDate.of(2015, Month.DECEMBER, 31), LocalDate.of(2015, Month.FEBRUARY, 1),  LocalDate.of(2015, Month.MARCH, 1));
        for(LocalDateTime ldt : ldts )
        {
            System.out.println("LocalDateTime : " + ldt);
        }
        System.out.println("meeting between " + ldts.size() );
    }
    
    @Test
    public void TestNonReapeting() throws SQLException
    {
        Meeting m = new Meeting(1);
        System.out.println("Non");
        ArrayList<Meeting> ms = Meeting.expandMeeting(m, LocalDate.of(2015, Month.JANUARY, 1), LocalDate.of(2016, Month.JANUARY, 30));
        for(Meeting a : ms)
        {
            System.out.println(a);
        }
        System.out.println("End");
        
    }
    
    @Test 
    public void TestToHTML()
    {
        System.out.println(Recurrence.toHTMLDropDown());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.Period;
import java.util.Arrays;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
    
}

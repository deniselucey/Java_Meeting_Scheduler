/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Nigel
 */
public class localDateTest
{
    
    public localDateTest()
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
    public void testToSting()
    {
        LocalDate a = LocalDate.now();
        LocalDate b = LocalDate.parse(a.toString());
        assertThat(a.equals(b), is(equalTo(true)));
        System.out.print(a.toString());
    }
    
      @Test
    public void testToEpoch()
    {
        LocalDate a = LocalDate.now();
        LocalDate b = LocalDate.ofEpochDay(a.toEpochDay());
        assertThat(a.equals(b), is(equalTo(true)));
        System.out.print(a.toString());
    }
    
    @Test
    public void testLocalDateTimecompareToLocalDate()
    {
        LocalDateTime end_time = LocalDateTime.of(2012, Month.MARCH, 1,1,0);
        LocalDate runs_until = LocalDate.of(2012, Month.MARCH, 1);
        
        int i = end_time.toLocalDate().compareTo(runs_until);
        int j = end_time.compareTo(runs_until.atStartOfDay());
        
        System.out.println("Value :" + i);
        System.out.println("Value :" + j);
    }
    
        
    @Test
    public void testDaysBetween()
    {
        LocalDate end_time = LocalDate.of(2012, Month.MAY, 1);
        LocalDate runs_until =  LocalDate.of(2012, Month.MAY, 5);
       
        System.out.println("\n daysValue"+  ChronoUnit.DAYS.between(end_time, runs_until)+"\n") ;
    }
    
     @Test
    public void testLocalDateToSQLDate()
    {
        LocalDateTime end_time = LocalDateTime.of(2012, Month.MAY, 1,2,30);
       // end_time.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println("\nDateFormatted " + end_time);
        java.sql.Date  sqlDate;  
        sqlDate = new java.sql.Date(2012,3,1);
        
    }
    @Test
    public void testDurationBetweenLocalDateTime()
    {
       LocalDateTime end_time   = LocalDateTime.of(2012, Month.MAY, 1,2,30);
       LocalDateTime start_time = LocalDateTime.of(2012, Month.MAY, 2,6,45);
       Duration d = Duration.between(end_time, start_time);
       Duration a = Duration.ofDays(2);
       System.out.println("Durationg between " + d);
       System.out.println("no minutes " + a.toMinutes());     
       
    }
}

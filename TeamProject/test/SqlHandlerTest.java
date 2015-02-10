/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertThat;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import teamproject.sql.SqlHandler;

/**
 *
 * @author Nigel
 */
public class SqlHandlerTest
{
    SqlHandler sqlHandler;
    
    public SqlHandlerTest()
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
        sqlHandler = new SqlHandler();
    }
    
    @After
    public void tearDown()
    {
        try
        {
            sqlHandler.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(SqlHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void TestConnection() throws SQLException
    {
        assertThat(sqlHandler.isConnected() , is(equalTo(true)));
    }
     
    @Test
    public void TestRunStatemtAndQuery() throws SQLException
    {
        String courseName = "TEST_COURSE";
        String sqlInsert = "INSERT INTO course VALUES(NULL,'" + courseName+ "' , 1)";
        String sqlSelect = "SELECT * FROM course WHERE course_title = '" + courseName + "'";
        String sqlDelete = "DELETE FROM course WHERE course_title = '" + courseName + "'";
        
        //insert a row into courses
        sqlHandler.runStatement(sqlInsert);
        //retreves rows from courses
        ResultSet result = sqlHandler.runQuery(sqlSelect);
        if(result != null)
        {
            result.next();
        }
        //asserts that the courser tile row is equal to courseName entered in insert statment
        assertThat(result.getString("course_title"), is(equalTo(courseName)));
        
        //deletes test row(s) and check if the results is empty
        sqlHandler.runStatement(sqlDelete);
        result = sqlHandler.runQuery(sqlSelect);
        assertThat(result.isBeforeFirst() , is(equalTo(false)));
    }
   @Test
    public void TestEmptyString() throws SQLException
    {
        
        String sqlInsert = "";
 
        
        //insert a row into courses
        assertThat(sqlHandler.runStatement(sqlInsert), is(equalTo(false)));
        //retreves rows from courses
    
  
    }
}

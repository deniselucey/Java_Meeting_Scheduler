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
        String sqlInsert = "INSERT INTO course VALUES(NULL,'" + courseName+ "' ,'cs4342' , 1)";
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
        assertThat(sqlHandler.runStatement(sqlDelete), is(equalTo(1)));
        result = sqlHandler.runQuery(sqlSelect);
        assertThat(result.isBeforeFirst() , is(equalTo(false)));
    }
   @Test
    public void TestEmptyString() throws SQLException
    {
        
        String sqlInsert = "";
 
        
        //insert a row into courses
        assertThat(sqlHandler.runStatement(sqlInsert), is(equalTo(0)));
        //retreves rows from courses
    
        assertThat(sqlHandler.runQuery(sqlInsert) == null, is(equalTo(true)));
    }
    
    @Test
    public void testPassingBatch()
    {
        String[] sql = {
            "insert into `User` values" +
            "(null,\"password\",  \"TEST\",\"TEST\", \"TEST@TEST.ie\",0,1);",
            "insert into `User` values" +
            "(null,\"password\",  \"TEST2\",\"TEST2\", \"TEST2@TEST2.ie\",0,1);"};
        try
        {
            sqlHandler.runStatement("Delete from `User` where firstname like \"TES%\";");
            sqlHandler.executeBatch(sql);
            
            ResultSet rs = sqlHandler.runQuery("Select * from `User` where firstname like \"TEST%\";");
            
            assertThat(rs.next(), is(equalTo(true)));
            sqlHandler.runStatement("Delete from `User` where firstname like \"TES%\";");
        } catch (SQLException ex)
        {
            Logger.getLogger(SqlHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Test
    public void testFailingBatchRollBack() throws SQLException
    {
        String[] sql = {
            "insert into `User` values" +
            "(null,\"password\", \"TEST\",\"TEST\", \"TEST@TEST.ie\",0,1);",
            "insert into `User` values" +
            "(null,\"password\", \"TEST2\",\"TEST2\", \"TEST@TEST.ie\",0,1);"};
         sqlHandler.runStatement("Delete from `User` where firstname like \"TES%\";");
        try
        {
            sqlHandler.executeBatch(sql); 
        } catch (SQLException ex)
        {
            //Logger.getLogger(SqlHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        ResultSet rs = sqlHandler.runQuery("Select * from `User` where firstname like \"TEST2\";");
        assertThat(rs.next(), is(equalTo(false)));
            
        rs = sqlHandler.runQuery("Select * from `User` where firstname like \"TEST\";");
        assertThat(rs.next(), is(equalTo(true)));
            
        sqlHandler.runStatement("Delete from `User` where firstname like \"TES%\";");
    }
    
    public void testBatctOneEmptyStatment() throws SQLException
    {
        String[] sql = {
            "insert into `User` values" +
            "(null,\"password\", \"TEST\",\"TEST\", \"TEST@TEST.ie\",0,1);",
            "" ,
            "", null};
        try
        {
            sqlHandler.runStatement("Delete from `User` where firstname like \"TES%\";");
           
            sqlHandler.executeBatch(sql);
            
            ResultSet rs = sqlHandler.runQuery("Select * from `User` where firstname like \"TEST2\";");
            assertThat(rs.next(), is(equalTo(false)));
            
            rs = sqlHandler.runQuery("Select * from `User` where firstname like \"TEST\";");
            assertThat(rs.next(), is(equalTo(false)));
            
            sqlHandler.runStatement("Delete from `User` where firstname like \"TEST%\";");
        } catch (SQLException ex)
        {
             Logger.getLogger(SqlHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            sqlHandler.runStatement("Delete from `User` where firstname like \"TEST%\";");
        }
        
    }
    public void testBatct2DiffrentTables() throws SQLException
    {
        String[] sql = {
            "insert into `User` values" +
            "(null,\"password\",  \"TEST\",\"TEST\", \"TEST@TEST.ie\",0,1);",
             
            "insert into meeting values\n" +
"(null,1,\"2015-01-21 12:00:00\",\"2015-01-21 12:00:00\",\"2015-01-21\", \"UNITTEST\", 1, \"that place\", 1,\"D0\"),",
        
        };
        try
        {
            sqlHandler.runStatement("Delete from `User` where firstname like \"TES%\";");
            sqlHandler.runStatement("Delete from `meeting` where meeting-name = \"UNITTEST\";");
            sqlHandler.executeBatch(sql);
            
            
            ResultSet rs = sqlHandler.runQuery("Select * from `User` where firstname like \"TEST\";");
           
            assertThat(rs.next(), is(equalTo(false)));
            
            rs = sqlHandler.runQuery("Select * from `meeting` wheremeeting-name = \"UNITTEST\";");
            assertThat(rs.next(), is(equalTo(false)));
             
          
            sqlHandler.runStatement("Delete from `User` where firstname like \"TES%\";");
            sqlHandler.runStatement("Delete from `meeting` where meeting-name = \"UNITTEST\";");
        } catch (SQLException ex)
        {
             Logger.getLogger(SqlHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            sqlHandler.runStatement("Delete from `User` where firstname like \"TEST%\";");
        }
        
    }
    
    @Test
    public void TestTransaction()
    {
        String sql = "START TRANSACTION;"
                //+ "SET autocommit = 0; "
                + "@result = (SELECT meeting_id FROM meeting WHERE meeting_name = 'fight'); "
                + "INSERT INTO is_attending VALUES"
                + "(@result, 4); "
                + "COMMIT;";
               // + "SET autocommit = 1; ";
        String set = "SET @result = (SELECT meeting_id FROM meeting WHERE meeting_name = \"fight\" LIMIT 1);";

        try {
            sqlHandler.runStatement(sql);
        } catch (SQLException ex) {
            Logger.getLogger(SqlHandlerTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}

package teamproject.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import teamproject.system.Property;
import teamproject.system.SystemSetting;

/**
 * Handler connection to database and the running of queries and statements.
 * use runQuery to execute SELECT Queries.
 * use runStatment for SQL that changes table values.
 * @author Nigel
 */
public class SqlHandler {

    protected Connection connection;
    protected String password;
    protected String user;
    protected String port;
    protected String url;
    protected String name;
    public static final int MAXBATCHCOUNT = 1000;

    //TODO deletecomment
    //sql below create a database user called admin with a password of password 
    //and grants this user all permission on the schedulerdatabase.
    //CREATE USER 'admin'@'localhost' IDENTIFIED BY 'password';
    //GRANT ALL PRIVILEGES ON schedulerdatabase.* TO 'admin'@'localhost' WITH GRANT OPTION;
    public SqlHandler()
    {
        //initilizes(if not allready) and loads settings for database
        SystemSetting.initSystemSetting();

        password = SystemSetting.getProperty(Property.DatabasePassword,null);
        user = SystemSetting.getProperty(Property.DatabaseUser,null);
        port = SystemSetting.getProperty(Property.DatabasePort,null);
        url = SystemSetting.getProperty(Property.DatabaseUrl,null);
        name = SystemSetting.getProperty(Property.DatabaseName,null);

        //links driver to class
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        } 
        catch (ClassNotFoundException ex)
        {
            Logger.getLogger(SqlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        try
        {
            connection = makeConnection();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(SqlHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Connection makeConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:mysql://"+ url + ":" + port + "/" + name, user, password); // "jdbc:mysql://localhost:3306/schedulerdatabase");   
    }

    /**
     * returns true if connection is not null and is not closed.
     * @return true if connection is not null and is not closed.
     * @throws SQLException 
     */
    public boolean isConnected() throws SQLException
    {
        if (connection == null) 
        {
            return false;
        }

        if (connection.isClosed()) 
        {
            return false;
        }
        return true;
    }


    /**
     * Close connection to database.
     * @throws SQLException 
     */
    public void close() throws SQLException
    {
        connection.close();
    }

    /**
     * Used to execute SELECT statements.
     * @param sql
     * @return Set of all rows that match Query.
     * @throws java.sql.SQLException
     */
    public ResultSet runQuery(String sql) throws SQLException
    {
        if(!sql.equals(""))
        {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        }
        return null;
    }

    /**
     * Used to execute table and data modifying statements
     * @param sql
     */
    public int runStatement(String sql) throws SQLException
    {
        if(!sql.equals(""))
        {
            Statement statement = connection.createStatement();

            statement.execute(sql);
            return statement.getUpdateCount();
        }
        return 0;
    }

     /**
     * Method use to execute a large batch of statements(>1000). 
     * @param sql array of SQL statements to execute. Length > 1000
     * @return an array of update counts containing one element for each command in the batch. The elements of the array are ordered according to the order in which commands were added to the batch.
     * @throws SQLException 
     */
    public int[] executeBatch(String[] sql) throws SQLException
    {
        Statement statement =this.connection.createStatement();

        for(String s : sql)
        {
            statement.addBatch(s);
        }
        return statement.executeBatch();
    }

    /**
     * Method use to execute a large batch of statements(>1000). 
     * @param sql array of SQL statements to execute. Length > 1000
     * @return an array of update counts containing one element for each command in the batch. The elements of the array are ordered according to the order in which commands were added to the batch.
     * @throws SQLException 
     */
    public int[] executeLongBatch(String[] sql) throws SQLException
    {
        if(sql.length <= MAXBATCHCOUNT)
        {
           return executeBatch(sql);
        }
        Statement statement =this.connection.createStatement();
        int counter = 0;
        int[] results = new int[sql.length];

        for(String s : sql)
        {
            statement.addBatch(s);
            if(++counter % MAXBATCHCOUNT == 0)
            {

                System.arraycopy(statement.executeBatch(), 0, results, counter - MAXBATCHCOUNT, MAXBATCHCOUNT);
            }
        }

        int[] last = statement.executeBatch();
        System.arraycopy(last, 0, results, (counter / MAXBATCHCOUNT)*MAXBATCHCOUNT, last.length);

        return results;
    }
}
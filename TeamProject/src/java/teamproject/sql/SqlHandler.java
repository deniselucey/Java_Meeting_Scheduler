package teamproject.sql;

import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import teamproject.system.Property;
import teamproject.system.SystemSetting;

/**
 * Handler connection to database and the running of queries and statements.
 * use runQuery to execute SELECT Queries.
 * use runStatment for SQL that changes table values.
 * @author Nigel
 */
public class SqlHandler {

	private ArrayList<String> sqlQueres;
	private Connection connection;
	private ArrayList<PreparedStatement> statements;

        private String password;
        private String user;
        private String port;
        private String url;
        private String name;
        
        
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
	public boolean runStatement(String sql) throws SQLException
	{
            if(!sql.equals(""))
            {
                Statement statement = connection.createStatement();
                
                return statement.execute(sql);
            }
            return false;
	}
        
        /**
	 * 
	 * @param query
	 */
	public boolean addQuery(String query)
	{
		// TODO - implement SqlHandler.addQuery
		throw new UnsupportedOperationException();
	}
	
        /**
	 * 
	 * @param query
	 */
	public boolean removeQuery(String query)
	{
		// TODO - implement SqlHandler.rmoveQuery
		throw new UnsupportedOperationException();
	}

	public boolean clearQuery()
	{
		// TODO - implement SqlHandler.clearQuery
		throw new UnsupportedOperationException();
	}
        	

	public boolean executeBatch()
	{
		// TODO - implement SqlHandler.executeBatch
		throw new UnsupportedOperationException();
	}

}
package teamproject.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class SqlHandler {

	private ArrayList<String> sqlQueres;
	private Connection connecting;
	private ArrayList<PreparedStatement> statement;

	/**
	 * 
	 * @param query
	 */
	public boolean addQuery(String query)
	{
		// TODO - implement SqlHandler.addQuery
		throw new UnsupportedOperationException();
	}

	public boolean executeBatch()
	{
		// TODO - implement SqlHandler.executeBatch
		throw new UnsupportedOperationException();
	}

	public void close()
	{
		// TODO - implement SqlHandler.close
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param statement
	 */
	public ResultSet runStatment(String statement)
	{
		// TODO - implement SqlHandler.runStatment
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param query
	 */
	public boolean rmoveQuery(String query)
	{
		// TODO - implement SqlHandler.rmoveQuery
		throw new UnsupportedOperationException();
	}

	public boolean clearQuery()
	{
		// TODO - implement SqlHandler.clearQuery
		throw new UnsupportedOperationException();
	}

}
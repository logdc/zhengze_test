package com.derby.test1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by applec on 15/4/28.
 */
public class FirstTestDBConn {
	private String framework = "embedded";
	private static String protocol = "jdbc:derby:";

	public static void main(String[] args) {
		Connection conn = null;
		ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
		PreparedStatement psInsert;
		PreparedStatement psUpdate;
		Statement s;
		ResultSet rs = null;
		try
		{

			String dbName = "MyDbTest"; // the name of the database

			conn = DriverManager.getConnection(protocol + dbName + ";create=true");

			System.out.println("Connected to and created database " + dbName);

			conn.setAutoCommit(false);

            /* Creating a statement object that we can use for running various
             * SQL statements commands against the database.*/
			s = conn.createStatement();
			statements.add(s);

			// We create a table...
			s.execute("create table location(num int, addr varchar(40))");
			System.out.println("Created table location");


			psInsert = conn.prepareStatement(
					"insert into location values (?, ?)");
			statements.add(psInsert);

			psInsert.setInt(1, 1956);
			psInsert.setString(2, "Webster St.");
			psInsert.executeUpdate();
			System.out.println("Inserted 1956 Webster");

			psInsert.setInt(1, 1910);
			psInsert.setString(2, "Union St.");
			psInsert.executeUpdate();
			System.out.println("Inserted 1910 Union");

			// Let's update some rows as well...

			// parameter 1 and 3 are num (int), parameter 2 is addr (varchar)
			psUpdate = conn.prepareStatement(
					"update location set num=?, addr=? where num=?");
			statements.add(psUpdate);

			psUpdate.setInt(1, 180);
			psUpdate.setString(2, "Grand Ave.");
			psUpdate.setInt(3, 1956);
			psUpdate.executeUpdate();
			System.out.println("Updated 1956 Webster to 180 Grand");

			psUpdate.setInt(1, 300);
			psUpdate.setString(2, "Lakeshore Ave.");
			psUpdate.setInt(3, 180);
			psUpdate.executeUpdate();
			System.out.println("Updated 180 Grand to 300 Lakeshore");

			rs = s.executeQuery(
					"SELECT num, addr FROM location ORDER BY num");
			System.out.println("Show query ....................................");
			while (rs.next()){
				System.out.print(rs.getInt(1));
				System.out.print(", ");
				System.out.println(rs.getString(2));
			}
			System.out.println("Show query .................................... end");

			// delete the table
			s.execute("drop table location");
			System.out.println("Dropped table location");

			conn.commit();
			System.out.println("Committed the transaction");
		}
		catch (SQLException sqle)
		{
			printSQLException(sqle);
		} finally {
			// release all open resources to avoid unnecessary memory usage

			// ResultSet
			try {
				if (rs != null) {
					rs.close();
					rs = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}

			// Statements and PreparedStatements
			int i = 0;
			while (!statements.isEmpty()) {
				// PreparedStatement extend Statement
				Statement st = (Statement)statements.remove(i);
				try {
					if (st != null) {
						st.close();
						st = null;
					}
				} catch (SQLException sqle) {
					printSQLException(sqle);
				}
			}

			//Connection
			try {
				if (conn != null) {
					conn.close();
					conn = null;
				}
			} catch (SQLException sqle) {
				printSQLException(sqle);
			}
		}
	}
	public static void printSQLException(SQLException e)
	{
		// Unwraps the entire exception chain to unveil the real cause of the
		// Exception.
		while (e != null)
		{
			System.err.println("\n----- SQLException -----");
			System.err.println("  SQL State:  " + e.getSQLState());
			System.err.println("  Error Code: " + e.getErrorCode());
			System.err.println("  Message:    " + e.getMessage());
			// for stack traces, refer to derby.log or uncomment this:
			//e.printStackTrace(System.err);
			e = e.getNextException();
		}
	}

}

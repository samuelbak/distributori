package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DbManager {
	private static String host = "127.0.0.1";
	private static String dbName = "distributori";
	private static String username = "root";
	private static String password = "";
	private static Connection connect = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
	
	
	public static boolean sendQuery(String query){
		
		return true;
	}
	public static void databaseConnect() throws Exception {
        try {
        	// This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB
            connect = DriverManager
                      .getConnection("jdbc:mysql://"+host+"/"+dbName+"?"
                                     + "user="+username+"&password="+password);

            // Statements allow to issue SQL queries to the database
            statement = connect.createStatement();
            // Result set get the result of the SQL query
            //resultSet = statement.executeQuery("SELECT * FROM impianti");
        } catch(Exception e){
        	e.printStackTrace();
        } finally{
        	close();
        }

	}
	
	private static void close() {
		try {
			if (resultSet != null) {
				resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
		} catch (Exception e) {
			e.printStackTrace();
        }
	}
}

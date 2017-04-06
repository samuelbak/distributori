package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseConnector extends Thread{
	
	private Connection connectionToDB;
	private Statement queryDataBase;
	private String user;
	private String password;
	private String host;
	private String dbName;
	private Integer port;
	
	public DatabaseConnector() throws SQLException{
		this.host = DatabaseSetup.hostName;
		this.dbName = DatabaseSetup.dataBaseName;
		this.user = DatabaseSetup.userName;
		this.password = DatabaseSetup.userPassword;
		this.port = DatabaseSetup.port;
		String url = "jdbc:mysql://"+this.host+":"+this.port+"/"+this.dbName;
		this.connectionToDB = DriverManager.getConnection(url, this.user, this.password);
		this.queryDataBase = connectionToDB.createStatement();
	}
	
	public ResultSet query(String query) throws SQLException{
		return queryDataBase.executeQuery(query); 
	}
	public Boolean queryStatement(String query) throws SQLException{
		return queryDataBase.execute(query); 
	}
	
	public boolean isConnected(){
		try{
			if(connectionToDB == null)
				return false;
			return (!connectionToDB.isClosed());
		}catch(Exception e){return false;}
	}
	
	public void close() throws SQLException{
		queryDataBase.close();
		connectionToDB.close();
	}
	
	public String getVersion() throws SQLException{
		ResultSet rs = this.query("select version()");
		if (rs.next())
            return (rs.getString(1));
		return "";
	}
	
	public ResultSet getTableNames() throws SQLException{
		ResultSet result = this.query("show tables");
		return result;
	}
}
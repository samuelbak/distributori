package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.sql.SQLException;
import java.util.Scanner;

public class CsvManagement {
	
	public static void downloadFile(String urlStr, String file) throws IOException {
        URL url = new URL(urlStr);
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
        fos.close();
        rbc.close();
    }
	
	public static void csvParser(String file, String delimiter) throws FileNotFoundException{
		Scanner scanner = new Scanner(new File(file));
	    scanner.useDelimiter(delimiter);
	    while(scanner.hasNext()){
	    	System.out.print(scanner.next()+"|");
	    }
	    scanner.close();
	}
	
	public static void writePlantsToDb(String file, String delimiter) throws FileNotFoundException, SQLException{
		DatabaseConnector db = new DatabaseConnector();
		Scanner scanner = new Scanner(new File(file));
		scanner.nextLine();
		scanner.nextLine();		
	    while(scanner.hasNextLine()){
	    	String line = scanner.nextLine();
	    	String[] values = line.split(delimiter);
	    	String query = "INSERT INTO impianti VALUES "+createValues(values);
	    	db.queryStatement(query);
	    }
	    scanner.close();
	}
	
	public static void writePricesToDb(String file, String delimiter) throws FileNotFoundException{
		Integer index = 1;
		DatabaseConnector db = null;
		try {
			db = new DatabaseConnector();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Scanner scanner = new Scanner(new File(file));
		scanner.nextLine();
		scanner.nextLine();		
	    while(scanner.hasNextLine()){
	    	String line = scanner.nextLine();
	    	String[] values = line.split(delimiter);
	    	String query = "INSERT INTO prezzi VALUES "+createValues(values, index++);
	    	try {
				db.queryStatement(query);
			} catch (SQLException e) {
				System.out.println(index+", "+values[0]);
			}
	    }
	    scanner.close();
	}
	
	public static void clearDb() throws SQLException{
		DatabaseConnector db = new DatabaseConnector();
		String query = "SET FOREIGN_KEY_CHECKS = 0";
		db.queryStatement(query);
		query = "TRUNCATE TABLE prezzi";
		db.queryStatement(query);
		query = "TRUNCATE TABLE impianti";
		db.queryStatement(query);
		query = "SET FOREIGN_KEY_CHECKS = 1";
		db.queryStatement(query);
	}
	
	public static String createValues(String[] values){
		String retString = "('";
		for(int i=0;i<(values.length-1);i++){
			retString += nascondiApostrofo(values[i])+"', '";
		}
		retString += nascondiApostrofo(values[values.length-1])+"')";
		return retString;
	}
	
	public static String createValues(String[] values, Integer index){
		String retString = "('"+index+"', '";
		for(int i=0;i<(values.length-1);i++){
			retString += nascondiApostrofo(values[i])+"', '";
		}
		retString += nascondiApostrofo(values[values.length-1])+"')";
		return retString;
	}
	
	public static String nascondiApostrofo(String text){
		return text.replace("'","\\\'");
	}

}

package src;

import java.io.IOException;
import java.sql.SQLException;
import util.CsvManagement;

public class Distributori {

	public static void main(String[] args) {

		try {
			System.out.println("Clearing database...");
			CsvManagement.clearDb();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			System.out.println("Downloading plants...");
			CsvManagement.downloadFile("http://www.sviluppoeconomico.gov.it/images/exportCSV/anagrafica_impianti_attivi.csv", "./impianti.csv");
			System.out.println("Downloading prices...");
			CsvManagement.downloadFile("http://www.sviluppoeconomico.gov.it/images/exportCSV/prezzo_alle_8.csv", "./prezzi.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		try {
			System.out.println("Writing plants to db...");
			CsvManagement.writePlantsToDb("./impianti.csv", ";");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Writing prices to db...");
			CsvManagement.writePricesToDb("./prezzi.csv", ";");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Done!");
	}

}

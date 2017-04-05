package src;

import java.io.FileNotFoundException;
import java.io.IOException;

import util.CsvManagement;
import util.DbManager;

public class Distributori {

	public static void main(String[] args) {
		try {
			CsvManagement.downloadFile("http://www.sviluppoeconomico.gov.it/images/exportCSV/prezzo_alle_8.csv", "./prezzi.csv");
			CsvManagement.downloadFile("http://www.sviluppoeconomico.gov.it/images/exportCSV/anagrafica_impianti_attivi.csv", "./impianti.csv");
		} catch (IOException e) {
			e.printStackTrace();
		}	
		try {
			CsvManagement.csvParser("./prezzi.csv", ";");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		DbManager.sendQuery("");
	}

}

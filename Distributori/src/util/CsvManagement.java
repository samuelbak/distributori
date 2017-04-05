package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
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

}

package update;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;

class UpdateManager {

	public static void main(String[] args) {
		
		UpdateWizard.createWizard();
		System.out.println("Hello World");
		
		/*
		try {
			URL website = new URL("https://www.perfcake.org/download/perfcake-6.1-bin.zip");
			System.out.println("You are downloading the file from " + website);
			
			ReadableByteChannel rbc = Channels.newChannel(website.openStream());
			Scanner reader = new Scanner(System.in);
			System.out.print("Please specify the file name: ");
			String fileName = reader.next();
			reader.close();
			
			FileOutputStream fos = new FileOutputStream(fileName + ".zip");
			System.out.println("The file is downloading....");
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("The download has finished!");
		*/
	}
	

}

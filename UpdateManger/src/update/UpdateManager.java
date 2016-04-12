package update;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class UpdateManager {

	public UpdateManager() {

	}

	public void startDownload(String filePath, JProgressBar bar) {
		Runnable updateThread = new Runnable() { 
			public void run() {
				try {
					URL website = new URL("https://www.perfcake.org/download/perfcake-6.1-bin.zip");
					HttpURLConnection httpConnection = (HttpURLConnection) (website.openConnection());
					long completeFileSize = httpConnection.getContentLengthLong();
					
					
					BufferedInputStream in = new BufferedInputStream(httpConnection.getInputStream());
					FileOutputStream fos = new FileOutputStream(new File(filePath + "/PerfCake.zip"));
					BufferedOutputStream bos = new BufferedOutputStream(fos, 1024);
					
					byte[] data = new byte[1024];
					long downloadedFileSize = 0; 
					int x = 0; 
					
					while((x = in.read(data, 0, 1024)) >= 0) {
						downloadedFileSize += x;
						
						final int currentProgress = (int) ((((double) downloadedFileSize) / 
								(double) completeFileSize) * 100000d);
						
						// Update progress bar
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								bar.setValue(currentProgress);
							}
							
						});
						
						bos.write(data, 0, x);
					}
					bos.close();
					in.close();
					
				} catch (FileNotFoundException e) { 
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
					
			}
		};
		new Thread(updateThread).start();
	}

}

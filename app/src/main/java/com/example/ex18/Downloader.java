package com.example.ex18;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import android.app.IntentService;
import android.content.Intent;

public class Downloader extends IntentService {

	public Downloader() {
		super(Downloader.class.getName());
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		String source = intent.getStringExtra("source");
		String destination = intent.getStringExtra("destination");
		downloadFile(source, destination);
	}
	
	private void downloadFile(String sourcePath, String outputPath){
		try {
			URL url = new URL(sourcePath);
			URLConnection urlConn = url.openConnection();
			InputStream in = urlConn.getInputStream();
			FileOutputStream fos = new  FileOutputStream(outputPath);
			int fileLength = urlConn.getContentLength();
			byte[] data = new byte[1024];
			int count=0, total=0;
			while ((count = in.read(data)) != -1) {
                total += count;
                // publishing the progress....
//                if (fileLength > 0) // only if total length is known
//                    publishProgress((int) (total * 100 / fileLength));
                fos.write(data, 0, count);
            }
			fos.flush();
			in.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package com.jagadish.download.manager;

import java.awt.FlowLayout;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ArrayBlockingQueue;

import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.jagadish.threadPool.MyThreadPoolExecutor;

public class DownloadManager {
	
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout(4));
        frame.setSize(300, 400);
        frame.setVisible(true);
        
        Downloader d1 =new Downloader(frame);
        Downloader d2 =new Downloader(frame);
        Downloader d3 =new Downloader(frame);
        Downloader d4 =new Downloader(frame);
        Downloader d5 =new Downloader(frame);
        Downloader d6 =new Downloader(frame);
        
        MyThreadPoolExecutor executor = new MyThreadPoolExecutor(5, new ArrayBlockingQueue<Runnable>(5));
        executor.execute(d1);
        executor.execute(d2);
        executor.execute(d3);
        executor.execute(d4);
        executor.execute(d5);
        executor.execute(d6);
        
	}

}

class Downloader implements Runnable {
	
	final JProgressBar jProgressBar;
    
	public Downloader(JFrame frame) {
		jProgressBar = new JProgressBar();
        jProgressBar.setMaximum(100000);
        frame.add(jProgressBar);
	}
	

	@Override
	public void run() {
		try {

			URL url = new URL(
					"http://www.it.griet.ac.in/wp-content/uploads/2014/08/UNIT-V_QA.pdf");
			HttpURLConnection httpConnection = (HttpURLConnection) (url
					.openConnection());
			long completeFileSize = httpConnection.getContentLength();

			java.io.BufferedInputStream in = new java.io.BufferedInputStream(
					httpConnection.getInputStream());
			java.io.FileOutputStream fos = new java.io.FileOutputStream(
					"Thread-id"+Thread.currentThread().getId()+".pdf");
			java.io.BufferedOutputStream bout = new BufferedOutputStream(fos,
					1024);
			byte[] data = new byte[1024];
			long downloadedFileSize = 0;
			int x = 0;
			while ((x = in.read(data, 0, 1024)) >= 0) {
				downloadedFileSize += x;

				// calculate progress
				final int currentProgress = (int) ((((double) downloadedFileSize) / ((double) completeFileSize)) * 100000d);

				// update progress bar
				SwingUtilities.invokeLater(new Runnable() {

					@Override
					public void run() {
						jProgressBar.setValue(currentProgress);
					}
				});

				bout.write(data, 0, x);
			}
			bout.close();
			in.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}

	}
	
}

/**
 * Modified from http://www.itcuties.com/java/how-to-speed-up-applications-using-threads-in-java/
 */
package com.itcuties.samples;

public class DownloaderApp {

	public static void main(String[] args) {
		// Images URLs
		String[] filesToDownload = new String[] {
				"http://gutenberg.net.au/ebooks01/0100011.txt",
				"http://gutenberg.net.au/ebooks02/0200201.txt",
				"http://gutenberg.net.au/ebooks/fr100073.txt",
				"http://gutenberg.net.au/ebooks/fr100272.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt",
				"http://gutenberg.net.au/ebooks/c00052.txt"
		}; 
		
		
		try {
			int nameIndex 	= 0;
			long startTime 	= 0;
			long endTime 	= 0;
			
			// Download images in this thread
			System.out.println("Downloading "+filesToDownload.length+" files with single thread");
			startTime = System.currentTimeMillis();
			for (String url: filesToDownload) {
				new FileDownloader(url,  "single" + (nameIndex++) + ".txt").download();
			}
			endTime = System.currentTimeMillis();
			System.out.println("\tdownload time: " + (endTime-startTime)+" ms");
			
			// Download images in multiple threads
			nameIndex 	= 0;
			Lock lock = new Lock();	// A lock object to synchronize threads on it.
			System.out.println("Downloading "+filesToDownload.length+" files with multiple threads");
			startTime = System.currentTimeMillis();
			for (String url: filesToDownload) {
				DownloadThread dt = new DownloadThread(url, "multiple" + (nameIndex++) + ".txt", lock);
				dt.start();	// Start download in another thread
			}
			
			// Wait here for all the threads to end
			while (lock.getRunningThreadsNumber() > 0)
				synchronized (lock) {
					lock.wait();
				}
			
			endTime = System.currentTimeMillis();
			System.out.println("\tdownload time: " + (endTime-startTime)+" ms");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

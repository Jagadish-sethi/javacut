package com.jagadish.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.jagadish.chess.domain.ChessGame;
import com.jagadish.concurrent.MyConcurrentCounter;
import com.jagadish.concurrent.MyConcurrentDemo;
import com.jagadish.concurrent.MyConcurrentHashMap;
import com.jagadish.download.manager.DownloadManager;
import com.jagadish.externalsort.ExternalSort;
import com.jagadish.findlargestnumbers.FindLargest;
import com.jagadish.genericObjectPool.MyPool;
import com.jagadish.mergesort.MergeSort;
import com.jagadish.producerConsumer.NProducerConsumer;
import com.jagadish.producerConsumer.ProducerConsumer;
import com.jagadish.sortMapbyValues.SortHashMap;
import com.jagadish.threadPool.MyThreadPoolExecutor;

public class Main {
	
	static BufferedReader br = new BufferedReader (new InputStreamReader(System.in));
	
	public static void main(String[] args) {
		
		int option = 0;
		
		while(option!=15){
			Main.readString("Press Enter key to Continue:");
			System.out.println("\n======================Problem Statment====================================\n");
			System.out.println("1. Design a concurrent Counter");
			System.out.println("2. Implement cowntDownLatch/cyclicBarrier functionality using user defined cowntDownLatch/cyclicBarrier");
			System.out.println("3. Write program to find largest and second largest element in an unsorted array");
			System.out.println("4. Implement GenericObjectPool");
			System.out.println("5. Implement Producer Consumer problem using blocking queue & using wait and notify");
			System.out.println("6. How do you implement producer consumer problem where there is ten producers and ten consumers");
			System.out.println("7. Sort hash map by values");
			System.out.println("8. Implement Thread pool or executor service");
			System.out.println("9. Implement Merge sort");
			System.out.println("10. Chess game");
			System.out.println("11. Implement Concurrent HashMap");
			System.out.println("12. Download Manager");
			System.out.println("13. External Sort");
			System.out.println("15. To Quit");
			option = readInteger("Please enter the corresponding number to perform the mapped operation : ");
			
			switch (option) {
			case 1: MyConcurrentCounter.main(null);	break;
			case 2: MyConcurrentDemo.main(null);	break;
			case 3: FindLargest.main(null);			break;
			case 4: MyPool.main(null);				break;
			case 5: ProducerConsumer.main(null);	break;
			case 6: NProducerConsumer.main(null);	break;
			case 7: SortHashMap.main(null);			break;
			case 8: MyThreadPoolExecutor.main(null);break;
			case 9: MergeSort.main(null);			break;
			case 10: ChessGame.main(null);			break;
			case 11: MyConcurrentHashMap.main(null);break;
			case 12: DownloadManager.main(null);break;
			case 13: ExternalSort.main(null);break;
			default:
				break;
			}
		}
		
		
	}
	
	public static String readString(String question) {
		System.out.print("\n"+question);
		String str = null;
		try {
			str=br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	public static char readChar(String question) {
		System.out.print("\n"+question);
		char c = '\0';
		try {
			c=(char)br.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
	
	public static int readInteger(String question) {
		System.out.print("\n"+question);
		int value = 0;
		try {
			value=Integer.parseInt(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}
	
	public static long readLong(String question) {
		System.out.print("\n"+question);
		long value = 0;
		try {
			value=Long.parseLong(br.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return value;
	}

}

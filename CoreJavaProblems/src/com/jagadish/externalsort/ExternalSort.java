package com.jagadish.externalsort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class ExternalSort {
	
	
	public void externalSort(){
		
	}
	
	public static void main(String[] args) {
		File inputFile = new File("FileTobeSorted.txt");
		File outputFile = new File("FileInSorted.txt");
		
		
		
		System.out.println(inputFile.exists());
		System.out.println(inputFile.length());
		List<File> files = sortInBatch(inputFile,5);
		
		try {
			mergeFiles(files,outputFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private static void mergeFiles(List<File> files, File outputFile) throws IOException {
		// TODO Auto-generated method stub
		PriorityQueue<FileBuffer> queue = new PriorityQueue<>(files.size(),new Comparator<FileBuffer>() {
			@Override
			public int compare(FileBuffer o1, FileBuffer o2) {
				return o1.peek().compareTo(o2.peek());
			}
		});
		
		for (File file : files) {
			queue.add(new FileBuffer(file));
		}
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		while(!queue.isEmpty()){
			FileBuffer fb = queue.poll();
			
			String value = fb.poll();
			
			bw.write(value);
			bw.write("\n");
			
			if(fb.isEmpty){
				fb.br.close();
				fb.file.delete();
			}else{
				queue.add(fb);
			}
			
		}
		bw.close();
		
	}

	private static List<File> sortInBatch(File inputFile, int i) {
		List<File> files =new ArrayList<>();
		long blocksize = inputFile.length()/i;
		try {
			FileReader fr = new FileReader(inputFile);
			BufferedReader br = new BufferedReader(fr);
			String line="";
			int count=0;
			while(line!=null){
				List<String> templist = new ArrayList<String>();
				while(count<blocksize && (line=br.readLine())!=null){
				System.out.println(line);
				templist.add(line);
				count+=line.length()+2;
				}
				count = 0;
				files.add(sortAndSave(templist));
			}
			System.out.println(count);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return files;
		
	}

	private static File sortAndSave(List<String> templist) {
		// TODO Auto-generated method stub
		Collections.sort(templist);
		File newtmpfile = null;
		try {
			newtmpfile = File.createTempFile("sortInBatch", "flatfile");
		
        newtmpfile.deleteOnExit();
		BufferedWriter fbw = new BufferedWriter(new FileWriter(newtmpfile));
		
		for (String string : templist) {
			fbw.write(string);
			fbw.write("\n");
		}
		fbw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newtmpfile;
	}

}

class FileBuffer {
	
	File file;
	String currentValue;
	BufferedReader br;
	boolean isEmpty;
	
	public FileBuffer(File file) throws IOException {
		this.file=file;
		br = new BufferedReader(new FileReader(file));
		currentValue=null;
		load();
	}

	private void load() throws IOException {
		currentValue = br.readLine();
		if(currentValue==null){
			isEmpty = true;
			currentValue=null;
		}else{
			isEmpty = false;
		}
		
		
	}
	
	public String peek(){
		return currentValue;
	}
	
	public String poll() throws IOException{
		String topelement = peek();
		load();
		return topelement;
	}
}

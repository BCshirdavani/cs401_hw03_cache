package LRU_cache;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TextFileStrategy implements GetDataStrategy {
	String filePath;
	File file;
	Scanner sc;

	//	CONSTRUCTOR
	public TextFileStrategy(String path){
		this.filePath = path;
		this.file = new File(path);
		try {
			this.sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 	Find value at KEY
	//	for a comma separated .txt file
	@Override
	public Object getData(int key) {
		Scanner sc = null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		int currentKey = -99;
		String line;
		String[] lineElements;
		Object value = null;
		//TODO: implement file.txt key value search and extract
		while( (sc.hasNextLine()) && (currentKey != key) ){
			line = sc.nextLine();
			lineElements = line.split(",");
			currentKey = Integer.parseInt(lineElements[0]);
			value = lineElements[1];
			System.out.println("\t\tcKey: " + currentKey + ", value: " + value);
		}
		System.out.println("\t\t\texinting scanner loop");

//		sc.remove();
		//	if not found
		if (currentKey != key){
			System.out.println("TextFileStrategy cannot find key " + key);
//			sc.close();
			return null;
		}
		//	if found
		else{
			System.out.println("TextFileStrategy found key.");
//			sc.close();
			return value;
		}

	}





}

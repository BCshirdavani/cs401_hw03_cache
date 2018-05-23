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
		}
		//	if not found
		if (currentKey != key){
			return null;
		}
		//	if found
		else{
			return value;
		}
	}





}

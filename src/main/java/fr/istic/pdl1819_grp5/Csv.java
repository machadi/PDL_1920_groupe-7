package fr.istic.pdl1819_grp5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Csv extends FileMatrix{

	public Csv(String name){
		super(name);
	}
	
	
	public void saveCsvFile(FileMatrix file) {
		
	File csv = new File("folder/"+getName()+".csv");
	        FileWriter fr = null;
	        try { fr = new FileWriter(csv);
	            fr.write(file.getText());
	        } catch (IOException e) {
	            e.printStackTrace();
	        }finally{
	            //close resources
	            try {
	                fr.close();
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
}
}


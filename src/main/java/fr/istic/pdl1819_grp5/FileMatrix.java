package fr.istic.pdl1819_grp5;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;




public class FileMatrix {

	
	private String name;
	
	private String text="";


	public FileMatrix(String name){
		this.name=name;
	}

	
	public String getName() {
		return name;
	}

	public String getText() {
		return text;
	}

    public void setName(String name) {
        this.name = name;
    }

    public void setText(String text) {
        this.text = text;
    }

	public File saveCsv(String path) throws IOException {
		File csv= new File(path+"/"+getName()+".csv");
		FileWriter fr = new FileWriter(csv);
		fr.write(this.getText());
		fr.close();
		return csv;
	}
}


package fr.istic.pdl1819_grp5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * CSV File that contain the translation of a HTML or Wikitext table in CSV
 */
public class FileMatrix {

	private String name;
	private String text;

	/**
	 * @param name FileMatrix
	 */
	public FileMatrix(String name){
		this.name=name;
	}

	/**
	 * To obtain the name of FileMatrix
	 * @return name of FileMatix
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the contents of FileMatrix
	 */
	public String getText() {
		return text;
	}

	/**
	 * Take a string in parameter and assigns it as the name of FileMatrix
	 * @param name of FileMatrix
	 */
    public void setName(String name) {
        this.name = name;
    }


    public void setText(String text) {
        this.text = text;
    }

	/**
	 * Creates a CSV file at the location specified in parameter,
	 * write the result inside
	 * return the file
	 * @param csvPath , path where to save File Matrix
	 * @return the file containing the result of conversion in CSV
	 * @throws IOException
	 */
	public File saveCsv(String csvPath) throws IOException {
		File csv= new File(csvPath);
		FileWriter fr = new FileWriter(csv);
		fr.write(this.getText());
		fr.close();
		return csv;

	}
}


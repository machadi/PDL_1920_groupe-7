package fr.istic.pdl1819_grp5;




public class ConverterToCsv implements Converter
{

	public ConverterToCsv(){
	}


	public FileMatrix convert(String text) {
		return new Csv();
	}

}


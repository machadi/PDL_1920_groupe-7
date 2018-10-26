package fr.istic.pdl1819_grp5;




public class ConverterToCsv implements Converter
{

	public ConverterToCsv(){
	}


	public FileMatrix convert(String text) {
		return new Csv();
	}

	public FileMatrix convertFromHtml(String text) {
		return new Csv();
	}

	public FileMatrix convertFromWikitext(String text) {
		return new Csv();
	}
}


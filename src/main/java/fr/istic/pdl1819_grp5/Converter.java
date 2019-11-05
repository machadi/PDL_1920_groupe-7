package fr.istic.pdl1819_grp5;


import java.io.IOException;
import java.util.Set;

import org.jsoup.nodes.Element;


public  interface Converter
{
	/**
	 * @param link of the page where to get the tables in HTML to convert to CSV
	 * @throws IOException can caused by the implementation in class ConverterToCSV
	 */
	Set<FileMatrix> convertFromHtml(String link) throws IOException;

	/**
	 * @param link of the page where we get the tables in Wikitext to convert to CSV
	 */
	Set<FileMatrix> convertFromWikitext(String link) ;

}


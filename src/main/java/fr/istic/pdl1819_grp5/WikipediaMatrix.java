package fr.istic.pdl1819_grp5;


import java.util.HashSet;
import java.util.Set;

public class WikipediaMatrix
{

	
	private Set<UrlMatrix> urlsMatrix;

	private Converter converter;

	private ExtractType extractType;

	public WikipediaMatrix(){

		this.urlsMatrix = new HashSet<UrlMatrix>();
		converter = new ConverterToCsv();
		this.extractType =  ExtractType.HTML; // Default extraction

	}

	/*
	*@return
	* set of urlMatrix fill to csv.
	*/
	public Set<UrlMatrix> getConvertResult() {

		return null;
	}

	public Set<UrlMatrix> getUrlsMatrix() {
		return urlsMatrix;
	}

	public ExtractType getExtractType() {
		return extractType;
	}

	public void setUrlsMatrix(Set<UrlMatrix> urlsMatrix) {
		this.urlsMatrix = urlsMatrix;
	}

	public void setExtractType(ExtractType extractType) {
		this.extractType = extractType;
	}
}


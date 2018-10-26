package fr.istic.pdl1819_grp5;


import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ConverterToCsv implements Converter
{

	public ConverterToCsv(){
	}



	public Set<FileMatrix> convertFromHtml(String url) {
		return new HashSet<FileMatrix>();
	}

	private String convertHtmlTable(Element htmlTable){
		Elements trs = htmlTable.select("tr");
	    Elements ths = trs.select("th");

	    StringBuilder csv = new StringBuilder("");
	    
	    int index =0;
	    for (Element th : ths) {
	    	
	        csv.append(index==0?th.text():","+th.text());
	        index++;
	    }
	    
	   // csv.append("\n");
	  
	    index=0;
	    for (Element tr : trs) {
	        Elements tds = tr.select("td");
	        
	       for(Element td : tds) {
	    	   csv.append(index==0?td.text():","+td.text());
	    	   index++;
	       }
	       
	       csv.append("\n");
	       index=0;
	    }
		
		return csv.toString();
	}

	public Set<FileMatrix> convertFromWikitext(String text) {
		return new HashSet<FileMatrix>();
	}


}


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

	public static void main(String[] args){

		Document doc  = Jsoup.parseBodyFragment(" <table style=\"width:100%\">\n" +
				"  <tr>\n" +
				"    <th>Firstname</th>\n" +
				"    <th>Lastname</th>\n" +
				"    <th>Age</th>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td>Jill</td>\n" +
				"    <td>Smith</td>\n" +
				"    <td>50</td>\n" +
				"  </tr>\n" +
				"  <tr>\n" +
				"    <td>Eve</td>\n" +
				"    <td>Jackson</td>\n" +
				"    <td>94</td>\n" +
				"  </tr>\n" +
				"</table> ");

		Element element = doc.getElementsByTag("table").first();

		System.out.println(new ConverterToCsv().convertHtmlTable(element));
	}


}


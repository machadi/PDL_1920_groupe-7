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

	public String convertHtmlTable(Element htmlTable){
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
	
	public static void main(String[] args) {
		ConverterToCsv conv = new ConverterToCsv();
		Document doc = Jsoup.parseBodyFragment("<table>\n" + 
				"  <tr>\n" + 
				"    <th>Company</th>\n" + 
				"    <th>Contact</th>\n" + 
				"    <th>Country</th>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"    <td>Alfreds Futterkiste</td>\n" + 
				"    <td>Maria Anders</td>\n" + 
				"    <td>Germany</td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"    <td>Centro comercial Moctezuma</td>\n" + 
				"    <td>Francisco Chang</td>\n" + 
				"    <td>Mexico</td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"    <td>Ernst Handel</td>\n" + 
				"    <td>Roland Mendel</td>\n" + 
				"    <td>Austria</td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"    <td>Island Trading</td>\n" + 
				"    <td>Helen Bennett</td>\n" + 
				"    <td>UK</td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"    <td>Laughing Bacchus Winecellars</td>\n" + 
				"    <td>Yoshi Tannamuri</td>\n" + 
				"    <td>Canada</td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"    <td>Magazzini Alimentari Riuniti</td>\n" + 
				"    <td>Giovanni Rovelli</td>\n" + 
				"    <td>Italy</td>\n" + 
				"  </tr>\n" + 
				"</table>");
		Element element = doc.getElementsByTag("table").first();
		System.out.println(conv.convertHtmlTable(element));
	}


}


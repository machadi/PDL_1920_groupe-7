package fr.istic.pdl1819_grp5;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class ConverterToCsv implements Converter
{
	private static int numberOfcsv;

	static{
		numberOfcsv = 0;
	}

	public ConverterToCsv(){
	}



	public Set<FileMatrix> convertFromHtml(String url) throws IOException {

		Set<FileMatrix> csvSet = new HashSet<FileMatrix>();

		Document doc = Jsoup.connect(url).get();

		Elements tables = doc.getElementsByTag("table");

		for(Element table : tables){
			csvSet.add(convertHtmlTable(table));
		}
		return csvSet;
	}

	private FileMatrix convertHtmlTable(Element htmlTable){

		Elements trs = htmlTable.select("tr");
	    Elements ths = trs.select("th");

	    StringBuilder csvBuilder = new StringBuilder("");
	    
	    int index =0;
	    for (Element th : ths) {
	    	
	        csvBuilder.append(index==0?th.text():","+th.text());
	        index++;
	    }
	    
	   // csvBuilder.append("\n");
	  
	    index=0;
	    for (Element tr : trs) {
	        Elements tds = tr.select("td");
	        
	       for(Element td : tds) {
	    	   csvBuilder.append(index==0?td.text():","+td.text());
	    	   index++;
	       }
	       
	       csvBuilder.append("\n");
	       index=0;
	    }


		numberOfcsv++;
	    Csv csv = new Csv("csv"+numberOfcsv);
	    csv.setText(csvBuilder.toString());

		return csv;
	}

	public Set<FileMatrix> convertFromWikitext(String text) {
		return new HashSet<FileMatrix>();
	}

	public static void main(String[] args) throws IOException {
		Document doc = Jsoup.parseBodyFragment("<table>\n" +
				" <th>\n" +
				" nombres\n" +
				" </th>\n" +
				" <th>\n" +
				" table\n" +
				" </th>\n" +
				"<tbody>\n" +
				"<tr><td>123344</td>\n" +
				"<td><table>\n" +
				" <th>\n" +
				" ney\n" +
				" </th>\n" +
				" <th>\n" +
				" mar\n" +
				" </th>\n" +
				"<tbody>\n" +
				"<tr><td>psg</td>\n" +
				"<td>brazil</td></tr>\n" +
				"\n" +
				"</tbody>\n" +
				"</table></td></tr>\n" +
				"\n" +
				"</tbody>\n" +
				"</table>");
		Elements tables = doc.getElementsByTag("table");

		System.out.println(new ConverterToCsv().convertHtmlTable(tables.first()));
	}


}


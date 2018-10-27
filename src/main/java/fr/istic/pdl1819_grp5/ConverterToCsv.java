package fr.istic.pdl1819_grp5;


import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import info.bliki.wiki.model.WikiModel;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
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

	public Set<FileMatrix> convertFromWikitext(String url) {
		Set<FileMatrix> csvSet = new HashSet<FileMatrix>();
		MediaWikiBot wikiBot = new MediaWikiBot(url.substring(0,url.lastIndexOf("iki/"))+"/");
		Article article= wikiBot.getArticle(url.substring(url.lastIndexOf("/")+1,url.length()));
		String htmlText = WikiModel.toHtml(article.getText());
		Document doc = Jsoup.parse(htmlText);

		Elements tables = doc.getElementsByTag("table");

		for(Element table : tables){
			csvSet.add(convertHtmlTable(table));
		}
		return csvSet;
	}


}


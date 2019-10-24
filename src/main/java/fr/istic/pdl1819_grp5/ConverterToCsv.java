package fr.istic.pdl1819_grp5;


import info.bliki.wiki.model.WikiModel;
import net.sourceforge.jwbf.core.contentRep.Article;
import net.sourceforge.jwbf.mediawiki.bots.MediaWikiBot;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;


/**
 * converter implementation
 */
public class ConverterToCsv implements Converter
{

    /*
    Cell of html table which have rowspan and colspan attribute
     */

    class PriorityCell{

        private  int colspan;
        private  int rowspan;
        private final int row; //ligne a laquelle se ttrouve la cellule
        private final int column;


        public PriorityCell(String rowspan, String colspan, int row, int column) {

            try {
                this.colspan = Integer.parseInt(colspan);
            }catch (NumberFormatException nException){
                this.colspan = -1;
            }
            try {
                this.rowspan = Integer.parseInt(rowspan);
            }catch (NumberFormatException nException){
                this.rowspan = -1;
            }




            this.row = row;
            this.column = column;
        }


    }

	/**
	 * @param table the param
	 * @return true if table is nested
	 */
    /*
     */
    public boolean isNested(Element table){

    	//parcours des parents du tableau du bas vers le haut: mode bottom_up
    	//boolean parent = table.parents().size()
		boolean hasTableParent = false;
		Elements parents = table.parents();
		for (Element parent : parents)
			if(parent.nodeName().equalsIgnoreCase("table")){
				hasTableParent =true;
				break;
			}
		//parcours des parents du tableau du bas vers le haut: mode bottom_up
		boolean hasTablechild = table.getElementsByTag("table").size()>1;

		return hasTableParent || hasTablechild;
	}


	/**
	 *
	 * @param table
	 * @return true if table is relevant
	 */
	public boolean isRelevant(Element table) {
		boolean isRelevant = table.selectFirst("[class*=\"nv-\"]")==null  || table.selectFirst("[class*=\"box\"]")==null
				|| !table.className().contains("box") || !table.className().contains("nv-");

		return table.className().contains("wikitable") && isRelevant;
	}




    private List<PriorityCell> listOfCells= new ArrayList<PriorityCell>();
	private static int numberOfcsv;
	private String separateur=",";
	private int nbRelev;
	private int nbNotRelev;
	private int wikiRelev;
	private int wikiNotRelev;

	static{
		numberOfcsv = 0;
	}

	public ConverterToCsv(){
		this.nbRelev=0;
		this.nbNotRelev=0;
		this.wikiRelev=0;
		this.wikiNotRelev=0;
	}

	public HashMap<String, Integer> getRelev(){
		HashMap<String, Integer> hm=new HashMap<>();
		hm.put("nbRelev", nbRelev);
		hm.put("nbNotRelev", nbNotRelev);
		hm.put("wikiRelev", wikiRelev);
		hm.put("wikiNotRelev", wikiNotRelev);
		return hm;

	}
	private int NumberOfColumn(Element table){
		Elements els=table.select("tr").first().children();
		int nbCol=0;
		for (Element el: els) {
			String colspan=el.attr("colspan");
			if(!colspan.equals("")){
				nbCol+=Integer.parseInt(colspan);
			}else{
				nbCol++;
			}
		}
		return nbCol;
	}
    private boolean hasPriorityCell(int row, int column){
        boolean found=false;
        for (PriorityCell p: listOfCells) {
            if(p.row==row && ((column<=p.column+p.colspan-1) || p.colspan==0) ){
                found=true;
                break;
            }else if( p.column==column && ((row<=p.row+p.rowspan-1) || p.rowspan==0) ){
                found=true;
                break;
            }
        }
        return found;
    }

	public Set<FileMatrix> convertFromHtml(String url) throws IOException {

		Set<FileMatrix> csvSet = new HashSet<FileMatrix>();

		try {
			Document doc = Jsoup.connect(url).get();
			Elements tables = doc.getElementsByTag("table");

			for(int i =0; i<tables.size();i++){
				if(isRelevant(tables.get(i))  &&  !isNested(tables.get(i)) ){
					csvSet.add(convertHtmlTable(tables.get(i)));
					nbRelev++;

				}
				else nbNotRelev++;

			}
		}catch (UnknownHostException e){
			//e.printStackTrace();

		}catch (HttpStatusException e){
			//e.printStackTrace();
		}


		return csvSet;
	}

	public FileMatrix convertHtmlTable(Element htmlTable) throws IndexOutOfBoundsException{

		//Nombre de colonne du tableau(La première ligne contient toujours le nombre de colonne)
		final int nbCol=NumberOfColumn(htmlTable);

		StringBuilder csvBuilder = new StringBuilder("");

        //Entete

		Elements trh=htmlTable.select("thead tr");
		writeInCsv(trh, csvBuilder, nbCol);


		listOfCells.clear();


		//corps
        	Elements trs = htmlTable.select("tbody tr");
		writeInCsv(trs, csvBuilder, nbCol);

		listOfCells.clear();

		// pied de table

		Elements trf=htmlTable.select("tfoot tr");
		writeInCsv(trf, csvBuilder, nbCol);


		numberOfcsv++;
	    Csv csv = new Csv("csv"+numberOfcsv);
	    csv.setText(csvBuilder.toString());

		return csv;
	}

	private void writeInCsv(Elements trs, StringBuilder csvBuilder, int nbCol){

		for (int i =0; i<trs.size();i++) {

			Element tr = trs.get(i);
			Elements tds = tr.children();

			int index=0;

			for(int j=0;j<nbCol;j++) {

				if(hasPriorityCell(i,j)){
					csvBuilder.append(separateur);

				}else {
					if(index>=tds.size()){
						csvBuilder.append(separateur);
					}else {
						String rowSpan = "";
						String columnSpan= "";

						rowSpan=tds.get(index).attr("rowspan");
						columnSpan=tds.get(index).attr("colspan");


						if(!rowSpan.equals("") || !columnSpan.equalsIgnoreCase("")){
							PriorityCell p= new PriorityCell(rowSpan,columnSpan,i,j);
							listOfCells.add(p);
						}

						String textAjout = tds.get(index).text();
						if (textAjout.contains(separateur)){
							textAjout="\""+tds.get(index).text()+"\"";
						}
						csvBuilder.append(index==0?textAjout:separateur+textAjout);
						index++;
					}

				}


			}

			csvBuilder.append("\n");
		}
	}

	public Set<FileMatrix> convertFromWikitext(String url) {
		Set<FileMatrix> csvSet = new HashSet<FileMatrix>();
		try {

			MediaWikiBot wikiBot = new MediaWikiBot(url.substring(0,url.lastIndexOf("iki/"))+"/");
			Article article= wikiBot.getArticle(url.substring(url.lastIndexOf("/")+1,url.length()));

			Document doc = Jsoup.parse(WikiModel.toHtml(article.getText()));

			Elements tables = doc.getElementsByTag("table");

			try {
				for(int i =0; i<tables.size();i++){

					if(!isNested(tables.get(i)) && isRelevant(tables.get(i))){
						csvSet.add(convertHtmlTable(tables.get(i)));
						wikiRelev++;

					}
					else wikiNotRelev++;
				}
			}catch (Exception e){

			}


		}catch (Exception e){
			//e.printStackTrace();
		}
		return csvSet;


	}


}


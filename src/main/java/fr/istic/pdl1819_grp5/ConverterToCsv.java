package fr.istic.pdl1819_grp5;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class ConverterToCsv implements Converter
{

    /*
    Celll of html table which have rowspan and colspan attribute
     */

    class PriorityCell{

        private  int colspan;
        private  int rowspan;
        private final int row;
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



    private List<PriorityCell> listOfCells= new ArrayList<PriorityCell>();
	private static int numberOfcsv;
	private String separateur=",";

	static{
		numberOfcsv = 0;
	}

	public ConverterToCsv(){
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
            }else if( p.column==column && ((row<=p.row+p.rowspan-1) || p.rowspan==0) ){
                found=true;
            }
        }
        return found;
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

	public FileMatrix convertHtmlTable(Element htmlTable){

        //ajout des entetes
		Elements trh=htmlTable.select("thead tr");
		Elements ths=trh.select("th");

	    StringBuilder csvBuilder = new StringBuilder("");

	    int index =0;
	    for (Element th : ths) {

	        csvBuilder.append(index==0?th.text():separateur+th.text());
	        index++;
	    }

	    //Nombre de colonnes(La première ligne contient toujours le nombre de colonne)
        int nbCol=NumberOfColumn(htmlTable);


        Elements trs = htmlTable.select("tbody tr");

	    int i=0;
	    for (Element tr : trs) {

	        Elements tds = tr.children();
            index=0;

	       for(int j=0;j<nbCol;j++) {


	           if(hasPriorityCell(i,j)){
	               csvBuilder.append(separateur);
               }else {
                   String rowSpan=tds.get(index).attr("rowspan");
                   String columnSpan=tds.get(index).attr("colspan");

                   if(!rowSpan.equals("") || !columnSpan.equals("")){
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
	       
	       csvBuilder.append("\n");
            i++;
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


package fr.istic.pdl1819_grp5;


import java.util.Set;

import org.jsoup.nodes.Element;


public  interface Converter
{
	/**

     * @param
     *  link
     * @return
     * a csv file
	 */
	
	public Set<FileMatrix> convertFromHtml(String link) ;
	public Set<FileMatrix> convertFromWikitext(String link) ;
	public String convertHtmlTable(Element htmlTable);

}


package fr.istic.pdl1819_grp5;


import java.util.Set;

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

}


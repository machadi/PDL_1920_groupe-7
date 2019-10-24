package fr.istic.pdl1819_grp5;


import java.io.IOException;
import java.util.HashMap;
import java.util.Set;


public  interface Converter
{
	/**

     * @param
     *  link
     * @return
     * a csv file
	 */
	
	public Set<FileMatrix> convertFromHtml(String link) throws IOException;

	/**
	 * @param link
	 * @return
	 **/
	public Set<FileMatrix> convertFromWikitext(String link) ;
	public HashMap getRelev();

}


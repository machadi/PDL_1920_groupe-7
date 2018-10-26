package fr.istic.pdl1819_grp5;



public  interface Converter 
{
	/**

     * @param
     *  link
     * @return
     * a csv file
	 */
	
	public FileMatrix convertFromHtml(String link) ;
	public FileMatrix convertFromWikitext(String link) ;

}


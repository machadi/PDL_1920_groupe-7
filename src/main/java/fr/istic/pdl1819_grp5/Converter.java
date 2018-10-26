package fr.istic.pdl1819_grp5;



public  interface Converter 
{
	/**

     * @param
     * text in html
     * @return
     * a csv file
	 */
	
	public FileMatrix convertFromHtml(String text) ;
	public FileMatrix convertFromWikitext(String text) ;

}


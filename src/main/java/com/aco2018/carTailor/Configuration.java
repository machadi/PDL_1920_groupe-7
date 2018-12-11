package com.aco2018.carTailor;


import java.io.PrintStream;
import java.util.Set;


/**
 * Represente client's configuration
 */
public  interface Configuration
{

	/**
	 *
	 * @return true if configuration is full
	 */
	public boolean isComplete() ;


	/**
	 *
	 * @return true if configuration is valid
	 */
	
	public boolean isValid() ;

	/**
	 *
	 * @param part add part to this configuration
	 * @return
	 */

	public boolean addPart(PartImp part);

	/**
	 * remove all part in this configuration
	 */
	public void resetConf();

	/**
	 *
	 * @param partNam part name which will delete
	 * @return return true if the part deleting with success
	 */
	public boolean deletePart(String partNam);

	/**
	 *
	 * @return return price configuratuion
	 */
	public double getPriceConfiguation();

	/**
	 *
	 * @param printStream print configuration descripttion in Stream
	 */
	public void printDesciption(PrintStream printStream);

	/**
	 *
	 * @return get part set of this configuration
	 */

	public Set<PartImp> getPartSet();
}


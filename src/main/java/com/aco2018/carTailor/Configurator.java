package com.aco2018.carTailor;


import java.io.File;
import java.util.Set;


public  interface Configurator 
{
	/**
	 *
	 * @param name categry 's name
	 * @return part set of  category
	 */
	public Set<PartTypeImpl> getVariantsForCategory(String name);

	/**
	 *
	 * @param jsonFile file for setup configurator
	 */
	public void launch(File jsonFile);

	/**
	 *
	 * @param partName part name
	 * @return return part
	 */
	public PartImp choosePartImp(String partName);

	/**
	 *
	 * @param jsonFile json file
	 * @return the jsonFile value
	 */
	public StringBuilder readFile  (File jsonFile);

	/**
	 *
	 * @param partName part name
	 * @return return part
	 */
	public PartTypeImpl getPartTypeByName(String partName);

	/**
	 *
	 * @param requirement requirement
	 * @param refererence part type
	 * @return return true if requirement has been added to patr type with succes
	 */
	public boolean addRequirement(String requirement,String refererence);

	/**
	 *
	 * @param requirement requirement
	 * @param referernce  part type
	 * @return return true if requirement has been deleting to patr type with succes
	 */
	public boolean removeRequirement(String requirement,String referernce);
	/**
	 *
	 * @param incompatibility incompatibility
	 * @param referernce part type
	 * @return return true if incompatibility has been added to patr type with succes
	 */
	public boolean addIncompatibility(String incompatibility,String referernce);
	/**
	 *
	 * @param incompatibility incompatibility
	 * @param referernce  part type
	 * @return return true if incompatibility has been deleting to patr type with succes
	 */
	public boolean removeIncompatibility(String incompatibility,String referernce);


}


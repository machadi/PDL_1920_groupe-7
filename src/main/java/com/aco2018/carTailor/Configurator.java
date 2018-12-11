package com.aco2018.carTailor;


import java.io.File;
import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface Configurator 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public  Set<PartImpl>getVariantsForCategory(String name);
	public void launch(File jsonFile);
	public PartImpl getPartByName(String name);
	public boolean addRequirement(String requirement,String referernce);
	public boolean removeRequirement(String requirement,String referernce);
	public boolean addIncompatibility(String incompatibility,String referernce);
	public boolean removeIncompatibility(String incompatibility,String referernce);
	public void printDescription();
	public StringBuilder readFile  (File jsonFile);
	public Set<CategoryImpl> getCategorySet();



}


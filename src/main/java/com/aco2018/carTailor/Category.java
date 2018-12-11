package com.aco2018.carTailor;


import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface Category 
{
	/**
	 *
	 * @param partTypeImpl  add  partType in the part type list of this category
	 */
	public void addPartType(PartTypeImpl partTypeImpl);

	/**
	 *
	 * @return the category name
	 */
	public String getCategoryName();




	/**
	 *
	 * @return part type list of this category
	 */
	public Set<PartTypeImpl> getParts();


}


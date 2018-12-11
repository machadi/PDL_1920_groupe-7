package com.aco2018.carTailor;


import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface PartType 
{
	/**
	 *
	 * @return category of part type
	 */
	public Category getCategory() ;

	/**
	 *
	 * @param category  the category of part type
	 */

	public void setCategory(CategoryImpl category) ;

	/**
	 *
	 * @return the description of part type
	 */
	public String getPartTypeDescription();

	/**
	 *
	 * @return name of part type
	 */
	public String getPartTypeName();

	/**
	 *
	 * @param partImp set part to the part type
	 */
	public void setPartImp(PartImp partImp);

	/**
	 *
	 * @return get part type of part type
	 */
	public PartImp getPartImp();

}


package com.aco2018.carTailor;


import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface Configuration 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean isComplete() ;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public boolean isValid() ;
	public boolean addPart(PartImpl part);
	public boolean deletePart(String partName);
	public void resetConf();
	public Set<PartImpl> getPartSet();



}


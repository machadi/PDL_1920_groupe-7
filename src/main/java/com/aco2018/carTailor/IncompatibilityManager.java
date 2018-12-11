package com.aco2018.carTailor;

import java.util.HashSet;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface IncompatibilityManager 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addIncompatibilities(PartImpl reference, Set<PartImpl> incompatility) ;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public void addRequirements(PartImpl reference,Set<PartImpl> requirements) ;


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<PartImpl> getIncompatibilities(PartImpl reference) ;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	public Set<PartImpl> getRequirements(PartImpl reference) ;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public boolean removeRequirement(PartImpl partIncompatibility,PartImpl partReference);

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public boolean removeIcompatibility(PartImpl partIncompatibility,PartImpl partReference);

	public boolean addRequirement(PartImpl partRequirement, PartImpl partReference);
	public boolean addIncompatibility(PartImpl partIncompatibility, PartImpl partReference);

}


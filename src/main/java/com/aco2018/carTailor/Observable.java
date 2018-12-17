package com.aco2018.carTailor;



/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface Observable 
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	public void notifyObserver() ;

	/**
	 *
	 * @param observer add observer
	 */

	public void register(Observer observer) ;

	/**
	 *
	 * @param observer remove observer
	 */

	public void unregister(Observer observer) ;


}


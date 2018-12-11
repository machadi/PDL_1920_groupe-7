package com.aco2018.carTailor;


import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public  interface Part 
{
	/**
	 *
	 * @return the part type of  part
	 */
	public PartType getPartType() ;

	/**
	 *
	 * @return set of all property
	 */


	public Set<String> getPropertyNames() ;

	/**
	 *
	 * @param propertyName property name
	 * @return return property if part contains this property else empty optional
	 */
	public Optional<String> getProperty(String propertyName);

	/**
	 *
	 * @param propertyName property name
	 * @param propertyValue add this value to property
	 */

	public void setProperty(String propertyName, String propertyValue);

	/**
	 *
	 * @param propertyName
	 * @return all values of property name
	 */
	public Set<String> getAvailablePropertyValues(String propertyName);

	/**
	 *
	 * @return part price
	 */

	public double getPrice();

	/**
	 *
	 * @return name of part
	 */
	public String getName();

	/**
	 *
	 * @return color of part
	 */
	public String getColor();

	/**
	 *
	 * @param color set color of part
	 */
	public void setColor(String color);

}


package com.aco2018.carTailor;

import java.util.HashSet;
import java.util.Set;



public  interface IncompatibilityManager 
{
	/**
	 *
	 * @param reference
	 * @param incompatility
	 * add add set incompatibility to reference
	 */

	public void addIncompatibilities(PartTypeImpl reference, Set<PartTypeImpl> incompatility) ;


	/**
	 *
	 * @param reference
	 * @param requirements
	 * add add set requirement to reference
	 */

	public void addRequirements(PartTypeImpl reference,Set<PartTypeImpl> requirements) ;




	public boolean addIncompatibility(PartTypeImpl partIncompatibility,PartTypeImpl partReference) ;



	public boolean addRequirement(PartTypeImpl partRequirement,PartTypeImpl partReference) ;




	public Set<PartTypeImpl> getIncompatibilities(PartTypeImpl reference) ;



	public Set<PartTypeImpl> getRequirements(PartTypeImpl reference) ;


	public boolean removeRequirement(PartTypeImpl partRequirement,PartTypeImpl partReference);

	public boolean removeIcompatibility(PartTypeImpl partIncompatibility,PartTypeImpl partReference);


}


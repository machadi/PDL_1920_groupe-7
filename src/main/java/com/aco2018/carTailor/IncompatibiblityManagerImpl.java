package com.aco2018.carTailor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;



public  class IncompatibiblityManagerImpl implements IncompatibilityManager
{

	Map<PartTypeImpl, Set<PartTypeImpl>> requirements = new HashMap<>();
	Map<PartTypeImpl, Set<PartTypeImpl>> incompatibilities = new HashMap<>();
	private static IncompatibiblityManagerImpl instance;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	private IncompatibiblityManagerImpl(){
		super();
	}

	public static IncompatibiblityManagerImpl getInstance(){
		if(instance == null){
			instance = new IncompatibiblityManagerImpl();
		}
		return instance;
	}


	@Override
	public void addIncompatibilities(PartTypeImpl reference, Set<PartTypeImpl> incompatilities) {

		incompatibilities.put(reference,incompatilities);
	}


	@Override
	public void addRequirements(PartTypeImpl reference,Set<PartTypeImpl> requirements) {
		this.requirements.put(reference, requirements);
	}

	@Override
	public boolean addIncompatibility(PartTypeImpl partIncompatibility, PartTypeImpl partReference) {
		boolean add=false;
		Set<PartTypeImpl>incompatibilitiesOfPartReference=getIncompatibilities(partReference);
		if(partIncompatibility!=null && partReference!=null){
			if(!incompatibilitiesOfPartReference.contains(partIncompatibility))
				add=incompatibilitiesOfPartReference.add(partIncompatibility);
		}
		return add;
	}

	@Override
	public boolean addRequirement(PartTypeImpl partRequirement, PartTypeImpl partReference) {
		boolean add=false;
		Set<PartTypeImpl>requirementsOfPartReference=getRequirements(partReference);
		if(partRequirement!=null && partReference!=null){
			if(!requirementsOfPartReference.contains(partRequirement)){
				add = requirementsOfPartReference.add(partRequirement);
			}
		}
		return add;
	}



	@Override
	public Set<PartTypeImpl> getIncompatibilities(PartTypeImpl reference) {
		Set<PartTypeImpl> incompatibilitiesFound = new HashSet<>(); ;
		if(incompatibilities.get(reference)!=null){
			incompatibilitiesFound = incompatibilities.get(reference);
		}else{
			incompatibilities.put(reference, incompatibilitiesFound);
		}
		return incompatibilitiesFound;
	}


	@Override
	public Set<PartTypeImpl> getRequirements(PartTypeImpl reference) {
		Set<PartTypeImpl> requirementsFound = new HashSet<>(); ;
		if(requirements.get(reference)!=null){
			requirementsFound = requirements.get(reference);
		}else{
			requirements.put(reference, requirementsFound);
		}
		return requirementsFound;
	}

	@Override
	public boolean removeIcompatibility(PartTypeImpl partIncompatibility,PartTypeImpl partReference) {
		boolean remove=false;
		if(partIncompatibility!=null && partReference!=null){
			Set<PartTypeImpl>incompatibilitiesOfPartReference=getIncompatibilities(partReference);
			if(incompatibilitiesOfPartReference.contains(partIncompatibility))
				remove=incompatibilitiesOfPartReference.remove(partIncompatibility);
		}
		return remove;
	}

	@Override
	public boolean removeRequirement(PartTypeImpl partRequirement,PartTypeImpl partReference) {
		boolean remove=false;
		Set<PartTypeImpl>requirementsOfPartReference=getRequirements(partReference);
		if(partRequirement!=null && partReference!=null){
			if(requirementsOfPartReference.contains(partRequirement)){
				remove=requirementsOfPartReference.remove(partRequirement);
				getRequirements(partRequirement).remove(partReference);
			}
		}
		return remove;
	}


}


package com.aco2018.carTailor;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public  class IncompatibiblityManagerImpl implements IncompatibilityManager
{

	Map<PartImpl, Set<PartImpl>> requirements = new HashMap<>();
	Map<PartImpl, Set<PartImpl>> incompatibilities = new HashMap<>();
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

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public void addIncompatibilities(PartImpl reference, Set<PartImpl> incompatilities) {
		incompatibilities.put(reference,incompatilities);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public void addRequirements(PartImpl reference,Set<PartImpl> requirements) {
		this.requirements.put(reference, requirements);
	}


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public Set<PartImpl> getIncompatibilities(PartImpl reference) {
		Set<PartImpl> incompatibilitiesFound = new HashSet<>(); ;
		if(incompatibilities.get(reference)!=null){
			incompatibilitiesFound = incompatibilities.get(reference);
		}else{
			incompatibilities.put(reference, incompatibilitiesFound);
		}
		return incompatibilitiesFound;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public Set<PartImpl> getRequirements(PartImpl reference) {
		Set<PartImpl> requirementsFound = new HashSet<>(); ;
		if(requirements.get(reference)!=null){
			requirementsFound = requirements.get(reference);
		}else{
			requirements.put(reference, requirementsFound);
		}
		return requirementsFound;
	}

	@Override
	public boolean addIncompatibility(PartImpl partIncompatibility, PartImpl partReference) {
		boolean add=false;
		Set<PartImpl>incompatibilitiesOfPartReference=getIncompatibilities(partReference);
		if(partIncompatibility!=null && partReference!=null){
			if(!incompatibilitiesOfPartReference.contains(partIncompatibility))
				add=incompatibilitiesOfPartReference.add(partIncompatibility);
		}
		return add;
	}

	@Override
	public boolean addRequirement(PartImpl partRequirement, PartImpl partReference) {
		boolean add=false;
		Set<PartImpl>requirementsOfPartReference=getRequirements(partReference);
		if(partRequirement!=null && partReference!=null){
			if(!requirementsOfPartReference.contains(partRequirement)){
				add = requirementsOfPartReference.add(partRequirement);
			}
		}
		return add;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public boolean removeIcompatibility(PartImpl partIncompatibility,PartImpl partReference) {
		boolean remove=false;
		if(partIncompatibility!=null && partReference!=null){
			Set<PartImpl>incompatibilitiesOfPartReference=getIncompatibilities(partReference);
			if(incompatibilitiesOfPartReference.contains(partIncompatibility))
				remove=incompatibilitiesOfPartReference.remove(partIncompatibility);
		}
		return remove;
	}

	@Override
	public boolean removeRequirement(PartImpl partRequirement,PartImpl partReference) {
		boolean remove=false;
		Set<PartImpl>requirementsOfPartReference=getRequirements(partReference);
		if(partRequirement!=null && partReference!=null){
			if(requirementsOfPartReference.contains(partRequirement)){
				remove=requirementsOfPartReference.remove(partRequirement);
				getRequirements(partRequirement).remove(partReference);
			}
		}
		return remove;
	}

}


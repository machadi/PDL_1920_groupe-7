package com.aco2018.carTailor;
import java.util.HashSet;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class ConfigurationImpl implements Observable, Configuration
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<PartImpl> partSet;
	private Set<Observer>observers;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private IncompatibiblityManagerImpl incompatibiblityManagerImpl;

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public ConfigurationImpl(IncompatibiblityManagerImpl incompatibiblityManagerImpl){

		super();
		this.incompatibiblityManagerImpl=incompatibiblityManagerImpl;
		partSet=new HashSet<>();
		observers=new HashSet<>();
	}

	@Override
	public boolean addPart(PartImpl part){
		boolean add=false;
		if(part!=null){
			if(!partSet.contains(part)){
				add=partSet.add(part);
				notifyObserver();
			}
		}

		return add;

	}
	@Override
	public boolean deletePart(String partName){
		boolean remove=false;
		if(partName!=null){
			for(PartImpl partImpl:partSet){
				if(partImpl.getPartName().equalsIgnoreCase(partName)){
					partSet.remove(partImpl);
					remove=true;
					notifyObserver();
					break;
				}

			}
		}

		return remove;

	}
	@Override
	public void resetConf(){
		partSet.removeAll(partSet);
	}
	/*
	@Override
	public void description() {
		for(PartImpl partImpl:partSet){
			System.out.print(partImpl.getPartName()+"		");
		}
	}
	*/
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public void notifyObserver() {
		for(Observer observer:observers){
			observer.update();
		}

	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public void unregister(Observer observer) {
		if(!observers.contains(observer)){
			observers.remove(observer);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public void register(Observer observer) {
		if(!observers.contains(observer)){
			observers.add(observer);
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public boolean isValid() {
		boolean isValid = true;

		Set<PartImpl>incompatibilities;
		for(PartImpl p1 : partSet){
			if (incompatibiblityManagerImpl.getIncompatibilities(p1)!=null){
				incompatibilities= incompatibiblityManagerImpl.getIncompatibilities(p1);
				for (Part p2 : partSet){
					if(incompatibilities.contains(p2)){
						isValid=false;
						break;
					}


				}
				if (!isValid)
					break;
			}

		}

		return isValid;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public boolean isComplete() {
		boolean isComplete = true;
		Set<PartImpl>requirements;
		//verifying
		for (PartImpl p:partSet ){
			if(incompatibiblityManagerImpl.getRequirements(p)!=null){
				if(!partSet.containsAll(incompatibiblityManagerImpl.getRequirements(p))){
					isComplete=false;
					break;
				}
			}


		}

		return isComplete;
	}
	@Override
	public Set<PartImpl> getPartSet() {
		return partSet;
	}
}


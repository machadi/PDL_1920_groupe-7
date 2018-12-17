package com.aco2018.carTailor;
import java.util.HashSet;
import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class CategoryImpl implements Category
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	private String categoryName;



	private Set<PartTypeImpl> partTypeSet;


	public CategoryImpl(String name){
		super();
		this.categoryName = name;
		partTypeSet = new HashSet<>();
	}


	@Override
	public String getCategoryName() {
		// TODO implement me
		return categoryName;
	}


	@Override
	public void addPartType(PartTypeImpl partTypeImpl) {
		// TODO implement me
		if(!partTypeSet.contains(partTypeImpl)){
			partTypeSet.add(partTypeImpl);
		}
	}



	@Override
	public Set<PartTypeImpl> getParts() {
		return partTypeSet;
	}

}


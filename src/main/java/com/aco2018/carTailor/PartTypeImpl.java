package com.aco2018.carTailor;
import java.util.LinkedList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class PartTypeImpl implements PartType
{

	private String partTypeName;

	private String partTypeDescription;

	private CategoryImpl categoryImpl;

	private PartImp partImp;


	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public PartTypeImpl(String name, String description){
		super();
		this.partTypeName = name;
		this.partTypeDescription = description;
	}
	@Override
	public PartImp getPartImp() {
		return partImp;
	}
	@Override
	public void setPartImp(PartImp partImp) {
		this.partImp = partImp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public String getPartTypeName() {
		// TODO implement me
		return partTypeName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public String getPartTypeDescription() {
		// TODO implement me
		return partTypeDescription;
	}



	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */



	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public CategoryImpl getCategory() {
		// TODO implement me
		return categoryImpl;
	}
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	@Override
	public void setCategory(CategoryImpl categoryImpl) {

		this.categoryImpl = categoryImpl;
	}



}


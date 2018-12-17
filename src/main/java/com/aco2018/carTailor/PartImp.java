package com.aco2018.carTailor;


import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class PartImp implements Part
{
    private String color;
    private String name;
    private PartTypeImpl partType;
    public double price;
	private class Property{
		public Consumer<String> setter;
		public Supplier<String> getter;
		public Set<String> possibleValues;



		Property(Supplier<String> getter, Consumer<String> setter, Set<String> possibileValues){

			this.getter = getter;
			this.setter = setter;
			this.possibleValues = possibileValues;

		}
	}

    private Map<String, Property> properties = new HashMap<>();

	protected void addProperty(String name, Supplier<String> getter, Consumer<String> setter, Set<String> possibileValues)
    {
        properties.put(name, new Property(getter, setter, possibileValues));
    }

    @Override
    public Set<String> getPropertyNames() {
        return
                Collections.unmodifiableSet(properties.keySet());
    }
    @Override
    public Optional<String> getProperty(String propertyName) {
        Objects.requireNonNull(propertyName);
        if (properties.containsKey(propertyName)) {
            return
                    Optional.of(properties.get(propertyName).getter.get());
        }
        return Optional.empty();
    }
    @Override
    public void setProperty(String propertyName, String propertyValue) {
        Objects.requireNonNull(propertyName);
        Objects.requireNonNull(propertyValue);
        if ((properties.containsKey(propertyName)) &&
                (properties.get(propertyName).setter != null)) {
            properties.get(propertyName).setter.accept(propertyValue);
        } else {
            throw new IllegalArgumentException("bad property name or value: " + propertyName);
        }
    }
    @Override
    public Set<String> getAvailablePropertyValues(String propertyName) {
        if (properties.containsKey(propertyName)) {
            return
                    Collections.unmodifiableSet(properties.get(propertyName).possibleValues);
        }
        return Collections.emptySet();
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
	 */
	public PartImp(PartTypeImpl partTypeImpl){
		super();
		this.partType=partTypeImpl;
		this.name = partTypeImpl.getPartTypeName();
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
	@Override
	public PartTypeImpl getPartType() {
		// TODO implement me
		return partType;
	}
    @Override
	public void setColor(String color){
	    this.color = color;
    }
    @Override
    public String getColor(){
	   return color;
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice(){
        int plus=0;
	    if(color != null){
	        if(color.equalsIgnoreCase("bleu")){
	            plus= 200;
            }else if(color.equalsIgnoreCase("rouge")){
                plus= 450;
            }else if(color.equalsIgnoreCase("vert")){
                plus= 570;
            }else if (color.equalsIgnoreCase("orange")){

            }
        }

        return price+plus;
    }
}


package com.aco2018.carTailor;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Iterator;
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
	
	private Set<PartImp> partSet = new HashSet<>();
	private Set<Observer>observers = new HashSet<>();
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
	}

	@Override
	public boolean addPart(PartImp part){
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
			for(PartImp partImpl:partSet){
				if(partImpl.getName().equalsIgnoreCase(partName)){
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
	@Override
	public Set<PartImp> getPartSet() {
		return partSet;
	}

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
		Set<PartTypeImpl>incompatibilities;
		for(PartImp p1 : partSet){
			incompatibilities= incompatibiblityManagerImpl.getIncompatibilities(p1.getPartType());
			for (Part p2 : partSet){
				if(incompatibilities.contains(p2.getPartType()))
				{
					isValid=false;
					break;
				}

			}
			if (!isValid)
				break;
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

		//PartTypeSet of partImp
		Set<PartTypeImpl>partTypeSet = new HashSet<>();

		for (PartImp p:partSet ){
			if(!partTypeSet.contains(p.getPartType())){
				partTypeSet.add(p.getPartType());
			}

		}

		//verifying

		for (PartImp p:partSet ){
			//System.out.println(incompatibiblityManagerImpl.getRequirements(p.getPartType()).size());
			if(!partTypeSet.containsAll(incompatibiblityManagerImpl.getRequirements(p.getPartType()))){
				isComplete=false;
				break;
			}

		}
		return isComplete;
	}
	@Override
	public double getPriceConfiguation(){
		double price = 0.0;
		if (isValid()){
			for(PartImp part : partSet){
				price += part.getPrice();
			}
		}

		return price;
	}
	@Override
	public void printDesciption(PrintStream printStream){

		StringBuilder htlmDescriptor= new StringBuilder();

		String html = "";

		if(isValid()&&isComplete()){
			String head = "<!DOCTYPE html>\n" +
					"<html lang=\"en\">\n" +
					"<head>\n" +
					"    <meta charset=\"UTF-8\">\n" +
					"<style>\n" +
					"table {\n" +
					"  font-family: arial, sans-serif;\n" +
					"  border-collapse: collapse;\n" +
					"  width: 100%;\n" +
					"}\n" +
					"\n" +
					"td, th {\n" +
					"  border: 1px solid #dddddd;\n" +
					"  text-align: left;\n" +
					"  padding: 8px;\n" +
					"}\n" +
					"\n" +
					"tr:nth-child(even) {\n" +
					"  background-color: #dddddd;\n" +
					"}\n" +
					"</style>"+
					"    <title>Configuration description</title>\n" +
					"</head>\n" +
					"<body>";

			StringBuilder bodyB = new StringBuilder();
			bodyB.append("<table style=\"width:100%\">" +
					"<th>Part</th><th>Part Type</th><th>Description</th><th>Category</th><th>Price</th>");

			String[] row = new String[5];
			Iterator<PartImp> partIterator=partSet.iterator();
			while (partIterator.hasNext()){
				PartImp p=partIterator.next();
				row[0] = p.getName();
				row[1] = p.getPartType().getPartTypeName();
				row[2] = p.getPartType().getPartTypeDescription();
				row[3] = p.getPartType().getCategory().getCategoryName();
				row[4] = (String.valueOf(partIterator.next().getPrice()));
				bodyB.append(addRow(row));
			}


			html = htlmDescriptor.append(head).append(bodyB).append("</table><br>The price configuration is :<h3>"+getPriceConfiguation()+"</h3></body>\n" +
					"</html>").toString();
		}
		System.out.println(html);
		printStream.println(html);
	}
	private String addRow(String[] row){
		StringBuilder strB = new StringBuilder();
		strB.append("<tr>");
		for (int i=0; i<row.length; i++){
			strB.append("<td>"+row[i]+"</td>");
		}

		strB.append("</tr>");

		return strB.toString();

	}


}


package com.aco2018.carTailor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.Collections;
import java.util.HashSet;

import java.util.Set;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */
public class ConfiguratorImpl implements Configurator
{
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */
	
	private Set<CategoryImpl> categorySet;

	public IncompatibiblityManagerImpl getIncompatibiblityManager() {
		return incompatibiblityManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */


	private IncompatibiblityManagerImpl incompatibiblityManager;
	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 */
	public ConfiguratorImpl(){
		super();
		categorySet = new HashSet<>();
		incompatibiblityManager= IncompatibiblityManagerImpl.getInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!--  end-user-doc  -->
	 * @generated
	 * @ordered
	 */

	@Override
	public void launch(File jsonFile){
		StringBuilder strB=readFile(jsonFile);
		JSONObject jsonObject= new JSONObject(strB.toString());

		//Creating category and part

		JSONArray categoryArray =  jsonObject.getJSONArray("categoryList");

		int lenghtCategoryArray = categoryArray.length();

		for(int i=0; i<lenghtCategoryArray;i++){
			JSONObject categoryObject = categoryArray.getJSONObject(i);
			CategoryImpl currentCategory = new CategoryImpl(categoryObject.getString("name"));

			JSONArray partArray =  categoryObject.getJSONArray("partList");
			int lenghtPartArray = partArray.length();

			for (int j=0; j<lenghtPartArray;j++){
				JSONObject partObject = partArray.getJSONObject(j);
				PartImpl currentpart = new PartImpl(partObject.getString("name"),partObject.getString("description"));
				currentpart.setCategory(currentCategory);
				currentCategory.addPart(currentpart);
			}

			categorySet.add(currentCategory);

		}

		//requirements setting
		Set<PartImpl> requirements;

		JSONArray requirementArray = jsonObject.getJSONArray("requirementsList");
		int lenghtRequirementArray = requirementArray.length();

		for (int i=0; i<lenghtRequirementArray;i++){
			requirements = new HashSet<>();
			JSONObject currentRequirement = requirementArray.getJSONObject(i);
			PartImpl reference = getPartByName(currentRequirement.getString("name"));
			JSONArray partRequirements = currentRequirement.getJSONArray("requirements");
			int lenghtpartRequirements = partRequirements.length();
			for (int j=0; j<lenghtpartRequirements;j++){
				PartImpl part = getPartByName(partRequirements.getJSONObject(j).getString("name"));
				requirements.add(part);

			}
			incompatibiblityManager.addRequirements(reference, requirements);
		}

		//incompatibilities setting

		Set<PartImpl> incompatibilities;

		JSONArray incompatibilityArray = jsonObject.getJSONArray("incompatibilityList");
		int lenghtincompatibilityArray = incompatibilityArray.length();

		for (int i=0; i<lenghtincompatibilityArray;i++){
			incompatibilities = new HashSet<>();
			JSONObject currentIncompatibility = incompatibilityArray.getJSONObject(i);
			PartImpl reference = getPartByName(currentIncompatibility.getString("name"));
			JSONArray partIncompatiilies = currentIncompatibility.getJSONArray("incompatibilities");
			int lenghtpartIncompatiilies = partIncompatiilies.length();
			for (int j=0; j<lenghtpartIncompatiilies;j++){
				PartImpl part = getPartByName(partIncompatiilies.getJSONObject(j).getString("name"));
				incompatibilities.add(part);
			}

			incompatibiblityManager.addIncompatibilities(reference, incompatibilities);

		}





	}
	@Override
	public StringBuilder readFile  (File jsonFile){
		StringBuilder jsonText = new StringBuilder();
		FileReader fr = null;
		try {
			fr = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String line = null;
		try {
			line = br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (line != null)
		{
			try {
				jsonText.append(line);
				line = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			br.close();
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonText;
	}
	@Override
	public Set<PartImpl>getVariantsForCategory(String name){
		if(name!=null){
			for (CategoryImpl category : categorySet){
				if(category.getCategoryName().equalsIgnoreCase(name)){
					return Collections.unmodifiableSet(category.getParts());
				}
			}

		}
		return null;

	}


	@Override
	public PartImpl getPartByName(String partName){
		PartImpl part = null ;
		boolean found =false;
		for (CategoryImpl category : categorySet){
			for (PartImpl partImpl : category.getParts()){
				if(partImpl.getPartName().equalsIgnoreCase(partName)){
					part = partImpl;
					found = true;
					break;
				}
			}
			if(found)break;
		}

		return part;
	}
	@Override
	public void printDescription() {

		System.out.println("Category   PartName   PartDescription     incompatibilities        requirements\n");
		for (CategoryImpl category : categorySet){
			System.out.println(category.getCategoryName());

			for (PartImpl part : category.getParts()) {
				System.out.print("            " + part.getPartName() + "  ---  " + part.getPartDescription() + "    ");

				Set<PartImpl> requirements = incompatibiblityManager.getRequirements(part);
				Set<PartImpl> incompatibilities = incompatibiblityManager.getIncompatibilities(part);

				if(incompatibilities!=null)
				for (PartImpl incomp : incompatibilities) {
					System.out.print(incomp.getPartName() + "*");
				}
				System.out.print("---");
				if(requirements!=null)
				for (PartImpl req : requirements) {

					System.out.println(req.getPartName() + "*");
				}

				  System.out.println();

			}
		}
	}
	@Override
	public boolean addRequirement(String requirement,String reference){
		PartImpl partRequirement=getPartByName(requirement);
		PartImpl partReference=getPartByName(reference);
		return incompatibiblityManager.addRequirement(partRequirement,partReference);
	}
	@Override
	public boolean removeRequirement(String requirement,String reference){
		PartImpl partRequirement=getPartByName(requirement);
		PartImpl partReference=getPartByName(reference);
		return incompatibiblityManager.removeRequirement(partRequirement,partReference);
	}
	@Override
	public boolean addIncompatibility(String incompatibility,String reference){
		PartImpl partRequirement=getPartByName(incompatibility);
		PartImpl partReference=getPartByName(reference);
		return incompatibiblityManager.addIncompatibility(partRequirement,partReference);
	}
	@Override
	public boolean removeIncompatibility(String incompatibility,String reference){
		PartImpl partRequirement=getPartByName(incompatibility);
		PartImpl partReference=getPartByName(reference);
		return incompatibiblityManager.removeIcompatibility(partRequirement,partReference);
	}
	@Override
	public Set<CategoryImpl> getCategorySet() {
		return Collections.unmodifiableSet(categorySet);
	}
}


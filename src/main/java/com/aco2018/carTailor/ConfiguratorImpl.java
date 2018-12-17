package com.aco2018.carTailor;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
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

	private Set<CategoryImpl> categorySet = new HashSet<>();



	private IncompatibiblityManagerImpl incompatibiblityManager= IncompatibiblityManagerImpl.getInstance();

	public ConfiguratorImpl(){
		super();
	}

	public Set<CategoryImpl> getCategorySet() {
		return categorySet;
	}


	@Override
	public Set<PartTypeImpl>getVariantsForCategory(String name){
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
				PartTypeImpl currentPartTypeImpl = new PartTypeImpl(partObject.getString("name"),partObject.getString("description"));
				PartImp currentPartImp = new PartImp(currentPartTypeImpl);
				currentPartImp.price = Double.parseDouble(partObject.getString("prix"));
                currentPartTypeImpl.setPartImp(currentPartImp);


				if(currentCategory.getCategoryName().equalsIgnoreCase("exterior")){
                    JSONArray colorListArray=partObject.getJSONArray("colorList");
                    int lenghtcolorListArray=colorListArray.length();
                    Set<String> possibilities = new HashSet<>();

                    for(int k=0;k<lenghtcolorListArray;k++){
                        possibilities.add(colorListArray.getString(k));
                    }

                    currentPartImp.addProperty("color", currentPartImp::getColor, currentPartImp::setColor, possibilities);
                }

				currentPartTypeImpl.setCategory(currentCategory);
				currentCategory.addPartType(currentPartTypeImpl);
			}

			categorySet.add(currentCategory);

		}

		//requirements setting
		Set<PartTypeImpl> requirements;

		JSONArray requirementArray = jsonObject.getJSONArray("requirementsList");
		int lenghtRequirementArray = requirementArray.length();

		for (int i=0; i<lenghtRequirementArray;i++){
			requirements = new HashSet<>();
			JSONObject currentRequirement = requirementArray.getJSONObject(i);
			PartTypeImpl reference = getPartTypeByName(currentRequirement.getString("name"));
			JSONArray partRequirements = currentRequirement.getJSONArray("requirements");
			int lenghtpartRequirements = partRequirements.length();
			for (int j=0; j<lenghtpartRequirements;j++){
				PartTypeImpl part = getPartTypeByName(partRequirements.getJSONObject(j).getString("name"));
				requirements.add(part);

			}
			incompatibiblityManager.addRequirements(reference, requirements);
		}

		//incompatibilities setting

		Set<PartTypeImpl> incompatibilities;

		JSONArray incompatibilityArray = jsonObject.getJSONArray("incompatibilityList");
		int lenghtincompatibilityArray = incompatibilityArray.length();

		for (int i=0; i<lenghtincompatibilityArray;i++){
			incompatibilities = new HashSet<>();
			JSONObject currentIncompatibility = incompatibilityArray.getJSONObject(i);
			PartTypeImpl reference = getPartTypeByName(currentIncompatibility.getString("name"));
			JSONArray partIncompatiilies = currentIncompatibility.getJSONArray("incompatibilities");
			int lenghtpartIncompatiilies = partIncompatiilies.length();
			for (int j=0; j<lenghtpartIncompatiilies;j++){
				PartTypeImpl part = getPartTypeByName(partIncompatiilies.getJSONObject(j).getString("name"));
				incompatibilities.add(part);
			}

			incompatibiblityManager.addIncompatibilities(reference, incompatibilities);

		}

		//


	}
	@Override
	public PartImp choosePartImp(String partName){
       return  getPartTypeByName(partName)==null?null:getPartTypeByName(partName).getPartImp();
	}
	@Override
    public PartTypeImpl getPartTypeByName(String partName){
        PartTypeImpl partType = null ;
        boolean found =false;
        for (Category category : categorySet){
            for (PartTypeImpl partTypeImpl : category.getParts()){
                if(partTypeImpl.getPartTypeName().equalsIgnoreCase(partName)){
                    partType = partTypeImpl;
                    found = true;
                    break;
                }
            }
            if(found)break;
        }

        return partType;
    }
	@Override
	public boolean addRequirement(String requirement,String refererence){
		PartTypeImpl partRequirement=getPartTypeByName(requirement);
		PartTypeImpl partReference=getPartTypeByName(refererence);
		return incompatibiblityManager.addRequirement(partRequirement,partReference);
	}
	@Override
	public boolean removeRequirement(String requirement,String referernce){
		PartTypeImpl partRequirement=getPartTypeByName(requirement);
		PartTypeImpl partReference=getPartTypeByName(referernce);
		return incompatibiblityManager.removeRequirement(partRequirement,partReference);
	}
	@Override
	public boolean addIncompatibility(String incompatibility,String referernce){
		PartTypeImpl partIncompatibility=getPartTypeByName(incompatibility);
		PartTypeImpl partReference=getPartTypeByName(referernce);
		return incompatibiblityManager.addIncompatibility(partIncompatibility,partReference);
	}
	@Override
	public boolean removeIncompatibility(String incompatibility,String referernce){
		PartTypeImpl partIncompatibility=getPartTypeByName(incompatibility);
		PartTypeImpl partReference=getPartTypeByName(referernce);
		return incompatibiblityManager.removeIcompatibility(partIncompatibility,partReference);
	}



}


package com.aco2018.carTailor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class ClientTest {
    static Client  client=new Client();
    private static ConfigurationImpl configuration;
    private  static  ConfiguratorImpl configurator;
    @BeforeAll
     static void initialisation(){

        configurator=client.getConfiguratorImpl();
        configurator.launch(new File("adminConfiguration.json"));
        configuration=client.getConfigurationImpl();
        configuration.register(client);
    }


    @Test
    void demarrerSimulation() {
        assertTrue(!configurator.getCategorySet().isEmpty());

        CategoryImpl myCategory=chooseCategory("carosserie");
        assertEquals(myCategory,null);
        myCategory=chooseCategory("TRANSMISSION");
        assertTrue(myCategory!=null);

        PartImpl myPartImpl=selectPartImpl(myCategory,"REGUIO");;
        assertEquals(myPartImpl,null);
        myPartImpl=selectPartImpl(myCategory,"TA5");

        assertTrue(configuration.addPart(myPartImpl));

        myPartImpl=selectPartImpl(myCategory,"TC120");
        assertTrue(configuration.addPart(myPartImpl));
        myCategory=chooseCategory("ENGINE");

        myPartImpl=selectPartImpl(myCategory,"EG100");
        configuration.addPart(myPartImpl);
        assertFalse(client.isComplete());
        assertFalse(client.isValid());

        myCategory=chooseCategory("ENGINE");

        myPartImpl=selectPartImpl(myCategory,"EH120");
        configuration.addPart(myPartImpl);
        assertTrue(client.isComplete());
        assertFalse(client.isValid());

        myCategory=chooseCategory("ENGINE");

        myPartImpl=selectPartImpl(myCategory,"EG100");

        assertFalse(configuration.deletePart("edefrf"));
        assertTrue(configuration.deletePart(myPartImpl.getPartName()));
        assertTrue(client.isComplete());
        assertTrue(client.isComplete());




    }


    CategoryImpl chooseCategory(String nameCategory){
        CategoryImpl myCategory=null;
        for (CategoryImpl category:configurator.getCategorySet()){
            if(category.getCategoryName().equalsIgnoreCase(nameCategory)){
                myCategory=category;
                break;
            }
        }
        return myCategory;
    }

    PartImpl selectPartImpl(CategoryImpl category,String namePartImpl){
        PartImpl myPartImpl=null;
        for (PartImpl part:category.getParts()){
            if(part.getPartName().equalsIgnoreCase(namePartImpl)){
                myPartImpl=part;
                break;
            }
        }
        return myPartImpl;
    }



}
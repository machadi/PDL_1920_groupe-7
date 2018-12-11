package com.aco2018.carTailor;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    static Client client;
    static private ConfigurationImpl configuration;
    static private  ConfiguratorImpl configurator;
    @BeforeAll
    static void initialisation(){
        client=new Client();
        configurator=client.getConfigurator();
        configurator.launch(new File("adminConfiguration.json"));
        configuration=client.getConfiguration();
        configuration.register(client);
    }

    @Test
    void demarrerSimulation() {
        assertTrue(!configurator.getCategorySet().isEmpty());

        CategoryImpl myCategory=chooseCategory("carosserie");
        assertEquals(myCategory,null);
        myCategory=chooseCategory("TRANSMISSION");
        assertTrue(myCategory!=null);

        PartImp myPartImp=selectPartImpl(myCategory,"REGUIO");;
        assertEquals(myPartImp,null);
        myPartImp=selectPartImpl(myCategory,"TA5");

        assertTrue(configuration.addPart(myPartImp));

        myPartImp=selectPartImpl(myCategory,"TC120");
        assertTrue(configuration.addPart(myPartImp));
        myCategory=chooseCategory("ENGINE");

        myPartImp=selectPartImpl(myCategory,"EG100");
        configuration.addPart(myPartImp);
        assertFalse(client.isComplete());
        assertFalse(client.isValid());

        myCategory=chooseCategory("ENGINE");

        myPartImp=selectPartImpl(myCategory,"EH120");
        configuration.addPart(myPartImp);
        assertTrue(client.isComplete());
        assertFalse(client.isValid());

        //Car la configuration est invalide
        assertEquals(configuration.getPriceConfiguation(),0);

        myCategory=chooseCategory("ENGINE");

        myPartImp=selectPartImpl(myCategory,"EG100");

        assertFalse(configuration.deletePart("edefrf"));
        assertTrue(configuration.deletePart(myPartImp.getName()));

        assertEquals(configuration.getPriceConfiguation(),15290);

        assertTrue(client.isComplete());
        assertTrue(client.isComplete());


        myCategory=chooseCategory("EXTERIOR");

        myPartImp=selectPartImpl(myCategory,"XC");
        configuration.addPart(myPartImp);


        assertEquals(configuration.getPriceConfiguation(),16190);
        myPartImp.setColor("BLEU");
        assertEquals(configuration.getPriceConfiguation(),16390);

        myPartImp.setColor("vert");

        assertEquals(configuration.getPriceConfiguation(),16760);

        try {
            configuration.printDesciption(new PrintStream(new FileOutputStream("configuration.html")));
           // assertTrue(FileUtils.contentEquals(new File("configurationTestClient.html"),new File("configuration.html")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }








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

    PartImp selectPartImpl(CategoryImpl category,String namePartImpl){
        PartImp myPartImpl=null;
        for (PartTypeImpl part:category.getParts()){
            if(part.getPartImp().getName().equalsIgnoreCase(namePartImpl)){
                myPartImpl=part.getPartImp();
                break;
            }
        }
        return myPartImpl;
    }



}
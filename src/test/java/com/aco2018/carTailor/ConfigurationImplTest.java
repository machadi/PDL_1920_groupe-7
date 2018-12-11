package com.aco2018.carTailor;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ConfigurationImplTest {
    ConfiguratorImpl configurator;
    ConfigurationImpl configuration;
    @BeforeEach
    void createConfiguration(){
        configurator=new ConfiguratorImpl();
        configurator.launch(new File("adminConfiguration.json"));
        configuration=new ConfigurationImpl(IncompatibiblityManagerImpl.getInstance());
    }
    @Test
    void addPart() {
        assertFalse(configuration.addPart(configurator.choosePartImp("EROU")));
        assertTrue(configuration.addPart(configurator.choosePartImp("ED110")));
        assertTrue(configuration.addPart(configurator.choosePartImp("XM")));
        assertFalse(configuration.addPart(configurator.choosePartImp("ED110")));

    }

    @Test
    void deletePart() {
        Set<PartImp> preConf=new HashSet();
        addPart();
        assertFalse(preConf.containsAll(configuration.getPartSet())&&configuration.getPartSet().containsAll(preConf));
        assertTrue(configuration.deletePart("ED110"));
        assertFalse(configuration.deletePart("frff"));
        assertFalse(configuration.deletePart(null));
    }

    @Test
    void resetConf() {
        Set<PartImp> preConf=new HashSet();
        addPart();
        assertFalse(preConf.containsAll(configuration.getPartSet())&&configuration.getPartSet().containsAll(preConf));
        configuration.resetConf();
        assertEquals(preConf,configuration.getPartSet());
    }

    @Test
    void isComplete() {
        configuration.addPart(configurator.choosePartImp("EG100"));
        configuration.addPart(configurator.choosePartImp("EH120"));
        configuration.addPart(configurator.choosePartImp("TC120"));
        assertTrue(configuration.isComplete());
        configuration.deletePart("TC120");
        assertFalse(configuration.isComplete());

    }

    @Test
    void isValid() {
        configuration.addPart(configurator.choosePartImp("EG100"));
        configuration.addPart(configurator.choosePartImp("EH120"));
        configuration.addPart(configurator.choosePartImp("TC120"));
        configuration.addPart(configurator.choosePartImp("TA5"));
        assertFalse(configuration.isValid());
        configuration.deletePart("EG100");
        assertTrue(configuration.isValid());
    }

    @Test
    void getPriceConfiguation() {
        assertEquals(0,configuration.getPriceConfiguation());
        addPart();
        assertEquals(5170,configuration.getPriceConfiguation());
    }


}
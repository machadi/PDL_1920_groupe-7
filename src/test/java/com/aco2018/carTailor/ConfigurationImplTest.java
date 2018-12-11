package com.aco2018.carTailor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
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
        //assertFalse(configuration.addPart(configurator.getPartByName("EROU")));
        assertTrue(configuration.addPart(configurator.getPartByName("ED110")));
        assertTrue(configuration.addPart(configurator.getPartByName("XM")));
        assertFalse(configuration.addPart(configurator.getPartByName("ED110")));
    }

    @Test
    void deletePart() {
        Set<PartImpl> preConf=new HashSet();
        addPart();
        assertFalse(preConf.containsAll(configuration.getPartSet())&&configuration.getPartSet().containsAll(preConf));
        assertTrue(configuration.deletePart("ED110"));
        assertFalse(configuration.deletePart("frff"));
        assertFalse(configuration.deletePart(null));

    }

    @Test
    void resetConf() {
        Set<PartImpl> preConf=new HashSet();
        addPart();
        assertFalse(preConf.containsAll(configuration.getPartSet())&&configuration.getPartSet().containsAll(preConf));
        configuration.resetConf();
        assertEquals(preConf,configuration.getPartSet());
    }



    @Test
    void isComplete() {
        configuration.addPart(configurator.getPartByName("EG100"));
        configuration.addPart(configurator.getPartByName("EH120"));
        configuration.addPart(configurator.getPartByName("TC120"));
        assertTrue(configuration.isComplete());
        configuration.deletePart("TC120");
        assertFalse(configuration.isComplete());

    }
    @Test
    void isValid() {
        configuration.addPart(configurator.getPartByName("EG100"));
        configuration.addPart(configurator.getPartByName("EH120"));
        configuration.addPart(configurator.getPartByName("TC120"));
        configuration.addPart(configurator.getPartByName("TA5"));
        assertFalse(configuration.isValid());
        configuration.deletePart("EG100");
        assertTrue(configuration.isValid());

    }
}
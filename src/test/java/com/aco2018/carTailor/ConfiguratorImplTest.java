package com.aco2018.carTailor;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class ConfiguratorImplTest {

    ConfiguratorImpl configurator;
    @BeforeEach
    void createConfiguration(){
        configurator=new ConfiguratorImpl();
        configurator.launch(new File("adminConfiguration.json"));
    }

    @Test
    void addCategory() {
        for(CategoryImpl category:configurator.getCategorySet()){
            if(category.getCategoryName()=="Engine"){
                assertEquals(6,category.getParts().size());
            }else if (category.getCategoryName()=="Transmission"){
                assertEquals(6,category.getParts().size());
            }else if (category.getCategoryName()=="Interior"){
                assertEquals(3,category.getParts().size());
            }else if(category.getCategoryName()=="Exterior"){
                assertEquals(3,category.getParts().size());
            }
        }
    }

    @Test
    void getPartByName() {
        assertTrue("XS".equalsIgnoreCase(configurator.getPartByName("XS").getPartName()));
        assertEquals(null,(configurator.getPartByName("fvvgvgb")));
    }

    @Test
    void addRequirement() {

        assertTrue(configurator.addRequirement("IH","TM5"));
        assertFalse(configurator.addRequirement("IH","TM5"));
        assertFalse(configurator.addRequirement("AMG","TM5"));
        assertFalse(configurator.addRequirement("TM5","AMOJ"));
        assertFalse(configurator.addRequirement("TRTY","AMOJ"));


    }

    @Test
    void removeRequirement() {
        assertTrue(configurator.removeRequirement("EH120","TC120"));
        assertFalse(configurator.removeRequirement("AMG","TM5"));
        assertFalse(configurator.removeRequirement("TM5","AMOJ"));
        assertFalse(configurator.removeRequirement("TRTY","AMOJ"));
    }

    @Test
    void addIncompatibility() {
        assertTrue(configurator.addIncompatibility("IH","TM5"));

        assertFalse(configurator.addIncompatibility("AMG","TM5"));
        assertFalse(configurator.addIncompatibility("TM5","AMOJ"));
        assertFalse(configurator.addIncompatibility("TRTY","AMOJ"));

    }

    @Test
    void removeIncompatibility() {
        assertTrue(configurator.removeIncompatibility("EG210","XC"));
        assertFalse(configurator.removeIncompatibility("AMG","TM5"));
        assertFalse(configurator.removeRequirement("TM5","AMOJ"));
        assertFalse(configurator.removeRequirement("TRTY","AMOJ"));
    }

    @Test
    void getCategorySet() {
        assertEquals(4,configurator.getCategorySet().size());
    }
}
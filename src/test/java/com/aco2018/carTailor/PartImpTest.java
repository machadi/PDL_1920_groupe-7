package com.aco2018.carTailor;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PartImpTest {

    PartImp partimp ;
    PartTypeImpl partType;
    @BeforeEach
    void creatingPart(){
        partType = new PartTypeImpl("part", "description");
        partimp =new PartImp(partType);
    }

    void addProperty() {
        Set<String> possibilities= new HashSet<>();
        possibilities.add("bleu");
        possibilities.add("blanc");
        possibilities.add("rouge");
        partimp.addProperty("color", partimp::getColor, partimp::setColor, possibilities);
    }

    @Test
    void getPropertyNames() {
        addProperty(); // add one property
        assertEquals(1, partimp.getPropertyNames().size());

    }

    @Test
    void getProperty() {

        assertTrue(partimp.getAvailablePropertyValues("inexistant").isEmpty());
        addProperty();//add color
        assertFalse(partimp.getAvailablePropertyValues("color").isEmpty());

    }

    @Test
    void getPartType() {
        assertEquals(partimp.getPartType(), partType);
    }

    @Test
    void getColor() {
        assertTrue(partimp.getColor() == null);
        addProperty();//add property color but color is ever empty
        assertTrue(partimp.getColor()==null);
        partimp.setColor("blanc");
        assertTrue(partimp.getColor().equalsIgnoreCase("blanc"));
    }

    @Test
    void getName() {

        assertTrue(partimp.getName().equalsIgnoreCase("part"));
    }

    @Test
    void getPrice() {
        assertTrue(partimp.getPrice() ==0);
        addProperty();// color
        partimp.setColor("bleu");
        assertEquals(200, partimp.getPrice());
    }

    @Test
    void getPropertyNames1() {
        assertTrue(partimp.getPropertyNames().isEmpty());
        addProperty();//add color property
        assertFalse(partimp.getPropertyNames().isEmpty());
        assertTrue(partimp.getPropertyNames().size()==1);


    }

    @Test
    void getAvailablePropertyValues() {
        assertTrue(partimp.getAvailablePropertyValues("inexistant").isEmpty());
        addProperty();//add color property with 3 values
        assertFalse(partimp.getAvailablePropertyValues("color").isEmpty());
        assertFalse(partimp.getAvailablePropertyValues("colror").size()==3);
    }

    @Test
    void setColor() {
        addProperty();//add color property
        partimp.setColor("bleu");
        assertTrue(partimp.getColor().equalsIgnoreCase("bleu"));
    }

    @Test
    void getColor1() {
        assertTrue(partimp.getColor()==null);
        addProperty();
        partimp.setColor("blanc");
        assertFalse(partimp.getColor()==null);
        assertTrue(partimp.getColor().equalsIgnoreCase("blanc"));
    }
}
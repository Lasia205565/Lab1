package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lab_1.Lab1;

public class TestCase2_2 {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
 
    @Test
    public void testQueryBridgeWords() {
        Lab1 lab1 = new Lab1();
        lab1.init("data");
        assertEquals("The bridge words from \"made\" to \"own\" is: her, our",Lab1.queryBridgeWords("made","own"));
    }
    }




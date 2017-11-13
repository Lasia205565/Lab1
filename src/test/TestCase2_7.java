package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lab_1.Lab1;

public class TestCase2_7 {

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
        assertEquals("ERROR: No \"wait\" in the graph!",Lab1.queryBridgeWords("wait","something"));
    }

}


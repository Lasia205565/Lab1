package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import boundary.OpenTextInterface;
import control.BridgeWordControl;
import entity.DirectedGraph;

public class TestCase2_8 {

	DirectedGraph graph;
	@Before
	public void setUp() throws Exception {
	    OpenTextInterface tf = new OpenTextInterface();
    	tf.execute("data");
    	graph = tf.getGraph();
	}

    @After
    public void tearDown() throws Exception {
    }
 
    @Test
    public void testQueryBridgeWords() {
        BridgeWordControl bc = new BridgeWordControl(graph);
        assertEquals("ERROR: No \"wait\" in the graph!",bc.queryBridgeWords("something","wait"));
    }

}


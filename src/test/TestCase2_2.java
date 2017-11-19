package test;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import boundary.BridgeWord;
import boundary.TargetFile;
import entity.DirectedGraph;
import lab1.Lab1;

public class TestCase2_2 {

	DirectedGraph graph;
	@Before
	public void setUp() throws Exception {
    	TargetFile tf = new TargetFile();
    	tf.execute("data");
    	graph = tf.getGraph();
	}

    @After
    public void tearDown() throws Exception {
    }
 
    @Test
    public void testQueryBridgeWords() {
    	BridgeWord bw = new BridgeWord(graph);
        assertEquals("The bridge words from \"made\" to \"own\" is: her, our",bw.queryBridgeWords("made","own"));
    }
    }




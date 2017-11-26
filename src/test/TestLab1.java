package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import boundary.OpenTextInterface;
import entity.DirectedGraph;

public class TestLab1 {
	
	DirectedGraph graph;
	@Before
	public void setUp() throws Exception {
	    OpenTextInterface tf = new OpenTextInterface();
    	tf.execute("data");
    	graph = tf.getGraph();
	}

	@Test
	public void testQueryBridgeWords() {
		List<String> test1 = graph.findBridgeWords("aaa", "bbb");
		assertTrue(test1.isEmpty());
		List<String> test2 = graph.findBridgeWords("silk", "make");
		assertTrue(test2.isEmpty());
		List<String> test3 = graph.findBridgeWords("own", "silk");
		assertTrue(test3.isEmpty());
		List<String> test4 = graph.findBridgeWords("how", "make");
		assertTrue(test4.get(0).equals("to"));
		List<String> test5 = graph.findBridgeWords("aaa", "make");
		assertTrue(test5.isEmpty());
	}

}

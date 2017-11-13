package test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import lab_1.Lab1;

public class TestLab1 {

	@Before
	public void setUp() throws Exception {
		Lab1 lab1 = new Lab1();
		lab1.init("/home/siyuan/data");
	}

	@Test
	public void testQueryBridgeWords() {
		List<String> test1 = Lab1.findBridgeWords("aaa", "bbb");
		assertTrue(test1.isEmpty());
		List<String> test2 = Lab1.findBridgeWords("silk", "make");
		assertTrue(test2.isEmpty());
		List<String> test3 = Lab1.findBridgeWords("own", "silk");
		assertTrue(test3.isEmpty());
		List<String> test4 = Lab1.findBridgeWords("how", "make");
		assertTrue(test4.get(0).equals("to"));
		List<String> test5 = Lab1.findBridgeWords("aaa", "make");
		assertTrue(test5.isEmpty());
	}
	@Test
	public void testCalcShortestPath() {
		String test1 = Lab1.calcShortestPath("aaa", "bbb");
		assertTrue(test1.equals("ERROR: No \"aaa\" and \"bbb\" in the graph!"));
		String test2 = Lab1.calcShortestPath("family", "bbb");
		assertTrue(test2.equals("ERROR: No \"bbb\" in the graph!"));
		String test3 = Lab1.calcShortestPath("aaa", "made");		
		assertTrue(test3.equals("ERROR: No \"aaa\" in the graph!"));
		String test4 = Lab1.calcShortestPath("something", "going");		
		assertTrue(test4.equals("from \"something\" to \"going\" : Unreachable"));
		String test5 = Lab1.calcShortestPath("roof", "roof");		
		assertTrue(test5.equals("from \"roof\" to \"roof\" : roof --> roof"));
		String test6 = Lab1.calcShortestPath("going", "for");		
		assertTrue(test6.equals("from \"going\" to \"for\" : going --> to --> make --> a --> box --> for"));
	}
}

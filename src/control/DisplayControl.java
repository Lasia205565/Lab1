package control;

import entity.DirectedGraph;

public class DisplayControl {
	/**
	 * show graph.
	 * @param g g
	 * @param way way
	 */
	public void showGraph(final DirectedGraph g, final int way) {
	    if (way == 0) {
	        System.out.println(g.toString());
	    } else {
	        g.printGraph();
	    }
	}
}

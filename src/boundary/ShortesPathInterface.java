package boundary;

import java.util.Scanner;

import control.ShortesPathControl;
import entity.DirectedGraph;


public class ShortesPathInterface {
	
	DirectedGraph graph;

	public ShortesPathInterface(DirectedGraph graph) {
		super();
		this.graph = graph;
	}

	public void execute() {
		@SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);

	    if (graph == null) {
	        System.out.println("ERROR: Please open a file first.");
			
	        return;
	    }
	    System.out.println(
	            "Please enter one or two words(eg: hungry / "
	            + "eat noodles). The first two will be taken "
	            + "if there're more than two:");
	    String[] a1 = (sc.nextLine()).split("\\s+");
	    ShortesPathControl spc = new ShortesPathControl(graph);
	    if (a1.length >= 2) {
	        
	        System.out.print(spc.calcShortestPath(a1[0], a1[1]));
	        return;
	    } else {
	        if (a1[0].equals("")) {
	            System.out.println("ERROR: No words found. "
	                    + "Check the input.");
	            return;
	        }
	        System.out.print(spc.calcShortestPath(a1[0]));
	        return;
	    }
		
	}

}

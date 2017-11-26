package boundary;


import java.util.Scanner;


import control.NewTextControl;
import entity.DirectedGraph;

public class NewTextInterface{
	DirectedGraph graph;

	public NewTextInterface(DirectedGraph graph) {
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
	    System.out.println("Please enter a sentence"
	            + "(eg: many girls one two three) :");
	    String line2 = sc.nextLine();
	    if (line2.isEmpty()) {
	        System.out.println("ERROR: Can't read the sentence. "
	                + "Check the input.");
			
			return;
	    }
	    NewTextControl nc = new NewTextControl(graph);
	    System.out.println("> > " + nc.generateNewText(line2));
		
	}

}

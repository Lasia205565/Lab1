package boundary;

import java.util.Scanner;

import entity.DirectedGraph;

public class ShowGraphInterface {
	
	DirectedGraph graph;

	public ShowGraphInterface(DirectedGraph graph) {
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
        System.out.print("Do you want the Graph view (default) or "
                + "the Text view? (g or t) ");
        String way = sc.nextLine();
		
        if (way.equals("t")) {
            System.out.println(graph.toString());
        } else {
            graph.printGraph();
        }
	}

}

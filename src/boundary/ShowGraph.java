package boundary;

import java.util.Scanner;

import control.DisplayControl;
import entity.DirectedGraph;

public class ShowGraph {
	
	DirectedGraph graph;

	public ShowGraph(DirectedGraph graph) {
		super();
		this.graph = graph;
	}


	public void execute() {
		Scanner sc = new Scanner(System.in);
        if (graph == null) {
            System.out.println("ERROR: Please open a file first.");
            return;
        }
        System.out.print("Do you want the Graph view (default) or "
                + "the Text view? (g or t) ");
        String way = sc.nextLine();
		
		DisplayControl dc = new DisplayControl();
        if (way.equals("t")) {
            dc.showGraph(graph, 0);
        } else {
            dc.showGraph(graph, 1);
        }
	}

}

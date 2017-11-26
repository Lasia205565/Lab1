package boundary;


import java.util.Scanner;

import control.BridgeWordControl;
import entity.DirectedGraph;

public class BridgeWordInterface {

	DirectedGraph graph;

	public BridgeWordInterface(DirectedGraph graph) {
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
				"Please enter two words(eg: apple tasty). The first " + "two will be taken if there're more than two:");
		String[] a = sc.nextLine().split("\\s+");
		if (a.length < 2) {
			System.out.println("ERROR: No enough words." + "Check the input.");
			
			return;
		}
		BridgeWordControl bc = new BridgeWordControl(graph);
		System.out.print(bc.queryBridgeWords(a[0], a[1]));
		
	}	

}

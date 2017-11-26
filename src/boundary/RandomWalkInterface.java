package boundary;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

import control.RandomWalkControl;
import entity.DirectedGraph;

public class RandomWalkInterface{
	
	DirectedGraph graph;

	public RandomWalkInterface(DirectedGraph graph) {
		super();
		this.graph = graph;
	}

	public void execute() {
	    if (graph == null) {
	        System.out.println("ERROR: Please open a file first.");
	        return;
	    }
	    System.out.println("Here comes the random walk;"
	            + " Enter 's' to force it stop.");
	    RandomWalkControl rc = new RandomWalkControl(graph);
	    String randomText = rc.randomWalk();
	    System.out.println("\nThe Random Path: \n> > "
	            + randomText
	            + "\nWhere do you want to save this text? ");
		@SuppressWarnings("resource")
        Scanner sc = new Scanner(System.in);
	    String filename = sc.nextLine();
        
	    if (filename.isEmpty()) {
	        return;
	    }
	    try {
	        PrintWriter aPrintWriter = new PrintWriter(filename); // NOPMD by hit_s on 17-10-19 下午10:31
	        aPrintWriter.println(randomText);
	        aPrintWriter.flush();
	        aPrintWriter.close();
	    } catch (FileNotFoundException e) {
	        System.out.println("ERROR: Can't save the file. ");
	        System.out.println(e);
	    }
		
	}


}

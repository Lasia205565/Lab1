package boundary;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import entity.DirectedGraph;

public class RandomWalk{
	
	DirectedGraph graph;

	public RandomWalk(DirectedGraph graph) {
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
	    String randomText = randomWalk();
	    System.out.println("\nThe Random Path: \n> > "
	            + randomText
	            + "\nWhere do you want to save this text? ");
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

	/**
	 * random walk.
	 * @return random walk
	 */
	String randomWalk() {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    PrintStream output = new PrintStream(bos);
	    ArrayList<String> nextWordsList;
	    ArrayList<int[]> edgePairList = new ArrayList<int[]>(graph.getSize());
	    Random random = new Random();
		Scanner sc = new Scanner(System.in);
		
	    int s = random.nextInt(graph.getVertexSize()); // 随机选中第一个词
	    String preWord = graph.getWord(s);
	    String nextWord = null;
	    System.out.println(preWord);
	    output.append(preWord + " ");
	
	    while (true) {
	        String choice = sc.nextLine();
	        
	        if (choice.equals("s")) {
	            System.out.println("DONE: User command is received.");
	            return bos.toString();
	        } else {
	            nextWordsList = graph.findNext(preWord);
	            if (nextWordsList.isEmpty()) {
	                System.out.println("DONE: No word comes from this one.");
	                return bos.toString();
	            } else {
	                int m = random.nextInt(nextWordsList.size());
	                nextWord = nextWordsList.get(m);
	                System.out.println(nextWord);
	                output.append(nextWord + " ");
	                int[] edgePair = {graph.locateVertex(preWord), m };
	                for (int[] tempPair : edgePairList) {
	                    if (tempPair[0] == edgePair[0]
	                            && tempPair[1] == edgePair[1]) {
	                        System.out.println("DONE: Have been to this edge.");
	                        return bos.toString();
	                    }
	                }
	                edgePairList.add(edgePair);
	                preWord = nextWord;
	            }
	        } // else
	    } // while
	    
	}

}

package boundary;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import entity.DirectedGraph;


public class ShortestPath {
	
	DirectedGraph graph;

	public ShortestPath(DirectedGraph graph) {
		super();
		this.graph = graph;
	}

	public void execute() {
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
		
	    if (a1.length >= 2) {
	        System.out.print(calcShortestPath(a1[0], a1[1]));
	        return;
	    } else {
	        if (a1[0].equals("")) {
	            System.out.println("ERROR: No words found. "
	                    + "Check the input.");
	            return;
	        }
	        System.out.print(calcShortestPath(a1[0]));
	        return;
	    }
		
	}

	/**
	 * calc..
	 * @param word1 word1
	 * @param word2 word1
	 * @return path
	 */
	public String calcShortestPath(final String word1, final String word2) {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    int v5 = graph.locateVertex(word1.toLowerCase());
	    int v6 = graph.locateVertex(word2.toLowerCase());
	    if (v5 == -1 && v6 == -1) {
	        return ("ERROR: No \"" + word1 + "\" and \"" + word2
	                + "\" in the graph!");
	    } else if (v5 == -1 && v6 != -1) {
	        return ("ERROR: No \"" + word1 + "\" in the graph!");
	    } else if (v5 != -1 && v6 == -1) {
	        return ("ERROR: No \"" + word2 + "\" in the graph!");
	    }
	    PrintStream output = new PrintStream(bos);
	    int num = graph.getVertexSize();
	    int mindis;
	    int mindisIndex = v5;
	    int[] distance = new int[num];
	    int[] path = new int[num];
	    boolean[] sign1 = new boolean[num];
	    for (int i = 0; i < num; i++) {
	        distance[i] = graph.getWeight(v5, i);
	        sign1[i] = false;
	        path[i] = v5;
	    }
	    distance[v5] = 0;
	    sign1[v5] = true;
	    for (int i = 0; i < num; i++) {
	        mindis = Integer.MAX_VALUE;
	        for (int j = 0; j < num; j++) {
	            if (!sign1[j] && distance[j] < mindis) {
	                mindis = distance[j];
	                mindisIndex = j;
	            }
	        }
	        sign1[mindisIndex] = true;
	        for (int j = 0; j < num; j++) {
	            if (!sign1[j]
	                    && graph.getWeight(mindisIndex, j) != Integer.MAX_VALUE) {
	                if (graph.getWeight(mindisIndex, j) + mindis < distance[j]) {
	                    distance[j] = graph.getWeight(mindisIndex, j) + mindis;
	                    path[j] = mindisIndex;
	                }
	            }
	        }
	    }
	    for (int i = 0; i < num; i++) {
	        if (i == v6) {
	            output.print("from \"" + word1 + "\" to \"" + word2 + "\" : ");
	            StringBuffer line2 = new StringBuffer();
	            if (distance[i] == Integer.MAX_VALUE) {
	                return ("from \"" + word1 + "\" to \"" + word2
	                        + "\" : Unreachable\n");
	            } else {
	                line2.append(graph.getWord(i));
	                int t = path[i];
	                while (t != v5) {
	                    line2.insert(0, graph.getWord(t) + " --> ");
	                    t = path[t];
	                }
	                line2.insert(0, word1 + " --> ");
	            }
	            output.println(line2.toString());
	        }
	    }
	    return bos.toString();
	}

	/**
	 * calc..
	 * @param word1 word1
	 * @return path
	 */
	String calcShortestPath(final String word1) {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    PrintStream output = new PrintStream(bos);
	    int v7 = graph.locateVertex(word1.toLowerCase());
	    if (v7 == -1) {
	        return ("ERROR: No \"" + word1 + "\" in the graph!");
	    }
	    for (int i = 0; i < graph.getVertexSize(); i++) {
	        if (i != graph.locateVertex(word1)) {
	            output.print(calcShortestPath(word1, graph.getWord(i)));
	        }
	    }
	    return bos.toString();
	}

}

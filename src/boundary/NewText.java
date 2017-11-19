package boundary;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import entity.DirectedGraph;

public class NewText{
	DirectedGraph graph;

	public NewText(DirectedGraph graph) {
		super();
		this.graph = graph;
	}

	public void execute() {
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
	    System.out.println("> > " + generateNewText(line2));
		
	}

	/**
	 * generate new text.
	 * @param inputText input text
	 * @return new text
	 */
	String generateNewText(final String inputText) {
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    PrintStream output = new PrintStream(bos);
	    String[] word1 = new String[graph.getSize()];
	    word1 = inputText.split(" ");
	    output.print(word1[0] + " ");
	    for (int i = 0; i < word1.length - 1; i++) {
	        ArrayList<String> bridgeWords =
	                graph.findBridgeWords(word1[i].toLowerCase(),
	                        word1[i + 1].toLowerCase());
	        if (bridgeWords.isEmpty()) {
	            output.print(word1[i + 1] + " ");
	        } else {
	            int n = bridgeWords.size();
	            Random random = new Random();
	            int s = random.nextInt(n);
	            output.print(bridgeWords.get(s) + " ");
	            output.print(word1[i + 1] + " ");
	        }
	    }
	    return bos.toString();
	}
}

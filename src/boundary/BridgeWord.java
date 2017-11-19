package boundary;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import entity.DirectedGraph;

public class BridgeWord {

	DirectedGraph graph;

	public BridgeWord(DirectedGraph graph) {
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
				"Please enter two words(eg: apple tasty). The first " + "two will be taken if there're more than two:");
		String[] a = sc.nextLine().split("\\s+");
		if (a.length < 2) {
			System.out.println("ERROR: No enough words." + "Check the input.");
			
			return;
		}
		System.out.print(queryBridgeWords(a[0], a[1]));
		
	}

	/**
	 * query bridge words.
	 * 
	 * @param word1
	 *            word1
	 * @param word2
	 *            word2
	 * @return bridge words
	 */

	public String queryBridgeWords(final String word1, final String word2) {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		StringBuilder ret = new StringBuilder();
		int v1 = graph.locateVertex(word1.toLowerCase());
		int v2 = graph.locateVertex(word2.toLowerCase());
		ArrayList<String> bridgeWords = 
				graph.findBridgeWords(word1.toLowerCase(), word2.toLowerCase());
		if (word1 == "" && word2 == "") {
			ret.append("ERROR: No words found.Check the input.");
		} else if (word1 == "" || word2 == "") {
			ret.append("ERROR: No enough words. Check the input.");
		} else if (bridgeWords.isEmpty()) {
			if (v1 == -1 && v2 == -1) {
				ret.append("ERROR: No \"" + word1 + "\" and \"" + word2 + "\" in the graph!");
			} else if (v1 == -1 && v2 != -1) {
				ret.append("ERROR: No \"" + word1 + "\" in the graph!");
			} else if (v1 != -1 && v2 == -1) {
				ret.append("ERROR: No \"" + word2 + "\" in the graph!");
			} else {
				ret.append("ERROR: No bridge words from \"" + word1 + "\" to \"" + word2 + "\"!");
			}
		} else {
			ret.append("The bridge words from \"" + word1 + "\" to \"" + word2 + "\" is: " + bridgeWords.get(0));
			for (int i = 1; i < bridgeWords.size(); i++) {
				ret.append(", " + bridgeWords.get(i));
			}
		}
		return ret.toString() + bos.toString();
	}

}

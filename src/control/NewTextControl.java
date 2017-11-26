/**
 * 
 */
package control;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import entity.DirectedGraph;

/**
 * @author 31644
 *
 */
public class NewTextControl {
    
    DirectedGraph graph;
    
    public NewTextControl(DirectedGraph graph) {
        super();
        this.graph = graph;
    }
    
    public String generateNewText(final String inputText) {
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

/**
 * 
 */
package control;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import entity.DirectedGraph;

/**
 * @author 31644
 *
 */
public class BridgeWordControl {
    
    DirectedGraph graph;
  
    public BridgeWordControl(DirectedGraph graph) {
        super();
        this.graph = graph;
    }
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

/**
 * 
 */
package control;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import entity.DirectedGraph;

/**
 * @author 31644
 *
 */
public class RandomWalkControl {

    DirectedGraph graph;
    
    public RandomWalkControl(DirectedGraph graph) {
        super();
        this.graph = graph;
    }
    
    
    public String randomWalk() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(bos);
        ArrayList<String> nextWordsList;
        ArrayList<int[]> edgePairList = new ArrayList<int[]>(graph.getSize());
        Random random = new Random();
        @SuppressWarnings("resource")
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

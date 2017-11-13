package lab_1;

import java.io.*;
import java.util.*;

/**
 * @author Lasia
 */
public class Lab1 {
    /**
     * graph.
     */
    private static DirectedGraph graph;
    /**
     * words.
     */
    private static String[] words;
    /**
     * sc.
     */
    private static Scanner sc = new Scanner(System.in);
    /**
     * graphReady.
     */
    private static boolean graphReady; // flag that graph has been build
    /**
     * @author  someone
     */
    public static class DirectedGraph {
        /**
         * vertextList.
         */
        private ArrayList<String> vertexList;
        /**
         * edges.
         */
        private int[][] edges;
        /**
         * @param n param.
         */
        public DirectedGraph(final int n) {
            edges = new int[n][n];
            // fill the array
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                        if (i == j) {
                            edges[i][j] = 0;
                        } else {
                            edges[i][j] = Integer.MAX_VALUE;
                        }
                }
            }
            vertexList = new ArrayList<String>(n);
            graphReady = false;
        }
        /**
         * @param word word.
         */
        public DirectedGraph(final String[] word) {
            this(word.length);
            for (int i = 0; i < word.length; i++) {
                if (locateVertex(word[i]) == -1) {
                    insertVertex(word[i]);
                }
            }
            int v1, v2, newEdge;
            for (int i = 0; i < word.length - 1; i++) {
                v1 = locateVertex(word[i]);
                v2 = locateVertex(word[i + 1]);
                if (getWeight(v1, v2) == Integer.MAX_VALUE) {
                    newEdge = 1;
                } else {
                    newEdge = getWeight(v1, v2) + 1;
                }
                insertEdge(v1, v2, newEdge);
            }
        }
        /**
         * get word.
         * @param n n
         * @return word
         */
        public String getWord(final int n) {
            return vertexList.get(n);
        }
        /**
         * get edge
         * @param v1
         * @param v2
         * @return edge
         */
        public int getEdge(int v1, int v2) {
            return edges[v1][v2];
        }
        /**
         * insert vertex.
         * @param name name
         */
        public void insertVertex(final String name) {
            vertexList.add(name);
        }
        /**
         * insert edge.
         * @param v1 v1
         * @param v2 v2
         * @param weight weight
         */
        public void insertEdge(final int v1, final int v2, final int weight) {
            edges[v1][v2] = weight;
        }
        /**
         * get weight.
         * @param v1 v1
         * @param v2 v2
         * @return weight
         */
        public int getWeight(final int v1, final int v2) {
            return edges[v1][v2];
        }
        /**
         * hasEdge.
         * @param v1 v1
         * @param v2 v2
         * @return boolean
         */
        public boolean hasEdge(final int v1, final int v2) {
            if (v1 == v2) {
                return edges[v1][v2] != 0;
            } else {
                return edges[v1][v2] != Integer.MAX_VALUE;
            }
        }
        /**
         * locate vertex.
         * @param vertexName sth
         * @return sth
         */
        public int locateVertex(final String vertexName) {
            return vertexList.indexOf(vertexName);
        }
        /**
         * find next.
         * @param word word
         * @return next
         */
        public ArrayList<String> findNext(final String word) {
            ArrayList<String> nWords = new ArrayList<String>(getVertexSize());
            int v = locateVertex(word.toLowerCase());
            for (int k = 0; k < getVertexSize(); k++) {
                if (getWeight(v, k) >= 1 && v != k
                        && getWeight(v, k) < Integer.MAX_VALUE) {
                    nWords.add(getWord(k));
                }
            }
            return nWords;
        }
        /**
         * get vertex size.
         * @return size
         */
        public int getVertexSize() {
            return vertexList.size();
        }
        /**
         * toString.
         */
        @Override public String toString() {
            StringBuffer sb = new StringBuffer("Vertices:\n\t");
            for (String v : vertexList) {
                sb.append(v + ", ");
            }
            sb.setLength(sb.length() - 2);
            sb.append("\nAdjacency list: ");
            for (int i = 0; i < vertexList.size(); i++) {
                sb.append("\n\t" + getWord(i));
                for (int j = 0; j < getVertexSize(); j++) {
                    if (edges[i][j] >= 1
                            && edges[i][j] < Integer.MAX_VALUE) {
                        sb.append(" --> " + getWord(j)
                                + "(" + edges[i][j] + ")");
                    }
                }
            }
            return sb.toString();
        }
        /**
         * print graph.
         */
        public void printGraph() {
            GraphVisualization.printGraph(vertexList, edges);
        }
    }
    /**
     * main.
     * @param args console input
     */
    public static void main(final String[] args) {
        System.out.println(
                "\t\t\t\t\t(๑•̀ㅂ•́) ✧Welcome  \n"
                + "\t\t\tPlease select the corresponding option as required: ");
        printMenu();
        System.out.print("\nCommand (Enter h for help): ");
        char choice;
        String word3 = null;
        String word4 = null;
        while (sc.hasNext()) {
            choice = sc.next().charAt(0);
            sc.nextLine();
            switch (choice) {
            case '1':
                System.out.print("Select and open the file: ");
                String fileName = sc.nextLine();
                BufferedReader br;
                try {
                    br = new BufferedReader(new FileReader(fileName));
                } catch (FileNotFoundException e) {
                    System.out.println("ERROR: Can't access the file.");
                    System.out.println(e);
                    break;
                }
                System.out.print("OK: The target file is found.\n> > ");
                String fileLine;
                StringBuffer newText = new StringBuffer();
                try {
                    while ((fileLine = br.readLine()) != null) {
                        System.out.println('\t' + fileLine);
                        for (int i = 0; i < fileLine.length(); i++) {
                            char c = fileLine.charAt(i);
                            if (c >= 'A' && c <= 'Z') {
                                char b = Character.toLowerCase(c);
                                newText.append(b);
                            }
                            if ((c >= 'a' && c <= 'z') || c == ' ') {
                                newText.append(c);
                            } else if (c == ',' || c == '.'
                                    || c == '!' || c == '?') {
                                newText.append(' ');
                            }
                        }
                        newText.append(' ');
                    }
                    br.close();
                } catch (IOException e) {
                    System.out.println("ERROR: Can't read the content.");
                    System.out.println(e);
                }
                words = newText.toString().trim().split("\\s+");
                graph = new DirectedGraph(words);
                graphReady = true; // Graph is gotten.
                break;
            case '2':
                if (!graphReady) {
                    System.out.println("ERROR: Please open a file first.");
                    break;
                }
                System.out.print("Do you want the Graph view (default) or "
                        + "the Text view? (g or t) ");
                String way = sc.nextLine();
                if (way.equals("t")) {
                    showGraph(graph, 0);
                } else {
                    showGraph(graph, 1);
                }
                break;
            case '3':
                if (!graphReady) {
                    System.out.println("ERROR: Please open a file first.");
                    break;
                }
                System.out.println(
                        "Please enter two words(eg: apple tasty). The first "
                        + "two will be taken if there're more than two:");
                String[] a = sc.nextLine().split("\\s+");
                if (a.length < 2) {
                    System.out.println("ERROR: No enough words."
                            + "Check the input.");
                    break;
                }
                word3 = a[0];
                word4 = a[1];
                System.out.print(queryBridgeWords(word3, word4));
                break;
            case '4':
                if (!graphReady) {
                    System.out.println("ERROR: Please open a file first.");
                    break;
                }
                System.out.println("Please enter a sentence"
                        + "(eg: many girls one two three) :");
                String line2 = sc.nextLine();
                if (line2.isEmpty()) {
                    System.out.println("ERROR: Can't read the sentence. "
                            + "Check the input.");
                    break;
                }
                System.out.println("> > " + generateNewText(line2));
                break;
            case '5':
                if (!graphReady) {
                    System.out.println("ERROR: Please open a file first.");
                    break;
                }
                System.out.println(
                        "Please enter one or two words(eg: hungry / "
                        + "eat noodles). The first two will be taken "
                        + "if there're more than two:");
                String[] a1 = (sc.nextLine()).split("\\s+");
                if (a1.length >= 2) {
                    word3 = a1[0];
                    word4 = a1[1];
                    System.out.print(calcShortestPath(word3, word4));
                    break;
                } else {
                    word3 = a1[0];
                    if (word3.equals("")) {
                        System.out.println("ERROR: No words found. "
                                + "Check the input.");
                        break;
                    }
                    System.out.print(calcShortestPath(word3));
                    break;
                }
            case '6':
                if (!graphReady) {
                    System.out.println("ERROR: Please open a file first.");
                    break;
                }
                System.out.println("Here comes the random walk;"
                        + " Enter 's' to force it stop.");
                String randomText = randomWalk();
                System.out.println("\nThe Random Path: \n> > "
                        + randomText
                        + "\nWhere do you want to save this text? ");
                String filename = sc.nextLine();
                if (filename.isEmpty()) {
                    break;
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
                break;
            case '0':
                System.out.println("Welcome next time !");
                return;
            case 'h':
                printMenu();
                break;
            default:
                System.out.println("ERROR: Can't understand this choice.");
            }
            System.out.print("\nCommand (Enter h for help): ");
        }
        sc.close();
    }
    
    public void init(String fileName) {
         BufferedReader br;
         try {
             br = new BufferedReader(new FileReader(fileName));
         } catch (FileNotFoundException e) {
             System.out.println("ERROR: Can't access the file.");
             System.out.println(e);
             return;
         }
         String fileLine;
         StringBuffer newText = new StringBuffer();
         try {
             while ((fileLine = br.readLine()) != null) {
                 for (int i = 0; i < fileLine.length(); i++) {
                     char c = fileLine.charAt(i);
                     if (c >= 'A' && c <= 'Z') {
                         char b = Character.toLowerCase(c);
                         newText.append(b);
                     }
                     if ((c >= 'a' && c <= 'z') || c == ' ') {
                         newText.append(c);
                     } else if (c == ',' || c == '.'
                             || c == '!' || c == '?') {
                         newText.append(' ');
                     }
                 }
                 newText.append(' ');
             }
             br.close();
         } catch (IOException e) {
             System.out.println("ERROR: Can't read the content.");
             System.out.println(e);
         }
         words = newText.toString().trim().split("\\s+");
         graph = new DirectedGraph(words);
         graphReady = true; // Graph is gotten.
    }
    /**
     * print menu.
     */
    static void printMenu() {
        System.out.println("\t\t\t1.Enter the file path and name "
                + "(eg.F:/test.txt)");
        System.out.println("\t\t\t2.Show the diagraph");
        System.out.println("\t\t\t3.Query the bridge words between"
                + " two words (eg:hello world)");
        System.out.println("\t\t\t4.complete the sentence by bridge"
                + " words(eg:hello world one two three)");
        System.out.println("\t\t\t5.calculate the shortest path by "
                + "one word or two words(eg:hello world/hello)");
        System.out.println("\t\t\t6.Walk randomly");
        System.out.println("\t\t\t0.exit");
    }
    /**
     * show graph.
     * @param g g
     * @param way way
     */
    static void showGraph(final DirectedGraph g, final int way) {
        if (way == 0) {
            System.out.println(g.toString());
        } else {
            g.printGraph();
        }
    }
    /**
     * find bridge words.
     * @param word1 word1
     * @param word2 word1
     * @return bridge words
     */
    public static ArrayList<String>
        findBridgeWords(final String word1, final String word2) {
        ArrayList<String> bridgeWords = new ArrayList<String>(words.length);
        int v3 = graph.locateVertex(word1.toLowerCase());
        int v4 = graph.locateVertex(word2.toLowerCase());
        if (v3 == -1 || v4 == -1) {
            return bridgeWords;
        }
        for (int k = 0; k < words.length; k++) {
            if (graph.hasEdge(v3, k) && graph.hasEdge(k, v4)) {
                bridgeWords.add(graph.getWord(k));
            }
        }
        return bridgeWords;
    }
    /**
     * query bridge words.
     * @param word1 word1
     * @param word2 word2
     * @return bridge words
     */

    public static String queryBridgeWords(final String word1, final String word2) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        StringBuilder ret = new StringBuilder();
        int v1 = graph.locateVertex(word1.toLowerCase());
        int v2 = graph.locateVertex(word2.toLowerCase());
        ArrayList<String> bridgeWords =
                findBridgeWords(word1.toLowerCase(), word2.toLowerCase());
        if(word1==""&&word2=="")
        {
            ret.append("ERROR: No words found.Check the input.");
        }
        else if(word1==""||word2=="")
        {
            ret.append("ERROR: No enough words. Check the input.");
        }
        else if (bridgeWords.isEmpty()) {
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
            ret.append("The bridge words from \"" + word1 + "\" to \""
                    + word2 + "\" is: " + bridgeWords.get(0));
            for (int i = 1; i < bridgeWords.size(); i++) {
                ret.append(", " + bridgeWords.get(i));
            }
        }
        return ret.toString() + bos.toString();
    }
    /**
     * generate new text.
     * @param inputText input text
     * @return new text
     */
    static String generateNewText(final String inputText) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(bos);
        String[] word1 = new String[words.length];
        word1 = inputText.split(" ");
        output.print(word1[0] + " ");
        for (int i = 0; i < word1.length - 1; i++) {
            ArrayList<String> bridgeWords =
                    findBridgeWords(word1[i].toLowerCase(),
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
    /**
     * calc..
     * @param word1 word1
     * @param word2 word1
     * @return path
     */
    public static String calcShortestPath(final String word1, final String word2) {
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
    static String calcShortestPath(final String word1) {
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
    /**
     * random walk.
     * @return random walk
     */
    static String randomWalk() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(bos);
        ArrayList<String> nextWordsList;
        ArrayList<int[]> edgePairList = new ArrayList<int[]>(words.length);
        Random random = new Random();


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

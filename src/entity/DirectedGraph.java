package entity;

import java.util.ArrayList;

import lab1.GraphVisualization;

/**
 * @author  someone
 */
public class DirectedGraph {
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
    public int getSize() {
    	return vertexList.size();
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
	/**
	 * find bridge words.
	 * 
	 * @param word1
	 *            word1
	 * @param word2
	 *            word1
	 * @return bridge words
	 */
	public ArrayList<String> findBridgeWords(final String word1, final String word2) {
		ArrayList<String> bridgeWords = new ArrayList<String>(getSize());
		int v3 = locateVertex(word1.toLowerCase());
		int v4 = locateVertex(word2.toLowerCase());
		if (v3 == -1 || v4 == -1) {
			return bridgeWords;
		}
		for (int k = 0; k < getSize(); k++) {
			if (hasEdge(v3, k) && hasEdge(k, v4)) {
				bridgeWords.add(getWord(k));
			}
		}
		return bridgeWords;
	}
}

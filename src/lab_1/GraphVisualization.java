package lab_1;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jgrapht.graph.*;
import org.jgrapht.*;
import org.jgrapht.event.*;

import com.mxgraph.model.*;
import com.mxgraph.view.*;

import com.mxgraph.layout.*;
import com.mxgraph.swing.*;

//show the graph
/**
* @parameter
* @since
* @return
*/
public class GraphVisualization
    extends JApplet implements ActionListener {
    private static final long serialVersionUID = 2202072534703043194L;
    //the Dimension of the frame
    /**
    * @parameter
    * @since
    * @return
    */
    private static final Dimension DEFAULT_SIZE = new Dimension(800, 600);
    //the button to save img
    /**
    * @parameter
    * @since
    * @return
    */
    private static JButton button;
    //declare the frame
    /**
    * @parameter
    * @since
    * @return
    */
    private static JFrame frame;
    //declare the Image
    /**
     * @parameter
     * @since
     * @return
     */
    private static BufferedImage  bufferImg;
    /**
     * @parameter
     * @since
     * @return
     */
    private static JGraphXAdapter<String, DefaultWeightedEdge> jgxAdapter;

    //initialize the frame and paint graph on it
    /**
    * @since
    * @return
    * @param vertexList comment here
    * @param edges comment here
    */
    public static void printGraph(final ArrayList<String> vertexList,
        final int[][] edges) {
        GraphVisualization applet = new GraphVisualization();
        applet.init(vertexList, edges);
        applet.initButton();

        //the frame to show img
        frame = new JFrame();
        frame.setTitle("Visualization");
        JPanel framePanel = (JPanel) frame.getContentPane();
        framePanel.add(applet);
        frame.getContentPane().add(button, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        //create img
        bufferImg = new BufferedImage(applet.getWidth(),
            applet.getHeight(),
            BufferedImage.TYPE_INT_ARGB);
        Graphics2D  save = bufferImg.createGraphics();

       //add img to frame
    	applet.paint(save);
    }

    //initialize the button
    /**
    * @parameter
    * @since
    * @return
    */
    
    public static void setButton(String buttonText){
        button = new JButton(buttonText);
        
    }
    
    public final void initButton() {
    	setButton("淇濆瓨");
        button.addActionListener(this);
    }

    //璁剧疆filter 灏嗗浘鍍忎繚瀛�
    /**
    * @since
    * @return
    * @param e comment here
    */
    public final void actionPerformed(final ActionEvent e) {
        final JFileChooser chooser = new JFileChooser();
        final FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG & PNG Images", "jpg", "png");
        chooser.setFileFilter(filter);
        final int returnVal = chooser.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
        	try {
    			ImageIO.write(bufferImg, "PNG",
    					chooser.getSelectedFile());
    		} catch (IOException e1) {
    			e1.printStackTrace();
    		}
        }
    }

    //initialize the graph
    
    public static void setjgxAdapter(ListenableDirectedWeightedGraph<String,
        DefaultWeightedEdge> graph){
    	jgxAdapter = new JGraphXAdapter<>(graph);
    }
    
    /**
    * @since
    * @param vertexList comment here
    * @param edges comment here
    * @return
    */
    public final void init(final ArrayList<String> vertexList,
        final int[][] edges) {
        // 鏂板缓涓�涓浘鐨勫璞�
        final ListenableDirectedWeightedGraph<String, DefaultWeightedEdge>
        graph =
            new ListenableDirectedWeightedGraph<String,
            DefaultWeightedEdge>(DefaultWeightedEdge.class);

        // 缁欏浘鍔犱笂鑺傜偣
        for (final String s: vertexList) {
        	graph.addVertex(s);
        }

        // 缁欏浘鍔犱笂杈�
        for (int i = 0; i < vertexList.size(); i++) {
        	for (int j = 0; j < vertexList.size(); j++) {
        		if (edges[i][j] != Integer.MAX_VALUE
        				&& edges[i][j] != 0) {
        			final String vertex1 = vertexList.get(i);
        			final String vertex2 = vertexList.get(j);
        			graph.addEdge(vertex1, vertex2);
        			graph.setEdgeWeight(
        					graph.getEdge(vertex1, vertex2),
        					edges[i][j]);
        		}
        	}
        }

        // create a visualization using JGraph, via an adapter
        setjgxAdapter(graph);

        //灏嗗浘鍍忓姞杩涢潰鏉�
        getContentPane().add(new mxGraphComponent(jgxAdapter));
        resize(DEFAULT_SIZE);

        // 鎶婃湁鍚戝浘杩炶捣鏉�
        final mxCircleLayout layout = new mxCircleLayout(jgxAdapter);
        layout.execute(jgxAdapter.getDefaultParent());

        // that's all there is to it!...
    }
}


//change the mxGraph to support directed graph
/**
* @param<E> comment here
* @param<V> comment here
* @since
* @return
*/
class JGraphXAdapter<V, E> extends mxGraph implements GraphListener<V, E> {
    /**
    * The graph to be drawn. Has vertices "V" and edges "E".
    */
    private transient Graph<V, E> graphT;

    /**
    * Maps the JGraphT-Vertices onto JGraphX-mxICells.
    * {@link #cellToVertexMap} is for the opposite
    * direction.
    */
    private final HashMap<V, mxICell> vertexToCellMap = new HashMap<>();

    /**
    * Maps the JGraphT-Edges onto JGraphX-mxICells.
    * {@link #cellToEdgeMap} is for the opposite
    * direction.
    */
    private transient HashMap<E, mxICell> edgeToCellMap = new HashMap<>();

    /**
    * Maps the JGraphX-mxICells onto JGraphT-Edges.
    * {@link #edgeToCellMap} is for the opposite
    * direction.
    */
    private final HashMap<mxICell, V> cellToVertexMap = new HashMap<>();

    /**
    * Maps the JGraphX-mxICells onto JGraphT-Vertices.
    * {@link #vertexToCellMap} is for the opposite
    * direction.
    */
    private final HashMap<mxICell, E> cellToEdgeMap = new HashMap<>();

    /**
    * If the graph changes through as ListenableGraph,
    * the JGraphXAdapter will automatically add/remove the
    * new edge/vertex as it implements the
    * GraphListener interface.
    * Throws a IllegalArgumentException if the graph is null.
    *  @param graph casted to graph
    */

    JGraphXAdapter(final ListenableGraph<V, E> graph) {
        // call normal constructor with graph class
        this((Graph<V, E>) graph);
        graph.addGraphListener(this);
    }

    /**
    * Constructs and draws a new mxGraph from a jGraphT graph.
    * use the constructor with the ListenableGraph parameter
    * instead or use this graph as a normal mxGraph.
    * Throws an IllegalArgumentException if the
    * parameter is null.
     *
    * @param graph is a graph
    */
    JGraphXAdapter(final Graph<V, E> graph) {
        super();

        // Don't accept null as jgrapht graph
        if (graph == null) {
            throw new IllegalArgumentException();
        } else {
            this.graphT = graph;
        }

        // generate the drawing
        insertJGraphT(graph);

        setAutoSizeCells(true);
    }

    /**
    * Returns Hashmap which maps the vertices onto their visualization mxICells.
    *
    * @return {@link #vertexToCellMap}
    */
    public HashMap<V, mxICell> getVertexToCellMap() {
    return vertexToCellMap;
}

    /**
    * Returns Hashmap which maps the edges onto their visualization mxICells.
    *
    * @return {@link #edgeToCellMap}
    */
    public HashMap<E, mxICell> getEdgeToCellMap() {
    return edgeToCellMap;
}

    /**
    * Returns Hashmap which maps the visualization mxICells onto their edges.
    *
    * @return {@link #cellToEdgeMap}
    */
    public HashMap<mxICell, E> getCellToEdgeMap() {
    return cellToEdgeMap;
}

    /**
    * Returns Hashmap which maps the visualization mxICells onto their vertices.
    *
    * @return {@link #cellToVertexMap}
    */
    public HashMap<mxICell, V> getCellToVertexMap() {
      return cellToVertexMap;
    }

    @Override
    //返回图的节点
    public void vertexAdded(final GraphVertexChangeEvent<V> edge) {
        addJGraphTVertex(edge.getVertex());
    }

    @Override
    //delete vertex i have comment here
    public void vertexRemoved(final GraphVertexChangeEvent<V> e) {
        final mxICell cell = vertexToCellMap.remove(e.getVertex());
        removeCells(new Object[] {cell });

        // remove vertex from hashmaps
        cellToVertexMap.remove(cell);
        vertexToCellMap.remove(e.getVertex());

        // remove all edges that connected to the vertex
        ArrayList<E> removedEdges = new ArrayList<>();

        // first, generate a list of all edges that have to be deleted
        // so we don't change the cellToEdgeMap.values by deleting while
        // iterating
        // we have to iterate over this because the graphT has already
        // deleted the vertex and edges so we can't query what the edges were
        for (final E edge : cellToEdgeMap.values()) {
            if (!graphT.edgeSet().contains(edge)) {
               removedEdges.add(edge);
            }
        }

        // then delete all entries of the previously generated list
        for (final E edge : removedEdges) {
            removeEdge(edge);
        }
    }

@Override
	//add edges to graph
    public void edgeAdded(final GraphEdgeChangeEvent<V, E> edge) {
        addJGraphTEdge(edge.getEdge());
        //System.out.println("changed!");
    }

@Override
    public void edgeRemoved(final GraphEdgeChangeEvent<V, E> edge) {
        removeEdge(edge.getEdge());
    }

    /**
    * Removes a jgrapht edge and its visual
    * representation from this graph completely.
    * @param edge The edge that will be removed
    */
    private void removeEdge(final E edge) {
        final mxICell cell = edgeToCellMap.remove(edge);
        removeCells(new Object[] {cell });

        // remove edge from hashmaps
        cellToEdgeMap.remove(cell);
        edgeToCellMap.remove(edge);
    }

    /**
    * Draws a new vertex into the graph.
    *
    * @param vertex vertex to be added to the graph
    */
    private void addJGraphTVertex(final V vertex) {
        getModel().beginUpdate();

        try {
            // create a new JGraphX vertex at position 0
            final mxICell cell = (mxICell) insertVertex(defaultParent,
            		null, vertex, 0, 0, 0, 0,
                    "shape=ellipse;perimeter=ellipsePerimeter");

            // update cell size so cell isn't "above" graph
            updateCellSize(cell);

            // Save reference between vertex and cell
            vertexToCellMap.put(vertex, cell);
            cellToVertexMap.put(cell, vertex);
        } finally {
            getModel().endUpdate();
        }
    }

/**
 * Draws a new egde into the graph.
 *
 * @param edge edge to be added to the graph.
 *  Source and target vertices are needed.
 */
    private void addJGraphTEdge(final E edge) {
        getModel().beginUpdate();

        try {
            // find vertices of edge
            final V sourceVertex = graphT.getEdgeSource(edge);
            final V targetVertex = graphT.getEdgeTarget(edge);

            // if the one of the vertices is not drawn, don't draw the edge
            if (!(vertexToCellMap.containsKey(sourceVertex)
                && vertexToCellMap.containsKey(targetVertex))) {
                return;
            }

            // get mxICells
            final Object sourceCell = vertexToCellMap.get(sourceVertex);
            final Object targetCell = vertexToCellMap.get(targetVertex);

            // add edge between mxICells
            final mxICell cell = (mxICell) insertEdge(defaultParent, null,
            		(int) graphT.getEdgeWeight(edge),
            		sourceCell, targetCell);

            // update cell size so cell isn't "above" graph
            updateCellSize(cell);

            // Save reference between vertex and cell
            edgeToCellMap.put(edge, cell);
            cellToEdgeMap.put(cell, edge);
        } finally {
            getModel().endUpdate();
        }
    }

    /**
    * Draws a given graph with all its vertices and edges.
    *
    * @param graph the graph to be added to the existing graph.
    */
    private void insertJGraphT(final Graph<V, E> graph) {
        for (final V vertex : graph.vertexSet()) {
            addJGraphTVertex(vertex);
        }

        for (final E edge : graph.edgeSet()) {
            addJGraphTEdge(edge);
        }
    }
}

//End JGraphXAdapter.java

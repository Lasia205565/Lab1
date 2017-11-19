package control;

import entity.DirectedGraph;
import entity.TargetText;

public class GraphControl {
	
	DirectedGraph graph;
	
	public DirectedGraph getGraph() {
		return graph;
	}

	TargetText words;

	public GraphControl(TargetText words) {
		super();
		this.words = words;
	}

	public GraphControl(DirectedGraph graph, TargetText words) {
		super();
		this.graph = graph;
		this.words = words;
	}

	public void execute() {
		words.setupWords();
        graph = new DirectedGraph(words.getWords());
	}

}

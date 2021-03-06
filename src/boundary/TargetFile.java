package boundary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import control.GraphControl;
import entity.DirectedGraph;
import entity.TargetText;

public class TargetFile {
	DirectedGraph graph;

	public DirectedGraph getGraph() {
		return graph;
	}

	public void execute() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Select and open the file: ");
		String fileName = sc.nextLine();
		execute(fileName);
	}
	
	public void execute(String fileName) {
		TargetText words = new TargetText();
		try {
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			System.out.print("OK: The target file is found.\n> > ");
			String fileLine;
			StringBuffer text = new StringBuffer();
			while ((fileLine = br.readLine()) != null) {
				System.out.println('\t' + fileLine);
				text.append(fileLine + " ");
			}
			words.setText(text.toString());
			br.close();
		} catch (IOException e) {
			System.out.println("ERROR: Can't read the content.");
			System.out.println(e);
		}
		GraphControl gc = new GraphControl(words);
		gc.execute();
		graph = gc.getGraph();
	}
}

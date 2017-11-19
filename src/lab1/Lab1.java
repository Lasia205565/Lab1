package lab1;

import java.util.*;

import boundary.*;
import entity.*;

/**
 * @author Lasia
 */
public class Lab1 {
    /**
     * graph.
     */
    private static DirectedGraph graph;
    
    public static void main(final String[] args) {
        System.out.println(
                "\t\t\t\t\t(๑•̀ㅂ•́) ✧Welcome  \n"
                + "\t\t\tPlease select the corresponding option as required: ");
        printMenu();
        System.out.print("\nCommand (Enter h for help): ");
        char choice;
        Scanner sc = new Scanner(System.in);
        while (true) {
            choice = sc.nextLine().charAt(0);
            switch (choice) {
            case '1':
            	TargetFile tf = new TargetFile();
            	tf.execute();
            	graph = tf.getGraph();
                break;
            case '2':
            	ShowGraph sg = new ShowGraph(graph);
            	sg.execute();
                break;
            case '3':
            	BridgeWord bw = new BridgeWord(graph);
            	bw.execute();
                break;
            case '4':
            	NewText nt = new NewText(graph);
            	nt.execute();
                break;
            case '5':
            	ShortestPath sp = new ShortestPath(graph);
            	sp.execute();
            	break;
            case '6':
            	RandomWalk rw = new RandomWalk(graph);
            	rw.execute();
                break;
            case '0':
                System.out.println("Welcome next time !");
                sc.close();
                return;
            case 'h':
                printMenu();
                break;
            default:
                System.out.println("ERROR: Can't understand this choice.");
            }
            System.out.print("\nCommand (Enter h for help): ");
        }
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

}

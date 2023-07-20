import com.mxgraph.layout.mxParallelEdgeLayout;
import com.mxgraph.view.mxGraph;

import javax.swing.*;
import java.io.InputStream;
import java.util.Scanner;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;
public class GraphVisualizer {

    public static void visualizeGraph(int[][] adjacencyMatrix) {
        mxGraph graph = new mxGraph();
        Object parent = graph.getDefaultParent();

        graph.getModel().beginUpdate();
        try {
            int numVertices = adjacencyMatrix.length;
            Object[] vertexCells = new Object[numVertices];

            // Create vertex cells
            for (int i = 0; i < numVertices; i++) {
                vertexCells[i] = graph.insertVertex(parent, null, i, 0, 0, 50, 50);
            }

            // Create edge cells with arrows on both sides
            for (int i = 0; i < numVertices; i++) {
                for (int j = 0; j < numVertices; j++) {
                    if (adjacencyMatrix[i][j] == 1) {
                        graph.insertEdge(parent, null, null, vertexCells[i], vertexCells[j], "endArrow=classic;startArrow=classic");
                    }
                }
            }

            // Run BFS on the graph and get the traversal order
            int startVertex = 0;
            GraphAdjMatrix graphAdjMatrix = new GraphAdjMatrix(numVertices);
            graphAdjMatrix.adjacencyMatrix = adjacencyMatrix; // Set the adjacency matrix in the graph object



        } finally {
            graph.getModel().endUpdate();
        }

        // Set layout to mxHierarchicalLayout for horizontal vertex placement
        mxHierarchicalLayout layout = new mxHierarchicalLayout(graph);
        layout.setOrientation(SwingConstants.WEST); // Horizontal layout
        layout.execute(parent);

        JFrame frame = new JFrame("Graph Visualization");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(new mxGraphComponent(graph));
        frame.pack();
        frame.setVisible(true);
    }
    public static void main(String[] args) {

        InputStream inputStream = GraphTest.class.getClassLoader().getResourceAsStream("graphData.txt");
        if (inputStream == null) {
            System.out.println("File not found.");
            return;
        }


        Scanner scanner = new Scanner(inputStream);

            int numVertices = Integer.parseInt(scanner.nextLine().trim());

            // Test Graph using adjacency list representation
            GraphAdjList graphAdjList = new GraphAdjList(numVertices);
            GraphAdjMatrix graphAdjMatrix = new GraphAdjMatrix(numVertices);

            while (scanner.hasNextLine()) {
                String[] lineParts = scanner.nextLine().trim().split(" ");
                int src = Integer.parseInt(lineParts[0]);
                for (int i = 1; i < lineParts.length; i++) {
                    int dest = Integer.parseInt(lineParts[i]);
                    graphAdjList.addEdge(src, dest);
                    graphAdjMatrix.addEdge(src, dest);
                }
            }

            scanner.close();

            System.out.println("Graph using Adjacency List 000:");
            graphAdjList.printGraph();

            int startVertex = 0;
            System.out.println("BFS Traversal from vertex " + startVertex + ":");
            graphAdjList.bfs(startVertex);

            System.out.println("DFS Traversal from vertex " + startVertex + ":");
            graphAdjList.dfs(startVertex);

            System.out.println("\nGraph using Adjacency Matrix representation:");
            graphAdjMatrix.printGraph();

            System.out.println("BFS Traversal from vertex " + startVertex + ":");
            graphAdjMatrix.bfs(startVertex);

            System.out.println("DFS Traversal from vertex " + startVertex + ":");
            graphAdjMatrix.dfs(startVertex);

            // Visualize the graph
            visualizeGraph(graphAdjMatrix.adjacencyMatrix);


    }
}
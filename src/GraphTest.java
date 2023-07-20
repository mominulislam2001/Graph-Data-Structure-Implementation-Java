import java.io.*;
import java.util.*;

public class GraphTest {

    public static void main(String[] args) {
        try {
            // Load the file using ClassLoader.getResource()
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

            System.out.println("Graph using Adjacency List representation:");
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


            // Rest of the code...
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
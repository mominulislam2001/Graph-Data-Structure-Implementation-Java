import java.util.*;

// GraphAdjList class representing a graph using an adjacency list
class GraphAdjList extends Graph {

    private List<List<Integer>> adjacencyList;

    public GraphAdjList(int numVertices) {
        super(numVertices);
        adjacencyList = new ArrayList<>();
        for (int i = 0; i < numVertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }

    @Override
    public void addEdge(int src, int dest) {
        adjacencyList.get(src).add(dest);
        adjacencyList.get(dest).add(src); // For undirected graph, add the reverse edge as well.
    }

    @Override
    public List<Integer> getNeighbors(int vertex) {
        return adjacencyList.get(vertex);
    }

    @Override
    public void printGraph() {
        for (int i = 0; i < numVertices; i++) {
            System.out.print("Vertex " + i + " -> ");
            for (int neighbor : adjacencyList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void bfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int currentVertex = queue.poll();
            System.out.print(currentVertex + " ");

            for (int neighbor : adjacencyList.get(currentVertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int startVertex) {
        Set<Integer> visited = new HashSet<>();
        dfsUtil(startVertex, visited);
        System.out.println();
    }

    private void dfsUtil(int currentVertex, Set<Integer> visited) {
        visited.add(currentVertex);
        System.out.print(currentVertex + " ");

        for (int neighbor : adjacencyList.get(currentVertex)) {
            if (!visited.contains(neighbor)) {
                dfsUtil(neighbor, visited);
            }
        }
    }
}

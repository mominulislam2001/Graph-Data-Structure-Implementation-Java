import java.util.List;

// Abstract Graph class
public abstract class Graph {

    protected int numVertices;

    public Graph(int numVertices) {
        this.numVertices = numVertices;
    }

    public int getNumVertices() {
        return numVertices;
    }

    public abstract void addEdge(int src, int dest);

    public abstract List<Integer> getNeighbors(int vertex);

    public abstract void printGraph();

    public abstract void dfs(int startVertex);

    public abstract void bfs(int startVertex);
}

public interface Graph {

    int addNode(String name);

    void printGraph();

    String findShortestPath(Node start, Node end);

}

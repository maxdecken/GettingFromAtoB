import java.util.ArrayList;

public class WeightedGraph implements Graph{

    Node[] nodes;
    int latestNode = 0;
    int nodeCount;
    Edge matrix[][];

    public WeightedGraph(int nodeCount){
        this.nodeCount = nodeCount;
        matrix = new Edge[nodeCount][nodeCount];
        nodes = new Node[nodeCount];
    }

    @Override
    public int addNode(String name) {
        int index = latestNode;
        Node n = new Node(index, name);
        if(latestNode < nodeCount) {
            nodes[latestNode] = n;
            latestNode = latestNode + 1;
        }else {
            System.out.println("Can not add node: " +  n.name + " ,all Nodes are filled up");
        }
        return index;
    }

    public void addEdge(Node start, Node end, int weight) {

        Edge e = new Edge(start, end, weight);

        matrix[start.index][end.index]=e;

        matrix[end.index][start.index] =e;
    }

    @Override
    public void printGraph() {

        for (int i = 0; i < nodeCount; i++) {
            System.out.print("Node " + nodes[i].name + " is connected to:");
            for (int j = 0; j <nodeCount ; j++) {
                if(matrix[i][j] != null){
                    System.out.print(nodes[j].name + " weight:(" + matrix[i][j].weight+ "), ");
                }
            }
            System.out.println();
        }

    }

    public Node getNode(int indexNode){
        return nodes[indexNode];
    }

    @Override
    public void findShortestPath(Node start, Node end) {

    }
}

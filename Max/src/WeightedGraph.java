import java.util.ArrayList;

public class WeightedGraph implements Graph{

    ArrayList<Node> nodes;
    int nodeCount;
    int matrix[][];

    public WeightedGraph(int nodeCount){
        this.nodeCount = nodeCount;
        matrix = new int[nodeCount][nodeCount];
        nodes = new ArrayList<>();
    }

    @Override
    public int addNode(String name) {
        int index = 0;
        for(Node node: nodes){
            if(index < node.index && index < nodeCount) {
                index = node.index + 1;
            }
        }
        Node n = new Node(index, name);
        nodes.add(n);
        return index;
    }

    public void addEdge(int start, int end, int weight) {

        matrix[start][end]=weight;

        matrix[end][start] =weight;
    }

    @Override
    public void printGraph() {
        /*System.out.println("Graph:");
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j <nodeCount ; j++) {
                System.out.print(matrix[i][j]+ " ");
            }
            System.out.println();
        }*/
        for (int i = 0; i < nodeCount; i++) {
            System.out.print("Node " + i + " is connected to:");
            for (int j = 0; j <nodeCount ; j++) {
                if(matrix[i][j]>0){
                    System.out.print(j + " weight:(" + matrix[i][j]+ "), ");
                }
            }
            System.out.println();
        }

    }

    @Override
    public void findShortestPath(Node start, Node end) {

    }
}

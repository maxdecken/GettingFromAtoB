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
    public String findShortestPath(Node start, Node end) {
        //same algorithms as for findCheapestPath (exercise 4),
        // but weight ad is always one, since weight of edges does not count.
        String way = "";

        double dis[] = new double[nodeCount];
        double prev[] = new double[nodeCount];
        boolean explored[] = new boolean[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            dis[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1;
            explored[i] =false;
        }
        dis[start.index] = 0;

        int current = start.index;

        while (!explored[end.index]){
            way = way + getNode(current).name + "-" ;
            explored[current] = true;
            int v = 0;
            double shortest = Double.POSITIVE_INFINITY;
            for (int j = 0; j <nodeCount ; j++) {
                if(matrix[current][j] != null && !explored[j]) {
                    dis[j] = 1 + dis[current];
                    if (shortest > dis[j]) {
                        //v = vertex with shortest way to previous node
                        v = nodes[j].index;
                        shortest = dis[j];
                    }
                }
            }
            explored[v] = true;
            prev[v] = current;
            shortest = Double.POSITIVE_INFINITY;
            int next = -1;
            for (int j = 0; j <nodeCount ; j++){
                if(matrix[v][j] != null && !explored[j]) {
                    if(dis[j] > dis[v] + 1){
                        dis[j] = dis[v] + 1;
                    }
                    if (shortest > dis[j]) {
                        //v = vertex with shortest way to previous node
                        next = nodes[j].index;
                        shortest = dis[j];
                    }
                }
            }
            current = next;
            if(current == end.index){
                break;
            }
        }
        way = way + end.name;
        return way;
    }

    public String findCheapestPath(Node start, Node end) {

        String way = "";

        double dis[] = new double[nodeCount];
        double prev[] = new double[nodeCount];
        boolean explored[] = new boolean[nodeCount];

        for (int i = 0; i < nodeCount; i++) {
            dis[i] = Double.POSITIVE_INFINITY;
            prev[i] = -1;
            explored[i] =false;
        }
        dis[start.index] = 0;

        int current = start.index;

        while (!explored[end.index]){
            way = way + getNode(current).name + "-" ;
            explored[current] = true;
            int v = -1;
            double shortest = Double.POSITIVE_INFINITY;
            for (int j = 0; j <nodeCount ; j++) {
                if(matrix[current][j] != null && !explored[j]) {
                    dis[j] = matrix[current][j].weight + dis[current];
                    if (shortest > dis[j]) {
                        //v = vertex with shortest way to previous node
                        v = nodes[j].index;
                        shortest = dis[j];
                    }
                }
            }
            explored[v] = true;
            prev[v] = current;
            shortest = Double.POSITIVE_INFINITY;
            int next = -1;
            for (int j = 0; j <nodeCount ; j++){
                if(matrix[v][j] != null && !explored[j]) {
                    if(dis[j] > dis[v] + matrix[v][j].weight){
                        dis[j] = dis[v] + matrix[v][j].weight;
                    }
                    if (shortest > dis[j]) {
                        //v = vertex with shortest way to previous node
                        next = nodes[j].index;
                        shortest = dis[j];
                    }
                }
            }
            current = next;
        }
        way = way + end.name;
        return way + " (cost:"+(int) dis[end.index]+")";
    }
}

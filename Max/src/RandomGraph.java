import java.util.Random;

public class RandomGraph {

    private int vert;
    private int edges;
    private WeightedGraph weightedGraph;

    public RandomGraph(int vert, int edges){
        this.vert = vert;
        this.edges = edges;
        weightedGraph = new WeightedGraph(vert);
        generateRandomNodes(vert);
        generateRandomEdges(edges);
    }

    private void generateRandomNodes(int count){
        for(int i = 0; i < count; i++){
            char letter = (char) (i + 65);
            String name = letter +"";
            weightedGraph.addNode(name);
        }
    }

    private void generateRandomEdges(int count){
        Random r = new Random();
        for(int i = 0; i < count; i++){
            Node n1 = weightedGraph.getNode(r.nextInt(vert));
            Node n2 = weightedGraph.getNode(r.nextInt(vert));
            weightedGraph.addEdge(n1, n2, r.nextInt(10)+1);
        }
    }

    public void print(){
        weightedGraph.printGraph();
    }
}

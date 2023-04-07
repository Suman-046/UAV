import java.util.ArrayList;

class Node {
    private double directTrust;
    private double indirectTrust;
    private ArrayList<Node> neighbors;
    
    public Node() {
        directTrust = 0.0;
        indirectTrust = 0.0;
        neighbors = new ArrayList<Node>();
    }
    
    public double getDirectTrust() {
        return directTrust;
    }
    
    public void setDirectTrust(double trust) {
        directTrust = trust;
    }
    
    public double getIndirectTrust() {
        return indirectTrust;
    }
    
    public void setIndirectTrust(double trust) {
        indirectTrust = trust;
    }
    
    public ArrayList<Node> getNeighbors() {
        return neighbors;
    }
    
    public void addNeighbor(Node neighbor) {
        neighbors.add(neighbor);
    }
    
    public int calculateIndirectTrust(double beta) {
        double sum = 0.0;
        for (Node neighbor : neighbors) {
            sum += neighbor.getDirectTrust() * getWeight(neighbor);
        }
        indirectTrust = beta * indirectTrust + (1 - beta) * sum;
        return (int) indirectTrust;
    }
    
    private double getWeight(Node neighbor) {
        // Calculate the weight based on factors such as the reliability of the direct trust score and the distance between nodes
        // Here, we use a simple weight of 1 for all neighbors
        return 1.0;
    }
}
public class in
{
    public static void main(String[] args) {
        Node node = new Node();
        node.calculateIndirectTrust(0.8);
        System.out.println(node.calculateIndirectTrust(0.2));
    }
}
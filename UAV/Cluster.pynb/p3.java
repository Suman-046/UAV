//**Implement the trust evaluation algorithm, which computes the trustworthiness score of each node based on its behavior.**
import java.util.ArrayList;
import java.util.List;

public class p3 {
    // Define a node class to hold the node id and its trust score
    public static class Node {
        public int id;
        public double trust_score;
    }

    // Define a function to compute the trust score of a node based on its behavior
    public static double compute_trust_score(int node_id, List<List<Integer>> interactions) {
        double trust_score = 0.0;
        int positive_count = 0, negative_count = 0;

        // Count the number of positive and negative interactions with other nodes
        for (int i = 0; i < interactions.get(node_id).size(); i++) {
            if (interactions.get(node_id).get(i) == 1) {
                positive_count++;
            } else {
                negative_count++;
            }
        }

        // Compute the trust score based on the ratio of positive to negative interactions
        if (positive_count + negative_count > 0) {
            trust_score = (double) positive_count / (positive_count + negative_count);
        }

        return trust_score;
    }

    // Define a function to evaluate the trustworthiness of all nodes in the network
    public static List<Node> evaluate_trust(List<List<Integer>> interactions) {
        List<Node> nodes = new ArrayList<>();

        // Create a node object for each node in the network and compute its trust score
        for (int i = 0; i < interactions.size(); i++) {
            Node node = new Node();
            node.id = i;
            node.trust_score = compute_trust_score(i, interactions);
            nodes.add(node);
        }

        return nodes;
    }

    public static void main(String[] args) {
        // Define a sample network with 5 nodes and their interactions
        List<List<Integer>> interactions = new ArrayList<>();
        interactions.add(List.of(0, 1, 1, 0, 1));
        interactions.add(List.of(1, 0, 1, 0, 0));
        interactions.add(List.of(1, 1, 0, 1, 0));
        interactions.add(List.of(0, 0, 1, 0, 1));
        interactions.add(List.of(0, 0, 0, 1, 0));
        
        

        // List<List<Boolean>> a1 = new ArrayList<>();
        // a1  += interactions.add(List.of(0, 0, 0, 1, 1));

        // Evaluate the trustworthiness of all nodes in the network
        List<Node> nodes = evaluate_trust(interactions);

        // Print the trust scores of all nodes
        for (int i = 0; i < nodes.size(); i++) {
            System.out.println("Node " + nodes.get(i).id + " has trust score " + nodes.get(i).trust_score);
        }
    }
}

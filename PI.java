import java.util.HashMap;
import java.util.Scanner;
class Node {
    final int MAX_INTERACTIONS = 100;
    private HashMap<String, Boolean> pastInteractionMap = new HashMap<>();
    private int numPositiveInteractions;
    private double trustScore;
    public Node() {
        this.numPositiveInteractions = 0;
        this.trustScore = 1.0;
    }
    public HashMap<String, Boolean> getPastInteractionMap () {
        return pastInteractionMap;
    }
    public int getNumPositiveInteractions() {
        return numPositiveInteractions;
    }
    public double getTrustScore() {
        return trustScore;
    }
}
class TrustCalculator {
    static Node node = new Node();
    static HashMap<String, Boolean> pastInteractionMap = node.getPastInteractionMap();
    static int numPositiveInteractions = node.getNumPositiveInteractions();
    static double trustScore = node.getTrustScore();
    static int MAX_INTERACTIONS = node.MAX_INTERACTIONS;

    public void addInteraction (Node node) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of node :");
        int n = sc.nextInt();
        for(int i=0; i<n; i++) {
            System.out.println("NodeId :");
            String s = sc.next();
            System.out.println("True/false :");
            boolean b = sc.nextBoolean();
            pastInteractionMap.put(s, b);
            sc.close();
        }
        if (pastInteractionMap.size() == MAX_INTERACTIONS) {
            pastInteractionMap.remove(pastInteractionMap.keySet().iterator().next());
        }
        updateTrustScore(node);
    }
    
    public double updateTrustScore(Node node) {
        int numPositive = 0;
        for (boolean positive : pastInteractionMap.values()) {
            if (positive) {
                numPositive++;
            }
        }
        numPositiveInteractions = numPositive;
        if (pastInteractionMap.isEmpty()) {
            trustScore = 1.0;
        } else {
            trustScore = (double) numPositiveInteractions / pastInteractionMap.size();
        }
        System.out.println(trustScore);
        return trustScore;
    }
} 

public class PI {
    public static void main(String[] args) {
        Node obj = new Node();
        TrustCalculator tc = new TrustCalculator();
        tc.addInteraction(obj);
    }
}
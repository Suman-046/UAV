import java.util.HashMap;

public class NodeTrustCalculator {

    private static final int MAX_INTERACTIONS = 100;

    private HashMap<String, Boolean> pastInteractionMap;
    private int numPositiveInteractions;
    private double trustScore;

    public NodeTrustCalculator() {
        pastInteractionMap = new HashMap<String, Boolean>();
        numPositiveInteractions = 0;
        trustScore = 1.0;
    }

    public void addInteraction(String nodeId, boolean positive) {
        if (pastInteractionMap.size() == MAX_INTERACTIONS) {
            pastInteractionMap.remove(pastInteractionMap.keySet().iterator().next());
        }
        pastInteractionMap.put(nodeId, positive);
        updateTrustScore();
    }

    private void updateTrustScore() {
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
    }

    public int getNumPositiveInteractions() {
        return numPositiveInteractions;
    }

    public double getTrustScore() {
        return trustScore;
    }

    public static void main(String[] args) {
        NodeTrustCalculator nodeTrustCalculator = new NodeTrustCalculator();
        nodeTrustCalculator.addInteraction("NodeA", true);
        nodeTrustCalculator.addInteraction("NodeB", true);
        nodeTrustCalculator.addInteraction("NodeC", false);
        System.out.println("Number of positive interactions: " + nodeTrustCalculator.getNumPositiveInteractions());
        System.out.println("Trust score: " + nodeTrustCalculator.getTrustScore());
    }
}
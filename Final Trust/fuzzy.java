import java.util.*;

public class fuzzy {
    // Declare variables to store trust values for each node
    private Map<String, Double> trustValues = new HashMap<>();

    // Declare variables to store history of communication for each node
    private Map<String, List<Boolean>> communicationHistory = new HashMap<>();

    // Define the fuzzy membership functions for trust values
    private double highTrustMembership(double trustValue) {
        if (trustValue >= 0.8) {
            return 1.0;
        } else if (trustValue >= 0.6) {
            return (trustValue - 0.6) / 0.2;
        } else {
            return 0.0;
        }
    }

    private double mediumTrustMembership(double trustValue) {
        if (trustValue >= 0.6 && trustValue < 0.8) {
            return (trustValue - 0.6) / 0.2;
        } else if (trustValue >= 0.8 && trustValue < 1.0) {
            return (1.0 - trustValue) / 0.2;
        } else {
            return 0.0;
        }
    }

    private double lowTrustMembership(double trustValue) {
        if (trustValue >= 0.0 && trustValue < 0.6) {
            return (0.6 - trustValue) / 0.6;
        } else if (trustValue >= 0.6) {
            return 0.0;
        } else {
            return 1.0;
        }
    }

    // Calculate the trust value of a node based on its communication history
    public void updateTrustValue(String nodeID, boolean successfulCommunication) {
        List<Boolean> history = communicationHistory.getOrDefault(nodeID, new ArrayList<>());
        history.add(successfulCommunication);
        communicationHistory.put(nodeID, history);

        double trustValue = calculateTrustValue(nodeID);
        trustValues.put(nodeID, trustValue);
    }

    private double calculateTrustValue(String nodeID) {
        List<Boolean> history = communicationHistory.getOrDefault(nodeID, new ArrayList<>());
        int successfulCommunications = (int) history.stream().filter(x -> x).count();
        int totalCommunications = history.size();

        if (totalCommunications == 0) {
            return 0.0;
        }

        double successRate = (double) successfulCommunications / totalCommunications;
        return successRate;
    }

    // Calculate the trustworthiness of a node based on its trust value
    public double calculateTrustworthiness(String nodeID) {
        double trustValue = trustValues.getOrDefault(nodeID, 0.0);

        double highTrust = highTrustMembership(trustValue);
        double mediumTrust = mediumTrustMembership(trustValue);
        double lowTrust = lowTrustMembership(trustValue);

        double numerator = (highTrust * 0.9) + (mediumTrust * 0.5) + (lowTrust * 0.1);
        double denominator = highTrust + mediumTrust + lowTrust;

        if (denominator == 0) {
            return 0.0;
        }

        return numerator / denominator;
    }
}

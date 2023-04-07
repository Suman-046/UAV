import java.util.HashMap;
class Node {
    private double batteryLevel = 100.0;
    private double batteryUsage;
    private double packetDeliveryRatio;
    private double transmissionDelay;

    public Node( double batteryUsage, double energy, double packetDeliveryRatio, double transmissionDelay) {
        // this.batteryLevel = batteryLevel;
        this.batteryUsage = batteryUsage;
        this.energy = energy;
        this.packetDeliveryRatio = packetDeliveryRatio;
        this.transmissionDelay = transmissionDelay;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public double getBatteryUsage() {
        return batteryUsage;
    }
    private double energy;

    public double getEnergy() {
        return energy;
    }
    public double getPacketDeliveryRatio() {
        return packetDeliveryRatio;
    }
    public double getTransmissionDelay() {
        return transmissionDelay;
    }
}

class TrustCalculator {
    private static final double LOW_BATTERY_THRESHOLD = 0.3;
    private static final double LOW_BATTERY_PENALTY = 0.5;
    private static final double HIGH_BATTERY_USAGE_THRESHOLD = 0.7;
    private static final double HIGH_BATTERY_USAGE_PENALTY = 0.3;
    

    public double calculateTrust(Node node) {
        double batteryLevel = node.getBatteryLevel();
        double batteryUsage = node.getBatteryUsage();
        double trust = 1.0;
        double energy = node.getEnergy();
        if (batteryLevel < LOW_BATTERY_THRESHOLD) {
            trust -= LOW_BATTERY_PENALTY;
        }
        
        if (batteryUsage > HIGH_BATTERY_USAGE_THRESHOLD) {
            trust -= HIGH_BATTERY_USAGE_PENALTY;
        }
        final double LOW_ENERGY_THRESHOLD = 0.3;
        final double LOW_ENERGY_PENALTY = 0.5;
        final double HIGH_ENERGY_VARIABILITY_THRESHOLD = 0.7;
        final double HIGH_ENERGY_VARIABILITY_PENALTY = 0.3;
       
        if (energy < LOW_ENERGY_THRESHOLD) {
            trust -= LOW_ENERGY_PENALTY;
        }
        final double LOW_PACKET_DELIVERY_RATIO_THRESHOLD = 0.5;
        final double LOW_PACKET_DELIVERY_RATIO_PENALTY = 0.5;
        final double HIGH_PACKET_DELIVERY_RATIO_VARIABILITY_THRESHOLD = 0.7;
        final double HIGH_PACKET_DELIVERY_RATIO_VARIABILITY_PENALTY = 0.3;
        final double HIGH_TRANSMISSION_DELAY_THRESHOLD = 1.0;
        final double HIGH_TRANSMISSION_DELAY_PENALTY = 0.5;
        
        return trust;
    }
}
class NodeTrustCalculator
{
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
}
class Average 
{
    
}
public class FinalTrust {
    public static void main(String[] args) {
        Node obj = new Node(10, 0.24, 0.24, 0.29);
        TrustCalculator obj1 = new TrustCalculator();
        System.out.println(obj1.calculateTrust(obj));
        System.out.println(obj.getBatteryLevel());
        System.out.println(obj.getBatteryUsage());
        System.out.println(obj.getPacketDeliveryRatio());
        NodeTrustCalculator nodeTrustCalculator = new NodeTrustCalculator();
        nodeTrustCalculator.addInteraction("NodeA", true);
        nodeTrustCalculator.addInteraction("NodeB", true);
        nodeTrustCalculator.addInteraction("NodeC", false);
        System.out.println("Number of positive interactions: " + nodeTrustCalculator.getNumPositiveInteractions());
        System.out.println("Trust score: " + nodeTrustCalculator.getTrustScore());   
    }
}
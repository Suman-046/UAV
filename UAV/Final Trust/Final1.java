import java.util.HashMap;
import java.util.Scanner;
class Node {
    // node for signal strength

    class Node_SS {
        private double energy;

    public Node_SS(double energy) {
        this.energy = energy;
    }

    public double getEnergy() {
        return energy;
    }
    }
    // node for node energy

    class Node_NE {
        private double batteryLevel =100.0;
        private double batteryUsage;

    public Node_NE(double batteryUsage) {
        // this.batteryLevel = batteryLevel;
        this.batteryUsage = batteryUsage;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public double getBatteryUsage() {
        return batteryUsage;
    }
    }
    // node for transmission delay

    class Node_TD {
        private double transmissionDelay;

    public Node_TD(double transmissionDelay) {
        this.transmissionDelay = transmissionDelay;
    }

    public double getTransmissionDelay() {
        return transmissionDelay;
    }
    }
    // node for packet delivery ratio

    class Node_PDR {
        private double packetDeliveryRatio;

    public Node_PDR(double packetDeliveryRatio) {
        this.packetDeliveryRatio = packetDeliveryRatio;
    }

    public double getPacketDeliveryRatio() {
        return packetDeliveryRatio;
    }
    }
    // node for past interaction

    class Node_PI {
    final int MAX_INTERACTIONS = 100;
    private HashMap<String, Boolean> pastInteractionMap = new HashMap<>();
    private int numPositiveInteractions;
    private double trustScore;
    public Node_PI() {
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
}

class TrustCalculator {
    private static final double LOW_BATTERY_THRESHOLD = 0.3;
    private static final double LOW_BATTERY_PENALTY = 0.5;
    private static final double HIGH_BATTERY_USAGE_THRESHOLD = 0.7;
    private static final double HIGH_BATTERY_USAGE_PENALTY = 0.3;

    private static final double LOW_ENERGY_THRESHOLD = 0.3;
    private static final double LOW_ENERGY_PENALTY = 0.5;

    private static final double LOW_PACKET_DELIVERY_RATIO_THRESHOLD = 0.5;
    private static final double LOW_PACKET_DELIVERY_RATIO_PENALTY = 0.5;

    private static final double HIGH_TRANSMISSION_DELAY_THRESHOLD = 1.0;
    private static final double HIGH_TRANSMISSION_DELAY_PENALTY = 0.5;

    static Node obj = new Node();
    static Node.Node_PI node = obj.new Node_PI();
    static HashMap<String, Boolean> pastInteractionMap = node.getPastInteractionMap();
    static int numPositiveInteractions = node.getNumPositiveInteractions();
    static double trustScore = node.getTrustScore();
    static int MAX_INTERACTIONS = node.MAX_INTERACTIONS;

    // trust calculate by node energy of a node
    public double TrustForNodeenergy(Node.Node_NE node) {
        double batteryLevel = node.getBatteryLevel();
        double batteryUsage = node.getBatteryUsage();
        double trust = 1.0;
        
        if (batteryLevel < LOW_BATTERY_THRESHOLD) {
            trust -= LOW_BATTERY_PENALTY;
        }
        
        if (batteryUsage > HIGH_BATTERY_USAGE_THRESHOLD) {
            trust -= HIGH_BATTERY_USAGE_PENALTY;
        }
        
        return trust;
    }
    // trust claculate by signal strength of a node
    public double TrustForSignalStrength(Node.Node_SS node) {
        double energy = node.getEnergy();
        double trust = 1.0;
        
        if (energy < LOW_ENERGY_THRESHOLD) {
            trust -= LOW_ENERGY_PENALTY;
        }
        
        // Add additional factors to the trust calculation here
        
        return trust;
    }
    // trust calculate by packet delivery ratio of a node
    public double TrustforPDR(Node.Node_PDR node) {
        double packetDeliveryRatio = node.getPacketDeliveryRatio();
        double trust = 1.0;
        
        if (packetDeliveryRatio < LOW_PACKET_DELIVERY_RATIO_THRESHOLD) {
            trust -= LOW_PACKET_DELIVERY_RATIO_PENALTY;
        }
        
        // Add additional factors to the trust calculation here
        
        return trust;
    }
    // calculate trust by transmission delay
    public double calculateTrust(Node.Node_TD node) {
        double transmissionDelay = node.getTransmissionDelay();
        double trust = 1.0;
        
        if (transmissionDelay > HIGH_TRANSMISSION_DELAY_THRESHOLD) {
            trust -= HIGH_TRANSMISSION_DELAY_PENALTY;
        }
        
        // Add additional factors to the trust calculation here
        
        return trust;
    }
    // calculate trust by past interaction of a node
    public double addInteraction (Node node) {
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
        return updateTrustScore(node);
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

public class Final1 {
    public static void main(String[] args) {
        Node obj = new Node();
        Node.Node_SS ss = obj.new Node_SS(0.24);
        TrustCalculator tc = new TrustCalculator();
        double a = tc.TrustForSignalStrength(ss);
        Node.Node_NE ne = obj.new Node_NE(52);
        double b = tc.TrustForNodeenergy(ne);
        Node.Node_PDR pdr = obj.new Node_PDR(0.50);
        double c = tc.TrustforPDR(pdr);
        Node.Node_TD td = obj.new Node_TD(0.50);
        double d = tc.calculateTrust(td);
        System.out.println("Total trust for Signal Strength: "+tc.TrustForSignalStrength(ss)); // 0.5
        System.out.println("Total trust for Node Energy: "+tc.TrustForNodeenergy(ne)); //0.7
        System.out.println("Total trust for PDR: "+tc.TrustforPDR(pdr)); //0.5
        System.out.println("Total trust for Transmission Delay: "+tc.calculateTrust(td)); //1.0
        // System.out.println("Total trust for Past Interaction: "+tc.addInteraction(obj)); //0.5
             
        
    }
}
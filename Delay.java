class Node {
    private double transmissionDelay;

    public Node(double transmissionDelay) {
        this.transmissionDelay = transmissionDelay;
    }

    public double getTransmissionDelay() {
        return transmissionDelay;
    }
}

class TrustCalculator {
    private static final double HIGH_TRANSMISSION_DELAY_THRESHOLD = 1.0;
    private static final double HIGH_TRANSMISSION_DELAY_PENALTY = 0.5;

    public double calculateTrust(Node node) {
        double transmissionDelay = node.getTransmissionDelay();
        double trust = 1.0;
        
        if (transmissionDelay > HIGH_TRANSMISSION_DELAY_THRESHOLD) {
            trust -= HIGH_TRANSMISSION_DELAY_PENALTY;
        }
        
        // Add additional factors to the trust calculation here
        
        return trust;
    }
}
public class Delay 
{
    public static void main(String[] args) {
        Node obj = new Node(0.29);
        TrustCalculator obj1 = new TrustCalculator();
        System.out.println(obj1.calculateTrust(obj));
    }
}
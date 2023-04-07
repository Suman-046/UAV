class Node {
    private double packetDeliveryRatio;

    public Node(double packetDeliveryRatio) {
        this.packetDeliveryRatio = packetDeliveryRatio;
    }

    public double getPacketDeliveryRatio() {
        return packetDeliveryRatio;
    }
}

class TrustCalculator {
    private static final double LOW_PACKET_DELIVERY_RATIO_THRESHOLD = 0.5;
    private static final double LOW_PACKET_DELIVERY_RATIO_PENALTY = 0.5;
    private static final double HIGH_PACKET_DELIVERY_RATIO_VARIABILITY_THRESHOLD = 0.7;
    private static final double HIGH_PACKET_DELIVERY_RATIO_VARIABILITY_PENALTY = 0.3;

    public double calculateTrust(Node node) {
        double packetDeliveryRatio = node.getPacketDeliveryRatio();
        double trust = 1.0;
        
        if (packetDeliveryRatio < LOW_PACKET_DELIVERY_RATIO_THRESHOLD) {
            trust -= LOW_PACKET_DELIVERY_RATIO_PENALTY;
        }
        
        // Add additional factors to the trust calculation here
        
        return trust;
    }
}
public class PDR {
    public static void main(String[] args) {
        Node obj = new Node(0.24);
        TrustCalculator obj2 = new TrustCalculator();
        System.out.println(obj2.calculateTrust(obj));
        System.out.println(obj.getPacketDeliveryRatio());
    }
}
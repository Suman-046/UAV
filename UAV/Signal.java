class Node {
    private double energy;

    public Node(double energy) {
        this.energy = energy;
    }

    public double getEnergy() {
        return energy;
    }
}

 class TrustCalculator {
    private static final double LOW_ENERGY_THRESHOLD = 0.3;
    private static final double LOW_ENERGY_PENALTY = 0.5;
    private static final double HIGH_ENERGY_VARIABILITY_THRESHOLD = 0.7;
    private static final double HIGH_ENERGY_VARIABILITY_PENALTY = 0.3;

    public double calculateTrust(Node node) {
        double energy = node.getEnergy();
        double trust = 1.0;
        
        if (energy < LOW_ENERGY_THRESHOLD) {
            trust -= LOW_ENERGY_PENALTY;
        }
        
        // Add additional factors to the trust calculation here
        
        return trust;
    }
}
public class Signal 
{
    public static void main(String[] args) {
        Node obj = new Node(0.24);
        TrustCalculator obj1 = new TrustCalculator();
        System.out.println(obj1.calculateTrust(obj));
        
    }
}

class Node {
    private double batteryLevel = 100.0;
    private double batteryUsage;

    public Node( double batteryUsage) {
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

class TrustCalculator {
    private static final double LOW_BATTERY_THRESHOLD = 0.3;
    private static final double LOW_BATTERY_PENALTY = 0.5;
    private static final double HIGH_BATTERY_USAGE_THRESHOLD = 0.7;
    private static final double HIGH_BATTERY_USAGE_PENALTY = 0.3;

    public double calculateTrust(Node node) {
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
}
public class NodeEnergy {
    public static void main(String[] args) {
        Node obj = new Node(10);
        TrustCalculator obj2 = new TrustCalculator();
        System.out.println(obj2.calculateTrust(obj));
        System.out.println(obj.getBatteryLevel());
        System.out.println(obj.getBatteryUsage());
    }
}
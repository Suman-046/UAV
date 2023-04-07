public class Recognition_Matrix {
    private double[][] matrix;
    private int numNodes;
    private double communicationRange;

    public Recognition_Matrix(int numNodes, double communicationRange) {
        this.numNodes = numNodes;
        this.communicationRange = communicationRange;
        this.matrix = new double[numNodes][numNodes];
    }

    public void calculateMatrix(UAV[] uavs) {
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                if (i == j) {
                    matrix[i][j] = 1.0;
                } else {
                    double distance = calculateDistance(uavs[i], uavs[j]);
                    if (distance <= communicationRange) {
                        matrix[i][j] = 1.0;
                    } else {
                        matrix[i][j] = 0.0;
                    }
                }
            }
        }
    }

    private double calculateDistance(UAV uav1, UAV uav2) {
        double xDiff = uav1.getX() - uav2.getX();
        double yDiff = uav1.getY() - uav2.getY();
        double zDiff = uav1.getZ() - uav2.getZ();
        return Math.sqrt(xDiff * xDiff + yDiff * yDiff + zDiff * zDiff);
    }

    public double[][] getMatrix() {
        return matrix;
    }
}

import java.util.stream.*;
import java.util.*;

public class weightage {
    public static void main(String[] args) {
        // define the number of nodes and trust parameters
        int N = 10; // number of nodes
        int P = 3; // number of trust parameters

        // define the adjacency matrix for the network
        int[][] adj_matrix = new int[N][N];

        // define the trust matrix for the network
        double[][][] trust_matrix = new double[N][N][P];

        // define the weight matrix for the trust parameters
        double[][] weight_matrix = new double[P][N];

        // define the membership functions for the trust parameters
        double[][] membership_functions = new double[P][3];

        // define the clusters for the nodes
        ArrayList<ArrayList<Integer>> clusters = new ArrayList<ArrayList<Integer>>();
        clusters.add(new ArrayList<Integer>());
        clusters.add(new ArrayList<Integer>());

        // function to calculate the degree of membership
        double calcMembership(double x, double a, double b, double c) {
            if (x <= a || x >= c) {
                return 0.0;
            } else if (x > a && x < b) {
                return (x - a) / (b - a);
            } else {
                return (c - x) / (c - b);
            }
        }

        // function to calculate the weight matrix
        void calcWeightMatrix() {
            for (int i = 0; i < P; i++) {
                double sum = 0.0;
                for (int j = 0; j < N; j++) {
                    double product = 1.0;
                    for (int k = 0; k < N; k++) {
                        if (adj_matrix[j][k] == 1) {
                            product *= calcMembership(trust_matrix[j][k][i], membership_functions[i][0], membership_functions[i][1], membership_functions[i][2]);
                        }
                    }
                    weight_matrix[i][j] = product;
                    sum += product;
                }
                for (int j = 0; j < N; j++) {
                    weight_matrix[i][j] /= sum;
                }
            }
        }

        // function to calculate the clusters
        void calcClusters() {
            for (int i = 0; i < N; i++) {
                if (clusters.get(0).size() < clusters.get(1).size()) {
                    clusters.get(0).add(i);
                } else {
                    clusters.get(1).add(i);
                }
            }
        }

        // input the adjacency matrix and trust matrix
        // ...

        // input the membership functions for the trust parameters
        // ...

        // calculate the weight matrix
        calcWeightMatrix();

        // calculate the clusters
        calcClusters();

        // print out the weight matrix and clusters
        // ...
    }
}

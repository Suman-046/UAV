import java.util.*;

public class N2 {

  public static final int N = 10; // number of nodes
  public static final int P = 3; // number of trust parameters

  // define the adjacency matrix for the network
  public static int[][] adj_matrix = new int[N][N];

  // define the trust matrix for the network
  public static double[][][] trust_matrix = new double[N][N][P];

  // define the weight matrix for the trust parameters
  public static double[][] weight_matrix = new double[P][N];

  // define the membership functions for the trust parameters
  public static double[][] membership_functions = new double[P][3];

  // function to calculate the degree of membership
  public static double calcMembership(double x, double a, double b, double c) {
    if (x <= a || x >= c) {
      return 0.0;
    } else if (x > a && x < b) {
      return (x - a) / (b - a);
    } else {
      return (c - x) / (c - b);
    }
  }

  // function to calculate the weight matrix
  public static void calcWeightMatrix() {
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

  // function to calculate the trust level of a node
  public static double calcTrust(int node) {
    double sum = 0.0;
    for (int i = 0; i < P; i++) {
      sum += weight_matrix[i][node] * trust_matrix[node][node][i];
    }
    return sum;
  }

  public static void main(String[] args) {
    // input the adjacency matrix and trust matrix
    // ...

    // input the membership functions for the trust parameters
    // ...

    // calculate the weight matrix
    calcWeightMatrix();

    // calculate the trust level of each node
    double[] trust_levels = new double[N];
    for (int i = 0; i < N; i++) {
      trust_levels[i] = calcTrust(i);
    }

    // print out the trust levels
    for (int i = 0; i < N; i++) {
      System.out.println("Node " + i + " trust level: " + trust_levels[i]);
    }
  }
}

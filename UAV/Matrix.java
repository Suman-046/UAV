import java.util.Scanner;
import java.util.Arrays;
public class Matrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of nodes : ");
        int nodeNum = sc.nextInt();
        double nodeValue[][] = new double[nodeNum][5];
        for(int i=0; i<nodeNum; i++) {
            System.out.println("Enter value for node "+(i+1));
            System.out.println("SS  PDR  NE  TD  PI");
            for(int j=0; j<5; j++) {
                nodeValue[i][j] = sc.nextDouble();
            }
        }
        // print using for loop
        for(int i=0; i<nodeNum; i++) {
            for(int j=0; j<5; j++) {
                System.out.print(nodeValue[i][j]+" ");
            }
            System.out.println();
        }
        // print using for-each loop
        System.out.println();

        for(double a[] : nodeValue) {
            System.out.println(Arrays.toString(a));
        }
        sc.close();
    }
}
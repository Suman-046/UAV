import java.text.DecimalFormat;
import java.util.Scanner;
public class Normalize {
    public static void main(String[] args) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
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
        // Standardize the values
        for(int i=0; i<nodeNum; i++) {
            for(int j=0; j<5; j++) {
                // for signal strength
                if(j==0) {
                    double ss = nodeValue[i][j] / 3.42;
                    nodeValue[i][j] =Double.parseDouble(decimalFormat.format(ss));
                }
                // for packet delivery ratio and node energy
                if(j==1 || j==2) {
                    double pkd_or_ne = nodeValue[i][j] / 100;
                    nodeValue[i][j] =Double.parseDouble(decimalFormat.format(pkd_or_ne));
                }
                // for transmission delay
                if(j==3) {
                    double td = (2.45 - nodeValue[i][j]) / 1.84;
                    nodeValue[i][j] =Double.parseDouble(decimalFormat.format(td));
                }
            }
        }
        // print using for loop
        System.out.println();
        for(int i=0; i<nodeNum; i++) {
            for(int j=0; j<5; j++) {
                System.out.print(nodeValue[i][j]+" ");
            }
            System.out.println();
        }
    }
}
import java.text.DecimalFormat;
import java.util.Scanner;
public class Direct_trust {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    public static void directTrust(double arr[][]) {
        double nodeWeight[] = new double[6];
        double dt[] = new double[6];
        for(int i=0; i<6; i++) {
            double totWeight =0;
            for(int j=0; j<4; j++) {
                if(i==0) {
                    totWeight += 0.25 * (arr[i][j]);
                    nodeWeight[i] = totWeight;
                }
                if(i==1) {
                    totWeight += 0.25 * (arr[i][j]);
                    nodeWeight[i] = totWeight;
                }
                if(i==2) {
                    totWeight += 0.25 * (arr[i][j]);
                    nodeWeight[i] = totWeight;
                }
                if(i==3) {
                    totWeight += 0.25 * (arr[i][j]);
                    nodeWeight[i] = totWeight;
                }
                if(i==4) {
                    totWeight += 0.25 * (arr[i][j]);
                    nodeWeight[i] = totWeight;
                }
                if(i==5) {
                    totWeight += 0.25 * (arr[i][j]);
                    nodeWeight[i] = totWeight;
                }
            }
        }
        // calculate direct trust
        for(int i=0; i<6; i++) {
            if(nodeWeight[i] <= 0.29) {
                double trust = 0.5 * (Math.pow(0.5, nodeWeight[i]));
                dt[i] = Double.parseDouble(decimalFormat.format(trust));
            }else if(nodeWeight[i] >= 0.3 && nodeWeight[i] <= 0.69) {
                double trust = 0.5 * (Math.pow(1.5, nodeWeight[i]));
                dt[i] = Double.parseDouble(decimalFormat.format(trust));
            }else {
                double trust = 0.5 * (Math.pow(2, nodeWeight[i]));
                dt[i] = Double.parseDouble(decimalFormat.format(trust));
            }
        }
        System.out.println("\n(: Direct Trust :)");
        // print direct trust of nodes
        System.out.println();
        for(int i=0; i<6; i++) {
            System.out.println("Direct trust for node "+(i+1) +" :"+dt[i]);
        }
    }
    public static void main(String[] args) {
        // DecimalFormat decimalFormat = new DecimalFormat("#.##");
        // Scanner sc = new Scanner(System.in);
        // System.out.print("Enter number of nodes : ");
        // int n = sc.nextInt();
        // double nodeValue[][] = new double[6][5];
        // double recog_Matrix[][] = new double[6][5];
        // for(int i=0; i<n; i++) {
        //     System.out.println("Enter value for node "+(i+1));
        //     System.out.println("SS  PDR  NE  TD  PI");
        //     for(int j=0; j<5; j++) {
        //         nodeValue[i][j] = sc.nextDouble();
        //     }
        // }

        double nodeValue[][] = {{0.82,50,52,1.52},
                                {2.01,91,82,0.86},
                                {1.19,73,76,1.09},
                                {0.71,62,39,1.31},
                                {1.47,68,65,1.34},
                                {3.18,88,92,0.78}};
        
        double recog_Matrix[][] = new double[6][4];

        // Standaerdize the values (normalized)
        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
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
        // print the standerdize value
        System.out.println("Standerdize value\n");
        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                System.out.print(nodeValue[i][j]+" ");
            }
            System.out.println();
        }
        // calculate the recignation matrix
        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                // for signal strength
                if(j==0) {
                    double max_num = nodeValue[i][j];
                    double min_num = nodeValue[i][j];
                    for(int n=0; n<6; n++) {
                        if(nodeValue[n][j] > max_num) {
                            max_num = nodeValue[n][j];
                        }
                    }
                    for(int n=0; n<6; n++) {
                        if(nodeValue[n][j] < min_num) {
                            min_num = nodeValue[n][j];
                        }
                    }
                    double a = (nodeValue[i][j] - min_num) / (max_num - min_num);
                    recog_Matrix[i][j] = Double.parseDouble(decimalFormat.format(a));
                }
                // for packet delivery ratio
                if(j==1) {
                    double max_num = nodeValue[i][j];
                    double min_num = nodeValue[i][j];
                    for(int n=0; n<6; n++) {
                        if(nodeValue[n][j] > max_num) {
                            max_num = nodeValue[n][j];
                        }
                    }
                    for(int n=0; n<6; n++) {
                        if(nodeValue[n][j] < min_num) {
                            min_num = nodeValue[n][j];
                        }
                    }
                    double b = (nodeValue[i][j] - min_num) / (max_num - min_num);
                    recog_Matrix[i][j] = Double.parseDouble(decimalFormat.format(b));
                }
                // for node energy
                if(j==2) {
                    double max_num = nodeValue[i][j];
                    double min_num = nodeValue[i][j];
                    for(int n=0; n<6; n++) {
                        if(nodeValue[n][j] > max_num) {
                            max_num = nodeValue[n][j];
                        }
                    }
                    for(int n=0; n<6; n++) {
                        if(nodeValue[n][j] < min_num) {
                            min_num = nodeValue[n][j];
                        }
                    }
                    double c  = (nodeValue[i][j] - min_num) / (max_num - min_num);
                    recog_Matrix[i][j] = Double.parseDouble(decimalFormat.format(c));
                }
                // for transmission delay
                if(j==3) {
                    double max_num = nodeValue[i][j];
                    double min_num = nodeValue[i][j];
                    for(int n=0; n<6; n++) {
                        if(nodeValue[n][j] > max_num) {
                            max_num = nodeValue[n][j];
                        }
                    }
                    for(int n=0; n<6; n++) {
                        if(nodeValue[n][j] < min_num) {
                            min_num = nodeValue[n][j];
                        }
                    }
                    double d = (nodeValue[i][j] - min_num) / (max_num - min_num);
                    recog_Matrix[i][j] = Double.parseDouble(decimalFormat.format(d));
                }
            }
        }
        // print recognition matrix using for loop
        System.out.println("\nRecognition Value\n");
        for(int i=0; i<6; i++) {
            for(int j=0; j<4; j++) {
                System.out.print(recog_Matrix[i][j]+" ");
            }
            System.out.println();
        }
        directTrust(recog_Matrix);
    }
}

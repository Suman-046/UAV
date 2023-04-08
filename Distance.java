import java.text.DecimalFormat;
import java.util.TreeMap;

public class Distance {
    static DecimalFormat decimalFormat = new DecimalFormat("#.##");
    static TreeMap<String,Double> avgDistance = new TreeMap<>();
    
    public static TreeMap<String,Double> calAvgDistance(double nodeDistance[][]) {
        for(int i=0; i<nodeDistance.length; i++) {
            double distance =0;
            for(int j=0; j<nodeDistance[0].length; j++) {
                distance += (nodeDistance[i][j] / 100);
            }
            avgDistance.put("Node"+(i+1), Double.parseDouble(decimalFormat.format(distance / (nodeDistance.length+1))));
        }
        return avgDistance;
    }
    public static void main(String[] args) {
        double nodeDistance[][] = {{20,15,55,65,30,15},
                                    {20,10,25,20,35,20},
                                    {15,10,20,15,40,5},
                                    {55,25,20,60,50,30},
                                    {65,20,15,60,10,25},
                                    {30,35,40,50,10,10}};

        TreeMap<String,Double> avgDistance = calAvgDistance(nodeDistance);
        System.out.println("Average node distance \n");
        System.out.println(avgDistance);
    }
}

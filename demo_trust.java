public class demo_trust {
    public static void main(String[] args) {
        double node_weight[] = new double[6];
        double node_trust[] = new double[6];
        double arr[][] = {{0.04,0.0,0.25,0.0},
                        {0.53,1.0,0.81,0.87},
                        {0.19,0.56,0.7,0.57},
                        {0.0,0.29,0.0,0.27},
                        {0.31,0.44,0.49,0.22},
                        {1.0,0.93,1.0,1.0}};

        for(int i=0;i<6; i++) {
            double weight =0;
            for(int j=0; j<4; j++) {
                if(i==0) {
                    weight += 0.25 * (arr[i][j]);
                    node_weight[i] =weight;
                    System.out.println(node_weight[i]);
                }
                if(i==1) {
                    weight += 0.25 * (arr[i][j]);
                    System.out.println(weight);
                    node_weight[i] =weight;
                }
                if(i==2) {
                    weight += 0.25 * (arr[i][j]);
                    node_weight[i] =weight;
                }
                if(i==3) {
                    weight += 0.25 * (arr[i][j]);
                    node_weight[i] =weight;
                }
                if(i==4) {
                    weight += 0.25 * (arr[i][j]);
                    node_weight[i] =weight;
                }
                if(i==6) {
                    weight += 0.25 * (arr[i][j]);
                    node_weight[i] =weight;
                }
            }
        }
        for(int i=0; i<6; i++) {
            System.out.print(node_weight[i]+" ");
        }
        //trust
        System.out.println();
        for(int i=0; i<6; i++) {
            double trust = 0.5*(Math.pow((2), node_weight[i]));
            node_trust[i] =trust;
        }
        // 
        for(int i=0; i<6; i++) {
            System.out.println(node_trust[i]+" ");
        }
    }

}

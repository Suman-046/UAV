public class Column_Matrix {
    public static void main(String[] args) {
        int[][] matrix = new int[5][1]; // 5 rows, 1 column
        
        // assign values to the column
        for (int i = 0; i < matrix.length; i++) {
            matrix[i][0] = i + 1;
        }
        
        // print the matrix
        for (int i = 0; i < matrix.length; i++) {
            System.out.println(matrix[i][0]);
        }
    }
}

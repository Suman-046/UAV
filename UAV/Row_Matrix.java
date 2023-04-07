public class Row_Matrix {
    public static void main(String[] args) {
        int[][] matrix = new int[1][5]; // 1 row, 5 columns
        matrix[0] = new int[]{1, 2, 3, 4, 5}; // assign values to the row
        
        // print the matrix
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
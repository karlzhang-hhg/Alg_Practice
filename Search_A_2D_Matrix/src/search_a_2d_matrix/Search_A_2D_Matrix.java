/*Search a element in 2D matrix
 * Note:
1. Java use (int) as floor function;
2. Think the corner cases in detail before write in code;
3. In Java, if we want to pass an arry as a parameter, note that it only passes the reference not value.
    In order to pass by value, I have to deep copy one inside the function;
4. To call a function, I have to bluid a class. Then, create an instance for that class and call that function as the method of that instance with type of class;
5. Java doesn't really support multi-dimensional arrays like C or C++ do. In Java each row of a two-diemsional array is itself a one-dimensional array (but they may not in whole occupy a continous block of memory);
6. Notice that when two integers is divided by another, the result would be automaticall converted to an integer.
 */
package search_a_2d_matrix;
import java.io.*;
import java.util.*;
import java.lang.*;
/**
 *
 * @author kungangzhang
 */

class TwoDMatrix{
    //The first version
    public int[] search2DMatrix_Binary(int[][] M, int[] row_range, int[] col_range,int target){
        //Binary search method
        int[] ind = new int[2];
        ind[0] = -1;
        ind[1] = -1;
        //new row and column range used in the recursion
        int[] new_row_range = new int[2];
        int[] new_col_range = new int[2];
        int row_mid, col_mid;
        if (M == null){
            return ind;
        }
        int row = M.length;
        if (row == 0 || M[0] == null){
            return ind;
        }
        int col = M[0].length;
        if (col == 0){
            return ind;
        }
        
        ind[0] = -2;
        ind[1] = -2;
        if (row_range[0] == row_range[1] && col_range[0] == col_range[1]){
            if (M[row_range[0]][col_range[0]] == target){
                ind[0] = row_range[0];
                ind[1] = col_range[0];
                return ind;
            }else{
                return ind;
            }
        }
        
        
        //In Java, the (int) conversion use the floor conversion
        row_mid = (row_range[0]+(row_range[1]-row_range[0])/2);
        col_mid = (col_range[0]+(col_range[1]-col_range[0])/2);
        //no need to separately deal with cases of single row or single column
//        if (col_range[0] == col_range[1]){
//            if (target > M[row_mid][col_mid]){
//                if (row_mid < row_range[1]){
//                    new_row_range[0]=row_mid+1;
//                    new_row_range[1]=row_range[1];
//                    ind = search2DMatrix_Binary(M,new_row_range,col_range,target);
//                }else{
//                    ind = search2DMatrix_Binary(M,row_range,col_range,target);
//                }
//            }else{
//                if (target < M[row_mid][col_mid]){
//                    if (row_mid > row_range[0]){
//                        new_row_range[0]=row_range[0];
//                        new_row_range[1]=row_mid-1;
//                        ind = search2DMatrix_Binary(M,new_row_range,col_range,target);
//                    }else{
//                        ind = search2DMatrix_Binary(M,row_range,col_range,target);
//                        
//                    }
//                }else{
//                    ind[0] = row_mid;
//                    ind[1] = col_mid;
//                    return ind;
//                }
//            }
//            return ind;
//        }
       
        if (target > M[row_mid][col_mid]){
            //Deep copy
            System.arraycopy(row_range, 0, new_row_range, 0, 2);
            //new_row_range = row_range;
            if (col_mid < col_range[1]){
                new_col_range[0] = col_mid+1;
                new_col_range[1] = col_range[1];
            }else{
                System.arraycopy(col_range, 0, new_col_range, 0, 2);
                //new_col_range = col_range;
                if (row_mid < row_range[1]){
                    new_row_range[0] = row_mid+1;
                    new_row_range[1] = row_range[1];
                }
            }
            ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
            if ((ind[0] < 0 || ind[1] < 0) && row_mid < row_range[1]){
                new_row_range[0] = row_mid+1;
                new_row_range[1] = row_range[1];
                new_col_range[0] = col_range[0];
                new_col_range[1] = col_mid;
                ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
            }else{
                return ind;
            }
        }else{
            if (target < M[row_mid][col_mid]){
                System.arraycopy(row_range, 0, new_row_range, 0, 2);
//                new_row_range = row_range;
                if (col_mid < col_range[1]){
                    new_col_range[0] = col_range[0];
                    new_col_range[1] = java.lang.Math.max(col_mid-1,col_range[0]);
                }else{
                    System.arraycopy(col_range, 0, new_col_range, 0, 2);
//                    new_col_range = col_range;
                    if (row_mid < row_range[1]){
                        new_row_range[0] = row_range[0];
                        new_row_range[1] = java.lang.Math.max(row_mid-1, row_range[0]);
                    }
                }
                ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
                if ((ind[0] < 0 || ind[1] < 0) && row_mid > row_range[0]){
                    new_row_range[0] = row_range[0];
                    new_row_range[1] = row_mid-1;
                    new_col_range[0] = col_mid;
                    new_col_range[1] = col_range[1];
                    ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
                }else{
                    return ind;
                }
            }else{
                ind[0] = row_mid;
                ind[1] = col_mid;
                return ind;
            }
        }
        return ind;
    }
    
    //Simplier version, clearer version
//    public int[] search2DMatrix_Binary(int[][] M, int[] row_range, int[] col_range,int target){
//        //Binary search method
//        int[] ind = new int[2];
//        ind[0] = -1;
//        ind[1] = -1;
//        //new row and column range used in the recursion
//        int[] new_row_range = new int[2];
//        int[] new_col_range = new int[2];
//        int row_mid, col_mid;
//        if (M == null){
//            return ind;
//        }
//        int row = M.length;
//        if (row == 0 || M[0] == null){
//            return ind;
//        }
//        int col = M[0].length;
//        if (col == 0){
//            return ind;
//        }
//        
//        ind[0] = -2;
//        ind[1] = -2;
//        if (row_range[0] == row_range[1] && col_range[0] == col_range[1]){
//            if (M[row_range[0]][col_range[0]] == target){
//                ind[0] = row_range[0];
//                ind[1] = col_range[0];
//                return ind;
//            }else{
//                return ind;
//            }
//        }
//        
//        
//        //In Java, the (int) conversion use the floor conversion
//        row_mid = row_range[0]+(row_range[1]-row_range[0])/2;
//        col_mid = col_range[0]+(col_range[1]-col_range[0])/2;
//        if (target > M[row_mid][col_mid]){
//            //Deep copy
//            System.arraycopy(row_range, 0, new_row_range, 0, 2);
//            //The following comment is a wrong code
////            new_row_range = row_range;
//            new_col_range[0] = java.lang.Math.min(col_mid+1, col_range[1]);
//            new_col_range[1] = col_range[1];
//            if (col_mid==col_range[1]){
//                new_row_range[0] = java.lang.Math.min(row_mid+1, row_range[1]);
//                new_row_range[1] = row_range[1];
//            }
//            ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
//            if ((ind[0]<0 || ind[1]<0) && row_mid < row_range[1]){
//                new_row_range[0] = row_mid+1;
//                new_row_range[1] = row_range[1];
//                new_col_range[0] = col_range[0];
//                new_col_range[1] = col_mid;
//                ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
//            }else{
//                return ind;
//            }
//        }else{
//            if (target < M[row_mid][col_mid]){
//                System.arraycopy(row_range, 0, new_row_range, 0, 2);
//                //new_row_range = row_range;
//                new_col_range[0] = col_range[0];
//                new_col_range[1] = java.lang.Math.max(col_mid-1, col_range[0]);
//                if (col_mid == col_range[1]){
//                    new_row_range[0] = row_range[0];
//                    new_row_range[1] = java.lang.Math.max(row_mid-1,row_range[0]);
//                }
//                ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
//                if ((ind[0]<0||ind[1]<0) && row_mid > row_range[0]){
//                    new_row_range[0] = row_range[0];
//                    new_row_range[1] = row_mid-1;
//                    new_col_range[0] = col_mid;
//                    new_col_range[1] = col_range[1];
//                    ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
//                }else{
//                    return ind;
//                }
//            }else{
//                ind[0] = row_mid;
//                ind[1] = col_mid;
//            }
//        }
//        return ind;
//    }
    
    //Change the (int) conversion to the conventional 4-floor, 5-ceiling way, and the code become following.
//    public int[] search2DMatrix_Binary(int[][] M, int[] row_range, int[] col_range,int target){
//        //Binary search method
//        int[] ind = new int[2];
//        ind[0] = -1;
//        ind[1] = -1;
//        //new row and column range used in the recursion
//        int[] new_row_range = new int[2];
//        int[] new_col_range = new int[2];
//        int row_mid, col_mid;
//        if (M == null){
//            return ind;
//        }
//        int row = M.length;
//        if (row == 0 || M[0] == null){
//            return ind;
//        }
//        int col = M[0].length;
//        if (col == 0){
//            return ind;
//        }
//        
//        ind[0] = -2;
//        ind[1] = -2;
//        if (row_range[0] == row_range[1] && col_range[0] == col_range[1]){
//            if (M[row_range[0]][col_range[0]] == target){
//                ind[0] = row_range[0];
//                ind[1] = col_range[0];
//                return ind;
//            }else{
//                return ind;
//            }
//        }
//        
//        
//        //Here, by adding 0.5, I make the (int) conversion the same as convention that is 4-floor, 5-ceiling
//        row_mid = (int)(row_range[0]+(row_range[1]-row_range[0])/2.0+0.5);
//        col_mid = (int)(col_range[0]+(col_range[1]-col_range[0])/2.0+0.5);
//        if (target > M[row_mid][col_mid]){
//            System.arraycopy(row_range, 0, new_row_range, 0, 2);
//            //The next line is wrong;
//            //new_row_range = row_range;
//            new_col_range[0] = java.lang.Math.min(col_mid+1, col_range[1]);
//            new_col_range[1] = col_range[1];
//            if (col_mid==col_range[0]){
//                new_row_range[0] = java.lang.Math.min(row_mid+1, row_range[1]);
//                new_row_range[1] = row_range[1];
//            }
//            ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
//            if ((ind[0]<0 || ind[1]<0) && row_mid < row_range[1]){
//                new_row_range[0] = row_mid+1;
//                new_row_range[1] = row_range[1];
//                new_col_range[0] = col_range[0];
//                new_col_range[1] = col_mid;
//                ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
//            }else{
//                return ind;
//            }
//        }else{
//            if (target < M[row_mid][col_mid]){
//                System.arraycopy(row_range, 0, new_row_range, 0, 2);
//                //new_row_range = row_range;
//                new_col_range[0] = col_range[0];
//                new_col_range[1] = java.lang.Math.max(col_mid-1, col_range[0]);
//                if (col_mid == col_range[0]){
//                    new_row_range[0] = row_range[0];
//                    new_row_range[1] = java.lang.Math.max(row_mid-1,row_range[0]);
//                }
//                ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
//                if ((ind[0]<0||ind[1]<0) && row_mid > row_range[0]){
//                    new_row_range[0] = row_range[0];
//                    new_row_range[1] = row_mid-1;
//                    new_col_range[0] = col_mid;
//                    new_col_range[1] = col_range[1];
//                    ind = search2DMatrix_Binary(M,new_row_range,new_col_range,target);
//                }else{
//                    return ind;
//                }
//            }else{
//                ind[0] = row_mid;
//                ind[1] = col_mid;
//            }
//        }
//        return ind;
//    }
    
    //Starting from the upper-right corner to search for the target value.
    public int[] search2DMatrix_Sequential(int[][] M, int target){
        int[] ind = {-1,-1};
        
        if (M == null){
            return ind;
        }
        int n = M.length;
        if (n == 0 || M[0] == null){
            return ind;
        }
        int m = M[0].length;
        if (m == 0){
            return ind;
        }
        
        ind[0] = 0;
        ind[1] = m-1;
        
        while(true){
            if (target < M[ind[0]][ind[1]]){
                ind[1]--;
                if(ind[1] < 0){
                    ind[0] = -2;
                    ind[1] = -2;
                    return ind;
                }
            }else{
                if(target > M[ind[0]][ind[1]]){
                    ind[0]++;
                    if(ind[0] >= n){
                        ind[0] = -2;
                        ind[1] = -2;
                        return ind;
                    }
                }else{
                    return ind;
                }
            }
        }
    }
}

public class Search_A_2D_Matrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        
        //build a 2D matrix
        int n = in.nextInt();
        int m = in.nextInt();
        
        int[][] M = new int[n][m];
        for (int i = 0; i<n; ++i){
            for (int j = 0; j<m; ++j){
                M[i][j] = in.nextInt();
            }
        }
        
        TwoDMatrix twoDMatrix = new TwoDMatrix();
        int[] row_range = new int[2];
        int[] col_range = new int[2];
        int[] ind = new int[2];

        row_range[0] = 0;
        row_range[1] = n-1;
        col_range[0] = 0;
        col_range[1] = m-1;       
//        Scanner typein = new Scanner(System.in);
//        System.out.println("What is the target value you want to search in the 2D matrix?\n");
//        int target = typein.nextInt();
//        ind = twoDMatrix.search2DMatrix_Binary(M, row_range, col_range, target);
//        System.out.printf("The index of %d is (%d, %d)\n", target, ind[0], ind[1]);
        int target;
        while (in.hasNext()){
            target = in.nextInt();
            ind = twoDMatrix.search2DMatrix_Binary(M, row_range, col_range, target);
//            ind = twoDMatrix.search2DMatrix_Sequential(M,target);
            System.out.printf("The index of %d is (%d, %d)\n", target,ind[0], ind[1]);
        }
    }
    
}

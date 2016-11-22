/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maxsubarray;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */
class MaxSubArray_Func{
    public int onePass(int[] ar){
        if (ar == null || ar.length == 0){
            return Integer.MIN_VALUE;
        }
        
        int curSum = ar[0];
        int maxSum = ar[0];
        
        for (int i = 1; i < ar.length ; i++){
            curSum = Math.max(ar[i], curSum+ar[i]);
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }
}

public class MaxSubArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        int[] ar = new int[n];
        for (int i = 0;i<n;i++){
            ar[i] = in.nextInt();
        }
        MaxSubArray_Func maxsubarray = new MaxSubArray_Func();
        System.out.printf("The maximum sum of subarray is %d\n", maxsubarray.onePass(ar));
    }
    
}

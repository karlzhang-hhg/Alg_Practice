/*
 * This is to find the largest rectangle in a histogram. Actually, this is just to max a minimum of a histogram and its sub-histogram.
Decompose it into sub-problem. Find the smallest column and separate the hist in to two sub-histogram.
 */
package maxmin1;
import java.io.*;
import java.util.*;
/**
 *
 * @author kungangzhang
 */
class MaxMin1_Func{
    public int maxMin1(int[] hist,int left,int right){
        //Given a hist and the left and right of the boundaries of sub-hist
        //Return the maximum area of rectangle
        //TC: O(n); SC: O(logn)
        if (left == right) return hist[left];
        int min=hist[left],min_ind=left;
        for (int i = left;i<=right;i++){
            if (hist[i]<=min){
                min = hist[i];
                min_ind = i;
            }
        }
        if (min_ind == left){
            return Math.max(min*(right-left+1),maxMin1(hist,left+1,right));
        }else{
            if (min_ind == right){
                return Math.max(maxMin1(hist,left,right-1), min*(right-left+1));
            }else{
                int temp = Math.max(maxMin1(hist,left,min_ind-1), maxMin1(hist,min_ind+1,right));
                return Math.max(min*(right-left+1), temp);
            }
        }
        
    }
}

public class MaxMin1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        
        int n = in.nextInt();
        while(n!=-1){
            int[] hist = new int[n];
            for (int i=0;i<n;i++){
                hist[i] = in.nextInt();
            }
            
            MaxMin1_Func maxmin = new MaxMin1_Func();
            System.out.println(maxmin.maxMin1(hist, 0, n-1)+" ");
            
            n = in.nextInt();
        }
        in.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twosumi;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */

class TwoSumI_Func{
    public int[] twoPointers_2_for(int[] numAr, int targ){
        //Two for loop to swap through all array numAr to test if there is a pair 
        //with sum equal to the target
        //TC: O(n^2)
        //SC: O(n)
        //Return the index of the pair
        int n = numAr.length; //Length of the array numAR
        int[] ind = new int[2]; //Create a vector to store the index statifying the two-sum
        for (int i=0; i<n; i++){
            for (int j=i+1; j<n; ++j){
                if (numAr[i]+numAr[j] == targ){
                    ind[0] = i;
                    ind[1] = j;
                    return ind;
                }
            }
        }
        ind[0] = -1;
        ind[1] = -1;
        return ind;
    }
    
    public int twoPointers_1_for(int[] numAr, int targ){
        //Use one for loop and start two pointers at the beginning and end of the 
        //array and test if there is a pair with sum equal to the targ.
        //Before doing this I have to sort the array.
        //TC: O(nlogn);
        //SC: O(n);
        //Return true or false
        int n = numAr.length;
        Arrays.sort(numAr);
        int[] ind = {0,n-1};
        int temp;
        while (ind[0]<ind[1]){
            temp = numAr[ind[0]]+numAr[ind[1]];
            if (targ == temp){
                return 1;
            }else{
                if (targ > temp) ind[0]++;
                else ind[1]--;
            }
        }
        ind[0]=-1;
        ind[1]=-1;
        return 0;
    }
    
    public int[] hashmap_2_pass(int[] numAr, int targ){
        //Use Hashmap to store the remain for each element. Then use hashmap search to
        //test if an element in the set of remain.
        //Note that in condition we need to test if the index of an element equal to the index
        //of remain if there is a match.
        //Here, we assume the elements are unique, so that we can use Hashmap.
        //TC: O(n)
        //SC: O(n)
        int[] ind = {-1,-1};
        Map<Integer, Integer> remainSet = new HashMap<Integer, Integer>();//<remain, index>
        
        for (int i = 0; i < numAr.length; ++i){
            remainSet.put(targ-numAr[i], i);
        }
        
        for (int i=0; i < numAr.length; ++i){
            if (remainSet.containsKey(numAr[i]) && remainSet.get(numAr[i])!=i){
                ind[0] = i;
                ind[1] = remainSet.get(numAr[i]);
                return ind;
            }
        }
        return ind;
    }
    
    public int[] hashmap_1_pass(int[] numAr, int targ){
        //Use Hashmap, just one pass
        //TC: O(n);
        //SC: O(n);
        int[] ind = {-1,-1};
        Map<Integer,Integer> remainSet = new HashMap<Integer, Integer>();//<remain,index>
        remainSet.put(targ-numAr[0], 0);//put the remain of the first element into the Hashmap
        
        for (int i = 1; i < numAr.length; ++i){
            if (remainSet.containsKey(numAr[i])){
                ind[0] = remainSet.get(numAr[i]);
                ind[1] = i;
                return ind;
            }else{
                remainSet.put(targ-numAr[i], i);
            }
        }
        return ind;
    }
}

public class TwoSumI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Read number array from input.txt
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt(); // Number of entries in the array
        int[] numAr = new int[n]; // Create an arry to store the result
        for (int i = 0; i<n; ++i){
            numAr[i] = in.nextInt();
        }
        int targ = in.nextInt();
        
        TwoSumI_Func twosum_ind = new TwoSumI_Func();
        
        System.out.printf("The target is %d\n",targ);
        int[] ind = new int[2];
        ind = twosum_ind.hashmap_1_pass(numAr, targ);
        System.out.printf("(%d,%d)\n", ind[0],ind[1]);
//        int res;
//        res = twosum_ind.twoPointers_1_for(numAr, targ);
//        System.out.printf("The result is %d\n", res);
        
        Scanner typein = new Scanner(System.in);
        targ = typein.nextInt();
        System.out.printf("The target is %d\n",targ);
        ind = twosum_ind.hashmap_1_pass(numAr, targ);
        System.out.printf("(%d,%d)\n", ind[0],ind[1]);
//        res = twosum_ind.twoPointers_1_for(numAr, targ);
//        System.out.printf("The result is %d\n", res);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threesumi;
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
    public int[] hashmap_1_pass_ind_range(int[] numAr, int targ, int sub_len){
        //Use Hashmap, just one pass
        //Input: 
        //numAr: the array we want to process
        //targ: target value
        //sub_len: the sub length of the array that we processed
        //TC: O(n);
        //SC: O(n);
        int[] ind = {-1,-1};
        Map<Integer,Integer> remainSet = new HashMap<Integer, Integer>();//<remain,index>
        remainSet.put(targ-numAr[0], 0);//put the remain of the first element into the Hashmap
        
        for (int i = 1; i < sub_len; ++i){
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

class ThreeSum_Func{
    public int[] callTwoSum(int[] numAr,int targ){
        //Use HashMap to store <remain, index> pairs.
        //One big pass to find if there is any triple with sum equal to the target
        //Call two sum function.
        //TC: O(n^2);
        //SC: O(n);
        int n = numAr.length;
        int[] ind ={-1,-1,-1};
        int[] ind1 = {-1,-1};
        int temp;
        TwoSumI_Func twosum = new TwoSumI_Func();
        Map<Integer, Integer> remainSet = new HashMap<Integer, Integer>();//<remain, index>
        remainSet.put(targ-numAr[0], 0);
        remainSet.put(targ-numAr[1], 1);
        for (int i=2; i<n; ++i){
            temp = targ - numAr[i];
            ind1 = twosum.hashmap_1_pass_ind_range(numAr, temp, i);
            if (ind1[0]!=-1){
                ind[0] = ind1[0];
                ind[1] = ind1[1];
                ind[2] = i;
                return ind;
            }else{
                remainSet.put(temp, i);
            }
        }
        return ind;
    }
}

public class ThreeSumI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        Scanner typein = new Scanner(System.in);
        int n = in.nextInt();
        int[] numAr = new int[n];
        for (int i=0;i<n;++i){
            numAr[i] = in.nextInt();
        }
        int targ = in.nextInt();
        
        ThreeSum_Func threesum = new ThreeSum_Func();
        int[] ind =new int[3];
        ind = threesum.callTwoSum(numAr,targ);
        System.out.printf("The target is %d\n", targ);
        System.out.printf("(%d,%d,%d)\n", ind[0],ind[1],ind[2]);
        
        targ = typein.nextInt();
        ind = threesum.callTwoSum(numAr,targ);
        System.out.printf("The target is %d\n", targ);
        System.out.printf("(%d,%d,%d)\n", ind[0],ind[1],ind[2]);

    }
    
}

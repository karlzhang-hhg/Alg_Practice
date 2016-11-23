/*
 * Find the kth smallest element.
 */
package findkth;
import java.io.*;
import java.util.*;
/**
 *
 * @author kungangzhang
 */

class QuickSelect{
    public Integer quickSelect(ArrayList<Integer> arr, int k, int left, int right){
        //Given the head of an ArrayList, k, and left and right index of that ArrayList
        //Return the kth smallest value of that ArrayList
        //TC: O(n)(average);SC: O(n)
        //Comparing with the brutal force selection (TC: O(n^2))
        if (right==left) return arr.get(left);
        Integer targ = arr.get(right);
        int smaller = 0;
        for (int i=left;i<right;i++){
            Integer cur = arr.get(i);
            if (cur<targ){
                arr.remove(i);
                arr.add(left,cur);
                smaller++;
            }
        }
        if (smaller > k-1){
            return quickSelect(arr,k,left,left+smaller-1);
        }else{
            if (smaller == k-1) return targ;
            else return quickSelect(arr,k-smaller-1,left+smaller,right-1);
        }
    }
}
public class FindKth {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        ArrayList<Integer> arr = new ArrayList<Integer>();
        QuickSelect qs = new QuickSelect();
        int n = in.nextInt();
        while(n!=-1){
            while(n--!=0){
                arr.add(in.nextInt());
            }
            int m = in.nextInt();
            while(m--!=0){
                System.out.println(qs.quickSelect(arr, in.nextInt(), 0, arr.size()-1));
            }
            
        }
    }
    
}

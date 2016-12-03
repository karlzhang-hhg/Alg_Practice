/*
 Given a sorted array which is rotated at some unknown pivot, and a target value to search.
 Return the index of the target, or -1 if not found.
 */
package searchrotatedsortedarray;
import java.io.*;
import java.util.*;
/**
 *
 * @author kungangzhang
 */

class SRSA{
    public int searchRSA(int[] ar, int targ){
        //Search targ in a rotated sorted array and return the index of that targ if found, o.w. return -1
        //TC: O(logn)
        //SC: O(1)
        if (ar == null || ar.length == 0) return -1;
        int start = 0, end = ar.length-1;
        while (start+1<end){
            int mid = start+(end-start)/2;
            
            if (ar[mid]>ar[start]){
                if (targ >= ar[start] && targ <= ar[mid]){
                    end = mid;
                }else{
                    start = mid+1;
                }
            }else if (ar[mid]<ar[start]){
                if (targ >= ar[mid] && targ <= ar[end]){
                    start = mid;
                }else{
                    end = mid-1;
                }
            }else{
                //For duplicate cases
                start++;
            }
            
        }
        if (ar[start]==targ) return start;
        if (ar[end]==targ) return end;
        return -1;
    }
    
}
public class SearchRotatedSortedArray {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        SRSA srsa = new SRSA();
        
        int n = in.nextInt();
        while (n!=-1){
            int[] ar = new int[n];
            for (int i=0;i<n;i++){
                ar[i]=in.nextInt();
            }
            int m = in.nextInt();
            for (int i=0;i<m;i++){
                int targ = in.nextInt();
                System.out.println(srsa.searchRSA(ar, targ));
            }
            System.out.println();
            n = in.nextInt();
        }
    }
    
}

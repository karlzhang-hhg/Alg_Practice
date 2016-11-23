/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergesort;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */

class MergeSort_Func{
    public void mergeSort(int[] arr){
        if (arr==null | arr.length == 0) return;
        int n = arr.length;
        doSort(arr,0,n-1);
    }
    
    private void doSort(int[] arr, int start,int end){
        //The parameter arr is pass-by-reference
        //We don't need to return an arry, because the changes have already been recorded
        if (start == end) return;
        int mid = start + (end-start)/2;
        doSort(arr,start,mid);
        doSort(arr,mid+1,end);
        doMerge(arr,start,mid,end);
    }
    
    private void doMerge(int[] arr, int start,int mid,int end){
        //mid belong to the first half array
        int[] temp = new int[end-start+1];
        for (int i=0;i<end-start+1;i++) temp[i]=arr[start+i];
        int j=0,k=mid-start+1;//j<=mid-start;k<=end-start
        for (int i=start;i<=end;){
            //The index is very hard to get right
            while (j<=mid-start && (k>end-start || temp[j]<=temp[k])){
                arr[i] = temp[j];
                j++;
                i++;
            }
            while (k<=end-start && (j>mid-start || temp[k]<temp[j])){
                arr[i] = temp[k];
                k++;
                i++;
            }
        }
        
    }
    
    public void print(int[] arr){
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+" ");
        }
        System.out.println();
    }
}

public class MergeSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        MergeSort_Func ms = new MergeSort_Func();
        int n=in.nextInt();
        while(n!=-1){
            int[] arr = new int[n];
            for (int i=0;i<n;i++){
                arr[i]=in.nextInt();
            }
            ms.mergeSort(arr);
            ms.print(arr);
            n=in.nextInt();
        }
    }
    
}

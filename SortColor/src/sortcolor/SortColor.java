/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortcolor;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */
class SortColor_Func{
    public void twoWalls(int[] co){
        //Set two walls, left and right. The left 
        if (co == null || co.length<=2){
            return;
        }
        
        int n = co.length;
        int left = 0, right = n-1, cur = 0;
        while (cur <= right){
            if (co[cur] == 2){
                swap(co,cur,right);
                right--;
            }else{
                if (co[cur] == 0){
                    swap(co,cur,left);
                    left++;
                    cur++;//The reason that cur need to add one is that 
                          //the elements between left and cur including left
                          //must be 1.
                }else{
                    cur++;
                }
            }
            if (co[right] == 2){
                right--;
            }
        }
        
    }
    
    public void swap(int[] co,int i, int j){
        //Use ^ (bitwise XOR) to do the exchange
        co[i] ^= co[j];
        co[j] ^= co[i];
        co[i] ^= co[j];
    }
}
public class SortColor {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        
        int n = in.nextInt();
        int[] co = new int[n];
        System.out.println("The original color series:");
        for (int i = 0; i< n; i++){
            co[i] = in.nextInt();
            System.out.printf("%d ", co[i]);
        }
        System.out.println("\n");
        SortColor_Func sortcolor = new SortColor_Func();
        sortcolor.twoWalls(co);//This is a shallow copy;
        System.out.println("The sorted color series:");
        for (int i=0;i<n;i++){
            System.out.printf("%d ", co[i]);
        }
        System.out.println("\n");
    }
    
}

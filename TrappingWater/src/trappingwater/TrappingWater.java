/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trappingwater;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */
class TrappingWater_Cal{
    private Deque<Integer> deque = new LinkedList<Integer>();
    
    public int trappingWater_mine(int[] wall,int n){
        int temp,fillback;
        int water = 0;
        for (int i=0;i<n;i++){
            if (deque.isEmpty()){
                //Initial case for no left wall
                while (i+1 < n && wall[i+1]>=wall[i]) i++;
                deque.offerLast(i);
            }else{
                //There are some left walls
                if (wall[i] <= wall[deque.peekLast()]){
                    deque.offerLast(i);
                }else{
                    fillback = 0;//Record how many columns should push back to the deque, because I already count the sub-trap
                    while (wall[i] > wall[deque.peekLast()]){
                        //If current wall is larger or equal to the wall in the stack, pop out the wall
                        temp = deque.pollLast();
                        if (!deque.isEmpty()){
                            //If deque is not empty, that means some left wall is higher than wall at i
                            water = water + (wall[i]-wall[temp]);
                            fillback++;
                        }else{
                            //If deque is empty, left wall is lower than wall at i; deduct the over-calculate part
                            water = water - (wall[i]-wall[temp])*(i-temp-1);
                            while (i+1 < n && wall[i+1] >= wall[i]) i++;//Find new left wall
                            break;
                        }
                    }
                    while (fillback--!=0) {
                        //Fill back the number of culumns which have been calculated the sub-trap
                        deque.offerLast(i);
                    }
                    deque.offerLast(i);
                }
                
            }    
        }
        return water;
    }
    
    public int trappingWater_mine1(int[] wall,int n){
        //Much simplier and clearer version. The computation is less.
        //Whenever there is a higher right-wall, we fill water accordingly. This can be a visualization of solving this problem.
        //When problem is not very clear, I can think about a way to visualize or animate the dynamics of solving the problem.
        //TC: O(n); SC: O(n);
        int temp;
        int water = 0;
        deque.offerLast(0);
        for (int i=1;i<n;i++){
            if (wall[i] <= wall[deque.peekLast()]){
                deque.offerLast(i);
            }else{
                if (deque.isEmpty()){
                    deque.offerLast(i);
                }else{
                    do{
                        temp = deque.pollLast();
                        if (!deque.isEmpty()){
                            //The last column in the stack must be larger or equal the column just popped out, because of the assumption
                            //that all water stored in the column to the left and lower than the current column has been calculated.
                            water = water + (Math.min(wall[i], wall[deque.peekLast()])-wall[temp])*(i-deque.peekLast()-1);
                        }else{
                            //If the deque is emty, then no water can be stored.
                            break;
                        }    
                    }while(!deque.isEmpty() && wall[i]>wall[deque.peekLast()]);
                    deque.offerLast(i);
                }
            }
        }
        return water;
    }
    
    
    public int trappingWater_mine2(int[] wall, int n){
        //This is simplest way to solve
        //TC: O(n);SC: O(1);
        int left=0,right=n-1,leftw=0,rightw=n-1;
        int water = 0;
        while(left<right){
            if (wall[leftw]>wall[rightw]){
                //If I change the condition to wall[left] > wall[right]
                right--;
                if (wall[right]<wall[rightw]){
                    water = water + wall[rightw]-wall[right];
                }else{
                    rightw=right;
                }
            }else{
                left++;
                if (wall[left]<wall[leftw]){
                    water = water + wall[leftw]-wall[left];
                }else{
                    leftw=left;
                }
            }
        }
        return water;
    }
}
public class TrappingWater {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        
        int n = in.nextInt();
        while(n!=-1){
            int[] wall = new int[n];
            for (int i=0;i<n;i++){
                wall[i] = in.nextInt();
            }
            
            TrappingWater_Cal trappingwater = new TrappingWater_Cal();
            int water = trappingwater.trappingWater_mine2(wall, n);
            System.out.println(water+" ");
            
            n = in.nextInt();
        }
        in.close();
    }
    
}

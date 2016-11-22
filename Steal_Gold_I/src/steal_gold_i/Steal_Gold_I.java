/* Steal_gold_I
time: O(n);
space: O(3n);
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steal_gold_i;
import java.io.*;
import java.util.*;
/**
 *
 * @author kungangzhang
 */

class Street{
    int[] houses;
    HashMap<Integer,Integer> cache;
    
    Street(int[] houses){
        //Initialization of houses
        this.houses = houses;
        //Initialization of cache
        cache =new HashMap<Integer,Integer>();
    }
    
    int findResult(int pos, int count){
        if (pos == houses.length) return 0;
        //Here, I use 3*pos+count to index the solution space
        if (cache.containsKey(3*pos+count)) return cache.get(3*pos+count);
        //Don't steal the current house
        int res = findResult(pos+1,0);
        //Steal the current house
        int temp = 0;
        if (count <2){
            temp = findResult(pos+1,count+1)+houses[pos];
            if (temp > res) res = temp;
        }
        //Cache the result
        cache.put(3*pos+count,res);
        return res;
    }
}
        
public class Steal_Gold_I {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // Read houses in;
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        while(n!= -1){
            int[] houses = new int[n];
            for (int i=0;i<n;++i){
                houses[i] = in.nextInt();
            }
            //instantization the street
            Street street = new Street(houses);
            System.out.println(street.findResult(0, 0));
            n = in.nextInt();
        }
        in.close();
        
    }
    
}

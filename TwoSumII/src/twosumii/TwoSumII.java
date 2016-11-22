/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twosumii;
import java.io.*;
import java.util.*;
//There is a data stream. We need two interfaces: 1. add a number to a string;
//2. find if there is a pair with sum equal to the target.
//The most important thing about data stream problem is designing a data structure
//to achieve high efficiency.
/**
 *
 * @author kungangzhang
 */

class TwoSumII_Func{
    private final Map<Integer, Integer> map = new HashMap<Integer, Integer>();//<number, frequency>
    //Maintain a list to for iteration in find, to avoid iterate the map, which is slow
    private final List<Integer> list = new ArrayList<Integer>();

    public void add(Integer num){
        list.add(num);
        Integer freq = map.get(num);
        if (freq == null){
            map.put(num, 1);
        }else{
            map.put(num, freq+1);
        }
    }
    
    public boolean find(Integer targ){
        for (int curKey:list) {
            Integer remain = targ - curKey;
            Integer cnt = map.get(remain);
            if (cnt != null){
                //Should differentiate cases between unique elements and not unique elements
                if (curKey != remain || (curKey == remain && cnt >= 2)){
                    return true;
                }
            }
        }
        return false;
    }
}

public class TwoSumII {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner typein = new Scanner(System.in);
        Integer targ;
        TwoSumII_Func twosumii = new TwoSumII_Func();
        
        System.out.println("Please input a target number to add in:\n");
        Integer num = typein.nextInt();
        twosumii.add(num);
        boolean res;
        while (num != null){
            System.out.println("Please input a target number to check sum:\n");
            targ = typein.nextInt();
            if (targ != null){
                
                res = twosumii.find(targ);
                System.out.printf("%b\n\n", res);
            }
            System.out.println("Please input a number to add in:\n");
            num = typein.nextInt();
            if (num != null){
                twosumii.add(num);
            }
        }
        
    }
    
}

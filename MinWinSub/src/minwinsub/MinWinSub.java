/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minwinsub;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */
class MiniWinSub_Func{
    public String onePass(String str, String tar){
        //Input string and target string to find minimum substring that contain characters in target string
        //TC:O(n); SC:O(n);
        int n = str.length();//For string, the length function needs the parathesis
        Map<Character, Integer> substr_freq =new HashMap<Character, Integer>();
        char[] chS = str.toCharArray();//Convert a string to a character array so that we can loop through
        //Put all characters in tar into the Map
        for (char c:tar.toCharArray()){
            Integer freq = substr_freq.get(c);
            if (freq == null){
                substr_freq.put(c, 1);
            }else{
                substr_freq.put(c, freq+1);
            }
        }
        //
        int left = 0, right = 0, count = tar.length(), minLen = Integer.MAX_VALUE, startindex = -1;
        while (right < chS.length){
            Integer freq = substr_freq.get(chS[right]);
            if (freq != null){
                substr_freq.put(chS[right], freq-1);
                if (freq > 0)//in case of overmatch
                    count--;
            }
            right++;
            while (count == 0){
                if (right - left < minLen){
                    minLen = right - left;
                    startindex = left;
                }
                Integer exist = substr_freq.get(chS[left]);
                if (exist != null){
                    substr_freq.put(chS[left], exist+1);
                    if (exist ==0)//in case of overmatch
                        count++;
                }
                left++;
            }
        }
        return minLen == Integer.MAX_VALUE ? new String() : new String(chS,startindex,minLen);
    }
}

public class MinWinSub {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        String str,tar;
        MiniWinSub_Func miniwinsub = new MiniWinSub_Func();
        while (in.hasNextLine()){
            str = in.nextLine();
            tar = in.nextLine();
            System.out.println(miniwinsub.onePass(str, tar));            
        }
    }
    
}

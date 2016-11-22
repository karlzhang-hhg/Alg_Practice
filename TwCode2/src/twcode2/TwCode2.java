/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twcode2;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;
import java.lang.String;


class Y4M2{
    public static void displayIntro() {
    System.out.println("2015-08, 2016-04");
    System.out.println("");
    System.out.println("2015-08-15, clicks, 635");
    System.out.println("2016-03-24, app_installs, 683");
    System.out.println("2015-04-05, favorites, 763");
    System.out.println("2016-01-22, favorites, 788");
    System.out.println("2015-12-26, clicks, 525");
    System.out.println("2016-06-03, retweets, 101");
    System.out.println("2015-12-02, app_installs, 982");
    System.out.println("2016-09-17, app_installs, 770");
    System.out.println("2015-11-07, impressions, 245");
    System.out.println("2016-10-16, impressions, 567");
    System.out.println("");
}

    
    public Integer str2int(String date){
        String[] ss = date.split("-");
        return Integer.parseInt(ss[0].trim()+ss[1].trim());
    }
}
/**
 *
 * @author kungangzhang
 */
public class TwCode2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        Map<Integer, Map<String, Integer>> aggts = new HashMap<Integer,Map<String, Integer>>();
        Map<String, Integer> aggent = new HashMap<String, Integer>();
        List<Integer> ymset = new ArrayList<Integer>();
        Y4M2 trans = new Y4M2();
        
        Scanner in = new Scanner(System.in);
        //Y4M2.displayIntro();
        String nln = in.nextLine();
        String[] ss = nln.trim().split("\\s*,\\s*");
        Integer[] time_range = new Integer[2];
        
        time_range[0] = trans.str2int(ss[0]);
        time_range[1] = trans.str2int(ss[1]);
        
        nln = in.nextLine();
        
        int n = 0;
        Integer ym;
        Integer cnt0,cnt1;
        while (true){
            nln = in.nextLine();
            nln = nln.trim();
            if (nln.equals("")){
                break;                
            }
            ss = nln.trim().split("\\s*,\\s*");
            ym = trans.str2int(ss[0]);
            if (ym >= time_range[0] && ym < time_range[1]){
                if (!ymset.contains(ym)){
                    ymset.add(ym);
                    aggts.put(ym, new HashMap<String, Integer>());
                }
                aggent = aggts.get(ym);
                for (int i = 1;i < ss.length; i++) {
                    cnt0 = aggent.get(ss[i]);
                    cnt1 = Integer.parseInt(ss[i+1]);
                    if (cnt0 == null){
                        aggent.put(ss[i], cnt1);
                    }else{
                        aggent.put(ss[i], cnt0+cnt1);
                    }
                    i++;
                }
            }
            nln = null;
            ss = null;
        }
        
        Collections.sort(ymset, Collections.reverseOrder());
        String ptym;
        for (int i=0;i<ymset.size(); i++){
            ym = ymset.get(i);
            aggent = aggts.get(ym);
            ptym = Integer.toString(ym);
            nln = ptym.substring(0, 4)+"-"+ptym.substring(4, 6);
            for (String eng: aggent.keySet()){
                nln = nln + ", " + eng + ", " + Integer.toString(aggent.get(eng));
            }
            System.out.println(nln);            
        }
    }
    
}

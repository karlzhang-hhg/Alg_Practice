/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spiralmatrix;
import java.io.*;
import java.util.*;
/**
 *
 * @author kungangzhang
 */

class SpiralTraverse{
    public List<Integer> spiralTraverse(int[][] mat){
        if (mat==null || mat.length==0 || mat[0]==null || mat[0].length==0) return null;
        List<Integer> res = new ArrayList<Integer>();
        int n=mat.length,m=mat[0].length;
        kernel(res,mat,0,n,m);
        return res;
     }
    
    public void kernel(List<Integer> res,int[][] mat,int head, int n, int m){
        if (n<=0 || m<=0) return;
        for (int i=head;i<head+m;i++){
            res.add(mat[head][i]);
        }
        for (int i=head+1;i<head+n;i++){
            res.add(mat[i][head+m-1]);
        }
        for (int i=head+m-2;i>=head;i--){
            res.add(mat[head+n-1][i]);
        }
        for (int i=head+n-2;i>=head+1;i--){
            res.add(mat[i][head]);
        }
        kernel(res,mat,head+1,n-2,m-2);
    }
    public void print(List<Integer> res){
        for(int i =0;i<res.size();i++){
            System.out.print(res.get(i)+" ");
        }
        System.out.println();
    }
}
public class SpiralMatrix {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in=new Scanner(new File("input.txt"));
        SpiralTraverse st = new SpiralTraverse();
        int n=in.nextInt();
        
        while (n!=-1){
            int m=in.nextInt();
            int[][] mat = new int[n][m];
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    mat[i][j]=in.nextInt();
                }
            }
            
            List<Integer> res = st.spiralTraverse(mat);
            st.print(res);
            
            n=in.nextInt();
        }
        in.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gatesandwalls;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */

class GatesAndWalls_Func{
    private boolean isValid(int x,int y,int row,int col){
        return x>=0 && y>=0 && x<row && y<col;
    }
    public void gatesAndWalls1(int[][] rooms){
        //Starting from each gate and find distance from that gate to all node;
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0){
            return;
        }
        Deque<int[]> queue = new LinkedList<int[]>();
        for (int i=0;i<rooms.length;i++){
            for (int j=0;j<rooms[0].length;j++){
                if (rooms[i][j]==0){
                    //Put all gates into queue
                    bfsHelper(rooms,i,j);
                }
            }
        }
    }
    private void bfsHelper(int[][] rooms, int i,int j){
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0){
            return;
        }
        int row = rooms.length;
        int col = rooms[0].length;
        Deque<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[]{i,j});
        while (!queue.isEmpty()){
            int[] cur = queue.pollFirst();
            int x = cur[0];
            int y = cur[1];
            if (isValid(x+1,y,row,col) && rooms[x+1][y]>rooms[x][y]+1){
                rooms[x+1][y] = rooms[x][y]+1;
                queue.offerLast(new int[]{x+1,y});
            }
            if (isValid(x,y+1,row,col) && rooms[x][y+1]>rooms[x][y]+1){
                rooms[x][y+1] = rooms[x][y]+1;
                queue.offerLast(new int[]{x,y+1});
            }
            if (isValid(x-1,y,row,col) && rooms[x-1][y]>rooms[x][y]+1){
                rooms[x-1][y] = rooms[x][y]+1;
                queue.offerLast(new int[]{x-1,y});
            }
            if (isValid(x,y-1,row,col) && rooms[x][y-1]>rooms[x][y]+1){
                rooms[x][y-1] = rooms[x][y]+1;
                queue.offerLast(new int[]{x,y-1});
            }
        }

    }
    public void gatesAndWalls2(int[][] rooms){
        //Starting from all gate so that we can avoid repeated updating of distance
        if (rooms == null || rooms.length == 0 || rooms[0] == null || rooms[0].length == 0){
            return;
        }
        Deque<int[]> queue = new LinkedList<int[]>();
        for (int i=0;i<rooms.length;i++){
            for (int j=0;j<rooms[0].length;j++){
                if (rooms[i][j]==0){
                    //Put all gates into queue
                    queue.offerLast(new int[]{i,j});
                }
            }
        }
        multiEndbfsHelper(queue,rooms);
    }
    private void multiEndbfsHelper(Deque<int[]> queue, int[][] rooms){
        int row = rooms.length;
        int col = rooms[0].length;
        
        while (!queue.isEmpty()){
            int[] cur = queue.pollFirst();
            int x = cur[0];
            int y = cur[1];
            
            if (isValid(x+1,y,row,col) && rooms[x+1][y] == Integer.MAX_VALUE){
                rooms[x+1][y] = rooms[x][y]+1;
                queue.offerLast(new int[]{x+1,y});
            }
            if (isValid(x,y+1,row,col) && rooms[x][y+1] == Integer.MAX_VALUE){
                rooms[x][y+1] = rooms[x][y]+1;
                queue.offerLast(new int[]{x,y+1});
            }
            if (isValid(x-1,y,row,col) && rooms[x-1][y] == Integer.MAX_VALUE){
                rooms[x-1][y] = rooms[x][y]+1;
                queue.offerLast(new int[]{x-1,y});
            }
            if (isValid(x,y-1,row,col) && rooms[x][y-1] == Integer.MAX_VALUE){
                rooms[x][y-1] = rooms[x][y]+1;
                queue.offerLast(new int[]{x,y-1});
            }
        }
    }
    
    public void printRoom(int[][] rooms){
        for (int i=0;i<rooms.length;i++){
            for (int j=0;j<rooms[0].length;j++){
                System.out.print(rooms[i][j]+"  ");
            }
            System.out.println();
        }
    }
}


public class GatesAndWalls {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        GatesAndWalls_Func gaw = new GatesAndWalls_Func();
        
        int n = in.nextInt();
        while (n!=-1){
            int m = in.nextInt();
            int[][] rooms = new int[n][m];
            int cur;
            for (int i=0;i<n;i++){
                for (int j=0;j<m;j++){
                    cur = in.nextInt();
                    if (cur == -2) rooms[i][j] = Integer.MAX_VALUE;
                    else rooms[i][j]=cur;
                }
            }
            gaw.gatesAndWalls2(rooms);
            gaw.printRoom(rooms);
            n = in.nextInt();
        }
        in.close();
    }
    
}

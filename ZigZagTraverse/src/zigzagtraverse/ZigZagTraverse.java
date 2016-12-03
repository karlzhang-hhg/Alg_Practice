/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zigzagtraverse;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */
class Node{
    Integer val;
    Node left;
    Node right;
    Node(int val){
        this.val = val;
        left = null;
        right = null;
    }
}

class ZigZagTraverse_Func{
    public List<List<Integer>> deque_ZZT(Node root){
        //TC: O(n);
        //SC: O(n);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (root == null) return res;
        
        //Method: Using a deque to maintain the current nodes in the same level with the same order from left to right or from right to left
        Deque<Node> deque = new LinkedList<Node>();
        boolean left2right = true;//The order to put result into deque
        deque.addFirst(root);
        while(!deque.isEmpty()){
            int size = deque.size();
            List<Integer> list = new ArrayList<Integer>();
            for(int i=0;i<size;i++){
                if(left2right){
                    Node cur = deque.pollFirst();
                    list.add(cur.val);
                    if (cur.left!=null) deque.addLast(cur.left);
                    if (cur.right!=null) deque.addLast(cur.right);
                }else{
                    Node cur = deque.pollLast();
                    list.add(cur.val);
                    if (cur.right!=null) deque.addFirst(cur.right);
                    if (cur.left!=null) deque.addFirst(cur.left);
                }
            }
            left2right = left2right ? false:true;
            res.add(list);
        }
        printZZT(res);
        return res;
    }
    
    private void printZZT(List<List<Integer>> res){
        for (int i=0;i<res.size();i++){
            List<Integer> cur = res.get(i);
            for (int j=0;j<cur.size();j++){
                System.out.print(cur.get(j)+" ");
            }
            System.out.println();
        }
    }
    
    public List<Node> array_ZZT(Node root, int n){
        //TC: O(n);
        //SC: O(1);
        List<Node> res = new ArrayList<Node>();
        res.add(root);
        int start = 0;
        int end = 0;
        boolean left2right = false;
        while (res.size()<n){
            for (int i=end;i>=start;i--){
                Node cur=res.get(i);
                if (left2right){
                    if (cur.left != null)
                        res.add(cur.left);
                    if (cur.right != null)
                        res.add(cur.right);
                }else{
                    if (cur.right != null)
                        res.add(cur.right);
                    if (cur.left != null)
                        res.add(cur.left);
                }
            }
            left2right = left2right ? false:true;
            start = end+1;
            end = res.size()-1;
        }
        
        for (int i=0;i<res.size();i++){
            System.out.print(res.get(i).val+" ");
        }
        System.out.println();
        
        return res;
    }
}

public class ZigZagTraverse {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        ZigZagTraverse_Func zzt = new ZigZagTraverse_Func();
        
        int n = in.nextInt();
        while (n!=-1){
            List<Integer> root = new ArrayList<Integer>();//Track which node is the root; The integer is the index of a node
            for (int i=0;i<n;i++){
                root.add(i);
            }
            Node[] tree = new Node[n];
            //Build a tree
            for (int i=0;i<n;i++){
                Integer cur = in.nextInt();
                if (tree[i]==null)
                    tree[i] = new Node(cur);
                Integer left = in.nextInt();
                if (left != -1){
                    if (tree[left]==null)
                        tree[left] = new Node(left);
                    tree[i].left = tree[left];
                    root.remove(left);//left is not root; left is an object, not an int
                }
                Integer right = in.nextInt();
                if (right != -1){
                    if (tree[right]==null)
                        tree[right] = new Node(right);
                    tree[i].right = tree[right];
                    root.remove(right);//right is not root; right is an object, not an int
                }
            }
            List<List<Integer>> res = zzt.deque_ZZT(tree[root.get(0)]);
            zzt.array_ZZT(tree[root.get(0)], n);
            n = in.nextInt();
        }
    }
    
}

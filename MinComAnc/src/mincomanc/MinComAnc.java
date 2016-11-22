/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mincomanc;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */
class Node{
    int id;//Node value
    Node left;//Left children
    Node right;//Right children
    Node(int id){
        //Initialization of left and right children
        this.id = id;
        left = null;
        right = null;
    }
}

class TreeOperations{
    public Node minComAnc_v1(Node root,Node node1,Node node2){
        //Given a root of a tree, find the minimum common ancestor of the two nodes
        //Tree nodes are unique
        //There are some bugs in this function
        if (root == null) return null;
        if (root == node1 && containNode(root,node2)) return root;
        if (root == node2 && containNode(root,node1)) return root;
        if ((containNode(root.left,node1)&&containNode(root.left,node2)) || (containNode(root.left,node2)&&containNode(root.left,node1)))
            return root;
        Node temp1=null, temp2=null;
        temp1 = minComAnc_v1(root.left,node1,node2);
        temp2 = minComAnc_v1(root.right,node1,node2);
        if (temp1 != null) return temp1;
        if (temp2 != null) return temp2;
        return null;
    }
    
    public Node findFromRoot(Node root,Node a,Node b){
        //The simplest version 
        //TC: O(n);
        if (root==null || root==a || root==b) return root;//背答案
        Node left = findFromRoot(root.left,a,b);
        Node right = findFromRoot(root.right,a,b);
        
        if (left!=null && right!=null) return root;
        if (left!=null) return left;
        return right;
    }
    
    public boolean containNode(Node root,Node node){
        //Given a root of a tree, find whether this tree contain this node
        //DFS search for a node in a tree
        if (root == null) return false;
        if (root == node) return true;
        
        return (containNode(root.left,node) || containNode(root.right,node));
    }
}

public class MinComAnc {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        TreeOperations treeop = new TreeOperations();
        int n = in.nextInt();
        while (n!=-1){
            Node[] tree = new Node[n];
            for (int i=0;i<n;i++) tree[i] = new Node(i);
            
            for (int i=0;i<n;i++){
                int leftID = in.nextInt();
                if (leftID!=-1) tree[i].left = tree[leftID];
                int rightID = in.nextInt();
                if (rightID!=-1) tree[i].right = tree[rightID];
            }
            int m = in.nextInt();
            for (int i = 0;i<m;i++){
                int a=in.nextInt();
                int b=in.nextInt();
                System.out.print(a+"\n"+b+"\n"+treeop.minComAnc_v1(tree[0], tree[a], tree[b]).id+"\n\n");
            }
            n = in.nextInt();
        }
        in.close();
        
    }
    
}

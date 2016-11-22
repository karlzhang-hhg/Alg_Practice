/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threetravtree;
import java.io.*;
import java.util.*;
/**
 *
 * @author kungangzhang
 */
class Node{
    int id;
    boolean printed;
    Node left;
    Node right;
    Node(int id){
        this.id = id;
        this.printed = false;
        left = null;
        right = null;
    }
}

class TraverseTree{
    //The most simplest version of traverse a binary tree
    //Use recursion to achieve stack
    private Deque<Node> stack = new LinkedList<Node>();
    public void preRoot(Node root){
        if (root == null) return;
        System.out.print(root.id);
        preRoot(root.left);
        preRoot(root.right);        
    }
    
    public void midRoot(Node root){
        if (root == null) return;
        midRoot(root.left);
        System.out.print(root.id);
        midRoot(root.right);                
    }
    
    public void posRoot(Node root){
        if (root == null) return;
        posRoot(root.left);
        posRoot(root.right);
        System.out.print(root.id);
    }
    
}

public class ThreeTravTree {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        TraverseTree tratree = new TraverseTree();
        int n = in.nextInt();
        while (n!=-1){
            Node[] tree = new Node[n];
            for (int i=0;i<n;i++) tree[i] = new Node(i);
            
            for (int i=0;i<n;i++){
                int id = in.nextInt();
                int leftID = in.nextInt();
                if (leftID!=-1) tree[i].left = tree[leftID];
                int rightID = in.nextInt();
                if (rightID!=-1) tree[i].right = tree[rightID];
            }
            
            System.out.println("PreTraverse");
            tratree.preRoot(tree[0]);
            System.out.println();
            
            System.out.println("MidTraverse");
            tratree.midRoot(tree[0]);
            System.out.println();
            
            System.out.println("PosTraverse");
            tratree.posRoot(tree[0]);
            System.out.println();
            
            
            n = in.nextInt();
        }
        in.close();
    }
    
}

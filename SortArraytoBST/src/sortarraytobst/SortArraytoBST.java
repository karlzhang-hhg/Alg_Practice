/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortarraytobst;
import java.io.*;
import java.util.*;
/**
 *
 * @author kungangzhang
 */
class Node{
    int val;
    Node left;
    Node right;
    Node(int val){
        this.val = val;
        left = null;
        right = null;
    }
}
class ArraytoBST{
    public Node arraytoBST(int[] ar){
        //Given a sorted array, generate a height balanced BST for that array
        int n = ar.length;
        Node root = helperArray2BST(ar,0,n-1);
        
        //Print out the tree (Parent left right)
        printTree(root);
        
        return root;
    }
    
    private Node helperArray2BST(int[] ar,int start,int end){
        if (start>end){
            return null;
        }
        
        if (start == end){
            Node root = new Node(ar[start]);
            return root;
        }
        
        int mid = start+(end-start)/2;
        Node root = new Node(ar[mid]);
        root.left = helperArray2BST(ar,start,mid-1);
        root.right = helperArray2BST(ar,mid+1,end);
        return root;
    }
    
    private void printTree(Node root){
        if (root != null){
            System.out.print(root.val+" ");
            if (root.left != null) System.out.print(root.left.val+" ");
            else System.out.print(-1+" ");
            if (root.right != null) System.out.print(root.right.val+" ");
            else System.out.print(-1+" ");
            System.out.println();
            printTree(root.left);
            printTree(root.right);
        }
    }
}
public class SortArraytoBST {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        ArraytoBST ar2bst = new ArraytoBST();
        
        int n = in.nextInt();
        while (n!=-1){
            int[] ar = new int[n];
            for (int i=0;i<n;i++){
                ar[i]=in.nextInt();
            }
            ar2bst.arraytoBST(ar);
            n = in.nextInt();
        }
    }
    
}

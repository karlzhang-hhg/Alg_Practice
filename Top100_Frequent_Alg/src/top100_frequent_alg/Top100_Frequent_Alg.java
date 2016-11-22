/* Binary tree upside down
Three ways (stack, recursion, sequential) to do binary tree upside down;
Two ways (DFS, BFS) of printing out a tree.
 */
package top100_frequent_alg;
import java.io.*;
import java.util.*;
/**
 *
 * @author kungangzhang
 */

//define a class of TreeNode
class TreeNode{
    int id;
    TreeNode left;
    TreeNode right;
    TreeNode(int id){
        this.id = id;
        left =null;
        right = null;
    }
}

class UpSideDownBinary{
    public TreeNode upSideDownBinary_Stack(TreeNode root){
        //Use stack to achieve upside down
        if (root == null){
            return root;
        }
        
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        
        while(root != null){
            stack.offerLast(root);
            root = root.left;
        }
        
        TreeNode newRoot = stack.pollLast();
        TreeNode cur = newRoot;
        
        while(!stack.isEmpty()){
            TreeNode oriParent = stack.pollLast();
            cur.left = oriParent.right;
            cur.right = oriParent;
            
            oriParent.left = null;
            oriParent.right = null;
            cur = oriParent;
        }
        
        return newRoot;
    }
    
    public TreeNode upSideDownBinary_Recursion(TreeNode root){
        //Use recursion programming
        if (root == null || root.left == null){
            return root;
        }
        
        TreeNode newRoot = upSideDownBinary_Recursion(root.left);
        
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        
        return newRoot;
    }
    
    public TreeNode upSideDownBinary_Sequential(TreeNode root){
        //Use while loop
        if (root == null) return root;
        TreeNode node = root, parent = null, right = null;
        while(node != null){
            TreeNode left = node.left;
            node.left = right;
            right = node.right;
            node.right = parent;
            parent = node;
            node = left;
        }
        return parent;
    }
    
    public void PrintTree_DFS(TreeNode root){
        //Use DFS to traverse a tree given the root node and print the node with the format of this.id leftnode.id rightnode.id
        if (root == null){
            return;
        }
        int leftID,rightID;
        if (root.left == null){
            leftID = -1;
        }else{
            leftID = root.left.id;
        }
        if (root.right == null){
            rightID = -1;
        }else{
            rightID = root.right.id;
        }
        
        System.out.printf("%d %d %d\n",root.id, leftID, rightID);
        PrintTree_DFS(root.left);
        PrintTree_DFS(root.right);
    }
    
    public void PrintTree_BFS(TreeNode root){
        //Use BFS to traverse a tree given the root node and print the node with the format of this.id leftnode.id rightnode.id
        if (root == null){
            return;
        }
        
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        
        queue.add(root);
        
        TreeNode node = null;
        
        int leftID,rightID;
        
        while(!queue.isEmpty()){
            node = queue.poll();
            if (node.left == null){
                leftID = -1;
            }else{
                leftID = node.left.id;
                queue.add(node.left);
            }
            if (node.right == null){
                rightID = -1;
            }else{
                rightID = node.right.id;
                queue.add(node.right);
            }
            System.out.printf("%d %d %d\n",node.id, leftID, rightID);
        }
               
    }
}

public class Top100_Frequent_Alg {

    /**
     * @param args the command line arguments
     */
    
            
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("input.txt"));
        int n = in.nextInt();
        
        // Build a tree
        while(n!=-1){
            TreeNode[] tree = new TreeNode[n];
            for (int i=0; i<n; ++i) tree[i] = new TreeNode(-1);
            
            for (int i=0; i<n; ++i){
                int id = in.nextInt();
                tree[id].id = id;
                int leftID = in.nextInt();
                if (leftID != -1) tree[id].left = tree[leftID];
                int rightID = in.nextInt();
                if (rightID != -1) tree[id].right = tree[rightID];
            }
            n = in.nextInt();
            TreeNode newRoot = new TreeNode(-1);
//            System.out.println("Stack method:");
//            UpSideDownBinary method1 = new UpSideDownBinary();
//            newRoot = method1.upSideDownBinary_Stack(tree[0]);
//            System.out.printf("The new root node id after upside down is %d\n",newRoot.id);
//            System.out.println("The upside down tree looks like:");
//            method1.PrintTree_DFS(newRoot);
            
//            System.out.println("Recursion method:");
//            UpSideDownBinary method2 = new UpSideDownBinary();
//            newRoot = method2.upSideDownBinary_Recursion(tree[0]);
//            System.out.printf("The new root node id after upside down is %d\n",newRoot.id);
//            System.out.println("The upside down tree looks like:");
//            method2.PrintTree_DFS(newRoot);
            
            System.out.println("Sepuential method:");
            UpSideDownBinary method3 = new UpSideDownBinary();
            newRoot = method3.upSideDownBinary_Sequential(tree[0]);
            System.out.printf("The new root node id after upside down is %d\n",newRoot.id);
            System.out.println("The upside down tree looks like:");
            method3.PrintTree_BFS(newRoot);
            
            
        }
    
    }
    
}

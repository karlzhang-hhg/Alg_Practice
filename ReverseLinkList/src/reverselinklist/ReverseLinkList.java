/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reverselinklist;
import java.io.*;
import java.util.*;


/**
 *
 * @author kungangzhang
 */

class Node{
    int value;//Node value
    Node next;//Link to the next node;
    Node(){
        //Initialization
        next = null;
    }
}

class Iteration{
    public Node reverse(Node head){
        Node pre=null, cur=head, nex=null;
        while (cur != null){
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }
    
    public void display(Node head){
        while(head != null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }
}
public class ReverseLinkList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        
        int n = in.nextInt();
        while(n!=-1){
            Node head = null;
            Node pre = null;
            while(n--!=0){
                Node temp = new Node();
                temp.value = in.nextInt();
                
                if( head == null) head = temp;
                else pre.next = temp;
                pre = temp;
            }
            
            Iteration itr = new Iteration();
            itr.display(head);
            head = itr.reverse(head);
            itr.display(head);
            
            n = in.nextInt();
        }
        in.close();
    }
    
}

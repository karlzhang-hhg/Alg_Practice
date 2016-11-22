/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reorderlinklist;
import java.io.*;
import java.util.*;

/**
 *
 * @author kungangzhang
 */
class Node{
    int value;//Node value
    Node next;//Link to next node
    Node(){
        //Initialization
        next = null;
    }
}

class Reorder{
    public Node findmid(Node head){
        Node slowp=head, fastp=head;
        while(fastp!=null && fastp.next!=null){
            slowp = slowp.next;
            fastp = fastp.next.next;
        }
        Node temp=slowp.next;
        slowp.next = null;//Must set the next of the end node to null to prevent infinite loop
        return temp;
    }
    
    public Node reverse(Node head){
        Node pre=null,cur=head,nex=null;
        while(cur!=null){
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        return pre;
    }
    
    public Node merge(Node head1, Node head2){
        Node p1=head1,p2=head2;
        Node temp;
        while (p1 != null && p2!=null){
            temp = p2.next;
            p2.next = p1.next;
            p1.next = p2;
            p1 = p2.next;
            p2 = temp;
        }
        return head1;
    }
    
    public void display(Node head){
        while(head != null){
            System.out.print(head.value+" ");
            head = head.next;
        }
        System.out.println();
    }
}

public class ReorderLinkList {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        
        int n = in.nextInt();
        while(n != -1){
            Node head =null;
            Node pre = null;
            while(n--!=0){
                Node temp =new Node();
                temp.value = in.nextInt();
                if (head == null) head = temp;
                else pre.next = temp;
                pre = temp;
            }
            
            Reorder reor = new Reorder();
            reor.display(head);
            Node mid = reor.findmid(head);
            reor.merge(head, reor.reverse(mid));
            reor.display(head);
            
            n = in.nextInt();
        }
        in.close();
    }
    
}

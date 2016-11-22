/*
 * Try to traverse linkedlist only once. 
Should draw a diagram to clearify the simpliest case with respect to the return of function and input of function.
This problem is a very good example for shallow copy. Need another deep copy example. In java, if I create a linkedlist, simply "=" is just a shallow copy.
 */
package reverseinkgroup;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

class ReverseInK{
    public Node reverseInK(Node head,Node prehead,int k){
        Node pre=null,cur=head,nex=null;
        while(k--!=0 && cur!=null){
            nex = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nex;
        }
        head.next = cur;
        //Do not change prehead.next yet because the original prehead.next is the new prehead for the next k-group sub-list
        //prehead.next = pre;
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

public class ReverseInKGroup {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        
        int n = in.nextInt();
        while(n != -1){
            int k = in.nextInt();
            Node head =null;
            Node pre = null;
            while(n--!=0){
                Node temp =new Node();
                temp.value = in.nextInt();
                if (head == null) head = temp;
                else pre.next = temp;
                pre = temp;
            }
            
            ReverseInK reink = new ReverseInK();
            reink.display(head);
            Node prehead = new Node();
            Node newprehead = new Node();
            prehead.next = head;
            while (prehead.next != null){
                if (prehead.next == head){
                    head = reink.reverseInK(prehead.next, prehead, k);//At the beginning of the linkedlist, copy the new head to head
                    prehead = prehead.next;//Move to the next prehead
                }else{
                    newprehead = prehead.next;//Save the new prehead for the next k-group
                    prehead.next = reink.reverseInK(prehead.next, prehead, k);//prehead.next should be the new head of the k-group reversed sub-list 
                    prehead = newprehead;//prehead should be the new prehead for the next k-group
                }
                
            }
            reink.display(head);
            
            n = in.nextInt();
        }
        in.close();
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mergeksort;
import java.io.*;
import java.util.*;
import java.lang.*;


/**
 *
 * @author kungangzhang
 */

class ListNode{
    int val;
    ListNode next;
    ListNode(int val){
        this.val = val;
        next = null;
    }
}

class MyComparator implements Comparator<ListNode> {
    @Override
    public int compare(ListNode o1, ListNode o2){
        if(o1.val == o2.val) return 0;
        else return o1.val<o2.val ? -1:1;
    }
    
}

class MergeKSort_Func{
    public ListNode mergeKLists(ListNode[] lists){
        if (lists == null || lists.length == 0){
            return null;
        }
        if (lists.length == 1){
            return lists[0];
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.length, new MyComparator());
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        //1. Put all first nodes into minHeap
        for (ListNode n : lists){
            if(n != null){
                minHeap.add(n);
            }
        }
        //2. Find min elem in minHeap
        while (!minHeap.isEmpty()){
            ListNode next = minHeap.poll();
            if(next.next!=null) {
                minHeap.add(next.next);
            }
            prev.next = next;
            prev = prev.next;
        }
        return dummy.next;
    }
    
    public void printList(ListNode head,int n){
        ListNode cur = head;
        while (n--!=0){
            System.out.print(cur.val+" ");
            cur = cur.next;
        }
        System.out.println();
    }
}


public class MergeKSort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        Scanner in = new Scanner(new File("input.txt"));
        MergeKSort_Func mks = new MergeKSort_Func();
        
        int nlist = in.nextInt();
        int ntot = 0;
        while (nlist !=-1){
            ListNode[] lists =new ListNode[nlist];
            for (int i=0;i<nlist;i++){
                int nele = in.nextInt();
                if (nele != -1){
                    ntot +=nele;
                    lists[i] = new ListNode(in.nextInt());
                    ListNode cur=lists[i];
                    for (int j=1;j<nele;j++){
                        cur.next = new ListNode(in.nextInt());
                        cur = cur.next;
                    }    
                }else{
                    lists[i] = null;
                }
            }
            ListNode head = mks.mergeKLists(lists);
            mks.printList(head, ntot);
            nlist = in.nextInt();
        }
        in.close();
    }
    
}

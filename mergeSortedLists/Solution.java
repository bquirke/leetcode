package mergeSortedLists;

import java.util.LinkedList;

public class Solution {

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode mergeTwoLists() {
        ListNode tmp = new ListNode(0);
        ListNode dummyFirst = tmp;

        tmp.next = new ListNode(1);
        tmp = tmp.next;
        tmp.next = new ListNode(2);
        tmp = tmp.next;
        tmp.next = new ListNode(4);

        tmp = new ListNode(0);
        ListNode dummySec = tmp;

        tmp.next = new ListNode(1);
        tmp = tmp.next;
        tmp.next = new ListNode(3);
        tmp = tmp.next;
        tmp.next = new ListNode(4);

        return mergeTwoLists(dummyFirst.next, dummySec.next);
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode sorted = new ListNode(0);
        ListNode tmp = sorted;

        while (list1 != null && list2 != null){
            if(list1.val > list2.val){
                tmp.next = new ListNode(list2.val);
                tmp = tmp.next;
                tmp.next = new ListNode(list1.val);
            }
            else {
                tmp.next = new ListNode(list1.val);
                tmp = tmp.next;
                tmp.next = new ListNode(list2.val);
            }
            tmp = tmp.next;
            list1 = list1.next;
            list2 = list2.next;
        }


        return sorted.next;
    }

    private void createFirstListNode() {

    }
}

package leetcode.batch2;

import java.util.ArrayList;
import java.util.List;

public class PalindromeLL {

     //Definition for singly-linked list.
     public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }

     static class Solution {
          public boolean isPalindrome(ListNode head) {
               // two pointer approach
               List<Integer> array = new ArrayList<>();

               while(head != null){
                    array.add(head.val);
                    head = head.next;
               }

               int lhs = 0;
               int rhs = array.size() - 1;

               while(lhs < rhs){
                    Integer first = array.get(lhs);
                    Integer sec = array.get(rhs);
                    if(!first.equals(sec)){
                         return false;
                    }
                    lhs++;
                    rhs--;
               }

               return true;
          }

          // RECURSIVE APPROACH

          static class RecursiveSolution {
               public ListNode frontPointer;

               public boolean isPalindrome(ListNode head) {
                    frontPointer = head;
                    return recursivelyCheck(head);
               }


               public boolean recursivelyCheck(ListNode head){
                    // base case to "kick" the stackframe back up
                    if(head == null){
                         return true;
                    }

                    // transverse the ll with recursion
                    if(!recursivelyCheck(head.next)){
                         return false;
                    }

                    // now evaluate the frontPointer and the head which
                    // is currently at the end of the LL because of popping the stackframe

                    if(head.val != frontPointer.val){
                         return false;
                    }

                    // move the frontPointer forward for the next callstack
                    frontPointer = frontPointer.next;

                    return true;
               }
          }
     }

     public static void main(String[] args) {
          ListNode head = null;
          int[] testCase = {1,2,2,1};

          for(int i: testCase){
               head = new ListNode(i, head);
          }


          System.out.println(new Solution().isPalindrome(head));

          System.out.println(new Solution.RecursiveSolution().isPalindrome(head));
     }
}

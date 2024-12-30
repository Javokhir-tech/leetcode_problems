/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head.next == null) {
            return null;
        }
        
        ListNode curr = head;
        int len = 0;
        while (curr != null) {
            len++;
            curr = curr.next;
        }
        int counter = 0;
        
        ListNode fast = head.next;
        ListNode slow = head;
        
        if (len == n) {
            head = slow.next;
        }

        while (counter < len - n - 1) {
          fast = fast.next;
          slow = slow.next;
          counter++;
        }

        slow.next = fast.next;
        
        return head;
    }
    
}
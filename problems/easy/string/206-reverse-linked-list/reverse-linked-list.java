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
    public ListNode reverseList(ListNode head) {
        ListNode prev = head;
        if (prev == null || prev.next == null) {
            return head;
        }
        ListNode cur = prev.next;
        ListNode next = cur.next;
        prev.next = null;
        while (next != null) {
            cur.next = prev;
            prev = cur;
            cur = next;
            next = next.next;
        } 
        cur.next = prev;
        head = cur;
        return head;
    }
}
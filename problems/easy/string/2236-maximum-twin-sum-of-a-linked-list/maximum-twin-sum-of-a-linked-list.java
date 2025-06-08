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
    public int pairSum(ListNode head) {
        ListNode mid = findMid(head);
        mid = reverse(mid);
        return pairSum(mid, head);
    }

    private int pairSum(ListNode mid, ListNode head) { // calc max pair sum
        int sum = 0;
        while (mid != null) {
            sum = Math.max(sum, mid.val + head.val);
            mid = mid.next;
            head = head.next;
        }
        return sum;
    }

    private ListNode findMid(ListNode head) { // find mid of list
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode reverse(ListNode head) { // reverse list node, returns head of reversed list
        ListNode prev = null, curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }
}
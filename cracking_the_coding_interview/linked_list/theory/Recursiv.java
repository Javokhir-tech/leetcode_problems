package cracking_the_coding_interview.linked_list.theory;

import static cracking_the_coding_interview.linked_list.theory.Recursiv.Node.*;

public class Recursiv {
    public static void main(String[] args) {
//        var node = new Node(2);
//        insert(node, 3);
//        insert(node, 89);
//        insert(node, 5);
//        insertRecursive(node,12);
//        showRecursive(node);


        var node = new Node(9);
        insert(node, 7);
        insert(node, 3);
        insert(node, 4);
        insert(node, 2);
        insert(node, 8);
        insert(node, 5);

        //           - - - - - - - - - - - -
        //           |                      ^
        // 9 -> 7 -> 3 -> 4 -> 2 -> 8 -> 5 -|
        node.next.next.next.next.next.next.next = node.next.next.next;
        System.out.println(loopDetection(node).data);
//        show(node);
    }

    static class Node {
        int data;
        Node next;

        public Node(int data) {this.data = data; this.next = null;}

        public Node() {}

        public int getData() {
            return data;
        }

        static void insert(Node head, int data) {
            var node = new Node(data);

            if (head == null)
                head = node;

            else {
                var n = head;
                while (n.next != null) {  // logic for iterating through nodes
                    n = n.next;
                }
                n.next = node;
            }
        }

        static Node insertRecursive(Node head, int data) {
            if (head == null)
                return new Node(data);
            else
                head.next = insertRecursive(head.next, data);
            return head;
        }

        static void show(Node head) {
            var n = head;
            while (n.next != null) {
                System.out.print(n.data +  " -> ");
                n = n.next;
            }
            System.out.println(n.data);
        }

        static void showRecursive(Node head) {  // 2  3  89  5  ->  null
            if (head == null) return;
            System.out.print(head.data + " -> ");
            showRecursive(head.next);
        }

        static Node sumList(Node list1, Node list2) {
            var str1 = new StringBuilder();
            var str2 = new StringBuilder();

            while (list1 != null) {
                str1.append(list1.data);
                list1 = list1.next;
            }

            while (list2 != null) {
                str2.append(list2.data);
                list2 = list2.next;
            }

            var int1 = Integer.valueOf(str1.toString());
            var int2 = Integer.valueOf(str2.toString());

            var int3 = int1 + int2;

            var arr = String.valueOf(int3).split("");
            var result = new Node(Integer.parseInt(arr[arr.length - 1]));
            for (int i = arr.length - 2; i >= 0; i--)
                insert(result, Integer.parseInt(arr[i]));

            return result;
        }

        public static Node reverse(Node head) {
            var current = head;
            Node n;
            Node p = null;
            while (current != null) { // we use 3 pointers
                n = current.next;
                current.next = p;
                p = current;
                current = n;
            }
            return p;
        }

        public static Node reverseAndCopy(Node head) { // time O(n), space O(n)
            var curr = head;
            Node prev = null;
            Node node = null;
            while (curr != null) { // create new node, set prev to null, then set node.next to prev, set prev to node
                node = new Node(curr.data);
                node.next = prev;
                prev = node;
                curr = curr.next;
            }
            return node;
        }

        public static boolean isPalindrome(Node node) {
            var reversed = reverseAndCopy(node);
            while (node != null) {
                if (node.data != reversed.data) {
                    return false;
                }
                node = node.next;
                reversed = reversed.next;
            }
            return true;
        }

        public static Node loopDetection(Node node) {
            var fast = node;
            var slow = node;
            while (fast != null && slow != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
                if (slow == fast) {
                    var current = node;
                    while (current != slow) {
                        if (current == fast) {
                            return current;
                        }
                        current = current.next;
                        fast = slow.next;
                    }
                }
            }
            return null;
        }
    }
}

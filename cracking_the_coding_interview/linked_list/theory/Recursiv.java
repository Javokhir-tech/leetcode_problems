package cracking_the_coding_interview.linked_list.theory;

import static cracking_the_coding_interview.linked_list.theory.Recursiv.Node.*;

public class Recursiv {
    public static void main(String[] args) {
        var node = new Node(2);
        insert(node, 3);
        insert(node, 89);
        insert(node, 5);
        insertRecursive(node,12);
        showRecursive(node);
    }

    static class Node {
        int data;
        Node next;

        public Node(int data) {this.data = data;}

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
    }
}

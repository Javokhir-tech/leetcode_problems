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

        var node1 = new Node(6);
        insert(node1, 1);
        insert(node1, 7);
        var node2 = new Node(2);
        insert(node2, 9);
        insert(node2, 5);

        show(sumList(node1, node2));
    }

    static class Node {
        int data;
        Node next;

        public Node(int data) {this.data = data;}

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
    }
}

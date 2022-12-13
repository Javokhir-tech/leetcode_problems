package cracking_the_coding_interview.linked_list.delete_middle;

import cracking_the_coding_interview.linked_list.theory.LinkedList;

/*
* Implement an algorithm to delete a node in the middle (i.e., any node but
* the first and last node, not necessarily the exact middle) of a singly linked list,
* given only access to that node.
* */
public class Solution {

    public static void main(String[] args) {
        var list = new LinkedList();
        list.insert(4);
        list.insert(2);
        list.insert(3);
        list.insert(5);
        list.insert(1); // middle
        list.insert(7);
        list.insert(0);
//        list.insert(43);
//        list.insert(10);
        System.out.println(list.deleteMiddle());
        list.show();
    }
}

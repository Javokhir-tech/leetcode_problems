package cracking_the_coding_interview.linked_list.k_to_last;

import cracking_the_coding_interview.linked_list.theory.LinkedList;

/*
* Implement an algorithm to find the kth to last element of a singly linked list.
* 4 -> 2 -> 3 -> 5 -> 1 -> 7, k = 2     | 5
* */
public class Solution {
    public static void main(String[] args) {
        var list = new LinkedList();
        list.insert(4);
        list.insert(2);
        list.insert(3);
        list.insert(5);
        list.insert(1);
        list.insert(7);

        System.out.println(list.getKthToLast(6));
    }
}

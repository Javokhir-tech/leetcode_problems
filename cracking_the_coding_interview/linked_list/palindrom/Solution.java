package cracking_the_coding_interview.linked_list.palindrom;

import cracking_the_coding_interview.linked_list.theory.LinkedList;

/*
* check if list is a palindrome
* head -> 4 -> 2 -> 3 -> 5 -> 1 -> 7 -> null
*
*
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
        list.show();
        // 1st reverse a list
    }
}

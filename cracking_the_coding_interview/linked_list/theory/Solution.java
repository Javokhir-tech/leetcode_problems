package cracking_the_coding_interview.linked_list.theory;

public class Solution {

  public static void main(String[] args) {
    var list = new LinkedList();
    list.insert(9);
    list.insert(8);
    list.insert(8);
    list.insert(4);
    list.insert(7);
    list.insert(2);
    list.insert(5);
    list.insert(5);
    list.insert(1);
    list.insert(2);
    list.insert(7);
    list.insert(7);
    list.insert(8);
    list.insert(8);
    list.insert(1);
    list.insert(9);
    list.show();
    list.removeDuplicatesNoBuffer();
    list.show();
  }
}

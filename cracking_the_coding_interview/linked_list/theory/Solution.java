package cracking_the_coding_interview.linked_list.theory;

public class Solution {

  public static void main(String[] args) {
    var list = new LinkedList();
    list.insert(4);
    list.insert(7);
    list.insert(2);
    list.insert(5);
    list.insert(5);
    list.insert(2);
    list.insert(7);
    list.insert(7);
    list.show();
    list.removeDuplicates();
    list.show();
  }
}

package cracking_the_coding_interview.linked_list.theory;

public class Solution {

  public static void main(String[] args) {
    var list = new LinkedList();
    list.insert(4);
    list.insert(7);
    list.insert(2);
    list.insert(5);
    list.insert(75);

    list.insertAt(4, 20);

    list.insertFirst(1);
    list.insertFirst(90);
    list.insertLast(33);
    list.insertLast(12);

    list.delete(4);

    list.deleteAt(2);
    list.deleteFirst();
    list.deleteFirst();

    list.deleteLast();  // 12
    list.deleteLast();  // 33
    list.show();
  }
}

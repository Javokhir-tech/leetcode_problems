package cracking_the_coding_interview.linked_list.theory;

public class Linked_List {
  private Node head;

  void insert(int data) {
    var node = new Node(data);
    node.next = null;

    if (head == null) {
      head = node;
    }
    else {
      var n = head;
      while (n.next != null) {  // logic for iterating through nodes
        n = n.next;
      }
      n.next = node;
    }
  }

  void show() {
    var n = head;
    while (n.next != null) {
      System.out.print(n.data +  " -> ");
      n = n.next;
    }
    System.out.println(n.data);
  }

  private static class Node {
    int data;
    Node next;

    public Node(int data) {this.data = data;}
  }
}

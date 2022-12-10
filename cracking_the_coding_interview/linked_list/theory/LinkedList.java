package cracking_the_coding_interview.linked_list.theory;

public class LinkedList {
  private Node head;

  public void insert(int data) {
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

  public void show() {
    var n = head;
    while (n.next != null) {
      System.out.print(n.data +  " -> ");
      n = n.next;
    }
    System.out.println(n.data);
  }

  public void insertAt(int index, int data) {
    var node = new Node(data);
    node.next = null;
    int counter = 1;

    var n = head;
    if (index == 0) {
      head = node;
      node.next = n;
    }
    else {
      while (counter < index) {  // logic for iterating through nodes
        counter++;
        n = n.next;
      }
      node.next = n.next;
      n.next = node;
    }
  }

  public void insertFirst(int data) {
    var node = new Node(data);

    var n = head;
    node.next = n;
    head = node;
  }

  public void insertLast(int data) {
    var node = new Node(data);
    node.next = null;

    var n = head;
    while (n.next != null) {
      n = n.next;
    }
    n.next = node;

  }

  private static class Node {
    int data;
    Node next;

    public Node(int data) {this.data = data;}
  }
}

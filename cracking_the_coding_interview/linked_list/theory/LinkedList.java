package cracking_the_coding_interview.linked_list.theory;

import java.util.HashSet;

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

  public void delete(int data) {
    var n = head;

    if (n.data == data) {
      head = n.next;
    }
    while (n.next != null) {
      if (n.next.data == data) {
        n.next = n.next.next;
        break; /* head didn't change*/
      }
      n = n.next;
    }
  }

  public void deleteAt(int index) {
    var n = head;
    int counter = 0;
    Node prev = null;

    while (counter < index) {
      counter++;
      prev = n;
      n = n.next;
    }
    prev.next = n.next;
  }

  public void deleteFirst() {
    var n = head;
    head = n.next;
  }

  public void deleteLast() {
    var n = head;
    Node prev = null;
    while (n.next != null) {
      prev = n;
      n = n.next;
    }
    prev.next = null;
  }

  public void removeDuplicates() {  // remove duplicates from linked list time O(n), space O(n)
    var n = head;
    var set = new HashSet<Integer>();
    while (n.next != null) {
      if (set.contains(n.data)) {
        this.delete(n.data);
      }
      else
        set.add(n.data);
      n = n.next;
    }
  }

  public void removeDuplicatesNoBuffer() { // O(n^2) time, space O(1)
    var ptr1 = head;
    Node ptr2 = null;

    while (ptr1 != null && ptr1.next != null) {
      ptr2 = ptr1;
      while (ptr2.next != null) {
        if (ptr1.data == ptr2.next.data) {
          ptr2.next = ptr2.next.next;
        }
        else
          ptr2 = ptr2.next;
      }
      ptr1 = ptr1.next;
    }
  }

  private static class Node {
    int data;
    Node next;

    public Node(int data) {this.data = data;}
  }
}

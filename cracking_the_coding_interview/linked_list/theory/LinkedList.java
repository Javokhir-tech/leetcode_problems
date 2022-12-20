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
      if (set.contains(n.data))
        this.delete(n.data);
      else
        set.add(n.data);
      n = n.next;
    }
  }

  public void removeDuplicatesNoBuffer() { // O(n^2) time, space O(1)
    Node ptr1 = head, ptr2;

    while (ptr1 != null) {
      ptr2 = ptr1;
      while (ptr2.next != null) {
        if (ptr1.data == ptr2.next.data)
          ptr2.next = ptr2.next.next;
        else
          ptr2 = ptr2.next;
      }
      ptr1 = ptr1.next;
    }
  }

  public int getKthToLast(int k) {  // time O(n), space O(1)
    Node ptr1 = head, ptr2 = head;
    int counter = 0;
    int size = 0;

    while (ptr1.next != null) {
      size++;
      ptr1 = ptr1.next;
    }

    if (size - k < 0) {
      System.out.println("out of bounds");
      System.exit(-1);
    }

    while (counter != size - k) {
      counter++;
      ptr2 = ptr2.next;
    }
    return ptr2.data;
  }

  public int deleteMiddle() { // deletes middle node , time O(1), space(1)
    var node = head;
    int size = 0;
    while (node != null) {
      size++;
      node = node.next;
    }

    var middle = head;
    for (int i = 0; i < size/2; i++) {
      node = middle;  // prev node of middle node
      middle = middle.next;
    }
    node.next = middle.next;  // set prev's next to middle's next
    return middle.data;
  }

  public boolean isPalindrome() {


    return false;
  }

  public LinkedList reverse() {
    var current = head;
    Node n = null;
    Node p = null;

    while (current.next != null) {
      n = current.next;
      current.next = p;
      p = current;
      current = n;
    }
    head = current;
    return this;
  }

  private static class Node {
    int data;
    Node next;

    public Node(int data) {this.data = data; this.next = null;}

    public int getData() {
      return data;
    }
  }
}

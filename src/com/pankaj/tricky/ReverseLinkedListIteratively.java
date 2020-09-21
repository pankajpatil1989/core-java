package com.pankaj.tricky;

public class ReverseLinkedListIteratively {

    public static void main(String args[]) {
        SinglyLinkedList singlyLinkedList = getDefaultList();
        System.out.println("linked list before reversing : " + singlyLinkedList);
        singlyLinkedList.reverseIteratively();
        System.out.println("linked list after reversing : " + singlyLinkedList);
    }

    private static SinglyLinkedList getDefaultList() {
        SinglyLinkedList linkedlist = new SinglyLinkedList();
        linkedlist.append("A");
        linkedlist.append("B");
        linkedlist.append("C");
        linkedlist.append("D");
        linkedlist.append("E");
        linkedlist.append("F");
        return linkedlist;
    }

}

class SinglyLinkedList {
    private Node head;  // Head is the first node in linked list

    public void append(String data) {
        if (head == null) {
            head = new Node(data);
            return;
        }
        tail().next = new Node(data);
    }

    private Node tail() {
        Node tail = head;

        // Find last element of linked list known as tail
        while (tail.next != null) {
            tail = tail.next;
        }
        return tail;

    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head;
        while (current != null) {
            sb.append(current).append("-->");
            current = current.next;
        }
        if (sb.length() >= 3) {
            sb.delete(sb.length() - 3, sb.length());
            // to remove --> from last node
        }

        return sb.toString();
    }

    /**
     * Reverse linked list using 3 pointers approach in O(n) time
     * It basically creates a new list by reversing direction, and
     * subsequently insert the element at the start of the list.
     */
    public void reverseIteratively() {
        Node current = head;
        Node previous = null;
        Node next = null;

        // traversing linked list until there is no more element
        while (current.next != null) {

            // Saving reference of next node, since we are changing current node
            next = current.next;

            // Inserting node at start of new list
            current.next = previous;
            previous = current;

            // Advancing to next node
            current = next;
        }

        head = current;
        head.next = previous;
    }

    private static class Node {
        private Node next;
        private String data;

        public Node(String data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}

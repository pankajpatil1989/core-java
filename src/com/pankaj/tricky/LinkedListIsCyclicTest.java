package com.pankaj.tricky;

public class LinkedListIsCyclicTest {

    public static void main(String args[]) {

        //creating LinkedList with 5 elements including head
//        LinkedListTest linkedList = new LinkedListTest();
//        linkedList.appendIntoTail(new LinkedListTest.Node("101"));
//        linkedList.appendIntoTail(new LinkedListTest.Node("201"));
//        linkedList.appendIntoTail(new LinkedListTest.Node("301"));
//        linkedList.appendIntoTail(new LinkedListTest.Node("401"));
//
//        System.out.println("Linked List : " + linkedList);
//
//        if (linkedList.isCyclic()) {
//            System.out.println("Linked List is cyclic as it contains cycles or loop");
//        } else {
//            System.out.println("LinkedList is not cyclic, no loop or cycle found");
//        }

        //creating LinkedList with 5 elements including head
        LinkedListTest linkedList1 = new LinkedListTest();
        linkedList1.appendIntoTail(new LinkedListTest.Node("101"));
        LinkedListTest.Node cycle = new LinkedListTest.Node("201");
        linkedList1.appendIntoTail(cycle);
        linkedList1.appendIntoTail(new LinkedListTest.Node("301"));
        linkedList1.appendIntoTail(new LinkedListTest.Node("401"));
        linkedList1.appendIntoTail(cycle);

        //don't call toString method in case of cyclic linked list, it will throw OutOfMemoryError
//        System.out.println("Linked List : " + linkedList1);

        if (linkedList1.isCyclic()) {
            System.out.println("Linked List is cyclic as it contains cycles or loop");
        } else {
            System.out.println("LinkedList is not cyclic, no loop or cycle found");
        }

    }
}

 class LinkedListTest {
    private Node head;

    public LinkedListTest() {
        this.head = new Node("head");
    }

    public Node head() {
        return head;
    }

    public void appendIntoTail(Node node) {
        Node current = head;
        //find last element of LinkedList i.e. tail
        while (current.next() != null) {
            current = current.next();
        }
        //appending new node to tail in LinkedList
        current.setNext(node);
    }

    /* * If singly LinkedList contains Cycle then following would be true
     * * 1) slow and fast will point to same node i.e. they meet
     * * On the other hand if fast will point to null or next node of
     * * fast will point to null then LinkedList does not contains cycle.
     *  */
    public boolean isCyclic() {
        Node fast = head;
        Node slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next; //if fast and slow pointers are meeting then LinkedList is cyclic
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node current = head.next();
        while (current != null) {
            sb.append(current).append("-->");
            current = current.next();
        }
        sb.delete(sb.length() - 3, sb.length());
        // to remove --> from last node
        return sb.toString();
    }

    public static class Node {
        private Node next;
        private String data;

        public Node(String data) {
            this.data = data;
        }

        public String data() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }

        public Node next() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return this.data;
        }
    }
}



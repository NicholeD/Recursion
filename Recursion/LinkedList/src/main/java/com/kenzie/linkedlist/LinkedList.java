package com.kenzie.linkedlist;

import com.google.common.annotations.VisibleForTesting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * A bare-bones singly-linked list implementation.
 */
public class LinkedList {
    private Node head;
    private Node tail;

    /**
     * Constructs a new, empty linked list.
     */
    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Add an entry as the new first element in the list.
     * @param data The new value to add at the head of the list
     */
    public void addFirst(final Double data) {
        Node newHead = new Node(data, head);
        this.head = newHead;
        if (this.tail == null) {
            this.tail = newHead;
        }
    }

    /**
     * Add an entry as the new last element in the list.
     * @param data The new value to add at the tail of the list
     */
    public void addLast(final Double data) {
        Node node = new Node(data, null);

        if (this.head == null) {
            this.head = node;
            this.tail = head;
        } else {
            this.tail.setNext(node);
            this.tail = this.tail.getNext();
        }
    }

    /**
     * Returns the value of the first element in the list.
     * Throws IndexOutOfBoundsException if the list is empty.
     * @return the first value, or null if list. isempty
     */
    public Double getFirst() {

        return get(0);
    }

    /**
     * Returns the value of the Nth element in the list (zero-indexed).
     * Throws IndexOutOfBoundsException if n is size of list or
     * greater, or if n is negative.
     *
     * We normally would not want to call this method often on a linked
     * list because it is O(n), but this might be helpful for your
     * reverse() unit tests.
     *
     * @param n the index of the element to return data from
     * @return The value at that element, if the element exists
     */
    @VisibleForTesting
    public Double get(int n) {
        Node node = this.head;
        for (int i = 0; i < n; i++) {
            if (node == null) {
                break;
            }
            node = node.getNext();
        }
        if (node == null) {
            throw new IndexOutOfBoundsException("n was too big for this list: " + n);
        }

        return node.getData();
    }

    /**
     * Add the collection's data values to the end of the list.
     * @param collection The collection of Double to add to the list
     */
    public void addAll(final Collection<Double> collection) {
        for (Double data : collection) {
            addLast(data);
        }
    }

    /**
     * Computes and returns the sum of all Double elements in the list.
     * @return The sum of all elements in the list
     */
    public Double sum() {
        return sumRecursive(head);
    }

    public Double sumRecursive(Node node) {
        if (node == null) {
            return 0D;
        }
        return node.getData() + sumRecursive(node.getNext());
    }

    /**
     * Creates a new LinkedList that has all of the values of this
     * LinkedList in reverse order.
     * @return a new reverse order list
     */
    public LinkedList reverse() {
        LinkedList reversedList = new LinkedList();
        return reverseRecursive(reversedList, head);
    }

    private LinkedList reverseRecursive(LinkedList list, Node node) {
        if (node == null) {
            return list;
        }

        if (node.getNext() == null) {
            list.addFirst(node.getData());
            return list;
        }

        if (node.getNext() != null) {
            list.addFirst(node.getData());
            return reverseRecursive(list, node.getNext());
        }
        return new LinkedList();
    }

    /**
     * Calculates the size of the linked list. The number of nodes
     * with data.
     * @return size of the linked list
     */
    public int size() {
        return sizeRecursive(head);
    }

    private int sizeRecursive(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + sizeRecursive(node.getNext());
    }

    /**
     * Determines if the list contains a node with a double equal
     * to the number specified.
     * @param number to check for in the list
     * @return true, if the list contains the number.
     */
    public boolean contains(Double number) {
        return containsRecursive(head, number);
    }

    private boolean containsRecursive(Node node, Double i) {
        if (node == null) {
            return false;
        } else if (i.equals(node.getData())) {
            return true;
        } else {
            return containsRecursive(node.getNext(), i);
        }
    }

    /**
     * Computes and returns the max of all Double elements in the list.
     * @return The max double in the list.
     */
    public Double max() {
        return maxRecursive(head);
    }

    private Double maxRecursive(Node node) {
        if (node == null) {
            return null;
        }
        if (node.getNext() == null) {
            return node.getData();
        }
        return Math.max(node.getData(), maxRecursive(node.getNext()));
    }

    // EXTENSION
    @Override
    public int hashCode() {
        return hashCodeRecursive(head);
    }

    private int hashCodeRecursive(Node node) {
        if (node == null) {
            return Objects.hash();
        }
        int result = Objects.hash(node);

        if (node.getNext() != null) {
            return result+=hashCodeRecursive(node.getNext());
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LinkedList)) {
            return false;
        }
        LinkedList that = (LinkedList) o;
        return equalsRecursive(head, that.head);
    }

    private boolean equalsRecursive(Node node1, Node node2) {
        if (node1 == node2) {
            return true;
        }

        if (node1 != null && node2 == null) {
            return false;
        } else if (node1 == null) {
            return false;
        } else if (!node1.getData().equals(node2.getData())) {
            return false;
        } else {
            return equalsRecursive(node1.getNext(), node2.getNext());
        }
    }

    @Override
    public String toString() {
        Node node = head;
        StringBuilder stringBuilder = new StringBuilder("[");

        while (node != null) {
            stringBuilder.append(node.getData())
                    .append(", ");
            node = node.getNext();
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}

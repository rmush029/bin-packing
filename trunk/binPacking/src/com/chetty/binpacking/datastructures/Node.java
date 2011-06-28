package com.chetty.binpacking.datastructures;

/**
 *
 * @author Babji Prashanth, Chetty
 */
public class Node {
    private Node left;
    private Node right;
    private Comparable value;

    public Node(Comparable value) {
        this.value = value;
    }

    public Node(Node left, Node right, Comparable value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    /**
     * @return the left
     */
    public Node getLeft() {
        return left;
    }

    /**
     * @param left the left to set
     */
    public void setLeft(Node left) {
        this.left = left;
    }

    /**
     * @return the right
     */
    public Node getRight() {
        return right;
    }

    /**
     * @param right the right to set
     */
    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * @return the value
     */
    public Comparable getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Comparable value) {
        this.value = value;
    }
}

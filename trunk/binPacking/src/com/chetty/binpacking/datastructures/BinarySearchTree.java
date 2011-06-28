package com.chetty.binpacking.datastructures;

/**
 *
 * @author Babji Prashanth, Chetty
 */
public class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public BinarySearchTree(Comparable value) {
        Node newRoot = new Node(value);
        this.root = newRoot;
    }

    public void removeAll() {
        this.root = null;
    }

    public void add(Comparable value) {
        root = insert(value, root);
    }

    private Node insert(Comparable value, Node node) {
        if(node == null) {
            node = new Node(value);
        } else if(value.compareTo(node.getValue()) < 0) {
            node.setLeft(insert(value, node.getLeft()));
        } else if(value.compareTo(node.getValue()) >= 0) {
            node.setRight(insert(value, node.getRight()));
        }

        return node;
    }

    public void remove(Comparable value) {
        root = remove(value, root);
    }

    private Node remove(Comparable value, Node node) {
        if(node == null) {
            return node;
        } else if(value.compareTo(node.getValue()) < 0) {
            node.setLeft(remove(value, node.getLeft()));
        } else if(value.compareTo(node.getValue()) > 0) {
            node.setRight(remove(value, node.getRight()));
        } else if(node.getLeft() != null && node.getRight() != null) {
            node.setValue(findMin(node.getRight()).getValue());
            node.setRight(remove(node.getValue(), node.getRight()));
        } else {
            node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
        }

        return node;
    }

    private Comparable valueAt(Node node) {
        return (node == null) ? null : node.getValue();
    }

    public Comparable findMin() {
        return valueAt(findMin(root));
    }

    private Node findMin(Node node) {
        if(node == null) {
            return null;
        } else if(node.getLeft() == null) {
            return node;
        }

        return findMin(node.getLeft());
    }

    public Comparable findMax() {
        return valueAt(findMax(root));
    }

    private Node findMax(Node node) {
        if(node != null) {
            while(node.getRight() != null) {
                node = node.getRight();
            }
        }

        return node;
    }

    public Comparable find(Comparable value) {
        return valueAt(find(value, root));
    }    

    private Node find(Comparable value, Node node) {
        if(node == null) {
            return null;
        } else if(value.compareTo(node.getValue()) < 0) {
            return find(value, node.getLeft());
        } else if(value.compareTo(node.getValue()) > 0) {
            return find(value, node.getRight());
        }

        return node;
    }

    public Comparable ceiling(Comparable value) {
        return valueAt(getEqualOrHigherValue(value, root));
    }

    private Node getEqualOrHigherValue(Comparable value, Node node) {
        if(node == null) {
            return null;
        } else if(value.compareTo(node.getValue()) < 0) {
            Node left = node.getLeft();
            return (left != null && value.compareTo(left.getValue()) < 0) ? getEqualOrHigherValue(value, left) : node;
        } else if(value.compareTo(node.getValue()) > 0) {
            return getEqualOrHigherValue(value, node.getLeft());
        }

        return node;
    }

    public Comparable lower(Comparable value) {
        return valueAt(getNextLower(value, root));
    }

    private Node getNextLower(Comparable value, Node node) {
        if(node == null) {
            return null;
        } else if(value.compareTo(node.getValue()) < 0) {
            return getNextLower(value, node.getLeft());
        } else if(value.compareTo(node.getValue()) > 0) {
            Node right = node.getRight();
            return (right != null && value.compareTo(right.getValue()) > 0) ? getNextLower(value, right) : node;
        }

        return node;
    }

    public Comparable getRootValue() {
        return valueAt(root);
    }
}
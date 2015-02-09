/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.govschool.binarySearchTrees;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * @author Glaedwyn
 */
public class Tree {

    // We only need one reference, the root. Just like in a linked list
    // where we can access any node by traversing from the head, we can
    // do the same for a BST.
    private Node root;

    /**
     * Empty constructor. Creates a <code>Tree</code> with an empty root
     */
    public Tree() {
        this(null);
    }

    /**
     * Default constructor. Creates a <code>Tree</code> with the specified
     * <code>Node</code> as the root
     */
    public Tree(Node root) {
        this.root = root;
    }

    /**
     * Returns the root of the <code>Tree</code>.
     *
     * @return the root
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Tests if the <code>Tree</code> is empty.
     *
     * @return <code>true</code> if the root is <code>null</code>
     */
    public boolean isEmpty() {
        return root == null;
    }

    /**
     * Inserts some data into the <code>Tree</code>. This method uses the helper
     * method below to actually insert the data. This is a common OOP practice
     * when a method needs multiple arguments to work, but can be called in
     * practice with just one (as in this case)
     *
     * @param data the data to insert
     */
    public void insert(int data) {
        insert(data, this.getRoot());
    }

    /**
     * Inserts some data into the <code>Tree</code> recursively. If the input
     * <code>Node</code> is <code>null</code>, we assume that the
     * <code>Tree</code> is empty. Otherwise, we traverse down the
     * <code>Tree</code> recursively following the rules of a BST.
     *
     * @param data the data to insert
     * @param node the current <code>Node</code>
     */
    private void insert(int data, Node node) {
        if (node == null) {
            root = new Node(data);
        } else {
            if (data < node.getData()) {
                if (node.getLeft() == null) {
                    node.setLeft(new Node(data));
                } else {
                    insert(data, node.getLeft());
                }
            } else {
                if (node.getRight() == null) {
                    node.setRight(new Node(data));
                } else {
                    insert(data, node.getRight());
                }
            }
        }
    }

    /**
     * Prints the <code>Tree</code>. We use the recursive
     * <code>outputTree()</code> method below.
     */
    public void print() {
        outputTree(this.getRoot(), 0);
    }

    /**
     * Output a <code>Tree</code> rooted at a given <code>Node</code>. First,
     * the right sub-tree of the current <code>Node</code> is output, then the
     * <code>Node</code> itself, then the left sub-tree, recursively!
     */
    private void outputTree(Node curr, int spaces) {
        if (curr == null) {
            return;
        } else {
            outputTree(curr.getRight(), spaces + 5);
            for (int i = 0; i < spaces; i++) {
                System.out.print(" ");
            }
            System.out.print(curr.getData() + "\n");
            outputTree(curr.getLeft(), spaces + 5);
        }
    }

    public void printMax() {
        printMax(this.root);
    }

    private void printMax(Node node) {
        if (node == null) {
            System.out.print("Error : Empty tree");
        } else {
            if (node.getRight() == null) {
                System.out.print(node.getData());
            } else {
                printMax(node.getRight());
            }
        }
    }

    public void printMin() {
        printMin(this.root);
    }

    private void printMin(Node node) {
        if (node == null) {
            System.out.print("Error : Empty tree");
        } else {
            if (node.getLeft() == null) {
                System.out.print(node.getData());
            } else {
                printMin(node.getLeft());
            }
        }
    }

    public void printLeaves() {
        System.out.print(findLeaves(this.root));
    }

    private String findLeaves(Node node) {
        StringBuilder sb = new StringBuilder();
        if (node == null) {
            System.out.println("Error : Empty Tree");
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                if (sb.equals("")) {
                    sb.append(node.getData());
                } else {
                    sb.append(node.getData() + " ");
                }
            } else {
                if (node.getLeft() != null) {
                    sb.append(findLeaves(node.getLeft()));
                }
                if (node.getRight() != null) {
                    sb.append(findLeaves(node.getRight()));
                }
            }
        }
        return sb.toString();
    }

    public void printPaths() {
        Object[] arr = printPaths(this.root).toArray();
        for (int i = 0; i < arr.length; i++) {
            System.out.println(getPath(new Node((int) arr[i])).toString());
        }
    }

    public LinkedList<Integer> getPath(Node node) {
        return getPath(this.root, node);
    }

    private LinkedList<Integer> getPath(Node curr, Node find) {
        LinkedList<Integer> list = new LinkedList<>();
        if (!this.contains(root, find.getData())) {
            return null;
        } else {
            list.add(curr.getData());
            if (find.getData() > curr.getData()) {
                if (curr.getRight() != null) {
                    list.addAll(getPath(curr.getRight(), find));
                }
            } else {
                if (curr.getLeft() != null) {
                    list.addAll(getPath(curr.getLeft(), find));
                }
            }
        }
        return list;
    }

    private ArrayList<Integer> printPaths(Node node) {
        ArrayList<Integer> arr = new ArrayList<>();
        if (node == null) {
            System.out.println("Error : Empty Tree");
        } else {
            if (node.getLeft() == null && node.getRight() == null) {
                arr.add(node.getData());
            } else {
                if (node.getLeft() != null) {
                    arr.addAll(printPaths(node.getLeft()));
                }
                if (node.getRight() != null) {
                    arr.addAll(printPaths(node.getRight()));
                }
            }
        }
        return arr;
    }

    private int findMax(Node node) {
        if (node == null) {
            return 0;
        } else {
            if (node.getRight() == null) {
                return node.getData();
            } else {
                return findMax(node.getRight());
            }
        }
    }

    public void search(int i) {
        search(this.root, i);
    }

    private void search(Node node, int i) {
        if (node.getData() == i) {
            System.out.println(i + " is in the tree");
        } else {
            if (i > node.getData()) {
                if (node.getRight() != null) {
                    search(node.getRight(), i);
                } else {
                    System.out.println(i + " is not in the tree");
                }
            } else {
                if (node.getLeft() != null) {
                    search(node.getLeft(), i);
                } else {
                    System.out.println(i + " is not in the tree");
                }
            }
        }
    }

    public void findHeight() {
        System.out.println("The tree is " + findHeight(this.root)
                + " levels tall");
    }

    private int findHeight(Node node) {
        if (node == null) {
            return 0;
        } else {
            return 1 + maxOf(findHeight(node.getLeft()),
                    findHeight(node.getRight()));
        }
    }

    public void getMaxPrime() {
        if (getMaxPrime(this.root) == Integer.MIN_VALUE) {
            System.out.println("No prime numbers in the tree");
        } else {
            System.out.println("Max Prime : " + getMaxPrime(this.root));
        }
    }

    private int getMaxPrime(Node node) {
        int leftMaxPrime = 0;
        int rightMaxPrime = 0;
        if (node.getLeft() != null) {
            if (isPrime(getMaxPrime(node.getLeft()))) {
                leftMaxPrime = getMaxPrime(node.getLeft());
            }
        }
        if (node.getRight() != null) {
            if (isPrime(getMaxPrime(node.getRight()))) {
                rightMaxPrime = getMaxPrime(node.getRight());
            }
        }
        int maxPrime = Integer.MIN_VALUE;
        if (isPrime(node.getData())) {
            maxPrime = maxOf(node.getData(), maxOf(leftMaxPrime, rightMaxPrime));
        } else {
            maxPrime = maxOf(leftMaxPrime, rightMaxPrime);
        }
        return maxPrime;
    }

    private int maxOf(int i, int j) {
        if (j > i) {
            return j;
        } else {
            return i;
        }
    }

    public boolean isPrime(int p) {
        int i = 2;
        boolean m = true;

        if (p == 1 || p == 0) {
            return false;
        } else {
            while (i < p && m == true) {
                if (p % i == 0) {
                    m = false;
                } else {
                    i++;
                }
            }
        }

        return m;
    }

    public void printBranches() {
        if (root != null) {
            System.out.println("Values in the right subtree : ");
            if (root.getRight() != null) {
                outputBranches(root.getRight());
                System.out.println();
            }
            System.out.println("Values in the left subtree : ");
            if (root.getLeft() != null) {
                outputBranches(root.getLeft());
            }
        }
    }

    private void outputBranches(Node curr) {
        if (curr == null) {
            return;
        } else {
            outputBranches(curr.getRight());
            System.out.print(curr.getData() + " ");
            outputBranches(curr.getLeft());
        }
    }

    private boolean contains(Node node, int i) {
        if (node.getData() == i) {
            return true;
        } else {
            if (i > node.getData()) {
                if (node.getRight() != null) {
                    return contains(node.getRight(), i);
                } else {
                    return false;
                }
            } else {
                if (node.getLeft() != null) {
                    return contains(node.getLeft(), i);
                } else {
                    return false;
                }
            }
        }
    }
}

package ru.vsu.cs.course1;

/**
 * Represents a node in a binary tree.
 */
public class TreeNode {
    private int value;
    private TreeNode left;
    private TreeNode right;

    /**
     * Constructs a node with the specified value.
     * @param value The value of the node.
     */
    public TreeNode(int value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    /**
     * Gets the value of the node.
     * @return The value of the node.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the value of the node.
     * @param value The new value of the node.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Gets the left child of the node.
     * @return The left child node, or null if none.
     */
    public TreeNode getLeft() {
        return left;
    }

    /**
     * Sets the left child of the node.
     * @param left The new left child node.
     */
    public void setLeft(TreeNode left) {
        this.left = left;
    }

    /**
     * Gets the right child of the node.
     * @return The right child node, or null if none.
     */
    public TreeNode getRight() {
        return right;
    }

    /**
     * Sets the right child of the node.
     * @param right The new right child node.
     */
    public void setRight(TreeNode right) {
        this.right = right;
    }
}
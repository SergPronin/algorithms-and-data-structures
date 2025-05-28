package ru.vsu.cs.course1;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Utility class for generating random binary trees.
 */
public class RandomBinaryTree {

    /**
     * Generates a random binary tree with specified parameters.
     * @param minValue Minimum value for nodes (inclusive).
     * @param maxValue Maximum value for nodes (inclusive).
     * @param leftP Probability of a node having a left child (0 to 1).
     * @param rightP Probability of a node having a right child (0 to 1).
     * @param maxHeight Maximum height of the tree.
     * @param currentHeight Current depth in recursion (should be 0 for initial call).
     * @return Root node of the generated tree, or null if maxHeight is reached.
     * @throws IllegalArgumentException if parameters are invalid.
     */
    public static TreeNode generateTree(int minValue, int maxValue, double leftP, double rightP, int maxHeight, int currentHeight) {
        if (minValue > maxValue) {
            throw new IllegalArgumentException("minValue must be less than or equal to maxValue");
        }
        if (leftP < 0 || leftP > 1 || rightP < 0 || rightP > 1) {
            throw new IllegalArgumentException("leftP and rightP must be between 0 and 1");
        }
        if (maxHeight < 0) {
            throw new IllegalArgumentException("maxHeight must be non-negative");
        }
        if (currentHeight >= maxHeight) {
            return null;
        }

        int value = ThreadLocalRandom.current().nextInt(minValue, maxValue + 1);
        TreeNode node = new TreeNode(value);

        if (ThreadLocalRandom.current().nextDouble() < leftP) {
            node.setLeft(generateTree(minValue, maxValue, leftP, rightP, maxHeight, currentHeight + 1));
        }
        if (ThreadLocalRandom.current().nextDouble() < rightP) {
            node.setRight(generateTree(minValue, maxValue, leftP, rightP, maxHeight, currentHeight + 1));
        }
        return node;
    }
}
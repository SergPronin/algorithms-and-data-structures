package ru.vsu.cs.course1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RandomBinaryTree class.
 */
public class RandomBinaryTreeTest {

    /**
     * Tests that the generated tree does not exceed the maximum height.
     */
    @Test
    void testGenerateTreeHeight() {
        TreeNode root = RandomBinaryTree.generateTree(1, 10, 1.0, 1.0, 2, 0);
        assertNotNull(root, "Root should not be null");
        assertTrue(getHeight(root) <= 2, "Tree height should not exceed maxHeight");
    }

    /**
     * Tests that node values are within the specified range.
     */
    @Test
    void testGenerateTreeValuesInRange() {
        TreeNode root = RandomBinaryTree.generateTree(1, 10, 0.5, 0.5, 3, 0);
        assertTrue(checkValuesInRange(root, 1, 10), "Node values should be in range [1, 10]");
    }

    /**
     * Tests that invalid parameters throw an IllegalArgumentException.
     */
    @Test
    void testInvalidParameters() {
        assertThrows(IllegalArgumentException.class, () -> {
            RandomBinaryTree.generateTree(10, 1, 0.5, 0.5, 3, 0);
        }, "Should throw exception for minValue > maxValue");
        assertThrows(IllegalArgumentException.class, () -> {
            RandomBinaryTree.generateTree(1, 10, -0.1, 0.5, 3, 0);
        }, "Should throw exception for negative leftP");
        assertThrows(IllegalArgumentException.class, () -> {
            RandomBinaryTree.generateTree(1, 10, 0.5, 0.5, -1, 0);
        }, "Should throw exception for negative maxHeight");
    }

    private int getHeight(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(getHeight(node.getLeft()), getHeight(node.getRight()));
    }

    private boolean checkValuesInRange(TreeNode node, int min, int max) {
        if (node == null) return true;
        if (node.getValue() < min || node.getValue() > max) return false;
        return checkValuesInRange(node.getLeft(), min, max) && checkValuesInRange(node.getRight(), min, max);
    }
}
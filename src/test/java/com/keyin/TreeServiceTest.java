package com.keyin;

import com.keyin.Entities.TreeNode;
import com.keyin.Services.TreeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TreeServiceTest {

    @Autowired
    private TreeService treeService;

    @Test
    void testBuildTree() {
        List<Integer> numbers = Arrays.asList(5, 3, 7, 2, 4, 6, 8);
        TreeNode root = treeService.buildTree(numbers);

        assertNotNull(root);
        assertEquals(5, root.getValue());
        assertEquals(3, root.getLeft().getValue());
        assertEquals(7, root.getRight().getValue());
    }

    @Test
    void testSerializeTree() {
        TreeNode root = new TreeNode(5);
        root.setLeft(new TreeNode(3));
        root.setRight(new TreeNode(7));

        String expected = "{\"value\":5,\"left\":{\"value\":3,\"left\":null,\"right\":null},\"right\":{\"value\":7,\"left\":null,\"right\":null}}";
        assertEquals(expected, treeService.serializeTree(root));
    }

    @Test
    void testBuildBalancedTree() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7);
        TreeNode root = treeService.buildBalancedTree(numbers);

        assertNotNull(root);
        assertEquals(4, root.getValue());
        assertEquals(2, root.getLeft().getValue());
        assertEquals(6, root.getRight().getValue());
    }
}
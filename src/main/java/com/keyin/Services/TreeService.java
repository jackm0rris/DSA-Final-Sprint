package com.keyin.Services;

import com.keyin.Entities.TreeNode;
import com.keyin.Entities.TreeRecord;
import com.keyin.Repository.TreeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeService {

    @Autowired
    private TreeRepository treeRepository;

    public TreeNode buildTree(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) return null;

        TreeNode root = new TreeNode(numbers.get(0));
        for (int i = 1; i < numbers.size(); i++) {
            insert(root, numbers.get(i));
        }
        return root;
    }

    private void insert(TreeNode node, int value) {
        if (value < node.getValue()) {
            if (node.getLeft() == null) {
                node.setLeft(new TreeNode(value));
            } else {
                insert(node.getLeft(), value);
            }
        } else {
            if (node.getRight() == null) {
                node.setRight(new TreeNode(value));
            } else {
                insert(node.getRight(), value);
            }
        }
    }

    public String serializeTree(TreeNode root) {
        if (root == null) return "null";
        return String.format("{\"value\":%d,\"left\":%s,\"right\":%s}",
                root.getValue(),
                serializeTree(root.getLeft()),
                serializeTree(root.getRight()));
    }

    public TreeRecord saveTree(String numbers, TreeNode root) {
        String treeJson = serializeTree(root);
        TreeRecord record = new TreeRecord(numbers, treeJson);
        return treeRepository.save(record);
    }

    public List<TreeRecord> getAllTrees() {
        return treeRepository.findAllByOrderByCreatedAtDesc();
    }

    // Bonus: Balanced BST implementation
    public TreeNode buildBalancedTree(List<Integer> numbers) {
        if (numbers == null || numbers.isEmpty()) return null;

        List<Integer> sorted = numbers.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());

        return buildBalancedTree(sorted, 0, sorted.size() - 1);
    }

    private TreeNode buildBalancedTree(List<Integer> sorted, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        TreeNode node = new TreeNode(sorted.get(mid));
        node.setLeft(buildBalancedTree(sorted, start, mid - 1));
        node.setRight(buildBalancedTree(sorted, mid + 1, end));

        return node;
    }
}
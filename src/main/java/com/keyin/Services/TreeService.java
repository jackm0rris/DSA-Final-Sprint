package com.keyin.Services;

import com.keyin.Entities.TreeNode;
import com.keyin.Entities.TreeRecord;
import com.keyin.Repository.TreeRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TreeService {
    private final TreeRepository treeRepository;

    public TreeService(TreeRepository treeRepository) {
        this.treeRepository = treeRepository;
    }

    public TreeRecord processNumbers(String numbers) {
        List<Integer> numberList = parseNumbers(numbers);
        TreeNode root = buildTree(numberList);
        TreeRecord record = new TreeRecord(numbers, root);
        return treeRepository.save(record);
    }

    private List<Integer> parseNumbers(String numbers) {
        return Arrays.stream(numbers.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public TreeNode buildTree(List<Integer> numbers) {
        TreeNode root = new TreeNode(numbers.get(0));
        numbers.subList(1, numbers.size()).forEach(n -> insert(root, n));
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

    public List<TreeRecord> getAllTrees() {
        return treeRepository.findAll();
    }

    public String serializeTree(TreeNode root) {
        return "";
    }

    public TreeNode buildBalancedTree(List<Integer> numbers) {
        return null;
    }
}
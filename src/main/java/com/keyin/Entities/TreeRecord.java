package com.keyin.Entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TreeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputNumbers;

    @OneToOne(cascade = CascadeType.ALL)
    private TreeNode treeStructure;

    private LocalDateTime createdAt = LocalDateTime.now();

    public TreeRecord() {}

    public TreeRecord(String inputNumbers, TreeNode treeStructure) {
        this.inputNumbers = inputNumbers;
        this.treeStructure = treeStructure;
    }

    // Getters
    public Long getId() { return id; }
    public String getInputNumbers() { return inputNumbers; }
    public TreeNode getTreeStructure() { return treeStructure; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
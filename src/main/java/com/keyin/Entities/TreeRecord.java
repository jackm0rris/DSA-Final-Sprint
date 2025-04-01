package com.keyin.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class TreeRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String inputNumbers;
    private String treeStructure;
    private Date createdAt;

    public TreeRecord() {}

    public TreeRecord(String inputNumbers, String treeStructure) {
        this.inputNumbers = inputNumbers;
        this.treeStructure = treeStructure;
        this.createdAt = new Date();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getInputNumbers() { return inputNumbers; }
    public void setInputNumbers(String inputNumbers) { this.inputNumbers = inputNumbers; }
    public String getTreeStructure() { return treeStructure; }
    public void setTreeStructure(String treeStructure) { this.treeStructure = treeStructure; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
}
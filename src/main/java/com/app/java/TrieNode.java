package com.app.java;

import java.util.HashMap;

public class TrieNode {
    private char c;
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isLeaf;

    public TrieNode() {}

    public TrieNode(char c){
        this.c = c;
    }

    @Override
    public String toString() {
        return "TrieNode{" +
                "children=" + children +
                '}';
    }
}
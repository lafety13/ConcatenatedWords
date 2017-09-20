package com.app;

import java.util.*;

public class Parser {
    private int totalConcatWord;
    private String firstLongest = "";
    private String previousWord = "";
    private String secondLongest = "";
    private TrieNode root;

    public Parser() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
        HashMap<Character, TrieNode> children = root.children;

        for (int i = 0; i < word.length(); i++) {
            char letter = word.charAt(i);

            TrieNode trieNode;
            if (children.containsKey(letter)) {
                trieNode = children.get(letter);
            } else {
                trieNode = new TrieNode(letter);
                children.put(letter, trieNode);
            }
            children = trieNode.children;

            //set leaf node
            if (i == word.length() - 1) {
                trieNode.isLeaf = true;
                findLongestAndSecondLongestConcatWord(word);
                countConcatWord(word);
                previousWord = word;
            }
        }
    }

    public <T extends Collection<String>> void insert(T items) {
        if (items.isEmpty()) {
            return;
        }
        for (String item : items) {
            insert(item);
        }
    }

    private void countConcatWord(String word) {
        if (word.contains(previousWord)) {
            totalConcatWord++;
        }
    }

    private void findLongestAndSecondLongestConcatWord(String word) {
        if (firstLongest.length() <= word.length() && word.contains(previousWord)) {
            secondLongest = firstLongest;
            firstLongest = word;
        }
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
        TrieNode trieNode = searchNode(word);

        return trieNode != null && trieNode.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
        return searchNode(prefix) != null;
    }

    public TrieNode searchNode(String str) {
        Map<Character, TrieNode> children = root.children;
        TrieNode trieNode = null;
        for (int i = 0; i < str.length(); i++) {
            char letter = str.charAt(i);
            if (children.containsKey(letter)) {
                trieNode = children.get(letter);
                children = trieNode.children;
            } else {
                return null;
            }
        }

        return trieNode;
    }

    public int getTotalConcatWord() {
        return totalConcatWord;
    }

    public String getFirstLongest() {
        return firstLongest;
    }

    public String getSecondLongest() {
        return secondLongest;
    }
}

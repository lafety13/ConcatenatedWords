package com.app;

import java.util.*;

public class Parser {
    private String firstLongest = "";
    private String previousWord = "";
    private String secondLongest = "";
    private List<String> concatenatedWords = new ArrayList<>();
    private TrieNode root;

    public Parser() {
        root = new TrieNode();
    }


    public void insert(List<String> words) {
        if (words.isEmpty()) {
            return;
        }

        for (String word : words) {
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
                    previousWord = word;
                }
            }
        }
        findTotalConcatenatedWords(words);
    }

    private void findTotalConcatenatedWords(List<String> words) {
        Set<String> preWords = new HashSet<>();
        words.sort(new Comparator<String>() {
            public int compare (String s1, String s2) {
                return s1.length() - s2.length();
            }
        });

        for (String word : words) {
            if (canForm(word, preWords)) {
                concatenatedWords.add(word);
            }
            preWords.add(word);
        }
    }

    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue;
                if (dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
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
        return concatenatedWords.size();
    }

    public List<String> getConcatenatedWords() {
        return concatenatedWords;
    }

    public String getFirstLongest() {
        return firstLongest;
    }

    public String getSecondLongest() {
        return secondLongest;
    }
}

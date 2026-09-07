import java.util.*;

class Solution {
    class TrieNode {
        TrieNode[] children = new TrieNode[26];
    }
    
    public int minimumLengthEncoding(String[] words) {
        // Remove duplicates
        Set<String> wordSet = new HashSet<>(Arrays.asList(words));
        
        TrieNode root = new TrieNode();
        Map<TrieNode, Integer> nodeDepth = new HashMap<>();
        
        // Insert each reversed word into the trie, tracking the depth of its ending node
        for (String word : wordSet) {
            TrieNode node = root;
            for (int i = word.length() - 1; i >= 0; i--) {
                int c = word.charAt(i) - 'a';
                if (node.children[c] == null) {
                    node.children[c] = new TrieNode();
                }
                node = node.children[c];
            }
            nodeDepth.put(node, word.length());
        }
        
        // Sum up (depth + 1) for every leaf node (node with no children)
        int result = 0;
        for (Map.Entry<TrieNode, Integer> entry : nodeDepth.entrySet()) {
            TrieNode node = entry.getKey();
            boolean isLeaf = true;
            for (TrieNode child : node.children) {
                if (child != null) {
                    isLeaf = false;
                    break;
                }
            }
            if (isLeaf) {
                result += entry.getValue() + 1;
            }
        }
        
        return result;
    }
}

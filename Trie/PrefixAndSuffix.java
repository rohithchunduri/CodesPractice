/*
  This is an object to keep the TrieNode 
  Contains a HashMap for a Character and the next TrieNode.
 */

class Trie {
    HashMap<Character, Trie> children = new HashMap<Character, Trie>();
    int idx = -1;
}

// Utility for adding a string to a Trie
private void addTrie(Trie root , String currStr, int idx){
       
        Trie prev = root;
        prev = root;
       
        for(int j = 0 ; j < currStr.length(); j++){
            Trie currNode = null;
            char currCh = currStr.charAt(j);
           
            if(!prev.children.containsKey(currCh)){
                currNode = new Trie();
                currNode.idx = idx;
                prev.children.put(currCh, currNode);
            }
            else{
                currNode = prev.children.get(currCh);
            }
                prev = currNode;
        }
       
    }

// Utility for searching inside a Trie given a string
private int searchIdx(String currStr){
       
        Trie prev = root;
        prev = root;
        int idx = -1;
       
        for(int i = 0 ; i < currStr.length(); i++){
            Trie currNode = null;
            char currCh = currStr.charAt(i);
           
            if(!prev.children.containsKey(currCh)){
                break;
            }
           
            currNode = prev.children.get(currCh);
            prev = currNode;
           
            if(i == currStr.length()-1)
                idx = prev.idx;
        }
       
        return idx;
    }

// LeetCode Prefix and Suffix Question . https://leetcode.com/problems/prefix-and-suffix-search/
class WordFilter {
  
    Trie root = new Trie();
    public WordFilter(String[] words) {
        for(int i = words.length -1 ; i >= 0 ; i--){
            String currString1 = words[i];
            String currString = new StringBuilder("#"+ words[i]).toString();
            int currLength = currString1.length();
            for(int j = 0 ; j < currLength; j++){
                String newString = new String(currString1.substring(j, currLength) + currString);
                addTrie(root, newString, i);
            }
        }
    }
   
    public int f(String prefix, String suffix) {
        String currString = new StringBuilder(suffix + "#" + prefix).toString();
        return searchIdx(currString);
    }
}

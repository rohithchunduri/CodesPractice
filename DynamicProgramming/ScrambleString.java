/*
Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

Below is one possible representation of s1 = "great":

    great
   /    \
  gr    eat
 / \    /  \
g   r  e   at
           / \
          a   t
To scramble the string, we may choose any non-leaf node and swap its two children.

For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

    rgeat
   /    \
  rg    eat
 / \    /  \
r   g  e   at
           / \
          a   t
We say that "rgeat" is a scrambled string of "great".

Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

    rgtae
   /    \
  rg    tae
 / \    /  \
r   g  ta  e
       / \
      t   a
We say that "rgtae" is a scrambled string of "great".

Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

Example 1:

Input: s1 = "great", s2 = "rgeat"
Output: true
Example 2:

Input: s1 = "abcde", s2 = "caebd"
Output: false
*/


// Variation of MCM Problem 

class Solution {
    
    HashMap<String, Boolean> hMap = new HashMap<String, Boolean>();
    boolean solve(String a , String b){
        
        String temp = new String(a + "_" + b);
        
        if(hMap.containsKey(temp)){
            return hMap.get(temp);
        }
        
        if(a.length() != b.length()){
            return false;
        }
        
        if(a.equals(b)){
            hMap.put(temp, true);
            return true;
        }
        
        int n = a.length();
        boolean ans = false;
        for(int i = 1; i < n ; i++){
            
            //If not Swapped
            if((solve(a.substring(0, i),b.substring(0, i)) && solve(a.substring(i, n), b.substring(i, n))) ||
                (solve(a.substring(0, i), b.substring(n-i, n)) && (solve(a.substring(i, n), b.substring(0, n-i))))){
                    ans = true;
                    break;
                    
                }
            
        }
        hMap.put(temp, ans);
        return ans;
    }
    
    public boolean isScramble(String s1, String s2) {
        
        String inputA = new String(s1);
        String inputB = new String(s2);
        
        return solve(inputA, inputB);
    }
}

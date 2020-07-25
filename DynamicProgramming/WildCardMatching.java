/* Given an input string (s) and a pattern (p), implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).
*/

class Solution {
    
    String s1;
    String p1;
    int n;
    int m;
    
    HashMap<String, Boolean> hMap = new HashMap<String, Boolean>();
    
    boolean recurse(int i , int j){
        
        String currKey = i+"_"+j;
        
        boolean ans = false;
        
        if(hMap.containsKey(currKey)){
            return hMap.get(currKey);
        }
        
        if(i == 0 && j == 0){
            hMap.put(currKey, true);
            return true;
        }
        
        if(j == 0 && i > 0){
            hMap.put(currKey, false);
            return false;
        }
        
        if(i == 0 && j >= 1){
            if(p1.charAt(j-1) == '*'){
                if(j == 1){
                    ans = true;
                }
                else{
                    ans = recurse(i, j-1);
                }
            }
            hMap.put(currKey, ans);
            return ans;
        }
        
        char chA = s1.charAt(i-1);
        char chB = p1.charAt(j-1);
        
        if(chA == chB){
            ans = recurse(i-1, j-1) || ans;
        }
        
        if(chB == '?'){
            ans = recurse(i-1, j-1) || ans;
        }
        
        if(chB == '*'){
            ans = recurse(i-1, j) || recurse(i, j-1) || ans;
        }
        
        hMap.put(currKey, ans);
        
        return ans;
    }
    
    public boolean isMatch(String s, String p) {
        
        n = s.length();
        m = p.length();
        
        s1 = s;
        p1 = p;
        
        return recurse(n, m);
    }
}

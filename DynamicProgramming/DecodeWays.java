/*
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
*/

class Solution {
    
    int n;
    
    HashMap<Integer, Integer> hMap = new HashMap<Integer, Integer>();
    
    int recurse(int idx, String s){
        
        if(hMap.containsKey(idx)){
            return hMap.get(idx);
        }
        
        if(idx == n){
            hMap.put(idx, 1);
            return 1;
        }
        
        if(idx > n){
            hMap.put(idx, 0);
            return 0;
        }
        
        if(s.charAt(idx) == '0'){
            return 0;
        }
        
        char currCh = s.charAt(idx);
        
        int ans = 0;
        
        if(currCh == '1'){
            if(idx < n-1){
                char nextCh = s.charAt(idx+1);
                if(nextCh == '0'){
                    ans = recurse(idx+2, s);
                }
                else{
                    ans = recurse(idx+1, s) + recurse(idx+2, s);
                }
            }
            else{
                ans = recurse(idx+1, s);
            }
        }
        
        else if(currCh == '2'){
            if(idx < n-1){
                char nextCh = s.charAt(idx+1);
                if(nextCh == '0'){
                    ans = recurse(idx+2, s);
                }
                else{
                    if(nextCh == '1' || nextCh == '2' || nextCh == '3'|| nextCh == '4'|| nextCh == '5'|| nextCh == '6'){
                        ans = recurse(idx+1, s) + recurse(idx+2, s);
                    }
                    else{
                        ans = recurse(idx+1, s);
                    }
                }
            }
            else{
                ans = recurse(idx+1, s);
            }
        }
        
        else{
            ans = recurse(idx+1, s);
        }
        
        hMap.put(idx, ans);
        return ans;
        
    }
    
    public int numDecodings(String s) {
        n = s.length();
        return recurse(0, s);
    }
}

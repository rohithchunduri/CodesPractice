/* Given two words word1 and word2, find the minimum number of operations required to convert word1 to word2.

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
*/


class Solution {
    int dp[][];
    
    int editDistance(String A, String B, int i, int j){
        
        if(dp[i][j] != -1){
            return dp[i][j];
        }
        
        if(i == 0 || j  == 0){
            dp[i][j] = Math.max(i, j);
            return dp[i][j];
        }
        
        if(A.charAt(i-1) == B.charAt(j-1)){
            dp[i][j] = editDistance(A, B, i-1, j-1);
        }
        
        else{
            int minValue = 1 + Math.min(editDistance(A, B, i-1, j-1), Math.min(editDistance(A, B, i, j-1) , editDistance(A, B, i-1, j)));
            dp[i][j] = minValue;
        }
        
        return dp[i][j];
    }
    
    public int minDistance(String A, String B) {
        
        int n = A.length();
        int m = B.length();
        
        dp = new int[n+1][m+1];
        
        
        for(int i = 0 ; i <= n; i++){
            for(int j = 0 ; j <= m; j++){
                dp[i][j] = -1; 
            }
        }
        
        editDistance(A, B, n, m);
        
        return dp[n][m];
    }
}

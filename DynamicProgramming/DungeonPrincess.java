public class Solution {
    public int calculateMinimumHP(ArrayList<ArrayList<Integer>> A) {
        
        int n = A.size();
        int m = A.get(0).size();
        
        int dp[][] = new int[n][m];
        
        for(int i = n-1 ; i>=0 ; i--){
            for(int j = m-1; j >= 0 ; j--){
                if(i == n-1 && j == m-1){
                    dp[i][j] = Math.max(1, 1 - A.get(i).get(j));
                }
                
                else if(i == n-1){
                    dp[i][j] = Math.max(1, dp[i][j+1] - A.get(i).get(j));
                }
                
                else if(j == m-1){
                    dp[i][j] = Math.max(1, dp[i+1][j] - A.get(i).get(j));
                }
                
                else{
                    dp[i][j] = Math.max(1, Math.min(dp[i+1][j], dp[i][j+1]) - A.get(i).get(j));
                }
            }
        }
        
        return dp[0][0];
    }
}

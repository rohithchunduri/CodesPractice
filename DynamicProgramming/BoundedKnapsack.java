class Solution {
    public boolean canPartition(int[] nums) {
        
        int n = nums.length;
        int sum = 0;
        for(int i : nums){
            sum += i;
        }
        
        if(sum%2 == 1){
            return false;
        }
        
        boolean dp[] = new boolean[sum/2+1];
        
        dp[0] = true;
        for(int i = 1 ; i <= n ; i++){
            for(int j = sum/2 ; j >= 0 ; j--){
                if((j - nums[i-1]) >= 0){
                    dp[j] = dp[j] ||dp[j - nums[i-1]];
                }
            }
        }
        
        return dp[sum/2];
    }
}

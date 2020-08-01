public class Solution {
    public int maxProduct(final int[] A) {
        int n = A.length;
        
        int max_so_far = A[0];
        int mini = A[0];
        int maxi = A[0];
        
        for(int i = 1; i < n ; i++){
            
            if(A[i] < 0){
                mini = maxi^mini;
                maxi = maxi^mini;
                mini = maxi^mini;
            }
            
            maxi = Math.max(A[i], A[i]*maxi);
            mini = Math.min(A[i], A[i]*mini);
            
            max_so_far = Math.max(maxi, max_so_far);
        }
        return max_so_far;
    }
}

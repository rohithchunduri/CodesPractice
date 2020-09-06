/*Given an array of citations sorted in ascending order (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index.

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."

Example:

Input: citations = [0,1,3,5,6]
Output: 3 
Explanation: [0,1,3,5,6] means the researcher has 5 papers in total and each of them had 
             received 0, 1, 3, 5, 6 citations respectively. 
             Since the researcher has 3 papers with at least 3 citations each and the remaining 
             two with no more than 3 citations each, her h-index is 3.
Note:

If there are several possible values for h, the maximum one is taken as the h-index.
*/

class Solution {
    
    int idx(int[] citations, int target){
        
        int n = citations.length;
        
        int low = 0;
        int high = n-1;
        int mid = 0;
        int ans = Integer.MAX_VALUE;
        
        while(low <= high){
            mid = (low + high)/2;
            if(citations[mid] >= target){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    public int hIndex(int[] citations) {
        
        int n = citations.length;
        if(n == 0){
            return 0;
        }
        
        int low = 0;
        int high = n;
        int mid = 0;
        int ans = -1;
        
        while(low <= high){
            mid = (low + high)/2;
            int currIdx = idx(citations, mid);
            int currPap = n - currIdx;
            if(currPap >= mid){
                ans = mid;
                low = mid + 1;
            }
            else{
                high = mid - 1;
            }
        }
        return ans;
    }
}

Koko loves to eat bananas.  There are N piles of bananas, the i-th pile has piles[i] bananas.  The guards have gone and will come back in H hours.

Koko can decide her bananas-per-hour eating speed of K.  Each hour, she chooses some pile of bananas, and eats K bananas from that pile.  If the pile has less than K bananas, she eats all of them instead, and won't eat any more bananas during this hour.

Koko likes to eat slowly, but still wants to finish eating all the bananas before the guards come back.

Return the minimum integer K such that she can eat all the bananas within H hours.

Input: piles = [3,6,7,11], H = 8
Output: 4
Example 2:

Input: piles = [30,11,23,4,20], H = 5
Output: 30
Example 3:

Input: piles = [30,11,23,4,20], H = 6
Output: 23

class Solution {
    
    boolean isPossible(int piles[], int H, int currSpeed){
        int timeTaken = 0;
        for(int i : piles){
            int curr = 0;
            if(i%currSpeed == 0){
                curr = (i/currSpeed);
            }
            else{
                curr = (i/currSpeed) + 1;
            }
            timeTaken += curr;
        }
        
        if(timeTaken > H){
            return false;
        }
        
        return true;
    }

    public int minEatingSpeed(int[] piles, int H) {
        
        int mini = Integer.MAX_VALUE;
        int maxi = Integer.MIN_VALUE;
        
        for(int i : piles){
            mini = Math.min(i, mini);
            maxi = Math.max(i, maxi);
        }
        
        System.out.print(mini + " " + maxi);
        
        int low = 1;
        int high = maxi;
        int mid = 0;
        int ans = -1;
        
        while(low <= high){
            mid = (low + high)/2;
            if(isPossible(piles, H, mid)){
                ans = mid;
                high = mid - 1;
            }
            else{
                low = mid + 1;
            }
        }
        
        return ans;
    }
}

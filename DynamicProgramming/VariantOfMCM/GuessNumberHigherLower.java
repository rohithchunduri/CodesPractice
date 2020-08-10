/*
We are playing the Guess Game. The game is as follows:

I pick a number from 1 to n. You have to guess which number I picked.

Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

Example:

n = 10, I pick 8.

First round:  You guess 5, I tell you that it's higher. You pay $5.
Second round: You guess 7, I tell you that it's higher. You pay $7.
Third round:  You guess 9, I tell you that it's lower. You pay $9.

Game over. 8 is the number I picked.

You end up paying $5 + $7 + $9 = $21.
Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.

*/

class Solution {
    
    HashMap<String, Integer> hMap = new HashMap<String, Integer>();
    
    int recurse(int i, int j){
        
        String currKey = i+"_"+j;
        
        if(hMap.containsKey(currKey)){
            return hMap.get(currKey);
        }
        
        if( i == j){
            return 0;
        }
        
        if(i+1 == j){
            hMap.put(currKey, i);
            return i;
        }
        
        if(i+2 == j){
            hMap.put(currKey, i+1);
            return i+1;
        }
        
        int currMin = Integer.MAX_VALUE;
        
        for(int k = i+1 ; k < j ; k++){
            int currValue = k + Math.max(recurse(i, k-1), recurse(k+1, j));
            currMin = Math.min(currValue, currMin);
        }
        
        hMap.put(currKey, currMin);
        return currMin;
    }
    public int getMoneyAmount(int n) {
        return recurse(1, n);
    }
}

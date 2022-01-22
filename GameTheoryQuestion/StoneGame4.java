/* In this https://leetcode.com/problems/stone-game-iv/
   The players have to play optimally to win, 
      playing optimally means , the player should decide, whether to must win or must lose.
      if a player has to win , he should give the other player a state that makes him to lose,
      which would give the chance for the player to win
 */

class Solution {
    
    HashMap<Integer, Boolean> hMap = new HashMap<Integer, Boolean>();
    boolean isWinner(int n){
        if (hMap.containsKey(n))
            return hMap.get(n);
        
        int i = 1;
        int count = 1;
        boolean ans = false;
        while (i <= n){
            if (i == n) {
                ans = true;
                break;
            }
            boolean curr = isWinner(n - i);
            if (curr == false) {
                ans = true;
                break;
            }
            count += 1;
            i = count*count;
        }
        
        hMap.put(n, ans);
        return ans;
    }
    public boolean winnerSquareGame(int n) {
        hMap.put(1, true);
        hMap.put(0, true);
        return isWinner(n);
    }
}
      
  

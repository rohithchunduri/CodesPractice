class Solution {
    
    HashMap<Integer, Integer> hMap = new HashMap<Integer, Integer>();
    
    int recurse(int idx){
        
        if(hMap.containsKey(idx)){
            return hMap.get(idx);
        }
        
        if(idx == 0){
            return 1;
        }
        
        int ans = 0;
        for(int i = 1; i <= idx; i++){
            ans += (recurse(i-1)*recurse(idx - i));
        }
        
        hMap.put(idx, ans);
        return ans;
    }
    
    public int numTrees(int n) {
        return recurse(n);
    }
}

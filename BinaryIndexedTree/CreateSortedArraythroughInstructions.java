class Solution {
    
    int bit[];
    int max;
    int mod = (int)(Math.pow(10,9) + 7);
    
    void updateFreq(int idx){
        while(idx <= max){
            bit[idx] += 1;
            idx += idx & -idx;
        }
    }
    
    int getFreq(int idx){
        
        int sum = 0;
        while(idx > 0){
            sum += bit[idx];
            idx = idx - (idx & -idx);
        }
        
        return sum;
    }
    
    public int createSortedArray(int[] instructions) {
        
        int n = instructions.length;
        max = 0;
        HashMap<Integer, Integer> hMap = new HashMap<Integer, Integer>();
        
        for(int i = 0 ; i < n ; i++){
            max = Math.max(max, instructions[i]);
        }
        
        bit  = new int[max + 1];
        int cost = 0;
        
        updateFreq(instructions[0]);
        hMap.put(instructions[0], 1);
        
        int currVal = 0;
        
        for(int i = 1 ; i < n ; i++){
            
            currVal = instructions[i];
            
            updateFreq(currVal);
            hMap.put(currVal, hMap.getOrDefault(currVal, 0) + 1);
            
            int temp = getFreq(currVal - 1);
            
            cost = (cost + Math.min(temp, i+1 - temp - hMap.get(currVal)))%mod;
            
        }
        
        return cost;
    }
}

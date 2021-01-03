Suppose you have n integers from 1 to n. We define a beautiful arrangement as an array that is constructed by these n numbers successfully if one of the following is true for the ith position (1 <= i <= n) in this array:

The number at the ith position is divisible by i.
i is divisible by the number at the ith position.
Given an integer n, return the number of the beautiful arrangements that you can construct.

class Solution {
    
    int ans = 0;
    
    public void recurse(ArrayList<Integer> arrList, int idx, int n){
        
        if(idx == 0){
            ans += 1;
            return;
        }
        
        //Find Divisors and multiples of idx present in arr
        for(int k = idx; k <= n; k = k + idx){
            if(arrList.contains(k)){
                int index = arrList.indexOf(k);
                arrList.remove(index);
                recurse(arrList, idx-1, n);
                arrList.add(k);
            }
        }
        
        for(int k = 1; k < idx; k++){
            if(idx%k == 0){
                if(arrList.contains(k)){
                    int index = arrList.indexOf(k);
                    arrList.remove(index);
                    recurse(arrList, idx-1, n);
                    arrList.add(k);
                }
            }
        }
        
    }
    
    public int countArrangement(int n) {
        
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        for(int i = 1; i <= n; i++){
            arrList.add(i);
        }
        
        recurse(arrList, n, n);
        
        return ans;
    }
}

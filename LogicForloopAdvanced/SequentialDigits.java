//https://leetcode.com/problems/sequential-digits/


class Solution {
    int numberOfDigits(int n){
        return (int)Math.floor(Math.log10(n) + 1);
    }
    public List<Integer> sequentialDigits(int low, int high) {
        int nLDigits = numberOfDigits(low);
        int nHDigits = numberOfDigits(high);
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        for(int nDigits = nLDigits; nDigits <= nHDigits; nDigits++){
            for(int i = 1; i <= 9 - (nDigits -1); i++){
                int currCount = 0;
                for(int k = i, j = 1; j <= nDigits; j++,k++){
                    currCount += k*(Math.pow(10, nDigits - j));
                }
                if (currCount >= low && currCount <= high){
                    ans.add(currCount);
                }
            }
        }
        
        return ans;
    }
}

/*
Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in their binary representation and return them as an array.

Example 1:

Input: 2
Output: [0,1,1]
Example 2:

Input: 5
Output: [0,1,1,2,1,2]
*/

class Solution {
    public int[] countBits(int num) {
        
        int bits[] = new int[num+1];
        
        if(num == 0){
            return bits;
        }
        
        int lastPower = 0;
        bits[1] = 1;
        
        for(int i = 2 ; i <= num; i++){
            
            if((i&i-1) == 0){
                bits[i] = 1;
                lastPower = i;
                continue;
            }
            
            bits[i] = 1 + bits[i - lastPower];
        }
        
        return bits;
    }
}

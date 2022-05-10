/*
Find all valid combinations of k numbers that sum up to n such that the following conditions are true:

Only numbers 1 through 9 are used.
Each number is used at most once.
Return a list of all possible valid combinations. The list must not contain the same combination twice, and the combinations may be returned in any order.

Example 1:

Input: k = 3, n = 7
Output: [[1,2,4]]
Explanation:
1 + 2 + 4 = 7
There are no other valid combinations.
Example 2:

Input: k = 3, n = 9
Output: [[1,2,6],[1,3,5],[2,3,4]]
Explanation:
1 + 2 + 6 = 9
1 + 3 + 5 = 9
2 + 3 + 4 = 9
There are no other valid combinations.
*/

class Solution {
    
    ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
    
    void recurse(boolean arr[], int value, int k, Stack<Integer> stk, int start) {
        if (value == 0 && k == 0) {
            ArrayList<Integer> arrList = new ArrayList<Integer>();
            Iterator itr = stk.iterator();
            while (itr.hasNext()) {
                arrList.add((Integer)itr.next());
            }
            ans.add(arrList);
            return;
        }
        
        if (value < 0 || k == 0)
            return;
        
        for (int i = start ; i < arr.length; i++) {
            if (arr[i] == true) {                 
                arr[i] = false;
                stk.push(i+1);
                recurse(arr, value - (i + 1), k -1 , stk, i);
                stk.pop();
                arr[i] = true;
            }
        }
    }
    
    public List<List<Integer>> combinationSum3(int k, int n) {
        boolean values[] = new boolean[9];
        Arrays.fill(values, true);
        recurse(values, n, k, new Stack(), 0);
        return ans;
    }
}

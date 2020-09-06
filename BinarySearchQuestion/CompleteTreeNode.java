Given a complete binary tree, count the number of nodes.

Note:

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

Example:

Input: 
    1
   / \
  2   3
 / \  /
4  5 6

Output: 6

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int countNodes(TreeNode root) {
        
        if(root == null){
            return 0;
        }
        
        TreeNode root1 = root;
        
        int depth = 0;
        
        while(root1 != null){
            depth += 1;
            root1 = root1.left;
        }
        
        
        int low  =  1 << depth -1;
        int high =  (1 << depth) -1 ;
        int mid = 0;
        int ans = 0;
        boolean flag = true;
        
        while(low <= high){
            
            TreeNode currRoot = root;
            
            flag = true;
            mid = (low + high)/2;
            String bin = Integer.toBinaryString(mid);
            
            for(int i = 1 ;  i < bin.length(); i++){
                if(bin.charAt(i) == '0'){
                    if(currRoot.left == null){
                        flag = false;
                        break;
                    }
                    currRoot = currRoot.left;
                }
                else{
                    if(currRoot.right == null){
                        flag = false;
                        break;
                    }
                    currRoot = currRoot.right;
                }
            }
            
            if(flag){
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

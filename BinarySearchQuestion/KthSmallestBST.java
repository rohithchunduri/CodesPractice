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
    
    int count = 0;
    int value = 0;
    
    void recurse(TreeNode root, int k){
        
        if(root == null){
            return;
        }
        
        recurse(root.left, k);
        count += 1;
        if(count == k){
            value = root.val;
            return;
        }
        recurse(root.right, k);
    }
    
    public int kthSmallest(TreeNode root, int k) {
        recurse(root, k);
        return value;
    }
}

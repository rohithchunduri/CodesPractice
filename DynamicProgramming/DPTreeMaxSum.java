public class Solution {
    
    class TreeNode{
        
        int idx;
        int val;
        ArrayList<TreeNode> children = new ArrayList<TreeNode>();
        
        TreeNode(int idx, int val){
            this.val = val;
            this.idx = idx;
        }
        
        void addChildren(ArrayList<TreeNode> children){
            this.children = children;
        }
        
        public String toString(){
            String currString = "";
            for(TreeNode currNode : children){
                currString += currNode.val + " ";
            }
            
            return currString;
        }
    }
    
    List<Integer> adj[];
    HashMap<Integer, TreeNode> hMapTreeNode = new HashMap<Integer, TreeNode>();
    HashMap<TreeNode, Integer> hMapNodeVal  = new HashMap<TreeNode, Integer>();
    
    int recurse(TreeNode root){
        
        if(root == null){
            return 0;
        }
        
        /*
        if(hMapNodeVal.containsKey(root)){
            return hMapNodeVal.get(root);
        }
        */
        
        int childValue = 0;
        int grandValue = 0;
        
        for(int childNode : adj[root.idx]){
            TreeNode child = hMapTreeNode.get(childNode); 
            childValue += recurse(child);
            
            for(int grChildNode : adj[child.idx]){
                TreeNode grChild = hMapTreeNode.get(grChildNode); 
                grandValue += recurse(grChild);
            }
        }
        
        int maxValue = Math.max(childValue, grandValue + root.val);
        //hMapNodeVal.put(root, maxValue);
        
        return maxValue;
    }
    
    public int solve(ArrayList<Integer> A, ArrayList<Integer> B) {
        
        int n = A.size();
        adj = new ArrayList[n+1];
        
        for(int i = 0 ; i <=n ; i++){
            adj[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0 ; i < n; i++){
            adj[A.get(i)].add(i+1);
        }
        
        TreeNode root = new TreeNode(1, B.get(0));
        
        hMapTreeNode.put(1, root);
        
        for(int i = 1; i <= n ; i++){
            TreeNode currNode = hMapTreeNode.get(i);
            ArrayList<TreeNode> children = new ArrayList<TreeNode>();
            for(int k : adj[i]){
                if(hMapTreeNode.containsKey(k)){
                    children.add(hMapTreeNode.get(k));
                }
                else{
                    TreeNode currChild  = new TreeNode(k, B.get(k-1));
                    hMapTreeNode.put(k, currChild);
                    children.add(currChild);
                }
            }
            
            currNode.addChildren(children);
        }
        
        return recurse(root);
    }
}

public class Solution {
    
    class Node{
        int idx;
        int val;
        
        Node(int idx, int val){
            this.idx = idx;
            this.val = val;
        }
    }
    
    int find(int value, ArrayList<Integer> currList){
        
        int high = currList.size() - 1;
        int low = 0;
        int ans = -1;
        int mid = 0;
        
        while(low <= high){
            mid = (low + high)/2;
            int currVal = currList.get(mid);
            if(currVal >= value){
                ans = currVal;
                high = mid -1;
            }
            else{
                low = mid + 1;
            }
        }
        
        return ans;
    }
    
    public ArrayList<Integer> solve(int A, ArrayList<Integer> B, ArrayList<Integer> C, ArrayList<Integer> D, ArrayList<Integer> E, ArrayList<Integer> F) {
        
        List<Integer> adj[] = new List[A+1];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        HashMap<Integer, ArrayList<Integer>> hMap = new HashMap<Integer, ArrayList<Integer>>();
        HashSet<Integer> hSet = new HashSet<Integer>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        
        for(int i = 0 ; i < A+1 ; i++){
            adj[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0 ; i < B.size(); i++){
            int src  = B.get(i);
            int dest = C.get(i);
            adj[src].add(dest);
            adj[dest].add(src);
        }
        queue.add(1);
        int depth = 0;
        while(!queue.isEmpty()){
            ArrayList<Integer> currList = new ArrayList<Integer>();
            int currSize = queue.size();
            for(int i = 0 ; i < currSize; i++){
                int currIdx = queue.remove();
                hSet.add(currIdx);
                currList.add(D.get(currIdx -1));
                for(int nextIdx : adj[currIdx]){
                    if(!hSet.contains(nextIdx)){
                        queue.add(nextIdx);
                    }
                }
            }
            
            Collections.sort(currList);
            hMap.put(depth, currList);
            depth++;
        }
        
        for(int i = 0 ; i < E.size() ; i++){
            int level = E.get(i);
            int currVal = F.get(i);
            ans.add(find(currVal, hMap.get(level%(depth))));
        }
        
        return ans;
    }
}

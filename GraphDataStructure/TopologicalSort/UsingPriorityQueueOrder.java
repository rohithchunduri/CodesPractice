public class Solution {
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        
        List<Integer> adj[] = new List[A+1];
        int inDegree[] = new int[A+1];
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>();
        ArrayList<Integer> nodes = new ArrayList<Integer>();
        
        
        for(int i = 0 ; i < A+1; i++){
            adj[i] = new ArrayList<Integer>();
        }
        
        for(ArrayList<Integer> currList: B){
            int src  = currList.get(0);
            int dest = currList.get(1);
            inDegree[dest] += 1;
            adj[src].add(dest);
        }
        
        for(int i = 1; i <= A; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        
        while(!queue.isEmpty()){
            int currSrc = queue.remove();
            nodes.add(currSrc);
            for(int next : adj[currSrc]){
                inDegree[next] -= 1;
                if(inDegree[next] == 0){
                    queue.add(next);
                }
            }
        }
        
        if(nodes.size() == A){
            return nodes;
        }
        else{
            return new ArrayList<Integer>();
        }
    }
}

public class Solution {
    public int solve(int A, ArrayList<Integer> B, ArrayList<Integer> C) {
        
        List<Integer> adj[] = new List[A+1];
        int inDegree[] = new int[A+1];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> nodes = new HashSet<Integer>();
        
        for(int i = 0 ; i < A+1; i++){
            adj[i] = new ArrayList<Integer>();
        }
        
        for(int i = 0 ; i < B.size(); i++){
            int src  = B.get(i);
            int dest = C.get(i);
            adj[src].add(dest);
            inDegree[dest] += 1;
        }
        
        for(int i = 1; i <= A; i++){
            if(inDegree[i] == 0){
                queue.add(i);
            }
        }
        
        while(!queue.isEmpty()){
            int currSrc = queue.poll();
            nodes.add(currSrc);
            for(int next : adj[currSrc]){
                inDegree[next] -= 1;
                if(inDegree[next] == 0){
                    queue.add(next);
                }
            }
        }
        
        if(nodes.size() == A){
            return 1;
        }
        else{
            return 0;
        }
    }
}

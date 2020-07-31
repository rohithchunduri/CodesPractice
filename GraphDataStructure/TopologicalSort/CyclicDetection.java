public class Solution {
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        
        List<Integer> adj[] = new List[A+1];
        int inDegree[] = new int[A+1];
        LinkedList<Integer> queue = new LinkedList<Integer>();
        HashSet<Integer> nodes = new HashSet<Integer>();
        
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
            return 0;
        }
        else{
            return 1;
        }
    }
}

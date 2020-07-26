public class Solution {
    
    class Node{
        
        int wt;
        int src;
        int dest;
        boolean added = false;
        
        Node(int src, int dest, int wt){
            this.wt = wt;
            this.src = src;
            this.dest = dest;
        }
        
        public String toString(){
            return src + " " + dest + " " + wt + " ";
        }
        
        public int getWeight(){
            return this.wt;
        }
    }
    
    int find(int x, int parentArr[]){
        
        if(parentArr[x] != x){
            parentArr[x] = find(parentArr[x], parentArr);
        }
        
        return parentArr[x];
    }
    
    void union(int x, int y, int parentArr[], int sizeArr[]){
        int parX = find(x, parentArr);
        int parY = find(y, parentArr);
        if(parX != parY){
            int sizeX = sizeArr[parX];
            int sizeY = sizeArr[parY];
            if(sizeX < sizeY){
                sizeArr[parY] += sizeArr[parX];
                parentArr[parX] = parY;
            }
            else{
                sizeArr[parX] += sizeArr[parY];
                parentArr[parY] = parX;
            }
        }
    }
    
    public int solve(int A, ArrayList<ArrayList<Integer>> B) {
        
        int ans = 0;
        int n = B.size();
        int parentArr[] = new int[A];
        int sizeArr[]   = new int[A];
        
        Arrays.fill(sizeArr, 1);
        
        for(int i = 0 ; i < A; i++){
            parentArr[i] = i;
        }
        
        ArrayList<Node> arrNodes = new ArrayList<Node>();
        for(int i = 0 ; i < n ; i++){
            ArrayList<Integer> currList = B.get(i);
            int src  = currList.get(0);
            int dest = currList.get(1);
            int wt   = currList.get(2);
            arrNodes.add(new Node(src, dest, wt));
        }
        
        Collections.sort(arrNodes, Comparator.comparing(Node::getWeight));
        
        for(Node currNode : arrNodes){
            
            int currSrc = currNode.src  - 1;
            int destSrc = currNode.dest - 1;
            
            int srcPar = find(currSrc, parentArr);
            int destPar = find(destSrc, parentArr);
                
            if(srcPar != destPar){
                ans += currNode.wt;
                union(srcPar, destPar, parentArr, sizeArr);
            }
        }
        
        return ans;
    }
}

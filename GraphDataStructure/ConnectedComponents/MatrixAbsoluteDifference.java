/*Given a matrix C of integers, of dimension A x B.

For any given K, you are not allowed to travel between cells that have an absolute difference greater than K.

Return the minimum value of K such that it is possible to travel between any pair of cells in the grid through a path of adjacent cells.

NOTE:

Adjacent cells are those cells that share a side with the current cell.
*/

public class Solution {
    
    class Node{
        
        int idx;
        int src;
        int dest;
        int wt;
        boolean added = false;
        
        Node(int idx, int src, int dest, int wt){
            this.idx = idx;
            this.src = src;
            this.dest = dest;
            this.wt  = wt;
        }
        
        public String toString(){
            return idx + " " + src + " " + dest + " " + wt + " ";
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
    
    public ArrayList<Integer> solve(int A, ArrayList<ArrayList<Integer>> B) {
        
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int n = B.size();
        int parentArr[] = new int[A];
        int sizeArr[]   = new int[A];
        int ansArr[]    = new int[n];
        
        Arrays.fill(sizeArr, 1);
        Arrays.fill(ansArr, 0);
        
        
        for(int i = 0 ; i < A; i++){
            parentArr[i] = i;
        }
        
        ArrayList<Node> arrNodes = new ArrayList<Node>();
        for(int i = 0 ; i < n ; i++){
            ArrayList<Integer> currList = B.get(i);
            int src  = currList.get(0);
            int dest = currList.get(1);
            int wt   = currList.get(2);
            arrNodes.add(new Node(i, src, dest, wt));
        }
        
        Collections.sort(arrNodes, Comparator.comparing(Node::getWeight));
        TreeMap<Integer, ArrayList<Node>> mapNodes = new TreeMap<Integer, ArrayList<Node>>();
        
        for(Node currNode : arrNodes){
            int currWeight = currNode.getWeight();
            if(mapNodes.containsKey(currWeight)){
                mapNodes.get(currWeight).add(currNode);
            }
            else{
                ArrayList<Node> currList = new ArrayList<Node>();
                currList.add(currNode);
                mapNodes.put(currWeight, currList);
            }
        }
        
        //System.out.print(mapNodes + " ");
        
        for(Map.Entry<Integer, ArrayList<Node>> entry: mapNodes.entrySet()){
            
            ArrayList<Node> currNodes = entry.getValue();
            
            for(Node currNode : currNodes){
                
                int currSrc = currNode.src  - 1;
                int destSrc = currNode.dest - 1;
            
                int srcPar = find(currSrc, parentArr);
                int destPar = find(destSrc, parentArr);
                
                if(srcPar != destPar){
                    ansArr[currNode.idx] = 1;
                }
            }
            
            for(Node currNode : currNodes){
                
                int currSrc = currNode.src  - 1;
                int destSrc = currNode.dest - 1;
            
                int srcPar = find(currSrc, parentArr);
                int destPar = find(destSrc, parentArr);
                
                if(srcPar != destPar){
                   union(srcPar, destPar, parentArr, sizeArr);
                }
            }
        }

        for(int i : ansArr){
            System.out.print(i + " ");
        }
        
        return ans;
    }
}



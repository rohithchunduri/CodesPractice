/*Given a matrix C of integers, of dimension A x B.

For any given K, you are not allowed to travel between cells that have an absolute difference greater than K.

Return the minimum value of K such that it is possible to travel between any pair of cells in the grid through a path of adjacent cells.

NOTE:

Adjacent cells are those cells that share a side with the current cell.
*/

public class Solution {
    
    int rows[] = new int[]{-1, 0, 0 , 1};
    int cols[] = new int[]{0, -1, 1, 0};
    
    class Node{
        int src;
        int dest;
        int wt;
        
        Node(int src, int dest, int wt){
            this.src = src;
            this.dest = dest;
            this.wt  = wt;
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
    
    
    public int solve(int A, int B, ArrayList<ArrayList<Integer>> C) {
        
        ArrayList<Node> arrNodes = new ArrayList<Node>();
        
        int parentArr[] = new int[A*B];
        int sizeArr[]   = new int[A*B];
        HashSet<Integer> allNodes = new HashSet<Integer>();
        
        Arrays.fill(sizeArr, 1);
        
        for(int i = 0 ; i < A*B; i++){
            parentArr[i] = i;
        }
        
        for(int i = 0 ; i < A; i++){
            for(int j = 0 ; j < B; j++){
                
                for(int k = 0 ; k < 4 ; k++){
                    
                    int nextRow = i + rows[k];
                    int nextCol = j + cols[k];
                    
                    if((nextRow >= 0 && nextRow < A) && (nextCol >=0 && nextCol < B)){
                        
                        int src  =  i*B + j;
                        int dest =  nextRow*B + nextCol;
                        
                        int currWt = Math.abs(C.get(i).get(j) - C.get(nextRow).get(nextCol));
                        Node currNode = new Node(src, dest, currWt);
                        arrNodes.add(currNode);
                    }
                }
            }
        }
        
        int ans = 0;
        Collections.sort(arrNodes, Comparator.comparing(Node::getWeight));
        
        for(Node currNode : arrNodes){
            
            int currSrc = currNode.src;
            int destSrc = currNode.dest;
            
            int srcPar = find(currSrc, parentArr);
            int destPar = find(destSrc, parentArr);
            
            if(srcPar != destPar){
                union(srcPar, destPar, parentArr, sizeArr);
                allNodes.add(currSrc);
                allNodes.add(destSrc);
                
                if(allNodes.size() == A*B){
                   int srcPar1 = find(currSrc, parentArr);
                   int srcPar2 = find(destSrc, parentArr);
                   if((sizeArr[srcPar1] == A*B) || (sizeArr[srcPar2] == A*B)){
                       ans = currNode.wt; 
                       break;
                   }
                }
            }
        }
        
        return ans;
    }
}


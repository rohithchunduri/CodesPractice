/**
 Step 1: Calculate the DFS of the grid. To know which cell is connected to another , set the cell value to              the root of the cell when DFS is computed. 
          
 Step 2: When calculating the dfs of the grid, return the island size of the connected graph.
 
 Step 3: Now again traverse through the whole grid and where the cell value = 0 check if we are able to                combine the cells from four directions. 
*/


class Solution {
    
    int newGrid[][];
    int row[] = new int[] { -1, 0, 1, 0};
    int col[] = new int[] { 0, 1, 0, -1};
    int n;
    int m;
    
    int dfs(int i, int j, int root){
        int islands = 0;
        for(int k = 0; k < 4 ; k++){
            int newRow = row[k] + i;
            int newCol = col[k] + j;
            if(newRow >= 0 && newRow < n && newCol >=0 && newCol < m){
                if(newGrid[newRow][newCol] == 1){
                    newGrid[newRow][newCol] = root;
                    islands += dfs(newRow, newCol, root);
                }
            }
        }
        return islands+1;
    }
    
    public int largestIsland(int[][] grid) {
        
        n = grid.length;
        m = grid[0].length;
        
        newGrid = new int[n][m];
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        int maxValue = 0;
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                newGrid[i][j] = grid[i][j];
            }
        }
        
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(newGrid[i][j] != 1){
                    continue;
                }
                int currRoot = m*i + j + 2;
                newGrid[i][j] = currRoot;
                int islandSize = dfs(i, j, currRoot);
                map.put(currRoot, islandSize);
                maxValue = Math.max(maxValue, islandSize);
            }
        }
                
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < m ; j++){
                if(grid[i][j] == 0){
                    HashSet<Integer> hSet = new HashSet<Integer>();
                    for(int k = 0 ; k < 4 ; k++){
                        int newRow = i + row[k];
                        int newCol = j + col[k];
                        if(newRow >= 0 && newRow < n && newCol >= 0 && newCol < m){
                            int val = newGrid[newRow][newCol];
                            hSet.add(val);
                        }
                    }
                    int currVal = 1;
                    Iterator<Integer> it = hSet.iterator();
                    while(it.hasNext()){
                        int currKey = it.next();
                        currVal += map.getOrDefault(currKey, 0);
                    }
                    maxValue = Math.max(maxValue, currVal);
                }
            }
        }
        
        return maxValue;
    }
}

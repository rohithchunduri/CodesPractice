//Leetcode question https://leetcode.com/problems/count-servers-that-communicate/

class Solution {
    int visited[][];
    public int countServers(int[][] grid) {
        
        int rows = grid.length;
        int columns = grid[0].length;
        
        visited = new int[rows][columns];
        int count = 0;
        
        for (int i = 0 ; i < rows; i++) {
            for (int j = 0 ; j < columns; j++) {
                if (visited[i][j] == 1)
                    continue;
                else {
                    visited[i][j] = 1;
                    if (grid[i][j] == 0) 
                        continue;
                    else {
                        int curr = dfs(i, j, grid);
                        if (curr != 1)
                            count += curr;
                    }
                }
            }
        }
        return count;
    }
    
    int dfs(int i, int j, int[][] grid) {
        
        int count = 1;
        int rows = grid.length;
        int columns = grid[0].length;
        
        for (int k = 0 ; k < rows; k++) {
            if (visited[k][j] == 1)
                continue;
            else {
                visited[k][j] = 1;
                if (grid[k][j] == 0)
                    continue;
                else {
                    count += dfs(k, j, grid);
                }
            }
        }
        
        for (int k = 0; k < columns; k++) {
            if (visited[i][k] == 1)
                continue;
            else {
                visited[i][k] = 1;
                if (grid[i][k] == 0)
                    continue;
                else {
                    count += dfs(i, k, grid);
                }
            }
        }
        return count;
    }
}

//https://leetcode.com/problems/satisfiability-of-equality-equations/

class Solution {
    int[] parent = new int[26];
    int[] rank = new int[26];
    
    ArrayList<Integer> same = new ArrayList<Integer>();
    ArrayList<Integer> diff = new ArrayList<Integer>();
    
    int find(int u) {
        if (parent[u] == u)
            return u;
        return parent[u] = find(parent[u]);
    }
    
    void union(int u, int v) {
        u = find(u);
        v = find(v);
        
        if (u != v) {
            if (rank[u] < rank[v]) {
                int temp = v;
                v = u;
                u = temp;
            }
            parent[v] = u;
            
            if (rank[u] == rank[v])
                rank[u]++;
        }
    }
    
    public boolean equationsPossible(String[] equations) {
        
        for (int i = 0 ; i < 26; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
        
        for (int i = 0 ; i < equations.length; i++) {
            String equation = equations[i];
            if (equation.charAt(1) == equation.charAt(2))
                same.add(i);
            else
                diff.add(i);
        }
        
        for (int i : same) {
            String equation = equations[i];
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            union(a - 'a', b - 'a');
        }
        
        for (int i : diff) {
            String equation = equations[i];
            char a = equation.charAt(0);
            char b = equation.charAt(3);
            
            if (find(a - 'a') == find(b - 'a'))
                return false;
        }
        
        return true;
    }
}

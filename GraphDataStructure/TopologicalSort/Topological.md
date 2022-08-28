Topological Sort via DFS,
  1. Build the adjancey list.
  2. iterate through all the nodes.
  3. If the node is not visited, then do a dfs, keep a stack
  4. dfs approach
     public static void utilityFunction(Graph g, int v, boolean visited[], Stack < Integer > myStack) {
  // Mark the current node v as visited. 
  visited[v] = true;
  // traverse all the vertices adjacent to this 
  // vertex and do a recursive call to the utility function for these
  Iterator < Integer > i = g.getAdj()[v].iterator();
  Integer temp;
  while (i.hasNext()) {
   
   temp = i.next();
   if (!visited[temp])
    utilityFunction(g, temp, visited, myStack);
  }
  // Push current vertex in to the stack 
  myStack.push(v);
 }
      

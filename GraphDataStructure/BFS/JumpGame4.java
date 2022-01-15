//https://leetcode.com/problems/jump-game-iv/

class Solution {
    public int minJumps(int[] arr) {
        int sizeN = arr.length;
        int[] visited = new int[sizeN];
        int currDepth = 0;
        LinkedList<Integer> queue = new LinkedList<Integer>();
        HashMap<Integer, ArrayList<Integer>> hashMap = new HashMap<Integer, ArrayList<Integer>>();
        queue.add(0);
        boolean found = false;
        visited[0] = 1;
        //Generating hashMap 
        for (int i = 0 ; i < sizeN ; i++){
            int currVal = arr[i];
            if (hashMap.containsKey(currVal)) {
                ArrayList<Integer> currList = hashMap.get(currVal);
                currList.add(i);
            }
            else {
                ArrayList<Integer> currList = new ArrayList<Integer>();
                currList.add(i);
                hashMap.put(currVal, currList);
            }
        }
        
        //bfs to find the minimum steps required
        while(!queue.isEmpty()){
            if (found){
                break;
            }
            int currQueueSize = queue.size();
            for (int i = 0 ; i < currQueueSize; i++) {
                int currIdx = queue.remove();
                if (currIdx == sizeN - 1){
                    found = true;
                    break;
                }
                
                if (currIdx + 1 < sizeN) {
                    if (visited[currIdx + 1] == 0) {
                        visited[currIdx + 1] = 1;
                        queue.add(currIdx + 1);
                    }
                }
                
                if (currIdx - 1 > -1) {
                    if (visited[currIdx - 1] == 0){
                        visited[currIdx - 1] = 1;
                        queue.add(currIdx - 1);
                    }
                }
                
                int currVal = arr[currIdx];
                if (!hashMap.containsKey(currVal))
                    continue;
                for (int currNode : hashMap.get(currVal)) {
                    if (visited[currNode]  == 0){
                        visited[currNode] = 1;
                        queue.add(currNode);
                    }
                }
                //remove visited entries ?
                hashMap.remove(currVal);
                
            }
            currDepth += 1;
        }
        return currDepth -1 ;
    }
}

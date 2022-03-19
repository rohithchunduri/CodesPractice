class FreqStack {
    int maxCounter = 0;
    int currIdx = 0;
    HashMap<Integer, Integer> valueCounter = new HashMap<Integer, Integer>();
    HashMap<Integer, PriorityQueue<PQData>> counterQueue = new HashMap<Integer, PriorityQueue<PQData>>();;
    
    public FreqStack() {
        maxCounter = 0;
        currIdx = 0;
    }
    
    public void push(int val) {
        ++currIdx;
        int currCount = valueCounter.getOrDefault(val, 0) + 1;
        maxCounter = Math.max(maxCounter, currCount);
        valueCounter.put(val, currCount);
        if (counterQueue.containsKey(currCount)) {
            PriorityQueue currCounterQueue = counterQueue.get(currCount);
            currCounterQueue.add(new PQData(currIdx, val));
        }
        else{
            PriorityQueue currCounterQueue = new PriorityQueue<PQData>(Comparator.comparingInt(PQData::getPosition).reversed());
            currCounterQueue.add(new PQData(currIdx, val));
            counterQueue.put(currCount, currCounterQueue);
        }
    }
    
    public int pop() {
        PriorityQueue currCounterQueue = counterQueue.get(maxCounter);
        PQData currData = (PQData)currCounterQueue.remove();
        if (currCounterQueue.isEmpty()){
            counterQueue.remove(maxCounter);
            maxCounter = maxCounter - 1;
        }
        int currCount = valueCounter.get(currData.value);
        valueCounter.put(currData.value, currCount - 1);
        return currData.value;
    }
    
    class PQData {
        int position;
        int value;
        
        PQData(int position, int value){
            this.position = position;
            this.value = value;
        }
        
        int getPosition(){
            return this.position;
        }
    }
}

/**
 * Your FreqStack object will be instantiated and called as such:
 * FreqStack obj = new FreqStack();
 * obj.push(val);
 * int param_2 = obj.pop();
 */

/*
In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring", and use the dial to spell a specific keyword in order to open the door.

Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.

Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction and then by pressing the center button.

At the stage of rotating the ring to spell the key character key[i]:

You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all the spelling.
*/

class Solution {
    
    HashMap<Character, ArrayList<Integer>> hMap = new HashMap<Character, ArrayList<Integer>>();
    int n;
    int m;
    String value;
    HashMap<String, Integer> hValue = new HashMap<String, Integer>();
    
    int recurse(int i, int currPos){
        
        if( i == m){
            return 0;
        }
        
        String currKey = i+"_"+currPos;
        
        if(hValue.containsKey(currKey)){
            return hValue.get(currKey);
        }
        
        ArrayList<Integer> currList = hMap.get(value.charAt(i));
        
        int mini = Integer.MAX_VALUE;
        for(int newPos : currList){
            int currDist = 0;
            if(newPos >= currPos){
                currDist = newPos - currPos;
                currDist = Math.min(currDist , n - currDist);
            }
            else{
                currDist = currPos - newPos;
                currDist = Math.min(currDist, n - currDist);
            }
            mini = Math.min(mini, recurse(i+1, newPos) + currDist);
        }
        
        hValue.put(currKey, mini);
        return mini;
    }
    
    public int findRotateSteps(String ring, String key) {
        
        n = ring.length();
        m = key.length();
        value = key;
        
        for(int i = 0 ; i < n ; i++){
            char currCh = ring.charAt(i);
            if(hMap.containsKey(currCh)){
                hMap.get(currCh).add(i);
            }
            else{
                ArrayList<Integer> arrList = new ArrayList();
                arrList.add(i);
                hMap.put(currCh, arrList);
            }
        }
        
        return recurse(0, 0) + m;
    }
}

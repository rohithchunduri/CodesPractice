class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) 
            return 0;

        int maxVal = nums[0];
        int minVal = nums[0];
        for (int i = 0 ; i < nums.length; i++) {
            maxVal = Math.max(nums[i], maxVal);
            minVal = Math.min(nums[i], minVal);
        }
        if (maxVal == minVal)
            return 0;
        
        int gap = Math.max(((int)Math.floor((maxVal-minVal)/(nums.length - 1))), 1);
        if (gap == 0)
            return 0;
        
        int buckets = (int)Math.ceil((maxVal - minVal)/(gap));
        int bucketsArr[] = new int[(buckets + 1)*2];
        Arrays.fill(bucketsArr, Integer.MIN_VALUE);

        for (int i = 0; i < nums.length; i++) {
            int bucketIdx = (nums[i] - minVal)/gap;

            int minBucketVal = bucketsArr[bucketIdx*2];
            int maxBucketVal = bucketsArr[bucketIdx*2 + 1];

            if (minBucketVal == Integer.MIN_VALUE)
                bucketsArr[bucketIdx*2] = nums[i];
            
            if (maxBucketVal == Integer.MIN_VALUE)
                bucketsArr[bucketIdx*2 + 1] = nums[i];
            
            bucketsArr[bucketIdx*2] = Math.min(nums[i], bucketsArr[bucketIdx*2]);
            bucketsArr[bucketIdx*2 + 1] = Math.max(nums[i],  bucketsArr[bucketIdx*2 + 1]);
        }
        int maxGap = Integer.MIN_VALUE;
        int prevVal = bucketsArr[0];
        for (int i = 1; i < bucketsArr.length; i++) {
            if (prevVal != Integer.MIN_VALUE && bucketsArr[i] != Integer.MIN_VALUE) {
                maxGap = Math.max(bucketsArr[i] - prevVal, maxGap);
            }
            if (bucketsArr[i] != Integer.MIN_VALUE) {
                prevVal = bucketsArr[i];
            }
        }

        return maxGap;
    }
}

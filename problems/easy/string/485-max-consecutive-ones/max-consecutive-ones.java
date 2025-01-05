class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int maxCount = 0, right = 0, count = 0;

        while (right < nums.length) {
            if (nums[right] == 1) {
                count++;
            } else {
                maxCount = Math.max(maxCount, count);
                count = 0;
            } 
            right++;
        }
        return Math.max(maxCount, count);
    }
}
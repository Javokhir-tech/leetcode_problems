class Solution {
    public int maxSubarraySumCircular(int[] nums) {
        int globMax = nums[0], globMin = nums[0];
        int curMax = 0, curMin = 0, total = 0;
        for (int n : nums) {
            curMax = Math.max(n, curMax + n);
            curMin = Math.min(n, curMin + n);
            total += n;
            globMax = Math.max(globMax, curMax);
            globMin = Math.min(globMin, curMin);
        }
        if (globMax > 0)
            return Math.max(globMax, total - globMin);
        return globMax;
    }
}
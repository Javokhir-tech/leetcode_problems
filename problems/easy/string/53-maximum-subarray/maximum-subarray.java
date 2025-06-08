class Solution {
    public int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int curSum = 0;
        for (int n : nums) {
            curSum = Math.max(curSum + n, n);
            maxSum = Math.max(maxSum, curSum);
        }
        return maxSum;
    }
}
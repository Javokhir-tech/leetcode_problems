class Solution {
    public int subarraySum(int[] nums, int k) {
        // return number of sub arrays of nums whose sum == k
        // nums      [1,1,1]
        // prefixSum [1,2,3]
        
        // sum of sub arr prefix[right] - prefix[left - 1]
        
        // for i in range(0, nums.length)
            // sum of sub arr
            // if sum == k
                // count++
        int sum = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            sum = nums[i];
            if (sum == k) {
                count++;
            }
            for (int j = i + 1; j < nums.length; j++) {
                // calc sum from i -> j
                sum += nums[j];
                if (sum == k) {
                    count++;
                }
            }
        }
        return count;
    }
}

// [3 -2 4 1 8 -7 -4] k = 1
// count 3

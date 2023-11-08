package leetcodes.two_sum;

/*
* Given an array of integers nums and an integer target,
* return indices of the two numbers such that they add up to target.
* */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) { // iterate over nums
            int selected = nums[i]; // select one val from outer loop
            for (int k = i + 1; k < nums.length; k++) { // iterate over rest compare sum with target
                if (selected + nums[k] == target) { // if true set values to new array then break the loop
                    result[0] = i;
                    result[1] = k;
                    break;
                }
            }
        }
        return result;
    }
}

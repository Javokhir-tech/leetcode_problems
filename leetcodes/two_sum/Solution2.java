package leetcodes.two_sum;


import java.util.HashMap;

/*
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 *
 * Optimized version using hashmap
 * */
class Solution2 {
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        var map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {    // check if key exists
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                break;
            }
            map.put(nums[i], i);    // add index as a value, key as elem of param arr
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,4};
        var answer = twoSum(nums, 6);
        System.out.println(answer[0] + " " + answer[1]);
    }
}
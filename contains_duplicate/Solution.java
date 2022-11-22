package contains_duplicate;

import java.util.HashMap;

/*
 * Given an array of integers nums and an integer target,
 * return true if there's a repeated num in arr
 * return false if all elements are distinct
 *
 * */
public class Solution {
    public static boolean containsDuplicate(int[] nums) {
        var map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]))
                return true;
            map.put(nums[i], i);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        System.out.println(containsDuplicate(nums));
    }
}

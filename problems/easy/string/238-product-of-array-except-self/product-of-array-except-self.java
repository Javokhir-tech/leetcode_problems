class Solution {
    public int[] productExceptSelf(int[] nums) {
        // calculate prefix and suffix arrays then multiply both
        int[] answer = new int[nums.length];
        
        answer[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = nums[i - 1] * answer[i - 1];
        }
        int right = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] = answer[i] * right;
            right *= nums[i];
        }
        return answer;
        // time: o(n)
        // space: o(1);
    }
/** 
    public int[] productExceptSelf(int[] nums) {
        // calculate prefix and suffix arrays then multiply both
        int[] answer = new int[nums.length];
        int[] prefix = new int[nums.length];
        int[] suffix = new int[nums.length];
        //    1,  2,  3,  4
        
        //pre 1,  1,  2,  6
        //suf 24, 12, 4,  1
        //ans 24, 12, 8,  6
        prefix[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] * nums[i - 1];
        }
        suffix[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < nums.length; i++) {
            answer[i] = prefix[i] * suffix[i];
        }
        return answer;
        // time: o(n)
        // space: o(n);
    }
*/
}
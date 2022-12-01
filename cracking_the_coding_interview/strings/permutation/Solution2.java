package cracking_the_coding_interview.strings.permutation;

import java.util.Arrays;

/*
* the same problem but with optimized solution in terms of runtime
* time O(n)
* space O(n)
* */
public class Solution2 {
    private static boolean isPermutation(String a, String b) {
        if (a.length() != b.length()) return false;
        var arrA = sortArrays(a);
        var arrB = sortArrays(b);
        return Arrays.equals(arrA, arrB);
    }
    private static int[] sortArrays(String str) {
        var arr = new int[26];
        for (char c : str.toCharArray()) {
            arr[c - 'a']++;
        }
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(isPermutation("kabkacs", "kkcbsaa"));
    }
}

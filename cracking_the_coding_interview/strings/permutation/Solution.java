package cracking_the_coding_interview.strings.permutation;

import java.util.Arrays;

/*
* check if 2 given strs are permutation to other
* time O(nlogn)
* space O(1)
* */
public class Solution {

    private static boolean isPermutation(String a, String b) {
        if (a.length() != b.length()) return false;
        var arrA = sortArrays(a);
        var arrB = sortArrays(b);
        return Arrays.equals(arrA, arrB);
    }

    private static char[] sortArrays(String str) {
        var arr = str.toCharArray();
        Arrays.sort(arr);
        return arr;
    }

    public static void main(String[] args) {
        System.out.println(isPermutation("abac", "cbaa"));
    }
}

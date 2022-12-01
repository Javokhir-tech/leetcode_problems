package cracking_the_coding_interview.strings.palindrom_perm;

import java.util.HashMap;

/*
* check if permutation is palindrome (backward = forward)
* time O(n)
* space O(n)
* */
public class Solution {

    private static boolean isPermutation(String str) {
        var map = new HashMap<Character, Integer>(); // O(n)
        var arr = str.strip().split(" ");
        var result = String.join("", arr);

        for (char c : result.toCharArray())
            map.put(c, map.get(c) == null ? 1 : map.get(c) + 1);

        var res = 0;
        for (var entry : map.entrySet())
            res += (entry.getValue() % 2);

        return res == 0 || res == 1;
    }

    public static void main(String[] args) {
        System.out.println(isPermutation("qakbcblqzllazlm sskc"));
    }
}

package leetcodes.valid_anagram;

import java.util.HashMap;
import java.util.Map;

/*
* Given two strings s and t, return true if t is an anagram of s, and false otherwise.
* */
class Solution {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        var map = new HashMap<Character, Integer>();
        var map2 = new HashMap<Character, Integer>();
        return countLetters(s, map).equals(countLetters(t, map2));
    }

    private static Map<Character, Integer> countLetters(String s, Map<Character, Integer> map) {
        for (int i = 0; i < s.length(); i++) {
            map.put(s.charAt(i), map.get(s.charAt(i)) == null ? 1 : map.get(s.charAt(i)) + 1); // count by letter in string
        }
        return map;
    }

    public static void main(String[] args) {
        System.out.println(isAnagram("anagram", "nagaram"));
    }
}

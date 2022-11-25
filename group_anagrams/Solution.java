package group_anagrams;

import java.util.*;


/*
* Given an array of strings strs, group the anagrams together. You can return the answer in any order.
* Brute force solution, won't work when lots of elems in array
* */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        var globalList = new ArrayList<List<String>>();
        for (int i = 0; i < strs.length; i++) {
            // check if word not exist in sub list
             if (!existInList(strs[i], globalList)) {
                 var selected = strs[i];
                 var selectedList = new ArrayList<String>();
                 selectedList.add(selected);
                 for (int j = i + 1; j < strs.length; j++) {
                     // check if anagram selected strs[j]
                     if (isAnagram(selected, strs[j]))
                         selectedList.add(strs[j]);
                 }
                 globalList.add(selectedList);
             }
        }
        return globalList;
    }
    private boolean existInList(String selected, List<List<String>> globalList) {
        if (globalList.isEmpty())
            return false;
        for (List<String> strings : globalList) {
            if (strings.contains(selected))
                return true;
        }
        return false;
    }

    private boolean isAnagram(String s, String t) {
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
        var strs = new String[]{};
        var sol = new Solution();
        var result = sol.groupAnagrams(strs);
        result.forEach(System.out::println);
    }
}
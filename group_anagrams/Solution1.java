package group_anagrams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
* The same problem, but with optimized solution
* */
public class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        var globalList = new ArrayList<List<String>>();
        var map = new HashMap<String, List<String>>();
        for (String str : strs) { // O(s)
            var key = getSignature(str); // O(n) // optimized
            map.computeIfAbsent(key, k -> new ArrayList<>()); // 2nd param mapping function is called if key is not present
            map.get(key).add(str);
        }
        for (var entry: map.entrySet()) { // O(m)
            globalList.add(entry.getValue());
        }
        return globalList;
    }

    private String getSignature(String str) {
        // for each of 26 chars, use count of each char in each word as tuple for key in dict, value is the list of anagrams;
        char[] arr = new char[26];
        for (char c : str.toCharArray())
            arr[c - 'a']++;

        return new String(arr);
    }

    public static void main(String[] args) { // O(ab) < O(ablogb)
        var strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        var sol = new Solution1();
        var result = sol.groupAnagrams(strs); // runtime 13ms beats 75.96% memory 54.8Mb beats 80.52%
        result.forEach(System.out::println);
    }
}

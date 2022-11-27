package group_anagrams;

import java.util.ArrayList;
import java.util.Arrays;
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
            if (map.containsKey(key)) {  // check if sorted key is present, if so add item to map's value
                map.get(key).add(str);
            } else {
                var list = new ArrayList<String>();
                list.add(str);
                map.put(key, list);
            }
        }
        for (var entry: map.entrySet()) { // O(m)
            globalList.add(entry.getValue());
        }
        return globalList;
    }

    private String sortedString(String selected) {  // use sorted string as a key, performance bottleneck is here takes O(nlogn)
        char[] arrSelected = selected.toCharArray();
        Arrays.sort(arrSelected);
        return new String(arrSelected);
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
        System.out.println('c' + 'a');
        result.forEach(System.out::println);
    }
}

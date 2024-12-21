class Solution {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] arr = new int[26];
        for (char letter : s.toCharArray()) {
            arr[letter - 'a']++;
        }
        for (char letter : t.toCharArray()) {
            int occurence = arr[letter - 'a'];
            occurence--;
            if (occurence < 0) {
                return false;
            }
            arr[letter - 'a'] = occurence;
        }
        return true;
    }
}